package Interfaces;

import java.util.ArrayList;
import java.util.Iterator;

import Exceptions.BoundaryViolationException;
import Exceptions.EmptyTreeException;
import Exceptions.InvalidPositionException;
import GeneralTrees.GeneralTreeNode;

public interface Tree<T> extends Iterable<Position<T>> {

	Position<T> root() throws EmptyTreeException;

	Iterable<? extends Position<T>> children(Position<T> p)
			throws InvalidPositionException, BoundaryViolationException;

	Position<T> parent(Position<T> p) throws BoundaryViolationException,
			InvalidPositionException;

	T replace(Position<T> p, T newElement) throws InvalidPositionException;

	int size();

	boolean isEmpty();

	boolean isInternal(Position<T> p) throws InvalidPositionException;

	boolean isRoot(Position<T> p) throws InvalidPositionException,
			EmptyTreeException;

}