import BinaryTree.BinaryTreeNode;
import BinaryTree.LinkedBinaryTree;
import Exceptions.InvalidPositionException;
import Exceptions.NoEmptyTreeException;
import Interfaces.Position;

public class Test {

	/**
	 * @param args
	 * @throws InvalidPositionException
	 * @throws NoEmptyTreeException
	 */
	public static void main(String[] args) throws InvalidPositionException,
			NoEmptyTreeException {
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
	}

}
