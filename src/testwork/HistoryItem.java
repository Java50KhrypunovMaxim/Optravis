package testwork;

public class HistoryItem<T> {
	public int operation;
	public int index;
	public T value;
	public String metadata;

	public HistoryItem(int operation, int index, T value, String metadata) {
		this.operation = operation;
		this.index = index;
		this.value = value;
		this.metadata = metadata;
	}
}
