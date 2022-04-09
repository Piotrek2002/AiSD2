import org.apache.commons.lang.ArrayUtils;

import java.util.*;

public class GeneratedArrays {
    private final List<int[]> descendingArrays = new ArrayList<>();

    public GeneratedArrays(int size, int max) {

        Random random = new Random();
        for (int i = 0; i < 10; ++i) {
            Set<Integer> set = new TreeSet<>();
            while (set.size() < size) {
                set.add(random.nextInt(max) + 1);
            }
            int[] tab = new int[size];
            int j=0;
            for (int value : set.stream().toList()) {
                tab[j]=value;
                j++;
            }
            int[] sorted = Arrays.stream(tab).sorted().toArray();
            ArrayUtils.reverse(sorted);
            descendingArrays.add(sorted);

        }
    }

    public List<int[]> getDescendingArrays() {
        return descendingArrays;
    }

}
