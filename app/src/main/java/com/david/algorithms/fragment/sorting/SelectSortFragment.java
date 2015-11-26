package com.david.algorithms.fragment.sorting;

import android.os.Bundle;
import android.os.Message;
import android.support.percent.PercentRelativeLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.david.algorithms.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by xingzheng on 2015/11/25.
 */
public class SelectSortFragment extends BaseSortFragment {

    @Override
    protected String getCodeAssetName() {
        return "select_sort";
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startSorting();
    }

    private void startSorting() {
        new SortAlgorithmThread() {

            @Override
            public void run() {
                for (int i = 0; i < content.length - 1; i++) {
                    updateAndSleep(0, 100);
                    int selection = i;
                    updateAndSleep(1, 100);
                    for (int j = selection + 1; j < content.length; j++) {
                        updateAndSleep(2, 100);
                        if (content[selection] > content[j]) {
                            updateAndSleep(3, 100);
                            selection = j;
                            updateAndSleep(4, 100);
                        }
                    }
                    if (selection != i) {
                        updateAndSleep(6, 100);
                        int temp = content[i];
                        updateAndSleep(7, 100);
                        content[i] = content[selection];
                        updateAndSleep(8, 100);
                        content[selection] = temp;
                        updateAndSleep(9, 100);
                    }
                    updateAndSleep(11, 500);
                }
                weakHandler.sendEmptyMessage(12);
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
                break;
        }
    }
}
