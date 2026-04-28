import java.util.*;

class Asset {
    String name;
    double returnRate;
    double volatility;

    Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    @Override
    public String toString() {
        return name + ":" + returnRate + "% (" + volatility + ")";
    }
}

public class PortfolioSorter {

    // ---------------- Merge Sort (Stable, Ascending Return) ----------------
    public static void mergeSort(Asset[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(Asset[] arr, int left, int mid, int right) {
        Asset[] temp = new Asset[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            // Stable: <= preserves order
            if (arr[i].returnRate <= arr[j].returnRate) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        System.arraycopy(temp, 0, arr, left, temp.length);
    }

    // ---------------- Quick Sort (DESC return + ASC volatility) ----------------
    public static void quickSort(Asset[] arr, int low, int high) {
        if (low < high) {

            // Hybrid: use insertion sort for small partitions
            if (high - low < 10) {
                insertionSort(arr, low, high);
                return;
            }

            int pivotIndex = medianOfThree(arr, low, high);
            swap(arr, pivotIndex, high);

            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(Asset[] arr, int low, int high) {
        Asset pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(arr[j], pivot) < 0) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    // Comparator: DESC return, ASC volatility
    private static int compare(Asset a, Asset b) {
        if (a.returnRate != b.returnRate) {
            return Double.compare(b.returnRate, a.returnRate);
        }
        return Double.compare(a.volatility, b.volatility);
    }

    // ---------------- Insertion Sort (Hybrid) ----------------
    private static void insertionSort(Asset[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            Asset key = arr[i];
            int j = i - 1;

            while (j >= low && compare(arr[j], key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // ---------------- Pivot Selection ----------------

    // Random Pivot
    private static int randomPivot(int low, int high) {
        return low + new Random().nextInt(high - low + 1);
    }

    // Median-of-3 Pivot
    private static int medianOfThree(Asset[] arr, int low, int high) {
        int mid = (low + high) / 2;

        if (arr[low].returnRate > arr[mid].returnRate) swap(arr, low, mid);
        if (arr[low].returnRate > arr[high].returnRate) swap(arr, low, high);
        if (arr[mid].returnRate > arr[high].returnRate) swap(arr, mid, high);

        return mid;
    }

    // ---------------- Utility ----------------
    private static void swap(Asset[] arr, int i, int j) {
        Asset temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // ---------------- Main ----------------
    public static void main(String[] args) {

        Asset[] assets = {
                new Asset("AAPL", 12, 5),
                new Asset("TSLA", 8, 9),
                new Asset("GOOG", 15, 4)
        };

        // Merge Sort
        Asset[] mergeArr = assets.clone();
        mergeSort(mergeArr, 0, mergeArr.length - 1);
        System.out.println("Merge Sort (Ascending): " + Arrays.toString(mergeArr));

        // Quick Sort
        Asset[] quickArr = assets.clone();
        quickSort(quickArr, 0, quickArr.length - 1);
        System.out.println("Quick Sort (Descending): " + Arrays.toString(quickArr));
    }
}