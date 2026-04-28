import java.util.*;

public class AccountSearch {

    // ---------------- Linear Search ----------------
    public static void linearSearch(String[] arr, String target) {
        int first = -1, last = -1;
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                if (first == -1) first = i;
                last = i;
            }
        }

        System.out.println("Linear Search:");
        System.out.println("First occurrence: " + first);
        System.out.println("Last occurrence: " + last);
        System.out.println("Comparisons: " + comparisons);
    }

    // ---------------- Binary Search (Find one occurrence) ----------------
    public static int binarySearch(String[] arr, String target, Counter counter) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            counter.count++;

            int cmp = arr[mid].compareTo(target);

            if (cmp == 0) return mid;
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }

        return -1;
    }

    // ---------------- Count Occurrences using Binary Search ----------------
    public static int countOccurrences(String[] arr, String target, Counter counter) {
        int index = binarySearch(arr, target, counter);
        if (index == -1) return 0;

        int count = 1;

        // left side
        int left = index - 1;
        while (left >= 0 && arr[left].equals(target)) {
            counter.count++;
            count++;
            left--;
        }

        // right side
        int right = index + 1;
        while (right < arr.length && arr[right].equals(target)) {
            counter.count++;
            count++;
            right++;
        }

        return count;
    }

    // Helper class to track comparisons
    static class Counter {
        int count = 0;
    }

    // ---------------- Main ----------------
    public static void main(String[] args) {

        String[] logs = {"accA", "accB", "accB", "accC"}; // sorted

        String target = "accB";

        // Linear Search
        linearSearch(logs, target);

        // Binary Search + Count
        Counter counter = new Counter();
        int index = binarySearch(logs, target, counter);
        int count = countOccurrences(logs, target, counter);

        System.out.println("\nBinary Search:");
        System.out.println("Found at index: " + index);
        System.out.println("Total occurrences: " + count);
        System.out.println("Comparisons: " + counter.count);
    }
}