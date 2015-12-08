package com.david.algorithms.fragment.sorting;

/**
 * Created by xingzheng on 2015/11/25.
 */
public class BubbleSortFragment extends BaseSortFragment {

    @Override
    protected String getCodeAssetName() {
        return "bubble_sort";
    }

    public void startSorting() {
        new SortAlgorithmThread() {
            @Override
            public void run() {
                for (int i = 0; i < content.length - 1; i++) {
                    updateCodeAndSleep(0, 100);
                    for (int j = 0; j < content.length - i - 1; j++) {
                        updateCodeAndSleep(1, 100);
                        select(j);
                        if (content[j] > content[j + 1]) {
                            updateCodeAndSleep(2, 100);
                            select(j+1);
                            int temp = content[j];
                            updateCodeAndSleep(3, 100);
                            content[j] = content[j + 1];
                            updateCodeAndSleep(4, 100);
                            content[j + 1] = temp;
                            updateCodeAndSleep(5, 100);
                            swap(j,j+1);
                            unselect(j+1);
                        }
                        unselect(j);
                        updateCodeAndSleep(6, 100);
                    }
                    fixPosition(content.length-1-i);
                    updateCodeAndSleep(7, 100);
                }
                updateCodeAndSleep(8, 100);
                updateCodeAndSleep(-1, 100);
            }
        }.start();

    }

}
