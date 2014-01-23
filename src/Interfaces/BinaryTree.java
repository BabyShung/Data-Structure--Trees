package Interfaces;

import Exceptions.BoundaryViolationException;
import Exceptions.InvalidPositionException;

public interface BinaryTree<T> extends Tree<T> {
	Position<T> left(Position<T> p) throws InvalidPositionException,BoundaryViolationException;

	Position<T> right(Position<T> p) throws InvalidPositionException,BoundaryViolationException;

	boolean hasLeft(Position<T> p) throws InvalidPositionException;

	boolean hasRight(Position<T> p) throws InvalidPositionException;
}
