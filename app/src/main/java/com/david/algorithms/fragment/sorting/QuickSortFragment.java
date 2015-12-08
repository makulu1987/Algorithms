package com.david.algorithms.fragment.sorting;

/**
 * Created by xingzheng on 2015/11/25.
 */
public class QuickSortFragment extends BaseSortFragment {
    @Override
    protected String getCodeAssetName() {
        return "quick_sort";
    }

    public void startSorting() {
        new SortAlgorithmThread() {
            @Override
            public void run() {
                quickSort(content, 0, content.length - 1);
                updateViewAndSleep(0);
            }

            private void quickSort(int[] array, int low, int high) {
                if (high <= low) {
                    return;
                }
                int key = partition(array, low, high);
                quickSort(array, 0, key - 1);
                quickSort(array, key + 1, high);
            }

            private int partition(int[] array, int low, int high) {
                int key = array[low];
                int j = high;
                int i = low;
                while (i != j) {
                    while (array[j] >= key && i < j) {
                        j--;
                    }
                    while (array[i] <= key && i < j) {
                        i++;
                    }
                    if (i < j) {
                        swap(array, i, j);
                    }
                }
                array[low] = array[i];
                array[i] = key;
                return i;
            }

            private void swap(int[] array, int first, int second) {
                int temp = array[first];
                array[first] = array[second];
                array[second] = temp;
            }
        }.start();

    }

}
