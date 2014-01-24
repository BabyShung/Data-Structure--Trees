package BinaryTree;

import Interfaces.BTPosition;

public class BinaryTreeNode<T> implements BTPosition<T> {

	//storing structure
	private T element;
	private BTPosition<T> left, right, parent;

	public BinaryTreeNode(T element, BTPosition<T> left, BTPosition<T> right,
			BTPosition<T> parent) {
		setElement(element);
		setLeft(left);
		setRight(right);
		setParent(parent);
	}

	@Override
	public T element() {
		return element;
	}

	@Override
	public BTPosition<T> getLeft() {
		return left;
	}

	@Override
	public BTPosition<T> getRight() {
		return right;
	}

	@Override
	public BTPosition<T> getParent() {
		return parent;
	}

	@Override
	public void setLeft(BTPosition<T> p) {
		left = p;
	}

	@Override
	public void setRight(BTPosition<T> p) {
		right = p;
	}

	@Override
	public void setParent(BTPosition<T> p) {
		parent = p;
	}

	@Override
	public void setElement(T ele) {
		element = ele;
	}

}
