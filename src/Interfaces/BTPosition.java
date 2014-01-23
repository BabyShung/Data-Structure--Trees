package Interfaces;

public interface BTPosition<T> extends Position<T> {

	BTPosition<T> getLeft();

	BTPosition<T> getRight();

	BTPosition<T> getParent();

	void setLeft(BTPosition<T> p);

	void setRight(BTPosition<T> p);

	void setParent(BTPosition<T> p);
	
	void setElement(T ele);
}
