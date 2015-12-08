package com.david.algorithms.fragment.sorting;

/**
 * Created by xingzheng on 2015/11/25.
 */
public class CountSortFragment extends BaseSortFragment {
    @Override
    protected String getCodeAssetName() {
        return "count_sort";
    }

    @Override
    protected void startSorting() {
        new SortAlgorithmThread() {
            @Override
            public void run() {
                int[] tempArray = new int[content.length];
                int max = content[0];
                int min = content[0];
                for (int i : content) {
                    if (i > max) {
                        max = i;
                    }
                    if (i < min) {
                        min = i;
                    }
                }
                int k = max - min + 1;
                int[] countArray = new int[k];
                for (int aContent : content) {
                    countArray[aContent - min] += 1;
                }
                for (int i = 1; i < countArray.length; i++) {
                    countArray[i] = countArray[i] + countArray[i - 1];
                }
                for (int i = content.length - 1; i >= 0; i--) {
                    tempArray[--countArray[content[i] - min]] = content[i];
                }
                System.arraycopy(tempArray, 0, content, 0, content.length);
                updateViewAndSleep(100);
            }
        }.start();

    }
}
