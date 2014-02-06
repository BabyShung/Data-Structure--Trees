package Interfaces;

public interface Heap<T> {

	void insert(T element);

	BTPosition<T> remove();

	boolean isEmpty();
}
