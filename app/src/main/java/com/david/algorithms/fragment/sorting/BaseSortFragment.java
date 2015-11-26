package com.david.algorithms.fragment.sorting;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.percent.PercentRelativeLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.david.algorithms.R;
import com.david.algorithms.fragment.BaseFragment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by xingzheng on 2015/11/25.
 */
public abstract class BaseSortFragment extends BaseFragment {
    protected List<TextView> sortingItems = new ArrayList<>(10);

    protected int[] content;
    protected Random random = new Random();
    protected WeakHandler<BaseSortFragment> weakHandler;
    protected SortCodeListAdapter sortCodeListAdapter;
    protected ListView sorting_list_code_container;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weakHandler = new WeakHandler<>(this);
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        sortCodeListAdapter = new SortCodeListAdapter(context);
    }

    protected int getLayoutId() {
        return R.layout.fragment_sort;
    }

    protected abstract String getCodeAssetName();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewGroup sorting_list = (ViewGroup) view.findViewById(R.id.sorting_list);
        for (int i = 0; i < sorting_list.getChildCount(); i++) {
            sortingItems.add((TextView) sorting_list.getChildAt(i));
        }
        sorting_list_code_container = (ListView) view.findViewById(R.id.sorting_list_code_container);
        sorting_list_code_container.setAdapter(sortCodeListAdapter);
        initContent();
        initCode();
    }

    private void initContent() {
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

    private void initCode(){
        AssetManager assetManager=getActivity().getAssets();
        BufferedReader br = null;
        List<String> codes=new ArrayList<>();
        try {
            br=new BufferedReader(new InputStreamReader(assetManager.open(getCodeAssetName())));
            String temp;
            while(!TextUtils.isEmpty(temp=br.readLine())){
                codes.add(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        sortCodeListAdapter.setCodes(codes);
    }

    protected void updateContent() {
        for (int i = 0; i < 10; i++) {
            PercentRelativeLayout.LayoutParams lp = (PercentRelativeLayout.LayoutParams) sortingItems.get(i).getLayoutParams();
            lp.getPercentLayoutInfo().heightPercent = ((float) content[i]) / 50;
            sortingItems.get(i).setLayoutParams(lp);
        }
    }

    protected class SortAlgorithmThread extends AlgorithmThread {
        @Override
        public void updateAndSleep(int what, int time) {
            weakHandler.sendEmptyMessage(what);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected class SortCodeListAdapter extends BaseAdapter {
        private List<String> codes = new ArrayList<>();
        private LayoutInflater inflater;
        private int selected=-1;
        public SortCodeListAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        public void setCodes(List<String> codes) {
            this.codes.clear();
            notifyDataSetChanged();
            if (codes != null && codes.size() > 0) {
                this.codes.addAll(codes);
                notifyDataSetChanged();
            }
        }

        public void setSelection(int position){
            selected=position;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return codes.size();
        }

        @Override
        public String getItem(int position) {
            return codes.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.view_sort_code_item, parent, false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.refreshContent(codes.get(position));
            viewHolder.setSelected(position==selected);
            return convertView;
        }

        class ViewHolder {
            TextView contentView;

            public ViewHolder(View convertView) {
                this.contentView = (TextView) convertView.findViewById(R.id.view_sort_code_item_content);
            }

            public void refreshContent(String content) {
                contentView.setText(content);
            }

            public void setSelected(boolean selected){
                contentView.setBackgroundColor(getResources().getColor(selected?android.R.color.darker_gray:android.R.color.white));
            }
        }
    }
}
