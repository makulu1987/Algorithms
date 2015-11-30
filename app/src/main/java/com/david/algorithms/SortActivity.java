package com.david.algorithms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.david.algorithms.fragment.sorting.BubbleSortFragment;
import com.david.algorithms.fragment.sorting.CountSortFragment;
import com.david.algorithms.fragment.sorting.InsertSortFragment;
import com.david.algorithms.fragment.sorting.MergeSortFragment;
import com.david.algorithms.fragment.sorting.QuickSortFragment;
import com.david.algorithms.fragment.sorting.RQuickSortFragment;
import com.david.algorithms.fragment.sorting.RadixSortFragment;
import com.david.algorithms.fragment.sorting.SelectSortFragment;

/**
 * Created by xingzheng on 2015/11/25.
 */
public class SortActivity extends AppCompatActivity {
    public static final String SORT_BEAN = "sort_bean";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);
        String name = getIntent().getStringExtra(SORT_BEAN);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle(name);
        switch (name) {
            case "bubble_sort":
                getFragmentManager().beginTransaction().add(R.id.content_container, new BubbleSortFragment(), name).commit();
                break;
            case "select_sort":
                getFragmentManager().beginTransaction().add(R.id.content_container, new SelectSortFragment(), name).commit();
                break;
            case "insert_sort":
                getFragmentManager().beginTransaction().add(R.id.content_container, new InsertSortFragment(), name).commit();
                break;
            case "merge_sort":
                getFragmentManager().beginTransaction().add(R.id.content_container, new MergeSortFragment(), name).commit();
                break;
            case "quick_sort":
                getFragmentManager().beginTransaction().add(R.id.content_container, new QuickSortFragment(), name).commit();
                break;
            case "random_quick_sort":
                getFragmentManager().beginTransaction().add(R.id.content_container, new RQuickSortFragment(), name).commit();
                break;
            case "count_sort":
                getFragmentManager().beginTransaction().add(R.id.content_container, new CountSortFragment(), name).commit();
                break;
            case "radix_sort":
                getFragmentManager().beginTransaction().add(R.id.content_container, new RadixSortFragment(), name).commit();
                break;
        }
    }
}
