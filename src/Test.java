import java.security.InvalidKeyException;

import Exceptions.BoundaryViolationException;
import Exceptions.EmptyTreeException;
import Exceptions.InvalidPositionException;
import Exceptions.NoEmptyTreeException;
import Interfaces.BTPosition;
import Interfaces.Position;
import applications.Traveral_BinaryTree;

public class Test {

	/**
	 * @param args
	 * @throws InvalidPositionException
	 * @throws NoEmptyTreeException
	 * @throws EmptyTreeException
	 * @throws BoundaryViolationException
	 * @throws InvalidKeyException
	 */
	public static void main(String[] args) throws InvalidPositionException,
			NoEmptyTreeException, InvalidKeyException,
			BoundaryViolationException, EmptyTreeException {

		// test iterator
		// LinkedBinaryTree<Object> lbt = new LinkedBinaryTree<>();
		// Position<Object> root, left, right;
		// root = lbt.addRoot(5);
		// left = lbt.insertLeft(root, 4);
		// right = lbt.insertRight(root, 3);
		//
		// left = lbt.insertLeft(left, 2);
		// left = lbt.insertRight(left, 1);
		//
		// right = lbt.insertLeft(right, 6);
		// right = lbt.insertRight(right, 7);
		//
		// for (Position<Object> tmp : lbt) {
		// System.out.print(tmp.element() + " ");
		// }

		// test binary search tree
		Traveral_BinaryTree<Object> lbt = new Traveral_BinaryTree<>();
		BTPosition<Object> root, left, right;
		root = lbt.addRoot(5);

		left = lbt.insertLeft(root, 3);
		right = lbt.insertRight(root, 8);

		System.out.println("left " + root.getLeft().element());
		System.out.println("right " + root.getRight().element());

		lbt.insertLeft(left, 1);
		lbt.insertRight(left, 4);
		System.out.println("left " + root.getLeft().getLeft().element());
		System.out.println("right " + root.getLeft().getRight().element());
		lbt.insertLeft(right, 6);
		lbt.insertRight(right, 9);
		System.out.println("left " + root.getRight().getLeft().element());
		System.out.println("right " + root.getRight().getRight().element());
		for (Position<Object> tmp : lbt) {
			System.out.print(tmp.element() + " ");
		}
		System.out.println();

		lbt.preOrderTraveral();
		lbt.inOrderTraveral();
		lbt.postOrderTraveral();

		// BinarySearchTreeMap<Integer, String> bstm = new
		// BinarySearchTreeMap<>(
		// new Comparator<Integer>() {
		//
		// @Override
		// public int compare(Integer a, Integer b) {
		// if (a < b)
		// return -1;
		// else if (a > b)
		// return 1;
		// else
		// return 0;
		// }
		// });

	}

}
