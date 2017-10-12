package algo1;

/**
 * Created by ASUS on 03/10/2017.
 */
public class BinarySearchTree {

    public Node root = null;
    public int inorderIndex = 1;

    public Node search(int key) {
        return searchOnSub(root, key);
    }

    private Node searchOnSub(Node sub, int key) {
        if (sub.key == key) {
            return sub;
        }
        if (sub.key < key) {
            if (sub.rightChild != null) {

                return searchOnSub(sub.rightChild, key);
            } else {
                return null;
            }
        } else {
            if (sub.leftChild != null) {

                return searchOnSub(sub.leftChild, key);
            } else {

                return null;
            }
        }
    }

    public boolean add(int key) {
        Node node = new Node();
        node.key = key;

        if (root == null) {
            root = node;
            return true;
        }
        return addToSub(root, node);
    }

    private boolean addToSub(Node sub, Node toAdd) {

        if (sub.key <= toAdd.key) {
            if (sub.rightChild == null) {
                sub.rightChild = toAdd;
                toAdd.parent = sub;
                return true;
            } else {
                return addToSub(sub.rightChild, toAdd);
            }
        }
        if (sub.key > toAdd.key) {
            if (sub.leftChild == null) {
                sub.leftChild = toAdd;
                toAdd.parent = sub;
                return true;
            } else {
                return addToSub(sub.leftChild, toAdd);
            }
        }
        return false;
    }

    private int initsize(Node node) {
        int l;
        if (node.leftChild == null) {
            l = 0;
        } else {
            l = initsize(node.leftChild);
        }
        int r;
        if (node.rightChild == null) {
            r = 0;
        } else {
            r = initsize(node.rightChild);
        }
        return node.size = r + l + 1;
    }

    public int median() {
        initsize(root);
        int mid = (root.size + 1) / 2;
        System.out.println("mid :" + mid);
        return ithOrderSatistic(root, mid);
    }

    public void inOrder(Node node) {
        if (node.hasLeftChild()) {
            inOrder(node.leftChild);
        }

        System.out.print(" " + node.key + " ");
        inorderIndex++;
        if (node.hasRightChild()) {
            inOrder(node.rightChild);
        }
    }

    public int ithOrder(int i) {
        inorderIndex = 1;
        if (root == null) {
            System.out.println("root is null");
        }
        return ithOrderSatistic(root, i);
    }

    private Integer ithOrderSatistic(Node node, int i) {
        if (node.hasLeftChild()) {
            Integer integer =
                    ithOrderSatistic(node.leftChild, i);
            if (integer != null) {
                return integer;
            }
        }
        if (inorderIndex == i) {
            return node.key;
        }
        inorderIndex++;
        if (node.hasRightChild()) {
            Integer integer =
                    ithOrderSatistic(node.rightChild, i);
            if (integer != null) {
                return integer;
            }
        }
        return null;
    }

    private Node getMaxOfSub(Node sub) {
        Node node = sub;
        while (node.rightChild != null) {
            node = node.rightChild;
        }
        return node;
    }

    public Node max() {
        return getMaxOfSub(root);
    }

    private void swap(Node node1, Node node2) {
        int temp = node1.key;
        node1.key = node2.key;
        node2.key = temp;
    }

    public Node predecessor(Node node) {
        if (node.leftChild != null) {
            return getMaxOfSub(node.leftChild);
        }
        return null;
    }

    public boolean delete(int key) {
        Node node = search(key);
        return delete(key);
    }

    public boolean delete(Node node) {
        if (node.leftChild == null && node.rightChild == null) {
            node = null;
            return true;
        }
        if (node.rightChild == null) {
            Node newNode = new Node();
            newNode.key = newNode.leftChild.key;
            newNode.parent = node.parent;
            newNode.leftChild.parent = newNode;
            newNode.leftChild = node.leftChild;
            return true;
        }
        if (node.leftChild == null) {
            Node newNode = new Node();
            newNode.key = newNode.rightChild.key;
            newNode.parent = node.parent;
            newNode.rightChild.parent = newNode;
            newNode.rightChild = node.rightChild;
            return true;
        }
        Node prede = predecessor(node);
        swap(node, prede);
        return delete(prede);


    }

}
