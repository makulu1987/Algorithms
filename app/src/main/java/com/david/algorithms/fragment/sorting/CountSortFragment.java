package com.david.algorithms.fragment.sorting;

import android.os.Message;

/**
 * Created by xingzheng on 2015/11/25.
 */
public class CountSortFragment extends BaseSortFragment{
    @Override
    protected String getCodeAssetName() {
        return "count_sort";
    }

    @Override
    protected void startSorting() {
        new SortAlgorithmThread() {
            @Override
            public void run() {
                int[] tempArray=new int[content.length];
                updateAndSleep(0,100);
                int max=content[0];
                updateAndSleep(1,100);
                int min=content[0];
                updateAndSleep(2,100);
                for(int i:content){
                    updateAndSleep(3,100);
                    if(i>max){
                        updateAndSleep(4,100);
                        max=i;
                        updateAndSleep(5,100);
                    }
                    updateAndSleep(6,100);
                    if(i<min){
                        updateAndSleep(7,100);
                        min=i;
                        updateAndSleep(8,100);
                    }
                    updateAndSleep(9,100);
                }
                updateAndSleep(10,100);
                int k=max-min+1;
                updateAndSleep(11,100);
                int[] countArray=new int[k];
                updateAndSleep(12,100);
                for (int aContent : content) {
                    updateAndSleep(13,100);
                    countArray[aContent - min] += 1;
                    updateAndSleep(14,100);
                }
                updateAndSleep(15,100);
                for(int i=1;i<countArray.length;i++){
                    updateAndSleep(16,100);
                    countArray[i]=countArray[i]+countArray[i-1];
                    updateAndSleep(17,100);
                }
                updateAndSleep(18,100);
                for(int i=content.length-1;i>=0;i--){
                    updateAndSleep(19,100);
                    tempArray[--countArray[content[i]-min]]=content[i];
                    updateAndSleep(20,100);
                }
                updateAndSleep(21,100);
                System.arraycopy(tempArray, 0, content, 0, content.length);
                updateAndSleep(22,100);
                updateAndSleep(100,100);
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
                sortCodeListAdapter.setSelection(msg.what);
                break;
        }
    }
}
