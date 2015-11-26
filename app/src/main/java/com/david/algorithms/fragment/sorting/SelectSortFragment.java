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
    int[] content;
    List<TextView> codeViews = new ArrayList<>(11);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weakHandler = new WeakHandler<>(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initText();
        startSorting();
        ViewGroup sorting_list_code_container = (ViewGroup) view.findViewById(R.id.sorting_list_code_container);
        LayoutInflater.from(getActivity()).inflate(R.layout.view_select_sort_code, sorting_list_code_container, true);
        for (int i = 0; i < sorting_list_code_container.getChildCount(); i++) {
            codeViews.add((TextView) sorting_list_code_container.getChildAt(i));
        }

        codeViews.get(0).setText("for (int i = 0; i < content.length - 1; i++) {");
        codeViews.get(1).setText("    int selection=i;");
        codeViews.get(2).setText("    for (int j = selection+1; j < content.length; j++) {");
        codeViews.get(3).setText("        if (content[selection] > content[j]) {");
        codeViews.get(4).setText("            selection=j;");
        codeViews.get(5).setText("        }\n" +
                                 "    }");
        codeViews.get(6).setText("    if(selection!=i){");
        codeViews.get(7).setText("        int temp=content[i];");
        codeViews.get(8).setText("        content[i]=content[selection];");
        codeViews.get(9).setText("        content[selection]=temp;");
       codeViews.get(10).setText("    }\n" +
                                 "}");
    }

    private void initText() {
        content = new int[10];
        for (int i = 0; i < 10; i++) {
            content[i] = getRandomInt();
        }
        updateContent();
    }

    protected int getRandomInt() {
        int nextInt = random.nextInt(50);
        boolean contains = false;
        for (int aContent : content) {
            if (aContent == nextInt) {
                contains = true;
                break;
            }
        }
        if (contains) {
            return getRandomInt();
        }
        return nextInt;
    }

    private void updateContent() {
        for (int i = 0; i < 10; i++) {
            PercentRelativeLayout.LayoutParams lp = (PercentRelativeLayout.LayoutParams) sortingItems.get(i).getLayoutParams();
            lp.getPercentLayoutInfo().heightPercent = ((float) content[i]) / 50;
            sortingItems.get(i).setLayoutParams(lp);
        }
    }

    private void updateBack(int position) {
        removeBack();
        codeViews.get(position).setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
    }

    private void removeBack() {
        for (int i = 0; i < codeViews.size(); i++) {
            codeViews.get(i).setBackgroundColor(getResources().getColor(android.R.color.transparent));
        }
    }

    private void startSorting() {
        new AlgorithmThread() {
            @Override
            public void updateAndSleep(int what, int time) {
                weakHandler.sendEmptyMessage(what);
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

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

    private WeakHandler<SelectSortFragment> weakHandler;

    @Override
    protected void handleMessageIml(Message msg) {
        switch (msg.what) {
            case 11:
                updateContent();
                break;
            case 12:
                removeBack();
                break;
            default:
                updateBack(msg.what);
                break;
        }
    }
}
