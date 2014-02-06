package applications;

/**
 * This is a self-defined normal binary tree extends the "LinkedBinaryTree"
 * 
 * Added comparator-
 * 
 * Own methods:
 * 
 * 1.pre-order traversal
 * 2.in-order traversal
 * 3.post-order traversal
 * 4.isBST
 * 5.treeHeight
 * 6.treeDepth
 * 7.KDistanceFromRoot
 * 8.BFS iterator
 * 9.DFS iterator
 * 
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import BinaryTree.LinkedBinaryTree;
import Exceptions.EmptyTreeException;
import Exceptions.InvalidPositionException;
import Interfaces.BTPosition;
import Interfaces.Comparator;
import Interfaces.Position;

public class AdvancedBinaryTree<T> extends LinkedBinaryTree<T> {

	protected Comparator<T> comp;

	public AdvancedBinaryTree(Comparator<T> comp) {
		this.comp = comp;
	}

	/**
	 * pre-order traversal
	 */
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

	/**
	 * in-order traversal
	 */
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

	/**
	 * post-order traversal
	 */
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

	/**
	 * check isBST
	 * 
	 * checking subtrees---space O(1), time O(n)
	 * 
	 * @param min
	 * @param max
	 */
	public boolean isBST(T min, T max) throws InvalidPositionException {
		return isBST(checkPosition(root), min, max);
	}

	private boolean isBST(BTPosition<T> current, T min, T max) {

		if (current == null)
			return true;

		T currentV = current.element();
		if (comp.compare(currentV, min) > 0 && comp.compare(currentV, max) < 0)
			return isBST(current.getLeft(), min, currentV)
					&& isBST(current.getRight(), currentV, max);
		else
			return false;

		/**
		 * second thought: not space O(1) 1.in-order add all the elements into
		 * an arraylist,O(n) 2.scan through the al to check the order, then know
		 * if is BST,O(n)
		 */
	}

	/**
	 * depth and height
	 * 
	 */

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

	// -----depth
	public int treeDepth(Position<T> current) throws InvalidPositionException {
		BTPosition<T> currentBT = checkPosition(current);
		return treeDepthRec(currentBT);
	}

	private int treeDepthRec(BTPosition<T> current)
			throws InvalidPositionException {
		if (current == root)
			return 1;
		return 1 + treeDepthRec(current.getParent());
	}

	/**
	 * k distance from root
	 * 
	 * @throws InvalidPositionException
	 */
	public void KDistanceFromRoot(int k) throws InvalidPositionException {

		BTPosition<T> BTRoot = checkPosition(root);
		KDistanceFromRootRec(BTRoot, k);
	}

	private void KDistanceFromRootRec(BTPosition<T> current, int k) {

		if (current == null)
			return;
		if (k == 0) {
			System.out.println("find one at " + current.element());
		} else {
			KDistanceFromRootRec(current.getLeft(), k - 1);// note: can't use
															// --k here, it will
															// affect the blow
															// line's k
			KDistanceFromRootRec(current.getRight(), k - 1);
		}

		/**
		 * second thought: use BFS to get them
		 */

		/**
		 * follow up start from any node, get k distance?
		 */
	}

	@Override
	public Iterator<Position<T>> iterator() {
		// return new BFSIterator();
		return new DFSIterator();
	}

	/**
	 * BFS and DFS, *** important! The below two are almost the same
	 * 
	 * Follow up -------write BFS and DFS not in iterator class
	 */

	private class BFSIterator implements Iterator<Position<T>> {

		Queue<BTPosition<T>> queue;

		public BFSIterator() {
			queue = new LinkedList<BTPosition<T>>();
			queue.add(root);
		}

		@Override
		public boolean hasNext() {
			return !queue.isEmpty();
		}

		@Override
		public Position<T> next() {
			if (!hasNext()) {
				throw new Error("No more elements!");
			}
			BTPosition<T> current = queue.poll();// poll get the head node
			if (current.getLeft() != null)
				queue.add(current.getLeft());
			if (current.getRight() != null)
				queue.add(current.getRight());
			return current;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException(
					"Cannot remove from tree iterator.");
		}

	}

	private class DFSIterator implements Iterator<Position<T>> {

		Stack<BTPosition<T>> stack;

		public DFSIterator() {
			stack = new Stack<BTPosition<T>>();
			stack.push(root);
		}

		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

		@Override
		public Position<T> next() {
			if (!hasNext()) {
				throw new Error("No more elements!");
			}
			BTPosition<T> current = stack.pop();
			if (current.getLeft() != null)
				stack.push(current.getRight()); // add right first, if you wanna
												// scan left path first
			if (current.getRight() != null)
				stack.push(current.getLeft());
			return current;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException(
					"Cannot remove from tree iterator.");
		}

	}

	// print helper
	public void print(String dataStructure) {
		System.out.print(dataStructure + ": ");

		Iterator<Position<T>> bfs = new BFSIterator();

		while (bfs.hasNext())
			System.out.print(bfs.next() + " ");

		System.out.println();

		int nBlanks = 32;
		int itemsPerRow = 1;
		int column = 0;
		String dots = "................................";
		System.out.println(dots + dots);

		bfs = new BFSIterator();
		while (size > 0) {
			if (column == 0) {
				for (int k = 0; k < nBlanks; k++)
					System.out.print(" ");
			}
			System.out.print(bfs.next());
			if (!bfs.hasNext())
				break;
			if (++column == itemsPerRow) {
				nBlanks /= 2;
				itemsPerRow *= 2;
				column = 0;
				System.out.println();
			} else {
				for (int k = 0; k < nBlanks * 2; k++)
					System.out.print(" ");
			}

		}
		System.out.println();
		System.out.println(dots + dots);
	}

}
