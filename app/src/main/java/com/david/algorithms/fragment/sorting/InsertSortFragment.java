package com.david.algorithms.fragment.sorting;

/**
 * Created by xingzheng on 2015/11/25.
 */
public class InsertSortFragment extends BaseSortFragment {

    @Override
    protected String getCodeAssetName() {
        return "insert_sort";
    }

    public void startSorting() {
        new SortAlgorithmThread() {

            @Override
            public void run() {
                for (int i = 1; i < content.length; i++) {
                    updateCodeAndSleep(0, 100);
                    for (int j = 0; j < i; j++) {
                        updateViewAndSleep(0);
                        updateViewAndSleep(i, 0);
                        updateCodeAndSleep(1, 100);
                        if (content[i] < content[j]) {
                            updateViewAndSleep(j, 0);
                            updateCodeAndSleep(2, 100);
                            int temp = content[i];
                            updateCodeAndSleep(3, 100);
                            for (int k = i; k > j; k--) {
                                updateViewAndSleep(k, 0);
                                updateCodeAndSleep(4, 100);
                                content[k] = content[k - 1];
                                updateCodeAndSleep(5, 100);
                            }
                            updateCodeAndSleep(6, 100);
                            content[j] = temp;
                            updateCodeAndSleep(7, 100);
                        }
                        updateCodeAndSleep(8, 100);
                    }
                    updateCodeAndSleep(9, 10);
                    updateViewAndSleep(500);
                }
                updateCodeAndSleep(10, 100);
                updateCodeAndSleep(-1, 0);
            }
        }.start();

    }
}
