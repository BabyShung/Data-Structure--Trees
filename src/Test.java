import java.security.InvalidKeyException;
import java.util.ArrayList;

import BinarySearchTree.BST;
import Exceptions.BoundaryViolationException;
import Exceptions.EmptyTreeException;
import Exceptions.InvalidPositionException;
import Exceptions.NoEmptyTreeException;
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
		tbt.insertLeft(right, 7);
		tbt.insertRight(right, 9);

		BTPosition<Integer> btRoot = tbt.checkPosition(tbt.root());

		tbt.preOrderTraveral();
		tbt.inOrderTraveral();
		tbt.postOrderTraveral();
		tbt.postOrderTraveralIterative();

		System.out.println("B or D FS:");
		for (Position<Integer> tmp : tbt) {// self iterator,overrided super
											// class
			System.out.print(tmp + " ");
		}
		System.out.println();
		System.out.print("--BFS: ");
		ArrayList<BTPosition<Integer>> albfs = tbt.BFS();
		for (BTPosition<Integer> bt : albfs) {
			System.out.print(bt.element() + " ");
		}
		System.out.println();
		System.out.print("--DFS: ");
		ArrayList<BTPosition<Integer>> aldfs = tbt.DFS();
		for (BTPosition<Integer> bt : aldfs) {
			System.out.print(bt.element() + " ");
		}
		System.out.println();
		System.out.println("isBST: "
				+ tbt.isBST(btRoot, Integer.MIN_VALUE, Integer.MAX_VALUE));
		System.out.println("isBalanced: " + tbt.isBalanced(btRoot));
		System.out.println("isBalanced2: " + tbt.isBalanced2(btRoot));

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
		bst.insertNodeConcised(5);
		bst.insertNodeConcised(3);
		bst.insertNodeConcised(7);
		bst.insertNodeConcised(2);
		bst.insertNodeConcised(4);
		bst.insertNodeConcised(6);
		bst.insertNodeConcised(9);
		bst.insertNodeConcised(8);
		// System.out.println(bst.SearchNode(bst.root(), 3));
		bst.inOrderTraveral();
		bst.preOrderTraveral();
		bst.postOrderTraveral();
		// bst.print("Binary Search Tree"); // work only for full BT, need
		// overriding

		BTPosition<Integer> bstRoot = bst.checkPosition(bst.root());

		System.out.println("max element: " + bst.getMax(bstRoot).element());
		System.out.println("2nd max element: "
				+ bst.getSecondMax(bstRoot).element());

		System.out.println("root tree height: " + bst.treeHeight(bstRoot));
		System.out.println("root tree depth: " + bst.treeDepth(bstRoot));

		System.out.println("BST next largest: " + bst.NextLargest(5));
		System.out.println("BST next largest2: " + bst.NextLargest2(5));

		System.out.println("bst isBST: "
				+ bst.isBST(bstRoot, Integer.MIN_VALUE, Integer.MAX_VALUE));
		System.out.println("bst isBST2: "
				+ bst.isBST2(bstRoot, Integer.MIN_VALUE));

		System.out.println("bt isBST: "
				+ tbt.isBST(btRoot, Integer.MIN_VALUE, Integer.MAX_VALUE));
		System.out.println("bt isBST2: "
				+ tbt.isBST2(btRoot, Integer.MIN_VALUE));

		System.out
				.println("LCA: " + bst.lowestCommonAncestor(bst.root(), 9, 3));

		bst.KDistanceFromRoot(2);

		
		//Hao BST
		BST haoBST = new BST();
		haoBST.insert(5);
		haoBST.insert(3);
		haoBST.insert(7);
		haoBST.insert(4);
		haoBST.insert(2);
		haoBST.insert(6);
		haoBST.insert(9);
		haoBST.insert(15);
		haoBST.insert(11);
		haoBST.insert(13);

		System.out.println(haoBST.getMaxNode());
		System.out.println(haoBST.getSecondMax());

		// ArrayHeap<Integer> ah = new ArrayHeap<>(new Comparator<Integer>() {
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
		//
		// ah.insert(5);
		// ah.insert(4);
		// ah.insert(11);
		// ah.insert(9);
		// ah.insert(8);
		// ah.insert(2);
		// ah.insert(55);
		// ah.insert(24);
		// ah.insert(1);
		//
		// ah.print("ArrayHeap");
		//
		// ah.heapSort();
		//
		// ArrayList<BTPosition<Integer>> albt = new ArrayList<>();
		// albt.add(new HeapNode<Integer>(6, 0, albt));
		// albt.add(new HeapNode<Integer>(5, 1, albt));
		// albt.add(new HeapNode<Integer>(4, 2, albt));
		// albt.add(new HeapNode<Integer>(3, 3, albt));
		// albt.add(new HeapNode<Integer>(2, 4, albt));
		// albt.add(new HeapNode<Integer>(1, 5, albt));
		//
		// ArrayHeap<Integer> ahb = new ArrayHeap<>(new Comparator<Integer>() {
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
		// }, albt);
		// ahb.print("BuildUp Heap");
		// ahb.heapSort();

	}

}
