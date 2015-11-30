package com.david.algorithms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by xingzheng on 2015/11/25.
 */
public class SecondActivity extends AppCompatActivity {
    public static final String ALGORITHM_BEAN = "algorithmBean";
    AlgorithmBean algorithmBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        algorithmBean = getIntent().getParcelableExtra(ALGORITHM_BEAN);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void doClick(View view) {
        Intent intent=new Intent(this,SortActivity.class);
        switch (view.getId()) {
            case R.id.bubble_sort:
                intent.putExtra(SortActivity.SORT_BEAN,"bubble_sort");
                break;
            case R.id.select_sort:
                intent.putExtra(SortActivity.SORT_BEAN,"select_sort");
                break;
            case R.id.insert_sort:
                intent.putExtra(SortActivity.SORT_BEAN,"insert_sort");
                break;
            case R.id.merge_sort:
                intent.putExtra(SortActivity.SORT_BEAN,"merge_sort");
                break;
            case R.id.quick_sort:
                intent.putExtra(SortActivity.SORT_BEAN,"quick_sort");
                break;
            case R.id.random_quick_sort:
                intent.putExtra(SortActivity.SORT_BEAN,"random_quick_sort");
                break;
            case R.id.count_sort:
                intent.putExtra(SortActivity.SORT_BEAN,"count_sort");
                break;
            case R.id.radix_sort:
                intent.putExtra(SortActivity.SORT_BEAN,"radix_sort");
                break;
        }
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
