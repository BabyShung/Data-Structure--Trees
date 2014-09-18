package BinarySearchTree;

/**
 * Binary Search Tree based on linked list
 * 
 * It extends my self-defined AdvancedBinaryTree, 
 * which has all the traversals (pre,post,in order),etc.
 * AdvancedBinaryTree extends the LinkedBinaryTree ( basic methods: getLeft,getRight,etc).
 * 
 * 
 * 
 * Own methods:
 * 
 * 1.SearchNode, O(logN) if it is balanced
 * 2.InsertNode, O(logN) if it is balanced
 * 3.Remove (not done)	three cases, 
 * ---the delete node has no child -> do nothing
 * ---the delete node has one child -> move the subtree
 * ---the delete node has two children -> find the next largest
 * 				
 * 	
 * 
 * will improve the code and add delete method later.
 * 
 * 
 * @author haozheng
 * 
 */

import applications.AdvancedBinaryTree;
import BinaryTree.BinaryTreeNode;
import Exceptions.InvalidPositionException;
import Exceptions.NoEmptyTreeException;
import Interfaces.BTPosition;
import Interfaces.Comparator;
import Interfaces.Position;

public class BinarySearchTree<T> extends AdvancedBinaryTree<T> {

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
	public Position<T> insertNodeConcised(T value) throws NoEmptyTreeException,
			InvalidPositionException {
		if (root == null)
			return addRoot(value);
		BTPosition<T> current = checkPosition(root);
		while (true) {
			if (comp.compare(current.element(), value) > 0) {// left
				if (current.getLeft() == null) {
					BTPosition<T> newNode = new BinaryTreeNode<T>(value, null,
							null, current);
					current.setLeft(newNode);
					return newNode;
				}
				current = current.getLeft();
			} else if (comp.compare(current.element(), value) < 0) {// right
				if (current.getRight() == null) {
					BTPosition<T> newNode = new BinaryTreeNode<T>(value, null,
							null, current);
					current.setRight(newNode);
					return newNode;
				}
				current = current.getRight();
			} else
				return null;
		}
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

}
