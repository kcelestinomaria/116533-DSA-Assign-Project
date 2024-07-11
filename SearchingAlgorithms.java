public class SearchingAlgorithms {
    public static int linearSearch(Plant[] arr, String name) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(Plant[] arr, String name) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid].getName().compareTo(name) == 0) {
                return mid;
            } else if (arr[mid].getName().compareTo(name) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
