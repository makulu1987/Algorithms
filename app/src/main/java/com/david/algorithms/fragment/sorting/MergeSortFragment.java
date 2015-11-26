package com.david.algorithms.fragment.sorting;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

/**
 * Created by xingzheng on 2015/11/25.
 */
public class MergeSortFragment extends BaseSortFragment {
    @Override
    protected String getCodeAssetName() {
        return "merge_sort";
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
                for (int i = 1; i < content.length; i *= 2) {
                    mergeSort(content, i, content.length);
                }
                weakHandler.sendEmptyMessage(6);
            }

            private void mergeSort(int[] originArray, int gap, int length) {
                int i = 0;
                for (i = 0; i + 2 * gap <= length; i += 2 * gap) {
                    merge(originArray, i, i + gap - 1, i + 2 * gap - 1);
                }
                if (i + gap < length) {
                    merge(originArray, i, i + gap - 1, length - 1);
                }
            }

            private void merge(int[] originArray, int low, int mid, int high) {
                int i = low;
                int j = mid + 1;
                int k = 0;
                int tempArray[] = new int[high - low + 1];
                while (i <= mid && j <= high) {
                    if (originArray[i] < originArray[j]) {
                        tempArray[k] = originArray[i];
                        i++;
                    } else {
                        tempArray[k] = originArray[j];
                        j++;
                    }
                    k++;
                }
                while (i <= mid) {
                    tempArray[k] = originArray[i];
                    i++;
                    k++;
                }
                while (j <= high) {
                    tempArray[k] = originArray[j];
                    j++;
                    k++;
                }
                for (int m = low; m <= high; m++) {
                    originArray[m] = tempArray[m - low];
                }
                updateAndSleep(6,100);
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
