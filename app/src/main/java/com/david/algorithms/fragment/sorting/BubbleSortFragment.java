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
public class BubbleSortFragment extends BaseSortFragment {

    @Override
    protected String getCodeAssetName() {
        return "bubble_sort";
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
                    for (int j = 0; j < content.length - i - 1; j++) {
                        updateAndSleep(1, 100);
                        if (content[j] > content[j + 1]) {
                            updateAndSleep(2, 100);
                            int temp = content[j];
                            updateAndSleep(3, 100);
                            content[j] = content[j + 1];
                            updateAndSleep(4, 100);
                            content[j + 1] = temp;
                            updateAndSleep(5, 100);
                            updateAndSleep(6, 500);
                        }
                    }
                }
                weakHandler.sendEmptyMessage(7);
            }
        }.start();

    }

    @Override
    protected void handleMessageIml(Message msg) {
        switch (msg.what) {
            case 6:
                updateContent();
                break;
            default:
//                sorting_list_code_container.setSelection(msg.what);
                break;
        }
    }
}
