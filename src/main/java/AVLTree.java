import java.util.Arrays;

import static java.lang.Math.max;

public class AVLTree extends Tree{

    public AVLTree() {
        super();
    }

    public Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        } else if (node.key > key) {
            node.left = insert(node.left, key);
        } else if (node.key < key) {
            node.right = insert(node.right, key);
        } else {
            throw new RuntimeException("duplicate Key!");
        }
        return rebalance(node);
    }

    public Node delete(Node node, int key) {
        if (node == null) {
            return node;
        } else if (node.key > key) {
            node.left = delete(node.left, key);
        } else if (node.key < key) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left == null) ? node.right : node.left;
            } else {
                Node mostLeftChild = mostLeftChild(node.right);
                node.key = mostLeftChild.key;
                node.right = delete(node.right, node.key);
            }
        }
        if (node != null) {
            node = rebalance(node);
        }
        return node;
    }

    public void delete(int key){
        delete(root,key);
    }

    Node sortedArrayToAVL(int[] array, int start, int end) {

        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        Node node = new Node(array[mid]);

        node.left = sortedArrayToAVL(array, start, mid - 1);

        node.right = sortedArrayToAVL(array, mid + 1, end);

        return node;
    }

    public void createAVLTree(int[] array){
        root=sortedArrayToAVL(Arrays.stream(array).sorted().toArray(),0,array.length-1);
    }
    public Node rebalance(Node node) {
        updateHeight(node);
        int balanceFactor = getBalance(node);
        if (balanceFactor < -1) {
            if (getBalance(node.left) <= 0) {
                node = rotateRight(node);
            } else {
                node.left = rotateLeft(node.left);
                node = rotateRight(node);
            }
        }

        if (balanceFactor > 1) {
            if (getBalance(node.right) >= 0) {
                node = rotateLeft(node);
            } else {
                node.right = rotateRight(node.right);
                node = rotateLeft(node);
            }
        }
        return node;
    }

    void rebalance() {
        preOrder();
        root=rebalance(root);
        preOrder();
    }

    public void updateHeight(Node node) {
        int leftChildHeight = height(node.left);
        int rightChildHeight = height(node.right);
        node.height = max(leftChildHeight, rightChildHeight) + 1;
    }

    public Node rotateRight(Node node) {
        Node leftChild = node.left;

        node.left = leftChild.right;
        leftChild.right = node;

        updateHeight(node);
        updateHeight(leftChild);

        return leftChild;
    }

    public Node rotateLeft(Node node) {
        Node rightChild = node.right;

        node.right = rightChild.left;
        rightChild.left = node;

        updateHeight(node);
        updateHeight(rightChild);

        return rightChild;
    }
}
