package BinaryTree;

import java.util.ArrayList;
import java.util.Iterator;

import Exceptions.BoundaryViolationException;
import Exceptions.EmptyTreeException;
import Exceptions.InvalidPositionException;
import Exceptions.NoEmptyTreeException;
import Interfaces.BTPosition;
import Interfaces.BinaryTree;
import Interfaces.Position;

public class LinkedBinaryTree<T> implements BinaryTree<T> {

	protected BTPosition<T> root;
	protected int size;

	public LinkedBinaryTree() {
		root = null;
		size = 0;
	}

	@Override
	public Position<T> root() throws EmptyTreeException {
		if (root == null)
			throw new EmptyTreeException("Empty tree");
		return root;
	}

	@Override
	public Iterable<? extends Position<T>> children(Position<T> p)
			throws InvalidPositionException, BoundaryViolationException {
		ArrayList<Position<T>> children = new ArrayList<Position<T>>();
		if (hasLeft(p))
			children.add(left(p));
		if (hasRight(p))
			children.add(right(p));
		return children;
	}

	@Override
	public Position<T> parent(Position<T> p) throws BoundaryViolationException,
			InvalidPositionException {
		BTPosition<T> rp = checkPosition(p);
		Position<T> parentP = rp.getParent();
		if (parentP == null)
			throw new BoundaryViolationException("No parent");
		return parentP;
	}

	@Override
	public Position<T> addChild(Position<T> parent, T element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T replace(Position<T> p, T newElement)
			throws InvalidPositionException {
		BTPosition<T> rp = checkPosition(p);
		T tmp = p.element();
		rp.setElement(newElement);
		return tmp;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isInternal(Position<T> p) throws InvalidPositionException {
		checkPosition(p);
		return (hasLeft(p) || hasRight(p));
	}

	@Override
	public boolean isRoot(Position<T> p) throws InvalidPositionException,
			EmptyTreeException {
		checkPosition(p);
		return (p == root());
	}

	@Override
	public Iterator<Position<T>> iterator() {
		Iterable<Position<T>> ps = null;
		try {
			ps = positions();
		} catch (InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BoundaryViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ps.iterator();
	}

	@Override
	public Position<T> left(Position<T> p) throws BoundaryViolationException,
			InvalidPositionException {
		BTPosition<T> rp = checkPosition(p);
		Position<T> leftP = rp.getLeft();
		if (leftP == null)
			throw new BoundaryViolationException("No left child");
		return leftP;
	}

	@Override
	public Position<T> right(Position<T> p) throws BoundaryViolationException,
			InvalidPositionException {
		BTPosition<T> rp = checkPosition(p);
		Position<T> rightP = rp.getRight();
		if (rightP == null)
			throw new BoundaryViolationException("No right child");
		return rightP;
	}

	@Override
	public boolean hasLeft(Position<T> p) throws InvalidPositionException {
		BTPosition<T> rp = checkPosition(p);
		return (rp.getLeft() != null);
	}

	@Override
	public boolean hasRight(Position<T> p) throws InvalidPositionException {
		BTPosition<T> rp = checkPosition(p);
		return (rp.getRight() != null);
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	// own methods
	protected BTPosition<T> checkPosition(Position<T> p)
			throws InvalidPositionException {
		if (p == null || !(p instanceof BTPosition)) {
			throw new InvalidPositionException("Position is invalid");
		}
		return (BTPosition<T>) p;
	}

	private void preOrderPositions(Position<T> p, ArrayList<Position<T>> pal)
			throws InvalidPositionException, BoundaryViolationException {
		pal.add(p);
		if (hasLeft(p))
			preOrderPositions(left(p), pal);
		if (hasRight(p))
			preOrderPositions(right(p), pal);
	}

	private Iterable<Position<T>> positions() throws InvalidPositionException,
			BoundaryViolationException, EmptyTreeException {
		ArrayList<Position<T>> ps = new ArrayList<Position<T>>();
		if (size != 0)
			preOrderPositions(root(), ps);
		return ps;
	}

	public Position<T> addRoot(T ele) throws NoEmptyTreeException {
		if (!isEmpty())
			throw new NoEmptyTreeException("Tree already has a root");
		size = 1;
		root = new BinaryTreeNode<T>(ele, null, null, null);
		return root;
	}

	public Position<T> insertLeft(Position<T> p, T ele)
			throws InvalidPositionException {
		BTPosition<T> rp = checkPosition(p);
		Position<T> leftP = rp.getLeft();
		if (leftP != null)
			throw new InvalidPositionException("Node already has a left child");
		BTPosition<T> newNode = new BinaryTreeNode<T>(ele, null, null, rp);
		rp.setLeft(newNode);
		size++;
		return newNode;
	}

	public Position<T> insertRight(Position<T> p, T ele)
			throws InvalidPositionException {
		BTPosition<T> rp = checkPosition(p);
		Position<T> rightP = rp.getRight();
		if (rightP != null)
			throw new InvalidPositionException("Node already has a right child");
		BTPosition<T> newNode = new BinaryTreeNode<T>(ele, null, null, rp);
		rp.setRight(newNode);
		size++;
		return newNode;
	}

}