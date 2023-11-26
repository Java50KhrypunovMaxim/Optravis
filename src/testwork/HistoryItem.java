package testwork;

public class HistoryItem<T> {
	int operation;
	int index;
	T value;
	String metadata;

	public HistoryItem(int operation, int index, T value, String metadata) {
		this.operation = operation;
		this.index = index;
		this.value = value;
		this.metadata = metadata;
	}
}
