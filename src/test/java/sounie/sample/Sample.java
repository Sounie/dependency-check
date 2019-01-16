package sounie.sample;

import sounie.level1.Level1;
import sounie.level1.Level1To2;
import sounie.level2.Level2WithJerseyClient;

public class Sample {
    /**
     * Depend on something that depends on something else, but without exercising any code that involves the
     * second level dependency.
     */
    public static void main(String[] args) {
        Level1 level1 = new Level1();

        Level1To2 level1To2WithoutJersey = new Level1To2();
        Level2WithJerseyClient bringingInJersey = new Level2WithJerseyClient();
    }
}
