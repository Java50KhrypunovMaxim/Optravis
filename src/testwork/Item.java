package testwork;

public class Item<T> {
	private T value;
	private String metadata;
	private int index;

	public Item(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
