package com.david.algorithms.fragment.sorting;

import android.os.Bundle;
import android.view.View;
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
        sortingItems.add((TextView) view.findViewById(R.id.sorting_list_position_00));
        sortingItems.add((TextView) view.findViewById(R.id.sorting_list_position_01));
        sortingItems.add((TextView) view.findViewById(R.id.sorting_list_position_02));
        sortingItems.add((TextView) view.findViewById(R.id.sorting_list_position_03));
        sortingItems.add((TextView) view.findViewById(R.id.sorting_list_position_04));
        sortingItems.add((TextView) view.findViewById(R.id.sorting_list_position_05));
        sortingItems.add((TextView) view.findViewById(R.id.sorting_list_position_06));
        sortingItems.add((TextView) view.findViewById(R.id.sorting_list_position_07));
        sortingItems.add((TextView) view.findViewById(R.id.sorting_list_position_08));
        sortingItems.add((TextView) view.findViewById(R.id.sorting_list_position_09));
    }

}
