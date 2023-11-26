package testwork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class FancyList<T> {

	List<Item<T>> list = new ArrayList<>();
	private final Stack<HistoryItem<Item<T>>> history = new Stack<>();
	private int undoRedoPointer = -1;

	private void deleteElementsAfterPointer(int undoRedoPointer) {
		if (history.size() > undoRedoPointer + 1) {
			history.subList(undoRedoPointer + 1, history.size()).clear();
		}
	}

	public void add(T elem) {
		deleteElementsAfterPointer(undoRedoPointer);
		Item<T> item = new Item<>(elem);
		list.add(item);
		HistoryItem<Item<T>> hi = new HistoryItem<>(0, list.size() - 1, item, null);
		history.push(hi);
		undoRedoPointer++;
	}

	public boolean contains(T elem) {
		for (Item<T> item : list) {
			if (item.getValue() == elem)
				return true;
		}
		return false;
	}

	public void remove(int index) {
		deleteElementsAfterPointer(undoRedoPointer);
		HistoryItem<Item<T>> hi = new HistoryItem<>(1, index, list.remove(index), null);
		history.push(hi);
		undoRedoPointer++;
	}

	public T get(int index) {
		return list.get(index).getValue();
	}

	public int giveMeSize() {
		return list.size();
	}

	public void undo() {
		if (undoRedoPointer < 0)
			return;

		HistoryItem<Item<T>> hi = history.get(undoRedoPointer);
		if (hi.operation == 0) {
			list.remove(hi.index);
		} else {
			list.add(hi.index, hi.value);
		}
		undoRedoPointer--;
	}

	public void redo() {
		if (undoRedoPointer == history.size() - 1)
			return;

		undoRedoPointer++;
		HistoryItem<Item<T>> hi = history.get(undoRedoPointer);
		if (hi.operation == 0) {
			list.add(hi.index, hi.value);
		} else {
			list.remove(hi.index);
		}
	}

	public String getMetadata(int index) {
		return list.get(index).getMetadata();
	}

	public void setMetadata(int index, String metadata) {
		Item<T> item = list.get(index);
		String previousMetadata = item.getMetadata();
		item.setMetadata(metadata);
		HistoryItem<Item<T>> hi = new HistoryItem<>(2, index, item, previousMetadata);
		history.push(hi);
		undoRedoPointer++;
	}

}