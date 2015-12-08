package com.david.algorithms.fragment.sorting;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Message;
import android.support.percent.PercentFrameLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.david.algorithms.R;
import com.david.algorithms.fragment.BaseFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by xingzheng on 2015/11/25.
 */
public abstract class BaseSortFragment extends BaseFragment {

    protected int[] content;
    protected Random random = new Random();
    protected WeakHandler<BaseSortFragment> weakHandler;
    protected SortCodeAdapter sortCodeAdapter;
    protected RecyclerView sortingCode;
    protected RecyclerView sortingView;
    protected SortViewAdapter sortViewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weakHandler = new WeakHandler<>(this);
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        sortCodeAdapter = new SortCodeAdapter(context);
    }

    protected int getLayoutId() {
        return R.layout.fragment_sort;
    }

    protected abstract String getCodeAssetName();

    protected abstract void startSorting();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sortingCode = (RecyclerView) view.findViewById(R.id.sorting_code);
        sortingCode.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        sortingCode.setAdapter(sortCodeAdapter);
        sortingView = (RecyclerView) view.findViewById(R.id.sorting_view);
        sortingView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        sortingView.addItemDecoration(new SpacesItemDecoration(10));
        sortViewAdapter = new SortViewAdapter();
        sortingView.setAdapter(sortViewAdapter);


        initContent();
        initCode();

        view.findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSorting();
            }
        });
    }

    private void initContent() {
        content = new int[10];
        for (int i = 0; i < 10; i++) {
            content[i] = getRandomInt();
        }
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

    private void initCode() {
        AssetManager assetManager = getActivity().getAssets();
        BufferedReader br = null;
        List<String> codes = new ArrayList<>();
        try {
            br = new BufferedReader(new InputStreamReader(assetManager.open(getCodeAssetName())));
            String temp;
            while (!TextUtils.isEmpty(temp = br.readLine())) {
                codes.add(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        sortCodeAdapter.setCodes(codes);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateContent();
    }

    protected void updateContent() {
        sortingView.scrollToPosition(0);
        sortViewAdapter.clear();
        sortViewAdapter.notifyDataSetChanged();
    }

    @Override
    protected void handleMessageIml(Message msg) {
        switch (msg.what) {
            case 0:
                updateContent();
                break;
            case 1:
                sortingView.scrollToPosition(msg.arg1);
                sortViewAdapter.setSelected(msg.arg1);
                break;
            case 3:
                sortingView.scrollToPosition(msg.arg1);
                sortViewAdapter.setSelected(msg.arg1);
                sortViewAdapter.setSelected(msg.arg2);
                animateSwap(msg.arg1,msg.arg2);
                break;
            case 4:
                sortingView.scrollToPosition(0);
                clearSelect();
                break;
            case 5:
                sortingView.scrollToPosition(msg.arg1);
                int[] selected= (int[]) msg.obj;
                for(int select:selected){
                    select(select);
                }
                break;
            case 6:
                int[] unselected= (int[]) msg.obj;
                for(int unselect:unselected){
                    unselect(unselect);
                }
                break;
            case 7:
                int[] fixed= (int[]) msg.obj;
                for(int fix:fixed){
                    fixPosition(fix);
                }
                break;
            default:
                sortCodeAdapter.setSelection(msg.arg1);
                sortingCode.scrollToPosition(msg.arg1);
                break;
        }
    }


    private void animateSwap(int first,int second){
        if(first<second) {
            sortViewAdapter.notifyItemMoved(first, second);
            sortViewAdapter.notifyItemMoved(second, first);
        }else{
            sortViewAdapter.notifyItemMoved(second, first);
            sortViewAdapter.notifyItemMoved(first, second);
        }
    }

    private void clearSelect(){
        sortViewAdapter.clear();
    }

    private void select(int position){
        sortViewAdapter.setSelected(position);
    }
    private void unselect(int position){
        sortViewAdapter.setUnSelected(position);
    }

    private void fixPosition(int position){
        sortViewAdapter.setFixed(position);
    }
    private void unFixPosition(int position){
        sortViewAdapter.setUnFixed(position);
    }

    private void extractOut(int from,int to){

    }

    private void replaceRange(int start,int end,int[] array){

    }















    protected class SortAlgorithmThread extends AlgorithmThread {

        @Override
        public void updateViewAndSleep(int time) {
            weakHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void updateViewAndSleep(int arg1, int time) {
            Message msg = Message.obtain(weakHandler);
            msg.what = 1;
            msg.arg1 = arg1;
            msg.sendToTarget();
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void updateCodeAndSleep(int arg1, int time) {
            Message msg = Message.obtain(weakHandler);
            msg.what = 2;
            msg.arg1 = arg1;
            msg.sendToTarget();
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void swap(int first,int second){
            Message msg = Message.obtain(weakHandler);
            msg.what = 3;
            msg.arg1 = first;
            msg.arg2 = second;
            msg.sendToTarget();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void clearSelect(){
            Message msg = Message.obtain(weakHandler);
            msg.what = 4;
            msg.sendToTarget();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void select(int... position){
            Message msg = Message.obtain(weakHandler);
            msg.what = 5;
            msg.obj=position;
            msg.sendToTarget();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void unselect(int... position){
            Message msg = Message.obtain(weakHandler);
            msg.what = 6;
            msg.obj=position;
            msg.sendToTarget();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void fixPosition(int... position){
            Message msg = Message.obtain(weakHandler);
            msg.what = 7;
            msg.obj=position;
            msg.sendToTarget();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected class SortCodeAdapter extends RecyclerView.Adapter<SortCodeHolder> {
        private List<String> codes = new ArrayList<>();
        private LayoutInflater inflater;
        private int selected = -1;

        public SortCodeAdapter(Context context) {
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

        public void setSelection(int position) {
            selected = position;
            notifyDataSetChanged();
        }

        @Override
        public SortCodeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new SortCodeHolder(inflater.inflate(R.layout.view_sort_code_item, parent, false));
        }

        @Override
        public void onBindViewHolder(SortCodeHolder holder, int position) {
            holder.refreshContent(codes.get(position));
            holder.setSelected(position == selected);
        }

        @Override
        public int getItemCount() {
            return codes.size();
        }
    }

    protected class SortViewAdapter extends RecyclerView.Adapter<SortViewHolder> {
        private Set<Integer> selected = new HashSet<>();
        private Set<Integer> fixed=new HashSet<>();
        public void setSelected(int position) {
            selected.add(position);
            notifyItemChanged(position);
        }
        public void setUnSelected(int position) {
            selected.remove(position);
            notifyItemChanged(position);
        }

        public void setFixed(int position){
            fixed.add(position);
            notifyItemChanged(position);
        }
        public void setUnFixed(int position){
            fixed.remove(position);
            notifyItemChanged(position);
        }

        public void clear() {
            selected.clear();
            fixed.clear();
            notifyItemRangeChanged(0, content.length - 1);
        }

        @Override
        public SortViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new SortViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.view_sort_recycler_view_item, parent, false));
        }

        @Override
        public void onBindViewHolder(SortViewHolder holder, int position) {
            holder.refreshContent(content[position]);
            holder.refreshSelectState(selected.contains(position));
            holder.refreshFixedState(!fixed.contains(position));
        }

        @Override
        public int getItemCount() {
            return content.length;
        }
    }

    protected class SortViewHolder extends RecyclerView.ViewHolder {
        //        private PercentFrameLayout root;
        private TextView percentView;
        private TextView percentNumber;

        public SortViewHolder(View itemView) {
            super(itemView);
//            root= (PercentFrameLayout) itemView;
            percentView = (TextView) itemView.findViewById(R.id.view_sort_recycler_view_item_percent);
            percentNumber = (TextView) itemView.findViewById(R.id.view_sort_recycler_view_item_number);
        }

        public void refreshContent(int number) {
            float percent = ((float) number) / 50;
            PercentFrameLayout.LayoutParams lp = (PercentFrameLayout.LayoutParams) percentView.getLayoutParams();
            lp.getPercentLayoutInfo().heightPercent = percent;
            percentView.setLayoutParams(lp);
            percentNumber.setText(number + "");
        }

        public void refreshSelectState(boolean selected) {
            if (percentView.isSelected() ^ selected) {
                percentView.setSelected(selected);
            }
        }
        public void refreshFixedState(boolean fixed) {
            if (percentView.isEnabled() ^ fixed) {
                percentView.setEnabled(fixed);
            }
        }
    }

    protected class SortCodeHolder extends RecyclerView.ViewHolder {

        TextView contentView;

        public SortCodeHolder(View itemView) {
            super(itemView);
            this.contentView = (TextView) itemView.findViewById(R.id.view_sort_code_item_content);
        }

        public void refreshContent(String content) {
            contentView.setText(content);
        }

        public void setSelected(boolean selected) {
            contentView.setBackgroundColor(getResources().getColor(selected ? android.R.color.darker_gray : android.R.color.white));
        }

    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;

            // Add top margin only for the first item to avoid double space between items
//            if(parent.getChildPosition(view) == 0)
//                outRect.top = space;
        }
    }


//    public class MyItemAnimator extends RecyclerView.ItemAnimator {
//        private DefaultItemAnimator defaultItemAnimator;
//        List<RecyclerView.ViewHolder> mAnimationAddViewHolders = new ArrayList<>();
//        List<RecyclerView.ViewHolder> mAnimationRemoveViewHolders = new ArrayList<>();
//
//        @Override
//        public boolean animateDisappearance(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull ItemHolderInfo preLayoutInfo, @Nullable ItemHolderInfo postLayoutInfo) {
//            return false;
//        }
//
//        @Override
//        public boolean animateAppearance(@NonNull RecyclerView.ViewHolder viewHolder, @Nullable ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
//            return false;
//        }
//
//        @Override
//        public boolean animatePersistence(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
//            return false;
//        }
//
//        @Override
//        public boolean animateChange(@NonNull RecyclerView.ViewHolder oldHolder, @NonNull RecyclerView.ViewHolder newHolder, @NonNull ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
//            return false;
//        }
//
//        //需要执行动画时会系统会调用，用户无需手动调用
//        @Override
//        public void runPendingAnimations() {
//            if (!mAnimationAddViewHolders.isEmpty()) {
//
//                AnimatorSet animator;
//                View target;
//                for (final RecyclerView.ViewHolder viewHolder : mAnimationAddViewHolders) {
//                    target = viewHolder.itemView;
//                    animator = new AnimatorSet();
//
//                    animator.playTogether(
//                            ObjectAnimator.ofFloat(target, "translationX", -target.getMeasuredWidth(), 0.0f),
//                            ObjectAnimator.ofFloat(target, "alpha", target.getAlpha(), 1.0f)
//                    );
//
//                    animator.setTarget(target);
//                    animator.setDuration(100);
//                    animator.addListener(new Animator.AnimatorListener() {
//                        @Override
//                        public void onAnimationStart(Animator animation) {
//
//                        }
//
//                        @Override
//                        public void onAnimationEnd(Animator animation) {
//                            mAnimationAddViewHolders.remove(viewHolder);
//                            if (!isRunning()) {
//                                dispatchAnimationsFinished();
//                            }
//                        }
//
//                        @Override
//                        public void onAnimationCancel(Animator animation) {
//
//                        }
//
//                        @Override
//                        public void onAnimationRepeat(Animator animation) {
//
//                        }
//                    });
//                    animator.start();
//                }
//            }
//            else if(!mAnimationRemoveViewHolders.isEmpty()){
//            }
//        }
//        //remove时系统会调用，返回值表示是否需要执行动画
//        @Override
//        public boolean animateRemove(RecyclerView.ViewHolder viewHolder) {
//            return mAnimationRemoveViewHolders.add(viewHolder);
//        }
//
//        //viewholder添加时系统会调用
//        @Override
//        public boolean animateAdd(RecyclerView.ViewHolder viewHolder) {
//            return mAnimationAddViewHolders.add(viewHolder);
//        }
//
//        @Override
//        public boolean animateMove(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4) {
//            return false;
//        }
//
//        @Override
//        public void endAnimation(RecyclerView.ViewHolder viewHolder) {
//        }
//
//        @Override
//        public void endAnimations() {
//        }
//
//        @Override
//        public boolean isRunning() {
//            return !(mAnimationAddViewHolders.isEmpty()&&mAnimationRemoveViewHolders.isEmpty());
//        }
//
//    }

}
