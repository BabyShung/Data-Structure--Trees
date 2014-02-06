package GeneralTrees;

import java.util.ArrayList;
import java.util.Iterator;

import Interfaces.Position;
import Interfaces.Tree;

public class GeneralTreeNode<T> implements Position<T> {
	// A reference to the tree which this particular TreeNode is a member of.
	// We can then check to see if a node is in fact a member of a tree before
	// we 'operate' on it.
	private Tree<T> tree;
	private T element;
	private ArrayList<GeneralTreeNode<T>> children;
	private GeneralTreeNode<T> parent;

	public GeneralTreeNode(T elem, GeneralTreeNode<T> p, Tree<T> t) {
		element = elem;
		children = new ArrayList<GeneralTreeNode<T>>();
		parent = p;
		tree = t;
	}

	@Override
	public T element() {
		return element;
	}

	// Return the children of this TreeNode in an Iterator form
	public Iterator<GeneralTreeNode<T>> getChildren() {
		return children.iterator();
	}

	public ArrayList<GeneralTreeNode<T>> getDirectChilren() {
		return children;
	}

	public GeneralTreeNode<T> getParent() {
		return parent;
	}

	// Add child to the set of children of this node and return the child
	public GeneralTreeNode<T> addChild(GeneralTreeNode<T> child) {
		children.add(child);
		return child;
	}

	public T replace(T newElement) {
		T oldElement = element;
		element = newElement;
		return oldElement;
	}

	@Override
	public String toString() {
		return element.toString();
	}

	// Check to see if in fact this node is a member of the right tree. I am
	// going to make this 'package private' -- I only want my Tree class to be
	// doing this checking.
	boolean sameTree(Tree<T> t) {
		return t == tree;
	}
}