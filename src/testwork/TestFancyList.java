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
	public void testAddAndContains() {
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
	public void testRemoveAndUndoRemove() {
		fancy.add("a");
		fancy.add("b");
		fancy.add("c");
		fancy.remove(1);
		assertFalse(fancy.contains("b"));
		assertEquals(2, fancy.giveMeSize());

		fancy.undo();
		assertTrue(fancy.contains("b"));
		assertEquals(3, fancy.giveMeSize());
	}

	@Test
	public void testRedo() {
		fancy.add("a");
		fancy.undo();
		fancy.redo();
		assertTrue(fancy.contains("a"));
		assertEquals(1, fancy.giveMeSize());
	}

	@Test
	public void testSetAndGetMetadata() {
		fancy.add("a");
		fancy.setMetadata(0, "metadataA");
		assertEquals("metadataA", fancy.getMetadata(0));
	}

	@Test
	public void testUndoSetMetadata() {
		fancy.add("a");
		fancy.setMetadata(0, "metadataA");
		fancy.add("b");
		fancy.setMetadata(1, "metadatab");
		fancy.undo();
		assertEquals("metadatab", fancy.getMetadata(1));
	}

	@Test
	public void testRedoSetMetadata() {
		fancy.add("a");
		fancy.setMetadata(0, "metadataA");
		fancy.undo();
		fancy.redo();
		assertEquals("metadataA", fancy.getMetadata(0));
	}

}