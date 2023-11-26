package testwork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FancyList<T> {

	private List<T> list = new ArrayList<>();
	private LinkedList<T> removeList = new LinkedList<>();
	private LinkedList<T> addList = new LinkedList<>();
	private LinkedList<Integer> lastOperation = new LinkedList<>();
	private LinkedList<Integer> indexRemove = new LinkedList<>();
	private List<String> metadataList = new ArrayList<>();

	public void add(T elem) {
		list.add(elem);
		addList.add(elem);
		lastOperation.add(0);
		metadataList.add(null);
	}

	public boolean contains(T elem) {
		return list.contains(elem);
	}

	public void remove(int index) {
		if (index < 0 || index >= list.size()) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}

		T remElem = list.remove(index);
		removeList.add(remElem);
		lastOperation.add(1);
		indexRemove.add(index);
		metadataList.remove(index);
	}

	public T get(int index) {
		return list.get(index);
	}

	public int giveMeSize() {
		return list.size();
	}

	public void undo() {
		int lastOper = lastOperation.getLast();
		if (lastOper == 0 && !addList.isEmpty()) {
			T lastAddElem = addList.removeLast();
			list.remove(lastAddElem);
			metadataList.remove(metadataList.size() - 1);
		} else if (lastOper == 1 && !removeList.isEmpty() && !indexRemove.isEmpty()) {
			int lastIndex = indexRemove.removeLast();
			T lastRemoveElem = removeList.removeLast();
			list.add(lastIndex, lastRemoveElem);
			metadataList.add(lastIndex, null);
		}
	}

	public void setMetadata(int index, String metadata) {
		if (index < 0 || index >= metadataList.size()) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		metadataList.set(index, metadata);
	}

	public String getMetadata(int index) {
		if (index < 0 || index >= metadataList.size()) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		return metadataList.get(index);
	}
}
