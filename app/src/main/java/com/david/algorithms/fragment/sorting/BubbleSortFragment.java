package com.david.algorithms.fragment.sorting;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
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
    List<TextView> codeViews=new ArrayList<>(7);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weakHandler = new WeakHandler<BubbleSortFragment>(this) {
            @Override
            protected void handleMessageIml(Message msg) {
                switch (msg.what){
                    case 6:
                        weakReference.get().updateContent();
                        break;
                    case 7:
                        weakReference.get().removeBack();
                        break;
                    default:
                        weakReference.get().updateBack(msg.what);
                        break;
                }

            }
        };
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initText();
        startSorting();
        ViewGroup sorting_list_code_container= (ViewGroup) view.findViewById(R.id.sorting_list_code_container);
        LayoutInflater.from(getActivity()).inflate(R.layout.view_bubble_sort_code,sorting_list_code_container,true);
        TextView bubble_sort_code_00 = (TextView) view.findViewById(R.id.bubble_sort_code_00);
        bubble_sort_code_00.setText("for (int i = 0; i < content.length - 1; i++) {");
        TextView bubble_sort_code_01 = (TextView) view.findViewById(R.id.bubble_sort_code_01);
        bubble_sort_code_01.setText("    for (int j = 0; j < content.length - i - 1; j++) {");
        TextView bubble_sort_code_02 = (TextView) view.findViewById(R.id.bubble_sort_code_02);
        bubble_sort_code_02.setText("        if (content[j] > content[j + 1]) {");
        TextView bubble_sort_code_03 = (TextView) view.findViewById(R.id.bubble_sort_code_03);
        bubble_sort_code_03.setText("            int temp = content[j];");
        TextView bubble_sort_code_04 = (TextView) view.findViewById(R.id.bubble_sort_code_04);
        bubble_sort_code_04.setText("            content[j] = content[j + 1];");
        TextView bubble_sort_code_05 = (TextView) view.findViewById(R.id.bubble_sort_code_05);
        bubble_sort_code_05.setText("            content[j + 1] = temp;");
        TextView bubble_sort_code_06 = (TextView) view.findViewById(R.id.bubble_sort_code_06);
        bubble_sort_code_06.setText("         }\n" +
                "     }\n" +
                "}");
        codeViews.add(bubble_sort_code_00);
        codeViews.add(bubble_sort_code_01);
        codeViews.add(bubble_sort_code_02);
        codeViews.add(bubble_sort_code_03);
        codeViews.add(bubble_sort_code_04);
        codeViews.add(bubble_sort_code_05);
        codeViews.add(bubble_sort_code_06);
    }

    private void initText() {
        content=new int[10];
        for(int i=0;i<10;i++){
            content[i]=getRandomInt();
        }
        updateContent();
    }
    Random random = new Random();
    private int getRandomInt(){
        int nextInt=random.nextInt(50);
        boolean contains=false;
        for (int aContent : content) {
            if (aContent == nextInt) {
                contains = true;
                break;
            }
        }
        if(contains){
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
    private void updateBack(int position){
        for(int i=0;i<codeViews.size();i++){
            codeViews.get(i).setBackgroundColor(getResources().getColor(android.R.color.transparent));
        }
        codeViews.get(position).setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
    }
    private void removeBack(){
        for(int i=0;i<codeViews.size();i++){
            codeViews.get(i).setBackgroundColor(getResources().getColor(android.R.color.transparent));
        }
    }

    private void startSorting() {
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < content.length - 1; i++) {
                    weakHandler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    for (int j = 0; j < content.length - i - 1; j++) {
                        weakHandler.sendEmptyMessage(1);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if (content[j] > content[j + 1]) {
                            weakHandler.sendEmptyMessage(2);
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            int temp = content[j];
                            weakHandler.sendEmptyMessage(3);
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            content[j] = content[j + 1];
                            weakHandler.sendEmptyMessage(4);
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            content[j + 1] = temp;
                            weakHandler.sendEmptyMessage(5);
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            weakHandler.sendEmptyMessage(6);
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                weakHandler.sendEmptyMessage(7);
            }
        }.start();

    }

    private WeakHandler<BubbleSortFragment> weakHandler;
}
