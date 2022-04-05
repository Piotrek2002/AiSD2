import java.util.InputMismatchException;
import java.util.Scanner;

public class UserModule {

    private static final Scanner scanner = new Scanner(System.in);
    private static final AVLTree avlTree = new AVLTree();
    private static final BSTTree bstTree = new BSTTree();

    public void start() {
        userAVLTree();
    }

    private void userAVLTree() {
        createTree();

        bstTree.printTree();
        avlTree.printTree();
        boolean status = true;
        while (status) {
            int value = menu();
            if (value == 1) {

                bstTree.minimumValue();
                bstTree.maximumValue();
                avlTree.minimumValue();
                avlTree.maximumValue();
            } else if (value == 2) {

                System.out.println("How many nodes do you want to remove?");
                int n = scanner.nextInt();
                for (int i = 0; i < n; i++) {
                    System.out.println("Give node value: ");
                    int a=scanner.nextInt();
                    bstTree.delete(a);
                    avlTree.delete(a);
                }
            } else if (value == 3) {

                bstTree.inOrder();
                avlTree.inOrder();
            } else if (value == 4) {

                bstTree.preOrder();
                avlTree.preOrder();
            } else if (value == 5) {

                bstTree.deleteAll();
                System.out.println();
                avlTree.deleteAll();
                System.out.println();
                System.out.println("select new tree");
                createTree();
            } else if (value == 6) {

                System.out.println("Give node value: ");
                int a=scanner.nextInt();
                bstTree.preOrder(bstTree.find(a));
                System.out.println();
                avlTree.preOrder(avlTree.find(a));
                System.out.println();
            } else if (value == 7) {

                bstTree.rebalance();
                avlTree.rebalance();
            } else if (value == 8) {
                status = false;
            } else {
                System.out.println("you give wrong sign, please try again.");
            }
        }
    }

    private int menu() {

        System.out.println("Menu: ");
        System.out.println("1 - Highest and lowest value of element:");
        System.out.println("2 - Remove element");
        System.out.println("3 - elements in-order");
        System.out.println("4 - elements pre-order");
        System.out.println("5 - remove all elements in tree");
        System.out.println("6 - find subtrees in pre-order");
        System.out.println("7 - tree balancing");
        System.out.println("8 - quit");

        return scanner.nextInt();
    }

    private void createTree() {
        int n = 0;
        boolean bnError = true;
        try {
            do {
                System.out.println("Give array length from 1 to 10");
                n = scanner.nextInt();
            } while (n > 10 || n < 0);
            int[] a = new int[n];
        } catch (InputMismatchException e) {
            System.out.println("You gave a number in a wrong format");
            bnError = false;
        } catch (NegativeArraySizeException en) {
            System.out.println("You gave a negative number");
            bnError = false;
        }

        if (bnError) {
            int[] array = new int[n];
            System.out.println("Give next " + n + " numbers");
            int i = 0;
            while (i < n && bnError) {
                try {
                    array[i] = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("You gave a number in a wrong format");
                    bnError = false;
                }
                i++;
            }
            long start1=System.nanoTime();
            avlTree.createAVLTree(array);
            long stop1=System.nanoTime();
            System.out.println("AVL: "+(stop1-start1));
            long start2=System.nanoTime();
            bstTree.createBSTTree(array);
            long stop2=System.nanoTime();
            System.out.println("BST: "+(stop2-start2));
        }

    }

}
