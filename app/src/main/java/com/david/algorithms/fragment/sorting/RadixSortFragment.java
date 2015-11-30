package com.david.algorithms.fragment.sorting;

import android.os.Message;

/**
 * Created by xingzheng on 2015/11/25.
 */
public class RadixSortFragment extends BaseSortFragment {
    @Override
    protected String getCodeAssetName() {
        return "radix_sort";
    }

    @Override
    protected void startSorting() {
        new SortAlgorithmThread() {
            @Override
            public void run() {
                int k = 0, n = 1, m = 1;
                int temp[][] = new int[10][content.length];
                int order[] = new int[10];
                while (m <= 2) {
                    for (int aContent : content) {
                        int i = (aContent / n) % 10;
                        temp[i][order[i]] = aContent;
                        order[i]++;
                    }
                    for (int i = 0; i < 10; i++) {
                        if (order[i] != 0) {
                            for (int j = 0; j < order[i]; j++) {
                                content[k] = temp[i][j];
                                k++;
                            }
                        }
                        order[i] = 0;
                    }
                    k = 0;
                    n *= 10;
                    m++;
                }

                updateAndSleep(100, 10);
            }

//            private void sort(int[] number, int d) {
//                int k = 0, n = 1, m = 1;
//                int temp[][] = new int[10][number.length];
//                int order[] = new int[10];
//                while (m <= d) {
//                    for (int aNumber : number) {
//                        int lsd = (aNumber / n) % 10;
//                        temp[lsd][order[lsd]] = aNumber;
//                        order[lsd]++;
//                    }
//                    for (int i = 0; i < 10; i++) {
//                        if (order[i] != 0) {
//                            for (int j = 0; j < order[i]; j++) {
//                                number[k] = temp[i][j];
//                                k++;
//                            }
//                        }
//                        order[i] = 0;
//                    }
//                    n *= 10;
//                    k = 0;
//                    m++;
//                }
//            }
        }.start();
    }

    @Override
    protected void handleMessageIml(Message msg) {
        switch (msg.what) {
            case 100:
                updateContent();
                break;
            default:
                sortCodeListAdapter.setSelection(msg.what);
                break;
        }
    }
}
