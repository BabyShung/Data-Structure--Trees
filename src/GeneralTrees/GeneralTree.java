package GeneralTrees;

import java.util.ArrayList;
import java.util.Iterator;

import Interfaces.Position;
import Interfaces.Tree;

public class GeneralTree<T> implements Position<T>
{
    // A reference to the tree which this particular TreeNode is a member of.
    // We can then check to see if a node is in fact a member of a tree before
    // we 'operate' on it.
    private Tree<T> tree;
    private T element;
    private ArrayList<GeneralTree<T>> children;
    private GeneralTree<T> parent;


    public GeneralTree( T elem, GeneralTree<T> p, Tree<T> t )
    {
        element = elem;
        children = new ArrayList<GeneralTree<T>>( );
        parent = p;
        tree = t;
    }
    
    // Return the element stored by this particular tree node
    @Override
    public T element( )
    {
        return element;
    }
 
    // Return the children of this TreeNode in an Iterator form
    public Iterator<GeneralTree<T>> getChildren( )
    {
        return children.iterator( );
    }

    public ArrayList<GeneralTree<T>> getDirectChilrean()
    {
        return children;
    }

    public GeneralTree<T> getParent( )
    {
        return parent;
    }
    
    // Add child to the set of children of this node and return the child
    public GeneralTree<T> addChild( GeneralTree<T> child )
    {
        children.add( child );
        return child;
    }
    
    public T replace( T newElement )
    {
        T oldElement = element;
        element = newElement;
        return oldElement;
    }
    
    @Override
    public String toString( )
    {
        return element.toString( );
    }
    
    // Check to see if in fact this node is a member of the right tree.  I am
    // going to make this 'package private' -- I only want my Tree class to be
    // doing this checking.
    boolean sameTree( Tree<T> t )
    {
        return t == tree;
    }
}