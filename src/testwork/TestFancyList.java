package testwork;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestFancyList {

    FancyList<String> fancy;

    @BeforeEach
    public void setUp() {
        fancy = new FancyList<>();
    }

    @Test
    public void testAdd() {
        fancy.add("a");
        assertTrue(fancy.contains("a"));
        assertEquals(1, fancy.giveMeSize());
    }

    @Test
    public void testUndoAdd() {
        fancy.add("a");
        fancy.undo();
        assertEquals(0, fancy.giveMeSize());
        assertFalse(fancy.contains("a"));
    }

    @Test
    public void testRemove() {
        fancy.add("a");
        fancy.add("b");
        fancy.add("c");
        fancy.remove(1);
        assertFalse(fancy.contains("b"));
        assertEquals(2, fancy.giveMeSize());
    }

    @Test
    public void testUndoRemove() {
        fancy.add("a");
        fancy.add("b");
        fancy.add("c");
        fancy.add("d");
        fancy.remove(1);
        assertFalse(fancy.contains("b"));
        fancy.undo();
        assertTrue(fancy.contains("b"));
        assertEquals(4, fancy.giveMeSize());
        assertEquals("b", fancy.get(1));

        fancy.remove(3);
        fancy.undo();
        fancy.remove(2);
        fancy.undo();
        assertEquals("c", fancy.get(2));
        assertEquals("d", fancy.get(3));

        fancy.add("x");
        assertEquals("c", fancy.get(2));
        assertEquals("d", fancy.get(3));
        assertEquals("x", fancy.get(4));
    }

    @Test
    public void testSetAndGetMetadata() {
        fancy.add("a");
        fancy.add("b");
        fancy.setMetadata(0, "metadataA");
        fancy.setMetadata(1, "metadataB");

        assertEquals("metadataA", fancy.getMetadata(0));
        assertEquals("metadataB", fancy.getMetadata(1));
    }

    @Test
    public void testUndoSetMetadata() {
        fancy.add("a");
        fancy.add("b");
        fancy.setMetadata(0, "metadataA");
        fancy.setMetadata(1, "metadataB");

        fancy.undo();
        assertEquals(1, fancy.giveMeSize());
        assertEquals("metadataA", fancy.getMetadata(0));

        fancy.undo();
        assertEquals(0, fancy.giveMeSize());
    }
}
