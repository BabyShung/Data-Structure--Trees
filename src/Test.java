import java.security.InvalidKeyException;

import BinarySearchTree.BinarySearchTree;
import Exceptions.BoundaryViolationException;
import Exceptions.EmptyTreeException;
import Exceptions.InvalidPositionException;
import Exceptions.NoEmptyTreeException;
import Interfaces.BTPosition;
import Interfaces.Comparator;
import Interfaces.Position;
import applications.Traveral_BinaryTree;
import applications.isBinarySearchTree;

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
		// LinkedBinaryTree<Object> tbt = new LinkedBinaryTree<>();
		// Position<Object> root, left, right;
		// root = tbt.addRoot(5);
		// left = tbt.insertLeft(root, 4);
		// right = tbt.insertRight(root, 3);
		//
		// left = tbt.insertLeft(left, 2);
		// left = tbt.insertRight(left, 1);
		//
		// right = tbt.insertLeft(right, 6);
		// right = tbt.insertRight(right, 7);
		//
		// for (Position<Object> tmp : tbt) {
		// System.out.print(tmp.element() + " ");
		// }

		// test binary search tree
		Traveral_BinaryTree<Integer> tbt = new Traveral_BinaryTree<>(
				new Comparator<Integer>() {

					@Override
					public int compare(Integer a, Integer b) {
						if (a < b)
							return -1;
						else if (a > b)
							return 1;
						else
							return 0;
					}
				});
		BTPosition<Integer> root, left, right;
		root = tbt.addRoot(5);

		left = tbt.insertLeft(root, 3);
		right = tbt.insertRight(root, 8);

		tbt.insertLeft(left, 1);
		tbt.insertRight(left, 4);
		tbt.insertLeft(right, 6);
		tbt.insertRight(right, 9);
		for (Position<Integer> tmp : tbt) {
			System.out.print(tmp.element() + " ");
		}
		System.out.println();

		tbt.preOrderTraveral();
		tbt.inOrderTraveral();
		tbt.postOrderTraveral();

		System.out.println("Traveral-tree next largest: "
				+ tbt.BSTNextLargest(5));
		System.out.println("isBST: "
				+ tbt.isBST(Integer.MIN_VALUE, Integer.MAX_VALUE));

		BinarySearchTree<Integer> bst = new BinarySearchTree<>(
				new Comparator<Integer>() {

					@Override
					public int compare(Integer a, Integer b) {
						if (a < b)
							return -1;
						else if (a > b)
							return 1;
						else
							return 0;
					}
				});
		bst.insertNode(5);
		bst.insertNode(3);
		bst.insertNode(7);
		bst.insertNode(2);
		bst.insertNode(4);
		bst.insertNode(6);
		bst.insertNode(9);
		bst.insertNode(8);
		// System.out.println(bst.SearchNode(bst.root(), 3));
		// bst.inOrderTraveral();
		// bst.preOrderTraveral();
		// bst.postOrderTraveral();

		System.out.println("root tree height: " + bst.treeHeight(bst.root()));
		System.out.println("root tree depth: " + bst.treeDepth(bst.root()));

		System.out.println("BST next largest: " + bst.BSTNextLargest(7));

		System.out.println("isBST: "
				+ bst.isBST(Integer.MIN_VALUE, Integer.MAX_VALUE));

	}

}
