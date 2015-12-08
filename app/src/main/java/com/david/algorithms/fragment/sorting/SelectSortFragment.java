package com.david.algorithms.fragment.sorting;

/**
 * Created by xingzheng on 2015/11/25.
 */
public class SelectSortFragment extends BaseSortFragment {

    @Override
    protected String getCodeAssetName() {
        return "select_sort";
    }

    public void startSorting() {
        new SortAlgorithmThread() {

            @Override
            public void run() {
                for (int i = 0; i < content.length - 1; i++) {
                    updateCodeAndSleep(0, 100);
                    int selection = i;
                    select(selection);
                    updateCodeAndSleep(1, 100);
                    for (int j = selection + 1; j < content.length; j++) {
                        select(j);
                        updateCodeAndSleep(2, 100);
                        if (content[selection] > content[j]) {
                            updateCodeAndSleep(3, 0);
                            updateViewAndSleep(j, 200);
                            if(selection!=i){
                                unselect(selection);
                            }
                            selection = j;
                            updateCodeAndSleep(4, 100);
                        }else{
                            unselect(j);
                        }
                        updateCodeAndSleep(5, 100);
                    }
                    updateCodeAndSleep(6, 100);
                    if (selection != i) {
                        updateCodeAndSleep(7, 100);
                        int temp = content[i];
                        updateCodeAndSleep(8, 100);
                        content[i] = content[selection];
                        updateCodeAndSleep(9, 100);
                        content[selection] = temp;
                        updateCodeAndSleep(10, 100);
                        swap(selection,i);
                    }
                    unselect(selection,i);
                    updateCodeAndSleep(11, 100);
                }
                updateCodeAndSleep(12, 100);
                updateCodeAndSleep(-1, 100);
            }
        }.start();

    }

}
