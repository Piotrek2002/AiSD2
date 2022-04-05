import java.util.ArrayList;

public class BSTTree extends Tree{

    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    public Node deleteRecursive(Node current, int value) {
        if (current == null) {
            return null;
        }

        if (value == current.key) {
            if (current.left == null && current.right == null) {
                return null;
            }

            if (current.right == null) {
                return current.left;
            }

            if (current.left == null) {
                return current.right;
            }

            int smallestValue = mostLeftChild(current.right).key;
            current.key = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        }
        if (value < current.key) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }

        current.right = deleteRecursive(current.right, value);
        return current;
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
        return node;
    }
    public void createBSTTree(int[] array){
        root.key=array[0];
        for (int i=1;i<array.length;i++){
            insert(root,array[i]);
        }
    }

    private static void storeInOrderTraversal(Node root, ArrayList<Integer> inOrderNodes) {
        if (root != null) {
            storeInOrderTraversal(root.left, inOrderNodes);
            inOrderNodes.add(root.key);
            storeInOrderTraversal(root.right, inOrderNodes);
        }
    }
    private static Node convertSortedArrayToBalancedBST(ArrayList<Integer> inOrderNodes, int start, int end) {

        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        Node root = new Node(inOrderNodes.get(mid));
        root.left = convertSortedArrayToBalancedBST(inOrderNodes, start, mid - 1);
        root.right = convertSortedArrayToBalancedBST(inOrderNodes, mid + 1, end);
        return root;
    }
    private static Node convertToBalancedBST(Node root) {
        ArrayList<Integer> inOrderNodes = new ArrayList<>();
        storeInOrderTraversal(root, inOrderNodes);
        return convertSortedArrayToBalancedBST(inOrderNodes, 0, inOrderNodes.size() - 1);
    }

    void rebalance() {
        preOrder();
        root=convertToBalancedBST(root);
        preOrder();
    }
}
