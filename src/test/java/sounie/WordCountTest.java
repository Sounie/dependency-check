package sounie;

import com.google.common.collect.Sets;
import org.junit.Test;

import static org.junit.Assert.*;

public class WordCountTest {
    @Test
    public void canWeExcludeJerseyServer() {
        PathChecker checker = new PathChecker("sounie.hadoop",
                Sets.newHashSet("com.sun.jersey.server",
                        "com.sun.jersey.spi"));

        assertFalse(checker.checkDependencyPath());
    }

    @Test
    public void canWeExcludeJerseyClient() {
        PathChecker checker = new PathChecker("sounie.hadoop",
                Sets.newHashSet("com.sun.jersey.client", "com.sun.jersey.api.client"));

        assertFalse(checker.checkDependencyPath());
    }
}