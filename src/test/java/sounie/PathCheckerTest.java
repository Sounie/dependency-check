package sounie;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PathCheckerTest {
    @Test
    public void findsPathWhenExists() {
        PathChecker checker = new PathChecker("sounie.level2", Collections.singleton("com.sun.jersey.api.client"));

        assertTrue(checker.checkDependencyPath());
    }

    @Test
    public void doesNotFindPathWhenNoneExists() {
        PathChecker checker = new PathChecker("sounie.level1", Collections.singleton("com.sun.jersey.api.client"));

        assertFalse(checker.checkDependencyPath());
    }
}
