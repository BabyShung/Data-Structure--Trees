package Heap;

import java.util.ArrayList;

import Interfaces.BTPosition;

public class HeapNode<T> implements BTPosition<T> {

	private T element;
	private int index;
	private ArrayList<BTPosition<T>> container;

	public HeapNode(T element, int index, ArrayList<BTPosition<T>> container) {
		this.element = element;
		this.index = index;
		this.container = container;
	}
	
	@Override
	public T element() {
		return element;
	}

	@Override
	public BTPosition<T> getLeft() {
		return container.get(2 * index + 1);
	}

	@Override
	public BTPosition<T> getRight() {
		return container.get(2 * index + 2);
	}

	@Override
	public BTPosition<T> getParent() {
		return container.get((index - 1) / 2);
	}

	@Override
	public void setLeft(BTPosition<T> p) {
		container.set(2 * index + 1, p);
	}

	@Override
	public void setRight(BTPosition<T> p) {
		container.set(2 * index + 2, p);
	}

	@Override
	public void setParent(BTPosition<T> p) {
		container.set((index - 1) / 2, p);
	}

	@Override
	public void setElement(T ele) {
		element = ele;
	}

}
