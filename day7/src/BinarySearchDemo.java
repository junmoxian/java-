import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinarySearchDemo {
    public static void main(String [] args){
        List<String> copy = new ArrayList<>();
        copy.add("a");
        copy.add("c");
        copy.add("b");
        copy.add("d");

        Collections.sort(copy);
        System.out.println(copy);
        int index = Collections.binarySearch(copy, "b");
        System.out.println("Index of 'b': " + index);

    }
}
