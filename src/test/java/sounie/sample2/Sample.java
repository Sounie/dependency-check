package sounie.sample2;

import sounie.level2.Level2WithJerseyClient;

public class Sample {
    /**
     * Depend on something that depends on something else, but without exercising any code that involves the
     * second level dependency.
     */
    public static void main(String[] args) {
        Level2WithJerseyClient bringingInJersey = new Level2WithJerseyClient();
    }
}
