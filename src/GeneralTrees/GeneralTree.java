package GeneralTrees;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

import Interfaces.Position;
import Interfaces.Tree;

public class GeneralTree<T> implements Tree<T> {
	private GeneralTreeNode<T> root;
	private int size;

	public GeneralTree(T rootElement) {
		root = new GeneralTreeNode<T>(rootElement, null, this);
		size = 1;
	}

	@Override
	public Position<T> root() {
		return root;
	}

	@Override
	public Iterable<? extends Position<T>> children(Position<T> pos) {
		return castPositionToNode(pos).getDirectChilren();
	}

	@Override
	public Position<T> parent(Position<T> pos) {
		return castPositionToNode(pos).getParent();
	}

	@Override
	public T replace(Position<T> pos, T newElement) {
		return castPositionToNode(pos).replace(newElement);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isInternal(Position<T> pos) {
		return castPositionToNode(pos).getChildren().hasNext();
	}

	@Override
	public boolean isRoot(Position<T> pos) {
		return pos == root;
	}

	//add child
	public Position<T> addChild(Position<T> parent, T element) {
		size++;
		return castPositionToNode(parent).addChild(
				new GeneralTreeNode<T>(element, castPositionToNode(parent), this));
	}
	
	
	// This private method is used to see if a given position is a member of
	// this particular tree. It first checks to make sure 'pos' is of type
	// TreeNode, and if it is, checks to see if its tree reference is to this
	// particular tree. If not, an exception is thrown.
	private void checkTreeMembership(Position<T> pos) {
		if (!(pos instanceof GeneralTreeNode)
				|| !((GeneralTreeNode<T>) pos).sameTree(this)) {
			throw new IllegalArgumentException("Invalid position for this "
					+ "tree.");
		}
	}

	// Private helper method that takes a Position and returns the TreeNode
	// corresponding with that position (or throws an exception if the TreeNode
	// is not a valid node from this tree)
	private GeneralTreeNode<T> castPositionToNode(Position<T> pos) {
		checkTreeMembership(pos);
		return (GeneralTreeNode<T>) pos;
	}

	// ---------------------------------------------------------------------

	// Return an Iterator over the Positions of the tree
	@Override
	public Iterator<Position<T>> iterator() {
		// Hao implements DFS iterator
		// return new DepthFirstIterator();
		// return new BreadthFirstIterator();
		// return new DepthFirstIterator2();
		return new BreadthFirstIterator();
	}

	private class DepthFirstIterator implements Iterator<Position<T>> {

		GeneralTreeNode<T> cursor = root;

		Queue<GeneralTreeNode<T>> OutputQueue;

		public DepthFirstIterator() {
			OutputQueue = new LinkedList<GeneralTreeNode<T>>();
			this.buildDFSTraversal(cursor);
		}

		private void buildDFSTraversal(GeneralTreeNode<T> cur) {
			OutputQueue.add(cur);
			Iterator<GeneralTreeNode<T>> it = cur.getChildren();
			while (it.hasNext()) {
				this.buildDFSTraversal(it.next());
			}
		}

		@Override
		public boolean hasNext() {
			return !OutputQueue.isEmpty();
		}

		@Override
		public Position<T> next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			return OutputQueue.poll();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException(
					"Cannot remove from tree iterator.");
		}
	}

	private class DepthFirstIterator2 implements Iterator<Position<T>> {

		GeneralTreeNode<T> cursor = root;
		Stack<GeneralTreeNode<T>> stack;
		Queue<GeneralTreeNode<T>> OutputQueue;

		public DepthFirstIterator2() {
			stack = new Stack<GeneralTreeNode<T>>();
			OutputQueue = new LinkedList<GeneralTreeNode<T>>();
			this.buildStack(cursor);
		}

		private void buildStack(GeneralTreeNode<T> cur) {
			if (cur != null)
				stack.push(cur);

			while (!stack.isEmpty()) {
				GeneralTreeNode<T> tmp = stack.pop();
				OutputQueue.add(tmp);
				Iterator<GeneralTreeNode<T>> it = tmp.getChildren();
				while (it.hasNext()) {
					this.buildStack(it.next());
				}
			}
		}

		@Override
		public boolean hasNext() {
			return !OutputQueue.isEmpty();
		}

		@Override
		public Position<T> next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			return OutputQueue.poll();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException(
					"Cannot remove from tree iterator.");
		}
	}

	// This iterator returns elements in a 'breadth-first' order
	private class BreadthFirstIterator implements Iterator<Position<T>> {
		GeneralTreeNode<T> cursor = root;
		Queue<GeneralTreeNode<T>> queue;
		Queue<GeneralTreeNode<T>> OutputQueue;

		public BreadthFirstIterator() {
			queue = new LinkedList<GeneralTreeNode<T>>();
			OutputQueue = new LinkedList<GeneralTreeNode<T>>();
			this.buildQueue(cursor);
		}

		private void buildQueue(GeneralTreeNode<T> cur) {
			if (cur != null)
				queue.add(cur);

			while (!queue.isEmpty()) {
				GeneralTreeNode<T> tmp = queue.poll();
				OutputQueue.add(tmp);
				Iterator<GeneralTreeNode<T>> it = tmp.getChildren();
				while (it.hasNext()) {
					queue.add(it.next());
				}
			}
		}

		@Override
		public boolean hasNext() {
			return !OutputQueue.isEmpty();
		}

		@Override
		public Position<T> next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			return OutputQueue.poll();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException(
					"Cannot remove from tree iterator.");
		}
	}

	private class PostOrderIterator implements Iterator<Position<T>> {
		GeneralTreeNode<T> cursor = root;
		Queue<GeneralTreeNode<T>> OutputQueue;

		public PostOrderIterator() {
			OutputQueue = new LinkedList<GeneralTreeNode<T>>();
			this.buildPostOrderTraversal(cursor);
		}

		private void buildPostOrderTraversal(GeneralTreeNode<T> cur) {
			Iterator<GeneralTreeNode<T>> it = cur.getChildren();
			while (it.hasNext()) {
				this.buildPostOrderTraversal(it.next());
			}
			OutputQueue.add(cur);
		}

		@Override
		public boolean hasNext() {
			return !OutputQueue.isEmpty();
		}

		@Override
		public Position<T> next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			return OutputQueue.poll();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException(
					"Cannot remove from tree iterator.");
		}
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	// public ArrayList<GeneralTree<T>> TreeGetChildren(Position<T> pos) {
	// return castPositionToNode(pos).getDirectChilrean();
	// }

}