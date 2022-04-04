import java.util.InputMismatchException;
import java.util.Scanner;

public class UserModule {

    private static final Scanner scanner=new Scanner(System.in);
    private static final AVLTree avlTree=new AVLTree();

    public void start(){
        userAVLTree();
    }

    private void userBSTTree(){

    }

    private void userAVLTree(){
        createAVLTree();
        avlTree.printTree();
        boolean status =true;
        while (status){
            int value=menu();
            if (value==1){
                avlTree.minimumValue();
                avlTree.maximumValue();
            }else if (value==2){
                System.out.println("How many nodes do you want to remove?");
                int n=scanner.nextInt();
                for (int i=0;i<n;i++){
                    System.out.println("Give node value: ");
                    avlTree.delete(scanner.nextInt());
                }
            }else if (value==3){
                avlTree.inOrder();
            }else if (value==4){
                avlTree.preOrder();
            }else if (value==5){
                avlTree.deleteAll();
            }else if (value==6){
                System.out.println("Give node value: ");
                avlTree.preOrder(avlTree.find(scanner.nextInt()));
            }else if (value==7){
                avlTree.rebalance();
            }else if (value==8){
                status=false;
            }else {
                System.out.println("you give wrong sign, please try again.");
            }
        }
    }

    private int menu(){

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

    private void createAVLTree(){
        int n = 0;
        boolean bnError = true;
        try {
            do {
                System.out.println("Give array length from 1 to 10");
                n = scanner.nextInt();
            }while (n>10 || n<0);
            int[] a=new int[n];
        } catch (InputMismatchException e) {
            System.out.println("You gave a number in a wrong format");
            bnError = false;
        } catch (NegativeArraySizeException en){
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
            avlTree.createAVLTree(array);
        }

    }

}
