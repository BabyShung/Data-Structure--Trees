package applications;

import BinaryTree.LinkedBinaryTree;
import Exceptions.EmptyTreeException;
import Exceptions.InvalidPositionException;
import Interfaces.BTPosition;
import Interfaces.Comparator;
import Interfaces.Position;

public class Traveral_BinaryTree<T> extends LinkedBinaryTree<T> {

	protected Comparator<T> comp;

	public Traveral_BinaryTree(Comparator<T> comp) {
		this.comp = comp;
	}

	public T BSTNextLargest(T key) throws InvalidPositionException,
			EmptyTreeException {
		return BSTNextLargestRec(key, checkPosition(root()));
	}

	
	//seems inefficient, come up with a new one later
	private T BSTNextLargestRec(T key, BTPosition<T> node) {
		
		T found = null;

		if (node.getLeft() != null) {
			found = BSTNextLargestRec(key, node.getLeft());
		}

		if (comp.compare(node.element(), key) > 0 && found == null) {
			found = node.element();
		}

		if (node.getRight() != null && found == null) {
			found = BSTNextLargestRec(key, node.getRight());
		}

		return found;

	}

	public void preOrderTraveral() throws EmptyTreeException,
			InvalidPositionException {
		System.out.print("PreOrderTraveral ");
		preOrderRec(root());
		System.out.println();
	}

	private void preOrderRec(Position<T> node) throws InvalidPositionException {

		if (node != null) {
			BTPosition<T> BTnode = checkPosition(node);
			System.out.print(node.element() + " ");
			preOrderRec(BTnode.getLeft());
			preOrderRec(BTnode.getRight());
		}
	}

	public void inOrderTraveral() throws EmptyTreeException,
			InvalidPositionException {
		System.out.print("inOrderTraveral ");
		inOrderRec(root());
		System.out.println();
	}

	private void inOrderRec(Position<T> node) throws InvalidPositionException {

		if (node != null) {
			BTPosition<T> BTnode = checkPosition(node);
			inOrderRec(BTnode.getLeft());
			System.out.print(node.element() + " ");
			inOrderRec(BTnode.getRight());
		}
	}

	public void postOrderTraveral() throws EmptyTreeException,
			InvalidPositionException {
		System.out.print("postOrderTraveral ");
		postOrderRec(root());
		System.out.println();
	}

	private void postOrderRec(Position<T> node) throws InvalidPositionException {

		if (node != null) {
			BTPosition<T> BTnode = checkPosition(node);
			postOrderRec(BTnode.getLeft());
			postOrderRec(BTnode.getRight());
			System.out.print(node.element() + " ");
		}
	}

}
