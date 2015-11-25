package com.david.algorithms.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

/**
 * Created by xingzheng on 2015/11/25.
 */
public class BaseFragment extends Fragment{
    protected int getLayoutId(){
        return -1;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(getLayoutId()==-1)
            return super.onCreateView(inflater, container, savedInstanceState);
        else
            return inflater.inflate(getLayoutId(),container,false);
    }

    protected static class WeakHandler<T extends BaseFragment> extends Handler{
        protected WeakReference<T> weakReference;

        public WeakHandler(T t) {
            this.weakReference = new WeakReference<>(t);
        }

        @Override
        public void handleMessage(Message msg) {
            if(weakReference!=null&&weakReference.get()!=null) {
                handleMessageIml(msg);
            }
        }
        protected void handleMessageIml(Message msg){

        }
    }
}
