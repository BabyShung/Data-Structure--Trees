package BinarySearchTree;

/**
 * Binary Search Tree based on linked list
 * 
 * It extends the self-defined Traveral_BinaryTree, which has all the traversals (pre,post,in order).
 * Traveral_BinaryTree extends the LinkedBinaryTree ( basic methods: getLeft,getRight,etc).
 * 
 * 
 * 
 * Own methods:
 * 
 * SearchNode, O(logN) if it is balanced
 * insertNode, O(logN) if it is balanced
 * 
 * 
 * 
 * will improve the code and add delete method later.
 * 
 * 
 * @author haozheng
 * 
 */

import applications.Traveral_BinaryTree;
import BinaryTree.BinaryTreeNode;
import Exceptions.InvalidPositionException;
import Exceptions.NoEmptyTreeException;
import Interfaces.BTPosition;
import Interfaces.Comparator;
import Interfaces.Position;

public class BinarySearchTree<T> extends Traveral_BinaryTree<T> {

	public BinarySearchTree(Comparator<T> comp) {
		super(comp);
	}

	// Iterative Search Node
	public Position<T> SearchNode(Position<T> root, T value)
			throws InvalidPositionException {

		BTPosition<T> BTroot = checkPosition(root);

		while (BTroot != null) {
			T currentV = BTroot.element();
			if (currentV == value)
				break;
			if (comp.compare(currentV, value) > 0)
				BTroot = BTroot.getLeft();
			else
				BTroot = BTroot.getRight();
		}
		return BTroot;
	}

	// Recursive Search Node
	public Position<T> SearchNodeRec(Position<T> root, T value)
			throws InvalidPositionException {

		if (root == null)
			return null;

		BTPosition<T> BTroot = checkPosition(root);
		T currentV = BTroot.element();

		if (currentV == value)
			return BTroot;
		if (comp.compare(currentV, value) > 0)
			return SearchNodeRec(BTroot.getLeft(), value);
		else
			return SearchNodeRec(BTroot.getRight(), value);

	}

	// iterative insert node
	public Position<T> insertNode(T value) throws NoEmptyTreeException,
			InvalidPositionException {

		if (root == null) {
			return addRoot(value);
		}

		BTPosition<T> current = checkPosition(root);
		BTPosition<T> parent;

		while (true) {
			parent = current;

			if (comp.compare(current.element(), value) > 0) {
				current = current.getLeft();

				if (current == null) { // add new node at left
					BTPosition<T> newNode = new BinaryTreeNode<T>(value, null,
							null, parent);
					parent.setLeft(newNode);
					return newNode;
				}

			} else if (comp.compare(current.element(), value) < 0) {
				current = current.getRight();

				if (current == null) { // add new node at right
					BTPosition<T> newNode = new BinaryTreeNode<T>(value, null,
							null, parent);
					parent.setRight(newNode);
					return newNode;
				}
			} else { // currentV equals value, I ignored this case, you can
						// define the case
				return null;
			}

		}
	}

	public int treeHeight(Position<T> current) throws InvalidPositionException {

		BTPosition<T> currentBT = checkPosition(current);

		return treeHeightRec(currentBT);

	}

	private int treeHeightRec(BTPosition<T> current)
			throws InvalidPositionException {
		if (current == null)
			return 0;
		return 1 + Math.max(treeHeightRec(current.getLeft()),
				treeHeightRec(current.getRight()));
	}

}
