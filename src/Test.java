import java.security.InvalidKeyException;
import java.util.ArrayList;

import Exceptions.BoundaryViolationException;
import Exceptions.EmptyTreeException;
import Exceptions.InvalidPositionException;
import Exceptions.NoEmptyTreeException;
import Heap.ArrayHeap;
import Heap.HeapNode;
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

		tbt.preOrderTraveral();
		tbt.inOrderTraveral();
		tbt.postOrderTraveral();
		System.out.println("B or D FS:");
		for (Position<Integer> tmp : tbt) {// self iterator,overrided super
											// class
			System.out.print(tmp + " ");
		}
		System.out.println();

		tbt.print("Binary Tree");
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
		// bst.print("Binary Search Tree"); // work only for full BT, need overriding

		System.out.println("root tree height: " + bst.treeHeight(bst.root()));
		System.out.println("root tree depth: " + bst.treeDepth(bst.root()));

		System.out.println("BST next largest: " + bst.NextLargest(7));

		System.out.println("isBST: "
				+ bst.isBST(Integer.MIN_VALUE, Integer.MAX_VALUE));

		System.out
				.println("LCA: " + bst.lowestCommonAncestor(bst.root(), 9, 3));

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
		ah.print("ArrayHeap");
		
		ah.heapSort();
		
		ArrayList<BTPosition<Integer>> albt = new ArrayList<>();
		albt.add(new HeapNode<Integer>(10,0,albt));
		albt.add(new HeapNode<Integer>(4,1,albt));
		albt.add(new HeapNode<Integer>(18,2,albt));
		albt.add(new HeapNode<Integer>(7,3,albt));
		albt.add(new HeapNode<Integer>(23,4,albt));
		albt.add(new HeapNode<Integer>(30,5,albt));
		
		ArrayHeap<Integer> ahb = new ArrayHeap<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer a, Integer b) {
				if (a < b)
					return -1;
				else if (a > b)
					return 1;
				else
					return 0;
			}
		},albt);
		ahb.print("BuildUp Heap");
		ahb.heapSort();
	}

}
