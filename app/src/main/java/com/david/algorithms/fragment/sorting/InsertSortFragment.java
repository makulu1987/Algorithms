package com.david.algorithms.fragment.sorting;

import android.os.Message;

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
                    updateAndSleep(0, 100);
                    for (int j = 0; j < i; j++) {
                        updateAndSleep(1, 100);
                        if (content[i] < content[j]) {
                            updateAndSleep(2, 100);
                            int temp = content[i];
                            updateAndSleep(3, 100);
                            for (int k = i; k > j; k--) {
                                updateAndSleep(4, 100);
                                content[k] = content[k - 1];
                                updateAndSleep(5, 100);
                            }
                            updateAndSleep(6, 100);
                            content[j] = temp;
                            updateAndSleep(7, 100);
                        }
                        updateAndSleep(8, 100);
                    }
                    updateAndSleep(9, 10);
                    updateAndSleep(11, 500);
                }
                updateAndSleep(10, 100);
                updateAndSleep(-1, 0);
            }
        }.start();

    }

    @Override
    protected void handleMessageIml(Message msg) {
        switch (msg.what) {
            case 11:
                updateContent();
                break;
            default:
                sortCodeListAdapter.setSelection(msg.what);
                break;
        }
    }
}
