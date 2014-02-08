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

	public ArrayHeap(Comparator<T> comp, ArrayList<BTPosition<T>> unsorted) {
		this.comp = comp;
		if (unsorted == null)
			heap = new ArrayList<>();
		else {

			heap = unsorted;
			size = heap.size();
			buildHeap(0); // build heap from a unsorted datastructure,
							// O(n). and actually I should put root as input.
							// but I didn't implement binary tree, sad

		}
	}

	private void buildHeap(int index) {
		if (index + 1 > size)
			return;
		buildHeap(2 * index + 1);
		buildHeap(2 * index + 2);
		trickleDown(index);
	}

	public void heapSort() { // decreasing order, since this is maxHeap
		for (int i = 0; i < heap.size(); i++){
			BTPosition<T> tmp = remove();

			System.out.println("Removed: " + tmp.element());
		}
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void insert(T element) {
		// this constructor is bad, need redesign heapnode
		BTPosition<T> newNode = new HeapNode<T>(element, size, heap);
		heap.add(newNode);
		trickleUp(size++);
	}

	@Override
	public BTPosition<T> remove() { // remove the max key

		BTPosition<T> root = heap.get(0);
		heap.set(0, heap.get(size - 1)); // put the last to root
		trickleDown(0);
		size--;
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

	// printing helper, actually this method should be in ArrayBinaryTree(not
	// implemented)
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
