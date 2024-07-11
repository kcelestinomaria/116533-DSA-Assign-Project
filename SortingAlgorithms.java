import java.util.Arrays;

public class SortingAlgorithms {
    public static void bubbleSort(Plant[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j].getName().compareTo(arr[j + 1].getName()) > 0) {
                    Plant temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void insertionSort(Plant[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            Plant key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].getName().compareTo(key.getName()) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void selectionSort(Plant[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j].getName().compareTo(arr[minIdx].getName()) < 0) {
                    minIdx = j;
                }
            }
            Plant temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    public static void mergeSort(Plant[] arr) {
        if (arr.length > 1) {
            Plant[] left = Arrays.copyOfRange(arr, 0, arr.length / 2);
            Plant[] right = Arrays.copyOfRange(arr, arr.length / 2, arr.length);

            mergeSort(left);
            mergeSort(right);

            int i = 0, j = 0, k = 0;
            while (i < left.length && j < right.length) {
                if (left[i].getName().compareTo(right[j].getName()) <= 0) {
                    arr[k++] = left[i++];
                } else {
                    arr[k++] = right[j++];
                }
            }

            while (i < left.length) {
                arr[k++] = left[i++];
            }

            while (j < right.length) {
                arr[k++] = right[j++];
            }
        }
    }

    public static void quickSort(Plant[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(Plant[] arr, int low, int high) {
        Plant pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j].getName().compareTo(pivot.getName()) < 0) {
                i++;
                Plant temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Plant temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
}
