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
    int[] content;
    List<TextView> codeViews = new ArrayList<>(7);

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
        LayoutInflater.from(getActivity()).inflate(R.layout.view_bubble_sort_code, sorting_list_code_container, true);
        for (int i = 0; i < sorting_list_code_container.getChildCount(); i++) {
            codeViews.add((TextView) sorting_list_code_container.getChildAt(i));
        }
        codeViews.get(0).setText("for (int i = 0; i < content.length - 1; i++) {");
        codeViews.get(1).setText("    for (int j = 0; j < content.length - i - 1; j++) {");
        codeViews.get(2).setText("        if (content[j] > content[j + 1]) {");
        codeViews.get(3).setText("            int temp = content[j];");
        codeViews.get(4).setText("            content[j] = content[j + 1];");
        codeViews.get(5).setText("            content[j + 1] = temp;");
        codeViews.get(6).setText("         }\n" +
                                 "     }\n" +
                                 "}");
    }

    private void initText() {
        content = new int[10];
        for (int i = 0; i < 10; i++) {
            content[i] = getRandomInt();
        }
        updateContent();
    }

    private int getRandomInt() {
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

            public void updateAndSleep(int what, int time) {
                weakHandler.sendEmptyMessage(what);
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    private WeakHandler<BubbleSortFragment> weakHandler;

    @Override
    protected void handleMessageIml(Message msg) {
        switch (msg.what) {
            case 6:
                updateContent();
                break;
            case 7:
                removeBack();
                break;
            default:
                updateBack(msg.what);
                break;
        }
    }
}
