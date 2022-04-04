import static java.lang.Math.max;

public class AVLTree{

    private Node root = new Node();

    private Node insert(Node node, int key) {
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

    public Node find(int key) {
        Node current = root;
        while (current != null) {
            if (current.key == key) {
                break;
            }
            current = current.key < key ? current.right : current.left;
        }
        return current;
    }

    private Node delete(Node node, int key) {
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

    private Node mostLeftChild(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }
    void minimumValue(){
        System.out.println(mostLeftChild(root).key);
    }
    private Node mostRightChild(Node node) {
        Node current = node;
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }
    void maximumValue(){
        System.out.println(mostRightChild(root).key);
    }

    private void updateHeight(Node node) {
        int leftChildHeight = height(node.left);
        int rightChildHeight = height(node.right);
        node.height = max(leftChildHeight, rightChildHeight) + 1;
    }

    private int getBalance(Node node) {
        return height(node.right) - height(node.left);
    }

    private int height(Node node) {
        return node != null ? node.height : -1;
    }

    private Node rebalance(Node node) {
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
        rebalance(root);
        preOrder();
    }

    private Node rotateRight(Node node) {
        Node leftChild = node.left;

        node.left = leftChild.right;
        leftChild.right = node;

        updateHeight(node);
        updateHeight(leftChild);

        return leftChild;
    }

    private Node rotateLeft(Node node) {
        Node rightChild = node.right;

        node.right = rightChild.left;
        rightChild.left = node;

        updateHeight(node);
        updateHeight(rightChild);

        return rightChild;
    }

    void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    void preOrder() {
        preOrder(root);
        System.out.println();
    }

    private void inOrder(Node head)
    {
        if (head != null)
        {
            inOrder(head.left);
            System.out.print(head.key+" ");
            inOrder(head.right);
        }
    }

    public void inOrder()
    {
        inOrder(root);
        System.out.println();
    }

    public void deleteAll()
    {
        deleteAll(root);
        root=null;
    }

    private void deleteAll(Node head)
    {
        if (head != null)
        {
            deleteAll(head.left);
            deleteAll(head.right);
            System.out.print(head.key+" ");
        }
    }

    public void createAVLTree(int[] array){
        root.key=array[0];
        for (int i=1;i<array.length;i++){
            insert(root,array[i]);
        }
    }

    void printTree(Node currPtr, String indent, boolean last) {
        if (currPtr != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }
            System.out.println(currPtr.key);
            printTree(currPtr.left, indent, false);
            printTree(currPtr.right, indent, true);
        }
    }

    void printTree() {
        printTree(root, "", true);
    }
}
