package com.david.algorithms;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {
    private MainListAdapter adapter = new MainListAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        ((ListView) findViewById(R.id.main_content_list_view)).setAdapter(adapter);
        List<AlgorithmBean> beans = new ArrayList<>();
        beans.add(new AlgorithmBean(R.drawable.sorting, "Sorting").addTag("array").addTag("algorithm").addTag("bubble").addTag("select").addTag("selection").addTag("insert").addTag("insertion").addTag("merge").addTag("quick").addTag("randomized quick").addTag("counting").addTag("radix").addTag("sort").addTag("cs2020").addTag("cs1020").addTag("cs1010").addTag("cs3230").addTag("list").addTag("struktur data").addTag("sorting"));
        beans.add(new AlgorithmBean(R.drawable.bitmask, "Bitmask").addTag("bit manipulation").addTag("set").addTag("cs3233").addTag("cs2020").addTag("cs2010").addTag("array").addTag("list").addTag("ds").addTag("data structure").addTag("bitmask"));
        beans.add(new AlgorithmBean(R.drawable.list, "Linked List").addTag("stack").addTag("queue").addTag("doubly").addTag("deque").addTag("cs2020").addTag("cs1020").addTag("array").addTag("ds").addTag("data structure").addTag("linked"));
        beans.add(new AlgorithmBean(R.drawable.hashtable, "Hash Table").addTag("open addressing").addTag("linear").addTag("quadratic").addTag("probing").addTag("cs2020").addTag("cs1020").addTag("ds").addTag("data structure"));
        beans.add(new AlgorithmBean(R.drawable.heap, "Binary Heap").addTag("priority queue").addTag("recursive").addTag("cs2020").addTag("cs2010").addTag("recursion").addTag("ds").addTag("data structure").addTag("binary").addTag("heap"));
        beans.add(new AlgorithmBean(R.drawable.bst, "Binary Search Tree").addTag("adelson velskii landis").addTag("set").addTag("table").addTag("avl").addTag("cs2020").addTag("cs2010").addTag("recursion").addTag("recursive").addTag("ds").addTag("data structure").addTag("set").addTag("bst").addTag("binary").addTag("search").addTag("tree").addTag("priority").addTag("queue"));
        beans.add(new AlgorithmBean(R.drawable.graphds, "Graph Structures").addTag("tree").addTag("complete").addTag("bipartite").addTag("dag").addTag("cs2010").addTag("cs2020").addTag("graph").addTag("ds").addTag("data structure"));
        beans.add(new AlgorithmBean(R.drawable.ufds, "Union-Find DS").addTag("path compression").addTag("disjoint").addTag("set").addTag("data structure").addTag("union by rank").addTag("cs3233").addTag("cs2020").addTag("cs2010").addTag("array").addTag("tree").addTag("find").addTag("ds"));
        beans.add(new AlgorithmBean(R.drawable.segmenttree, "Segment Tree").addTag("dynamic").addTag("range").addTag("sum").addTag("min").addTag("max").addTag("cs3233").addTag("segment").addTag("tree").addTag("ds").addTag("data structure"));
        beans.add(new AlgorithmBean(R.drawable.fenwicktree, "Fenwick Tree").addTag("binary indexed tree").addTag("bit").addTag("dynamic").addTag("fenwick").addTag("range").addTag("sum").addTag("point").addTag("update").addTag("cs3233").addTag("binary").addTag("ds").addTag("data structure"));
        beans.add(new AlgorithmBean(R.drawable.recursion, "Recursion Tree/DAG").addTag("dynamic programming").addTag("dp").addTag("generic").addTag("cs1010").addTag("cs1020").addTag("cs2010").addTag("cs2020").addTag("cs3233").addTag("recursive").addTag("algorithm").addTag("recursion").addTag("tree").addTag("dag"));
        beans.add(new AlgorithmBean(R.drawable.dfsbfs, "Graph Traversal").addTag("bfs").addTag("dfs").addTag("cs2010").addTag("bipartite").addTag("scc").addTag("cut vertex").addTag("articulation point").addTag("bridge").addTag("cs2020").addTag("graph").addTag("algorithm"));
        beans.add(new AlgorithmBean(R.drawable.mst, "Min Spanning Tree").addTag("mst").addTag("prim").addTag("kruskal").addTag("graph").addTag("min").addTag("spanning").addTag("cs2010").addTag("cs2020").addTag("tree").addTag("algorithm"));
        beans.add(new AlgorithmBean(R.drawable.sssp, "SS Shorts Paths").addTag("sssp").addTag("single-source").addTag("bfs").addTag("dijkstra").addTag("bellman ford").addTag("cs2010").addTag("cs2020").addTag("single source").addTag("shortest path").addTag("graph").addTag("algorithm"));
        beans.add(new AlgorithmBean(R.drawable.maxflow, "Network Flow").addTag("max flow").addTag("edmonds karp").addTag("min cut").addTag("dinic").addTag("ford fulkerson").addTag("graph").addTag("cs3233").addTag("algorithm"));
        beans.add(new AlgorithmBean(R.drawable.matching, "Graph Matching").addTag("augmenting path").addTag("bipartite").addTag("graph").addTag("cs3233").addTag("matching").addTag("algorithm"));
        beans.add(new AlgorithmBean(R.drawable.cyclefinding, "Cycle Finding").addTag("floyd").addTag("tortoise-hare").addTag("math").addTag("cs3233").addTag("algorithm"));
        beans.add(new AlgorithmBean(R.drawable.suffixtree, "Suffix Tree").addTag("string").addTag("matching").addTag("lrs").addTag("lcs").addTag("cs3233").addTag("suffix").addTag("tree").addTag("ds").addTag("data structure"));
        beans.add(new AlgorithmBean(R.drawable.suffixarray, "Suffix Array").addTag("lcp").addTag("cs3233").addTag("matching").addTag("lrs").addTag("lcs").addTag("suffix").addTag("array").addTag("string").addTag("ds").addTag("data structure"));
        beans.add(new AlgorithmBean(R.drawable.geometry, "Geometry").addTag("polygon").addTag("convex").addTag("cut").addTag("winding").addTag("graham").addTag("scan").addTag("cs3233").addTag("computational").addTag("geometry").addTag("algorithm"));
        adapter.setAlgorithmBeans(beans);
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


    private class MainListAdapter extends BaseAdapter {
        private List<AlgorithmBean> algorithmBeans = new ArrayList<>();

        public void setAlgorithmBeans(List<AlgorithmBean> algorithmBeans) {
            this.algorithmBeans.clear();
            notifyDataSetChanged();
            this.algorithmBeans.addAll(algorithmBeans);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return algorithmBeans.size();
        }

        @Override
        public AlgorithmBean getItem(int position) {
            return algorithmBeans.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.list_item_main, parent, false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.refreshContent(algorithmBeans.get(position));
            return convertView;
        }

        private class ViewHolder {
            public TextView nameView;
            public TextView tag01;
            public TextView tag02;
            public TextView tag03;
            public TextView tag04;
            public GifImageView gifView;
            public Button button;

            public ViewHolder(View convertView) {
                nameView = (TextView) convertView.findViewById(R.id.list_item_name);
                tag01 = (TextView) convertView.findViewById(R.id.list_item_tag_01);
                tag02 = (TextView) convertView.findViewById(R.id.list_item_tag_02);
                tag03 = (TextView) convertView.findViewById(R.id.list_item_tag_03);
                tag04 = (TextView) convertView.findViewById(R.id.list_item_tag_04);
                gifView = (GifImageView) convertView.findViewById(R.id.list_item_gif);
                button = (Button) convertView.findViewById(R.id.list_item_start_train);
            }

            public void refreshContent(final AlgorithmBean bean) {
                nameView.setText(bean.name);
                gifView.setImageResource(bean.id);
                if (bean.tags.size() >= 1) {
                    tag01.setText(bean.tags.get(0));
                }
                if (bean.tags.size() >= 2) {
                    tag02.setText(bean.tags.get(1));
                }
                if (bean.tags.size() >= 3) {
                    tag03.setText(bean.tags.get(2));
                }
                if (bean.tags.size() >= 4) {
                    tag04.setText(bean.tags.get(3));
                }
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this,SecondActivity.class).putExtra(SecondActivity.ALGORITHM_BEAN,bean));
                    }
                });
            }
        }
    }
}
