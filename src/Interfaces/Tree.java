package Interfaces;

import java.util.ArrayList;
import java.util.Iterator;

import GeneralTrees.GeneralTree;

public interface Tree<T> extends Iterable<Position<T>> {

	Position<T> root();

	Iterator<? extends Position<T>> children(Position<T> pos);

	Position<T> parent(Position<T> pos);

	Position<T> addChild(Position<T> parent, T element);

	T replace(Position<T> pos, T newElement);

	int size();

	boolean isInternal(Position<T> pos);

	boolean isRoot(Position<T> pos);

	public ArrayList<GeneralTree<T>> TreeGetChildren(Position<T> pos);
}