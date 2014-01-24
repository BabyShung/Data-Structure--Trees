package BinarySearchTree;

import Interfaces.Entry;
import Interfaces.Position;

public class BSTEntry<K, V> implements Entry<K, V> {
	protected K key;
	protected V value;
	protected Position<Entry<K, V>> p;

	public BSTEntry() {
	}

	public BSTEntry(K k, V v, Position<Entry<K, V>> p) {
		key = k;
		value = v;
		this.p = p;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

	public Position<Entry<K, V>> getPosition() {
		return p;
	}

}