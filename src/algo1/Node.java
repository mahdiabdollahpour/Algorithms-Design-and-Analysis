package algo1;

/**
 * Created by ASUS on 04/10/2017.
 */
public class Node {
    public Node leftChild = null;
    public Node rightChild = null;
    public int key;
    public Node parent;
    public int size;

    public boolean hasLeftChild() {
        return leftChild != null;
    }

    public boolean hasRightChild() {
        return rightChild != null;
    }
}