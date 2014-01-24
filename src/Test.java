import java.security.InvalidKeyException;

import BinarySearchTree.BinarySearchTreeMap;
import BinaryTree.BinaryTreeNode;
import BinaryTree.LinkedBinaryTree;
import Exceptions.BoundaryViolationException;
import Exceptions.EmptyTreeException;
import Exceptions.InvalidPositionException;
import Exceptions.NoEmptyTreeException;
import Interfaces.Comparator;
import Interfaces.Entry;
import Interfaces.Position;

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
		
		LinkedBinaryTree<Object> lbt = new LinkedBinaryTree<>();
		Position<Object> root, left, right;
		root = lbt.addRoot(5);
		left = lbt.insertLeft(root, 4);
		right = lbt.insertRight(root, 3);

		left = lbt.insertLeft(left, 2);
		left = lbt.insertRight(left, 1);

		right = lbt.insertLeft(right, 6);
		right = lbt.insertRight(right, 7);

		for (Position<Object> tmp : lbt) {
			System.out.print(tmp.element() + " ");
		}

//		BinarySearchTreeMap<Integer, String> bstm = new BinarySearchTreeMap<>(
//				new Comparator<Integer>() {
//
//					@Override
//					public int compare(Integer a, Integer b) {
//						if (a < b)
//							return -1;
//						else if (a > b)
//							return 1;
//						else
//							return 0;
//					}
//				});


	}

}
