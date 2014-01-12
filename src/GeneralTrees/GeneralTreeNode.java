package GeneralTrees;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

import Interfaces.Position;
import Interfaces.Tree;

public class GeneralTreeNode<T> implements Tree<T> {
	private GeneralTree<T> root;
	private int size;

	public GeneralTreeNode(T rootElement) {
		root = new GeneralTree<T>(rootElement, null, this);
		size = 1;
	}

	@Override
	public Position<T> root() {
		return root;
	}

	@Override
	public Iterator<? extends Position<T>> children(Position<T> pos) {
		return castPositionToNode(pos).getChildren();
	}

	@Override
	public Position<T> parent(Position<T> pos) {
		return castPositionToNode(pos).getParent();
	}

	@Override
	public Position<T> addChild(Position<T> parent, T element) {
		size++;
		return castPositionToNode(parent).addChild(
				new GeneralTree<T>(element, castPositionToNode(parent), this));
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

	// This private method is used to see if a given position is a member of
	// this particular tree. It first checks to make sure 'pos' is of type
	// TreeNode, and if it is, checks to see if its tree reference is to this
	// particular tree. If not, an exception is thrown.
	private void checkTreeMembership(Position<T> pos) {
		if (!(pos instanceof GeneralTree) || !((GeneralTree<T>) pos).sameTree(this)) {
			throw new IllegalArgumentException("Invalid position for this "
					+ "tree.");
		}
	}

	// Private helper method that takes a Position and returns the TreeNode
	// corresponding with that position (or throws an exception if the TreeNode
	// is not a valid node from this tree)
	private GeneralTree<T> castPositionToNode(Position<T> pos) {
		checkTreeMembership(pos);
		return (GeneralTree<T>) pos;
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

		GeneralTree<T> cursor = root;

		Queue<GeneralTree<T>> OutputQueue;

		public DepthFirstIterator() {
			OutputQueue = new LinkedList<GeneralTree<T>>();
			this.buildDFSTraversal(cursor);
		}

		private void buildDFSTraversal(GeneralTree<T> cur) {
			OutputQueue.add(cur);
			Iterator<GeneralTree<T>> it = cur.getChildren();
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

		GeneralTree<T> cursor = root;
		Stack<GeneralTree<T>> stack;
		Queue<GeneralTree<T>> OutputQueue;

		public DepthFirstIterator2() {
			stack = new Stack<GeneralTree<T>>();
			OutputQueue = new LinkedList<GeneralTree<T>>();
			this.buildStack(cursor);
		}

		private void buildStack(GeneralTree<T> cur) {
			if (cur != null)
				stack.push(cur);

			while (!stack.isEmpty()) {
				GeneralTree<T> tmp = stack.pop();
				OutputQueue.add(tmp);
				Iterator<GeneralTree<T>> it = tmp.getChildren();
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
		GeneralTree<T> cursor = root;
		Queue<GeneralTree<T>> queue;
		Queue<GeneralTree<T>> OutputQueue;

		public BreadthFirstIterator() {
			queue = new LinkedList<GeneralTree<T>>();
			OutputQueue = new LinkedList<GeneralTree<T>>();
			this.buildQueue(cursor);
		}

		private void buildQueue(GeneralTree<T> cur) {
			if (cur != null)
				queue.add(cur);

			while (!queue.isEmpty()) {
				GeneralTree<T> tmp = queue.poll();
				OutputQueue.add(tmp);
				Iterator<GeneralTree<T>> it = tmp.getChildren();
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
		GeneralTree<T> cursor = root;
		Queue<GeneralTree<T>> OutputQueue;

		public PostOrderIterator() {
			OutputQueue = new LinkedList<GeneralTree<T>>();
			this.buildPostOrderTraversal(cursor);
		}

		private void buildPostOrderTraversal(GeneralTree<T> cur) {
			Iterator<GeneralTree<T>> it = cur.getChildren();
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

	public ArrayList<GeneralTree<T>> TreeGetChildren(Position<T> pos) {
		return castPositionToNode(pos).getDirectChilrean();
	}

}