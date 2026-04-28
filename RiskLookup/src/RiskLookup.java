import java.util.*;

public class RiskLookup {

    // ---------------- Linear Search ----------------
    public static void linearSearch(int[] arr, int target) {
        int comparisons = 0;
        boolean found = false;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                System.out.println("Linear: Found at index " + i);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Linear: Not found");
        }

        System.out.println("Comparisons: " + comparisons);
    }

    // ---------------- Binary Search (Insertion Point) ----------------
    public static int binaryInsertionPoint(int[] arr, int target, Counter counter) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            counter.count++;

            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) low = mid + 1;
            else high = mid - 1;
        }

        return low; // insertion index
    }

    // ---------------- Floor (largest ≤ target) ----------------
    public static Integer floor(int[] arr, int target, Counter counter) {
        int low = 0, high = arr.length - 1;
        Integer result = null;

        while (low <= high) {
            int mid = (low + high) / 2;
            counter.count++;

            if (arr[mid] == target) return arr[mid];
            else if (arr[mid] < target) {
                result = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    // ---------------- Ceiling (smallest ≥ target) ----------------
    public static Integer ceiling(int[] arr, int target, Counter counter) {
        int low = 0, high = arr.length - 1;
        Integer result = null;

        while (low <= high) {
            int mid = (low + high) / 2;
            counter.count++;

            if (arr[mid] == target) return arr[mid];
            else if (arr[mid] > target) {
                result = arr[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }

    // Counter for comparisons
    static class Counter {
        int count = 0;
    }

    // ---------------- Main ----------------
    public static void main(String[] args) {

        int[] risks = {10, 25, 50, 100}; // sorted
        int target = 30;

        // Linear Search (unsorted concept)
        linearSearch(risks, target);

        // Binary Search operations
        Counter counter = new Counter();

        int insertionIndex = binaryInsertionPoint(risks, target, counter);
        Integer floor = floor(risks, target, counter);
        Integer ceiling = ceiling(risks, target, counter);

        System.out.println("\nBinary Search:");
        System.out.println("Insertion index: " + insertionIndex);
        System.out.println("Floor: " + floor);
        System.out.println("Ceiling: " + ceiling);
        System.out.println("Comparisons: " + counter.count);
    }
}