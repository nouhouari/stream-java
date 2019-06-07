package io.getstream.cloud;

import io.getstream.client.Client;
import io.getstream.core.http.Token;
import io.getstream.core.models.Data;
import io.getstream.core.models.ProfileData;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;

public class CloudUserTest {
    private static final String apiKey = "gp6e8sxxzud6";
    private static final String secret = "7j7exnksc4nxy399fdxvjqyqsqdahax3nfgtp27pumpc7sfm9um688pzpxjpjbf2";
    private static final String userID = "db07b4a3-8f48-41f7-950c-b228364496e2";
    private static final Token token = buildToken();

    private static Token buildToken() {
        try {
            return Client.builder(apiKey, secret).build().frontendToken(userID);
        } catch (MalformedURLException e) {
            return null;
        }
    }

    @Before
    public void setup() throws Exception {
        Client.builder(apiKey, secret)
                .build()
                .user(userID)
                .getOrCreate()
                .join();
    }

    @Test
    public void get() throws Exception {
        CloudClient client = CloudClient.builder(apiKey, token, userID).build();

        CloudUser user = client.user(userID);
        Data result = user.get().join();
    }

    @Test
    public void delete() throws Exception {
        CloudClient client = CloudClient.builder(apiKey, token, userID).build();

        CloudUser user = client.user(userID);
        user.delete().join();
    }

    @Test
    public void getOrCreate() throws Exception {
        CloudClient client = CloudClient.builder(apiKey, token, userID).build();

        CloudUser user = client.user(userID);
        user.delete().join();
        Data result = user.getOrCreate().join();
    }

    @Test
    public void create() throws Exception {
        CloudClient client = CloudClient.builder(apiKey, token, userID).build();

        CloudUser user = client.user(userID);
        user.delete().join();
        Data result = user.create().join();
    }

    @Test
    public void update() throws Exception {
        CloudClient client = CloudClient.builder(apiKey, token, userID).build();

        CloudUser user = client.user(userID);
        Data result = user.update(new Data()
                .set("key", "value")
                .set("null", null)).join();
    }

    @Test
    public void profile() throws Exception {
        CloudClient client = CloudClient.builder(apiKey, token, userID).build();

        CloudUser user = client.user(userID);
        ProfileData result = user.profile().join();
    }
}