package com.david.algorithms.fragment.sorting;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.david.algorithms.R;
import com.david.algorithms.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xingzheng on 2015/11/25.
 */
public class BaseSortFragment extends BaseFragment {
    protected List<TextView> sortingItems = new ArrayList<>(10);

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sort;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewGroup sorting_list= (ViewGroup) view.findViewById(R.id.sorting_list);
        for(int i=0;i<sorting_list.getChildCount();i++){
            sortingItems.add((TextView) sorting_list.getChildAt(i));
        }
    }

}
