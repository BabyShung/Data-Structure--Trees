import java.security.InvalidKeyException;

import Exceptions.BoundaryViolationException;
import Exceptions.EmptyTreeException;
import Exceptions.InvalidPositionException;
import Exceptions.NoEmptyTreeException;
import Heap.ArrayHeap;
import Interfaces.BTPosition;
import Interfaces.Comparator;
import Interfaces.Position;
import applications.AdvancedBinarySearchTree;
import applications.AdvancedBinaryTree;

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

		// test binary tree
		AdvancedBinaryTree<Integer> tbt = new AdvancedBinaryTree<>(
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
		System.out.println("B or D FS:");
		for(Position<Integer> tmp : tbt){
			System.out.print(tmp+" ");
		}
		System.out.println();
		System.out.println("isBST: "
				+ tbt.isBST(Integer.MIN_VALUE, Integer.MAX_VALUE));

		AdvancedBinarySearchTree<Integer> bst = new AdvancedBinarySearchTree<>(
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

		System.out.println("BST next largest: " + bst.NextLargest(7));

		System.out.println("isBST: "
				+ bst.isBST(Integer.MIN_VALUE, Integer.MAX_VALUE));
		
		System.out.println("LCA: "
				+ bst.lowestCommonAncestor(bst.root(), 9, 3));
		
		bst.KDistanceFromRoot(2);

		
		ArrayHeap<Integer> ah = new ArrayHeap<>(new Comparator<Integer>() {

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
		
		ah.insert(5);
		ah.insert(4);
		ah.insert(11);
		ah.insert(9);
		ah.insert(8);
		ah.insert(2);
		ah.insert(55);
		ah.insert(24);
		ah.insert(1);
		ah.print();
	}

}
