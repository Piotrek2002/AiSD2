import java.util.ArrayList;
import java.util.List;

public class GeneratorModule {

    public void print() {
        List<GeneratedArrays> generatedArraysList = new ArrayList<>();
        generatedArraysList.add(new GeneratedArrays(10, 100));
        generatedArraysList.add(new GeneratedArrays(100, 1000));
        generatedArraysList.add(new GeneratedArrays(1000, 10000));
        generatedArraysList.add(new GeneratedArrays(2000, 20000));
        generatedArraysList.add(new GeneratedArrays(3000, 30000));
        generatedArraysList.add(new GeneratedArrays(4000, 40000));
        generatedArraysList.add(new GeneratedArrays(5000, 50000));
        generatedArraysList.add(new GeneratedArrays(6000, 60000));
        generatedArraysList.add(new GeneratedArrays(7000, 70000));
        generatedArraysList.add(new GeneratedArrays(8000, 80000));
        generatedArraysList.add(new GeneratedArrays(9000, 90000));
        generatedArraysList.add(new GeneratedArrays(10000, 100000));

        System.out.println("Time AVL: ");
        generatedArraysList.forEach(l -> createAVL(l.getDescendingArrays()));
        System.out.println("Time BST: ");
        generatedArraysList.forEach(l -> createBST(l.getDescendingArrays()));
    }

    private void createAVL(List<int[]> generatedArrays) {
        long sum = 0;
        long inorderSum=0;
        long sumMin=0;
        List<Long> timeList = new ArrayList<>();
        List<Long> timeListMin = new ArrayList<>();
        List<Long> timeListInOrder = new ArrayList<>();
        AVLTree avlTree = new AVLTree();
        long start = System.nanoTime();
        avlTree.createAVLTree(generatedArrays.get(0).clone());
        long stop = System.nanoTime();

        for (int i = 0; i < 10; i++) {

            start = System.nanoTime();
            avlTree.createAVLTree(generatedArrays.get(i).clone());
            stop = System.nanoTime();
            sum = sum + (stop - start);
            timeList.add(stop - start);

            start = System.nanoTime();
            avlTree.minimumValue();
            stop = System.nanoTime();
            sumMin = sumMin + (stop - start);
            timeListMin.add(stop - start);

            start = System.nanoTime();
            avlTree.inOrder();
            stop = System.nanoTime();
            inorderSum = inorderSum + (stop - start);
            timeListInOrder.add(stop - start);
        }
        System.out.println("create: elements: " + generatedArrays.get(0).length + ", time: " + (sum / 10) + ", standard deviation: " +
                standardDeviation(timeList, sum / 10));
        System.out.println("inOrder: elements: " + generatedArrays.get(0).length + ", time: " + (inorderSum / 10) + ", standard deviation: " +
                standardDeviation(timeListInOrder, sum / 10));
        System.out.println("Minimum: elements: " + generatedArrays.get(0).length + ", time: " + (sumMin / 10) + ", standard deviation: " +
                standardDeviation(timeListMin, sum / 10));
    }

    private void createBST(List<int[]> generatedArrays) {
        long sum = 0;
        long inorderSum=0;
        long sumMin=0;
        List<Long> timeList = new ArrayList<>();
        List<Long> timeListMin = new ArrayList<>();
        List<Long> timeListInOrder = new ArrayList<>();
        BSTTree bstTree = new BSTTree();
        long start = System.nanoTime();
        bstTree.createBSTTree(generatedArrays.get(0).clone());
        long stop = System.nanoTime();

        for (int i = 0; i < 10; i++) {

            start = System.nanoTime();
            bstTree.createBSTTree(generatedArrays.get(i).clone());
            stop = System.nanoTime();
            sum = sum + (stop - start);
            timeList.add(stop - start);

            start = System.nanoTime();
            bstTree.minimumValue();
            stop = System.nanoTime();
            sumMin = sumMin + (stop - start);
            timeListMin.add(stop - start);

            start = System.nanoTime();
            bstTree.inOrder();
            stop = System.nanoTime();
            inorderSum = inorderSum + (stop - start);
            timeListInOrder.add(stop - start);
        }
        System.out.println("create: elements: " + generatedArrays.get(0).length + ", time: " + (sum / 10) + ", standard deviation: " +
                standardDeviation(timeList, sum / 10));
        System.out.println("inOrder: elements: " + generatedArrays.get(0).length + ", time: " + (inorderSum / 10) + ", standard deviation: " +
                standardDeviation(timeListInOrder, sum / 10));
        System.out.println("Minimum: elements: " + generatedArrays.get(0).length + ", time: " + (sumMin / 10) + ", standard deviation: " +
                standardDeviation(timeListMin, sum / 10));
    }


    private long standardDeviation(List<Long> list, long avg) {
        long sumOfSquares = 0;
        for (long value : list) {
            sumOfSquares += Math.pow(value - avg, 2);
        }
        return (long) Math.sqrt(sumOfSquares / list.size());
    }
}
