package com.david.algorithms.fragment.sorting;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

import com.david.algorithms.R;

/**
 * Created by xingzheng on 2015/11/25.
 */
public class QuickSortFragment extends BaseSortFragment {
    @Override
    protected String getCodeAssetName() {
        return "quick_sort";
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        startSorting();
        view.findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSorting();
            }
        });
    }

    private void startSorting() {
        new SortAlgorithmThread() {
            @Override
            public void run() {
                quickSort(content, 0, content.length - 1);
                updateAndSleep(100, 100);
                weakHandler.sendEmptyMessage(6);
            }

            private void quickSort(int[] array, int low, int high) {
                if (high <= low) {
                    return;
                }
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
                        int temp = array[j];
                        array[j] = array[i];
                        array[i] = temp;
                    }
                }
                array[low] = array[i];
                array[i] = key;
                quickSort(array, low, i - 1);
                quickSort(array, i + 1, high);
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
