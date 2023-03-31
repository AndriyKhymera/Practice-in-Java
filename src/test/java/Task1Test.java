import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task1Test {

    @Test
    public void testIsCurrentVersionHigherOrEqual_withNull() {
        assertFalse(Task1.isCurrentVersionHigherOrEqual(null, null));
        assertFalse(Task1.isCurrentVersionHigherOrEqual("", null));
        assertFalse(Task1.isCurrentVersionHigherOrEqual(null, ""));
    }

    @Test
    public void testIsCurrentVersionHigherOrEqual_withWrongFormat() {
        assertFalse(Task1.isCurrentVersionHigherOrEqual("1.0.0", "1.0"));
        assertFalse(Task1.isCurrentVersionHigherOrEqual("1.0", "1.0.0"));
        assertFalse(Task1.isCurrentVersionHigherOrEqual("0", "1.0.0"));
    }

    @Test
    public void testIsCurrentVersionHigherOrEqual_withEqualVersions() {
        assertTrue(Task1.isCurrentVersionHigherOrEqual("1.0.0", "1.0.0"));
        assertTrue(Task1.isCurrentVersionHigherOrEqual("1.1.0", "1.1.0"));
        assertTrue(Task1.isCurrentVersionHigherOrEqual("1.1.1", "1.1.1"));
        assertTrue(Task1.isCurrentVersionHigherOrEqual("0.0.1", "0.0.1"));
    }

    @Test
    public void testIsCurrentVersionHigherOrEqual_withHigherVersions() {
        assertTrue(Task1.isCurrentVersionHigherOrEqual("0.0.0", "1.0.0"));
        assertTrue(Task1.isCurrentVersionHigherOrEqual("1.1.0", "1.2.0"));
        assertTrue(Task1.isCurrentVersionHigherOrEqual("0.0.0", "0.0.1"));
    }

    @Test
    public void testIsCurrentVersionHigherOrEqual_withLowerVersions() {
        assertFalse(Task1.isCurrentVersionHigherOrEqual("1.0.0", "0.1.1"));
        assertFalse(Task1.isCurrentVersionHigherOrEqual("0.1.0", "0.0.9"));
        assertFalse(Task1.isCurrentVersionHigherOrEqual("0.0.9", "0.0.8"));
    }

}
