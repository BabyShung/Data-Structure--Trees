package applications;

/**
 * 
 * Own methods:
 * 
 * 1.getNextLargest
 * 2.lowestCommonAncestor
 * 
 * 
 */

import BinarySearchTree.BinarySearchTree;
import Exceptions.EmptyTreeException;
import Exceptions.InvalidPositionException;
import Interfaces.BTPosition;
import Interfaces.Comparator;
import Interfaces.Position;

public class AdvancedBinarySearchTree<T> extends BinarySearchTree<T> {

	public AdvancedBinarySearchTree(Comparator<T> comp) {
		super(comp);
	}

	/**
	 * get next largest
	 * 
	 * @param key
	 */

	public T NextLargest(T key) throws InvalidPositionException,
			EmptyTreeException {
		return NextLargestRec(key, checkPosition(root()));
	}

	// seems inefficient, come up with a new one later
	private T NextLargestRec(T key, BTPosition<T> node) {

		T found = null;

		if (node.getLeft() != null) {
			found = NextLargestRec(key, node.getLeft());
		}

		if (comp.compare(node.element(), key) > 0 && found == null) {
			found = node.element();
		}

		if (node.getRight() != null && found == null) {
			found = NextLargestRec(key, node.getRight());
		}

		return found;

	}

	/**
	 * lowestCommonAncestor
	 * 
	 * @throws InvalidPositionException
	 */

	public BTPosition<T> lowestCommonAncestor(Position<T> root, T a, T b)
			throws InvalidPositionException {
		
		BTPosition<T> BTroot = checkPosition(root);
		while (BTroot != null) {

			T currentV = BTroot.element();
			if (comp.compare(currentV, a) > 1 && comp.compare(currentV, b) > 1) {
				BTroot = BTroot.getLeft();
			} else if (comp.compare(currentV, a) < 1
					&& comp.compare(currentV, b) < 1) {
				BTroot = BTroot.getRight();
			} else
				// this case include one of a or b is the ancestor
				return BTroot;
		}

		return null;// root is null, empty tree

	}

}
