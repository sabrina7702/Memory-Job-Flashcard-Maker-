package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestLevels {
    private Levels level;

    @BeforeEach
    void runBefore() {
        level = new Levels();
    }
    
    @Test
    void testConstructor() {
        assertEquals(1, level.getLevel());
    }

    @Test
    void testIncreaseLevel() {
        level.increaseLevel();
        assertEquals(2, level.getLevel());
        level.increaseLevel();
        assertEquals(3, level.getLevel());
    }

    @Test
    void testCanUnlockPerk() {
        assertEquals(1, level.getLevel());
        assertFalse(level.canUnlockPerk());
        level.increaseLevel();
        assertEquals(2, level.getLevel());
        assertFalse(level.canUnlockPerk());
        level.increaseLevel();
        assertEquals(3, level.getLevel());
        assertTrue(level.canUnlockPerk());
        level.increaseLevel();
        level.increaseLevel();
        assertEquals(5, level.getLevel());
        assertTrue(level.canUnlockPerk());
        level.increaseLevel();
        level.increaseLevel();
        assertEquals(7, level.getLevel());
        assertTrue(level.canUnlockPerk());
        level.increaseLevel();
        level.increaseLevel();
        assertEquals(9, level.getLevel());
        assertTrue(level.canUnlockPerk());
        level.increaseLevel();
        assertEquals(10, level.getLevel());
        assertFalse(level.canUnlockPerk());
    }
}
