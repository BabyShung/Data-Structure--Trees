package BinarySearchTree;

/**
 * 
 * not completed, use BinarySearchTree instead.
 * 
 */


import java.security.InvalidKeyException;
import java.util.ArrayList;

import BinaryTree.BinaryTreeNode;
import BinaryTree.LinkedBinaryTree;
import Exceptions.BoundaryViolationException;
import Exceptions.EmptyTreeException;
import Exceptions.InvalidEntryException;
import Exceptions.InvalidPositionException;
import Exceptions.NoEmptyTreeException;
import Interfaces.Comparator;
import Interfaces.Entry;
import Interfaces.Map;
import Interfaces.Position;

public class BinarySearchTreeMap<K, V> extends LinkedBinaryTree<Entry<K, V>>
		implements Map<K, V> {

	protected Comparator<K> comp;
	protected Position<Entry<K, V>> actionP;
	protected int numEntries = 0;

	public BinarySearchTreeMap(Comparator<K> comp) throws NoEmptyTreeException {
		this.comp = comp;

	}

	protected K key(Position<Entry<K, V>> p) {
		return p.element().getKey();
	}

	protected V value(Position<Entry<K, V>> p) {
		return p.element().getValue();
	}

	protected Entry<K, V> entry(Position<Entry<K, V>> p) {
		return p.element();
	}

	protected V replaceEntry(Position<Entry<K, V>> p, Entry<K, V> e)
			throws InvalidPositionException {
		((BSTEntry<K, V>) e).p = p;
		return replace(p, e).getValue();
	}

	protected void checkKey(K key) throws InvalidKeyException {
		if (key == null)
			throw new InvalidKeyException("Null key");
	}

	protected void checkEntry(Entry<K, V> e) throws InvalidEntryException {
		if (e == null || !(e instanceof BSTEntry)) {
			throw new InvalidEntryException("Invalid entry");
		}
	}

	protected Entry<K, V> insertAtExternal(Position<Entry<K, V>> p,
			Entry<K, V> e) {
		return null;
	}

	protected Position<Entry<K, V>> treeSearch(K key, Position<Entry<K, V>> p)
			throws InvalidPositionException, BoundaryViolationException {
		if (p == null)
			return null;
		else {
			K currentK = key(p);
			int comp = this.comp.compare(key, currentK);
			if (comp < 0)
				return treeSearch(key, left(p));
			else if (comp > 0)
				return treeSearch(key, right(p));
			else
				return p;
		}
	}

	// --------------------

	@Override
	public V get(K key) throws InvalidKeyException, InvalidPositionException,
			BoundaryViolationException, EmptyTreeException {
		checkKey(key);
		Position<Entry<K, V>> currentP = treeSearch(key, root());
		actionP = currentP;
		if (isInternal(currentP))
			return value(currentP);
		return null;
	}

	@Override
	public V put(K key, V value) throws InvalidPositionException,
			BoundaryViolationException, EmptyTreeException, InvalidKeyException {

		checkKey(key);

		Position<Entry<K, V>> currentP = treeSearch(key, root());
		BSTEntry<K, V> e = new BSTEntry<K, V>(key, value, currentP);
		actionP = currentP;
		if (!isInternal(currentP))
			insertAtExternal(currentP, e).getValue();
		return null;
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<K> keySet() {
		return null;

	}

	private void inOrderPositions(Position<Entry<K, V>> p,
			ArrayList<Entry<K, V>> ps) throws InvalidPositionException,
			BoundaryViolationException {

		if (hasLeft(p))
			inOrderPositions(left(p), ps);
		ps.add(p.element());
		if (hasRight(p))
			inOrderPositions(right(p), ps);

	}

	@Override
	public Iterable<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() throws InvalidPositionException,
			BoundaryViolationException, EmptyTreeException {
		ArrayList<Entry<K, V>> ps = new ArrayList<>();
		if (size != 0)
			inOrderPositions(root(), ps);
		return ps;
	}

}