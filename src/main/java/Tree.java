import java.util.Arrays;

import static java.lang.Math.max;

public class Tree {
    Node root = new Node();

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

    public Node mostLeftChild(Node node) {
        Node current = node;
//        System.out.print("path: "+current.key + " ");
        while (current.left != null) {
            current = current.left;
//            System.out.print(current.key + " ");
        }
//        System.out.println();
        return current;
    }
    void minimumValue(){

    }
    public Node mostRightChild(Node node) {
        Node current = node;
        System.out.print("path: "+current.key + " ");
        while (current.right != null) {
            current = current.right;
            System.out.print(current.key + " ");
        }
        System.out.println();
        return current;
    }
    void maximumValue(){
        System.out.println("max: "+mostRightChild(root).key);
    }

    public int getBalance(Node node) {
        return height(node.right) - height(node.left);
    }

    public int height(Node node) {
        if (node != null) return node.height;
        return -1;
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

    public void inOrder(Node head)
    {
        if (head != null)
        {
            inOrder(head.left);
            inOrder(head.right);
        }
    }

    public void inOrder()
    {
        inOrder(root);
    }

    public void deleteAll(Node head)
    {
        if (head != null)
        {
            deleteAll(head.left);
            deleteAll(head.right);
            head.left=null;
            head.right=null;
            System.out.print(head.key+" ");
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

    public void deleteAll()
    {
        deleteAll(root);
        root=new Node();
    }
}
