package sounie.level2;

import com.sun.jersey.api.client.Client;

public class Level2WithJerseyClient {

    private final Client client;

    public Level2WithJerseyClient() {
        client = new Client();
    }

    public Client getClient() {
        return client;
    }
}
