package com.david.algorithms.fragment.sorting;

import android.os.Message;

/**
 * Created by xingzheng on 2015/11/25.
 */
public class MergeSortFragment extends BaseSortFragment {
    @Override
    protected String getCodeAssetName() {
        return "merge_sort";
    }

    public void startSorting() {
        new SortAlgorithmThread() {
            @Override
            public void run() {
                for (int i = 1; i < content.length; i *= 2) {
                    updateAndSleep(0, 100);
                    mergeSort(content, i, content.length);
                    updateAndSleep(1, 100);
                }
                updateAndSleep(2, 100);
                weakHandler.sendEmptyMessage(6);
            }

            private void mergeSort(int[] originArray, int gap, int length) {
                int i;
                updateAndSleep(3, 100);
                for (i = 0; i + 2 * gap <= length; i += 2 * gap) {
                    updateAndSleep(4, 100);
                    merge(originArray, i, i + gap - 1, i + 2 * gap - 1);
                    updateAndSleep(5, 100);
                }
                updateAndSleep(6, 100);
                if (i + gap < length) {
                    updateAndSleep(7, 100);
                    merge(originArray, i, i + gap - 1, length - 1);
                    updateAndSleep(8, 100);
                }
                updateAndSleep(9, 100);
            }

            private void merge(int[] originArray, int low, int mid, int high) {
                int i = low;
                updateAndSleep(10, 100);
                int j = mid + 1;
                updateAndSleep(11, 100);
                int k = 0;
                updateAndSleep(12, 100);
                int tempArray[] = new int[high - low + 1];
                updateAndSleep(13, 100);
                while (i <= mid && j <= high) {
                    updateAndSleep(14, 100);
                    if (originArray[i] < originArray[j]) {
                        updateAndSleep(15, 100);
                        tempArray[k] = originArray[i];
                        updateAndSleep(16, 100);
                        i++;
                        updateAndSleep(17, 100);
                    } else {
                        updateAndSleep(18, 100);
                        tempArray[k] = originArray[j];
                        updateAndSleep(19, 100);
                        j++;
                        updateAndSleep(20, 100);
                    }
                    updateAndSleep(21, 100);
                    k++;
                    updateAndSleep(22, 100);
                }
                updateAndSleep(23, 100);
                while (i <= mid) {
                    updateAndSleep(24, 100);
                    tempArray[k] = originArray[i];
                    updateAndSleep(25, 100);
                    i++;
                    updateAndSleep(26, 100);
                    k++;
                    updateAndSleep(27, 100);
                }
                updateAndSleep(28, 100);
                while (j <= high) {
                    updateAndSleep(29, 100);
                    tempArray[k] = originArray[j];
                    updateAndSleep(30, 100);
                    j++;
                    updateAndSleep(31, 100);
                    k++;
                    updateAndSleep(32, 100);
                }
                updateAndSleep(33, 100);
                for (int m = low; m <= high; m++) {
                    updateAndSleep(34, 100);
                    originArray[m] = tempArray[m - low];
                    updateAndSleep(35, 100);
                }
                updateAndSleep(36, 100);
                updateAndSleep(100, 100);
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
                sorting_list_code_container.setSelection(msg.what);
                sortCodeListAdapter.setSelection(msg.what);
                break;
        }
    }

}
