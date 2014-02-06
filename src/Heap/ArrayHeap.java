package Heap;

/**
 *  Max heap
 * 
 * @author haozheng
 * 
 */

import java.util.ArrayList;

import Interfaces.BTPosition;
import Interfaces.Comparator;
import Interfaces.Heap;

//Here should also implement binarytree, I skipped
//or extends ArrayBinaryTree, I didn't implement one

public class ArrayHeap<T> implements Heap<T> {

	private ArrayList<BTPosition<T>> heap;
	private int size;
	private Comparator<T> comp;

	public ArrayHeap(Comparator<T> comp) {
		this.comp = comp;
		heap = new ArrayList<>();
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void insert(T element) {

		BTPosition<T> newNode = new HeapNode<T>(element, size, heap);
		heap.add(newNode);
		trickleUp(size++);
	}

	@Override
	public BTPosition<T> remove() { // remove the max key
		BTPosition<T> root = heap.get(0);
		heap.set(0, heap.get(size - 1)); // put the last to root
		trickleDown(0);
		return root;
	}

	private void trickleUp(int index) {
		int parent = (index - 1) / 2;
		BTPosition<T> bottom = heap.get(index);

		while (index > 0
				&& comp.compare(heap.get(parent).element(), bottom.element()) < 0) {
			heap.set(index, heap.get(parent));
			index = parent;
			parent = (parent - 1) / 2;
		}

		heap.set(index, bottom);
	}

	private void trickleDown(int index) {
		int largerChild;
		BTPosition<T> top = heap.get(index);

		while (index < size / 2) {

			int leftChild = 2 * index + 1;
			int rightChild = leftChild + 1;

			if (rightChild < size
					&& comp.compare(heap.get(leftChild).element(),
							heap.get(rightChild).element()) < 0)
				largerChild = rightChild;
			else
				largerChild = leftChild;

			// if top >= largerChild, no more tickle, break
			if (comp.compare(top.element(), heap.get(largerChild).element()) >= 0)
				break;

			heap.set(index, heap.get(largerChild));
			index = largerChild;
		}
		heap.set(index, top); // put old root to index
	}

	public boolean replace(int index, T newV) {
		if (index < 0 || index >= size)
			return false;

		T oldV = heap.get(index).element();

		heap.get(index).setElement(newV);

		if (comp.compare(oldV, newV) < 0)
			trickleUp(index);
		else
			trickleDown(index);
		return true;
	}

	// printing helper, actually this method should be in ArrayBinaryTree(not implemented)
	public void print(String dataStructure) {
		System.out.print(dataStructure + ": ");
		for (int i = 0; i < size; i++)
			if (heap.get(i) != null)
				System.out.print(heap.get(i).element() + " ");
			else
				System.out.print("--");
		System.out.println();

		int nBlanks = 32;
		int itemsPerRow = 1;
		int column = 0;
		int j = 0;
		String dots = "................................";
		System.out.println(dots + dots);

		while (size > 0) {
			if (column == 0) {
				for (int k = 0; k < nBlanks; k++)
					System.out.print(" ");
			}
			System.out.print(heap.get(j).element());
			if (++j == size)
				break;
			if (++column == itemsPerRow) {
				nBlanks /= 2;
				itemsPerRow *= 2;
				column = 0;
				System.out.println();
			} else {
				for (int k = 0; k < nBlanks * 2; k++)
					System.out.print(" ");
			}

		}
		System.out.println();
		System.out.println(dots + dots);
	}

}
