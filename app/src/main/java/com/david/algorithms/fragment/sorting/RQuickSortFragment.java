package com.david.algorithms.fragment.sorting;

import android.os.Message;

/**
 * Created by xingzheng on 2015/11/25.
 */
public class RQuickSortFragment extends BaseSortFragment {
    @Override
    protected String getCodeAssetName() {
        return "random_quick_sort";
    }

    public void startSorting() {
        new SortAlgorithmThread() {
            @Override
            public void run() {
                rQuickSort(content, 0, content.length - 1);
                updateAndSleep(100, 100);
                weakHandler.sendEmptyMessage(6);
            }

            private void rQuickSort(int[] array, int low, int high) {
                if (high <= low) {
                    return;
                }
                int r = random.nextInt(high - low) + low;
                swap(array, r, low);
                int key = partition(array, low, high);
                rQuickSort(array, 0, key - 1);
                rQuickSort(array, key + 1, high);
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

    @Override
    protected void handleMessageIml(Message msg) {
        switch (msg.what) {
            case 100:
                updateContent();
                break;
            default:
//                sorting_list_code_container.setSelection(msg.what);
//                sortCodeListAdapter.setSelection(msg.what);
                break;
        }
    }
}
