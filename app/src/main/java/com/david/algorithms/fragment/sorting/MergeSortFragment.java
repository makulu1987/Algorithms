package com.david.algorithms.fragment.sorting;

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
                    updateCodeAndSleep(0, 100);
                    mergeSort(content, i, content.length);
                }
                updateCodeAndSleep(36, 100);
                updateCodeAndSleep(-1, 0);
            }

            private void mergeSort(int[] originArray, int gap, int length) {
                int i;
                updateCodeAndSleep(2, 100);
                for (i = 0; i + 2 * gap <= length; i += 2 * gap) {
                    updateCodeAndSleep(3, 100);
                    merge(originArray, i, i + gap - 1, i + 2 * gap - 1);
                    updateCodeAndSleep(31, 100);
                }
                updateCodeAndSleep(32, 100);
                if (i + gap < length) {
                    updateCodeAndSleep(33, 100);
                    merge(originArray, i, i + gap - 1, length - 1);
                    updateCodeAndSleep(34, 100);
                }
                updateCodeAndSleep(35, 100);
            }

            private void merge(int[] originArray, int low, int mid, int high) {
                int i = low;
                updateCodeAndSleep(4, 100);
                int j = mid + 1;
                updateCodeAndSleep(5, 100);
                int k = 0;
                updateCodeAndSleep(6, 100);
                int tempArray[] = new int[high - low + 1];
                updateCodeAndSleep(7, 100);
                while (i <= mid && j <= high) {
                    updateViewAndSleep(0);
                    updateViewAndSleep(i, 0);
                    updateViewAndSleep(j, 0);
                    updateViewAndSleep(k, 0);
                    updateCodeAndSleep(8, 100);
                    if (originArray[i] < originArray[j]) {
                        updateCodeAndSleep(9, 100);
                        tempArray[k] = originArray[i];
                        updateCodeAndSleep(10, 100);
                        i++;
                        updateCodeAndSleep(11, 100);
                    } else {
                        updateCodeAndSleep(12, 100);
                        tempArray[k] = originArray[j];
                        updateCodeAndSleep(13, 100);
                        j++;
                        updateCodeAndSleep(14, 100);
                    }
                    updateCodeAndSleep(15, 100);
                    k++;
                    updateCodeAndSleep(16, 100);
                }
                updateCodeAndSleep(17, 100);
                while (i <= mid) {
                    updateViewAndSleep(0);
                    updateViewAndSleep(i, 0);
                    updateViewAndSleep(k, 0);
                    updateCodeAndSleep(18, 100);
                    tempArray[k] = originArray[i];
                    updateCodeAndSleep(19, 100);
                    i++;
                    updateCodeAndSleep(20, 100);
                    k++;
                    updateCodeAndSleep(21, 100);
                }
                updateCodeAndSleep(22, 100);
                while (j <= high) {
                    updateViewAndSleep(0);
                    updateViewAndSleep(j, 0);
                    updateViewAndSleep(k, 0);
                    updateCodeAndSleep(23, 100);
                    tempArray[k] = originArray[j];
                    updateCodeAndSleep(24, 100);
                    j++;
                    updateCodeAndSleep(25, 100);
                    k++;
                    updateCodeAndSleep(26, 100);
                }
                updateCodeAndSleep(27, 100);
                for (int m = low; m <= high; m++) {
                    updateViewAndSleep(0);
                    updateViewAndSleep(m, 0);
                    updateViewAndSleep(m - low, 0);
                    updateCodeAndSleep(28, 100);
                    originArray[m] = tempArray[m - low];
                    updateCodeAndSleep(29, 100);
                }
                updateCodeAndSleep(30, 100);
                updateViewAndSleep(100);
            }

        }.start();

    }


}
