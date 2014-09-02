package BinarySearchTree;

public class BST {

	private TreeNode root;

	public BST() {
		//this(5);
	}

	public BST(int rootValue) {
		root = new TreeNode(rootValue);
	}

	public TreeNode getMaxNode() {
		TreeNode current = root;
		if (current == null)
			return null;
		while (current.right != null) {
			current = current.right;
		}
		return current;
	}

	public TreeNode getSecondMax() {
		TreeNode current = root, parent = null;
		if (current == null)
			return null;

		while (current.right != null) {
			parent = current;
			current = current.right;
		}

		if (current.left != null) {
			current = current.left;

			while (current.right != null) {
				current = current.right;
			}
		} else
			return parent;

		return current;
	}

	public TreeNode insert(int value) {

		TreeNode current = root, parent = null;
		boolean isLeft = false;

		while (current != null) {

			parent = current;

			if (current.val == value) {
				// I ignore this case
			} else if (current.val > value) { // goes left
				current = current.left;
				isLeft = true;
			} else { // goes right
				current = current.right;
				isLeft = false;
			}
		}

		TreeNode newNode = new TreeNode(value);

		if (parent == null) {
			root = newNode;
		} else if (isLeft == true) {
			parent.left = newNode;
		} else {
			parent.right = newNode;
		}

		return newNode;
	}

}
