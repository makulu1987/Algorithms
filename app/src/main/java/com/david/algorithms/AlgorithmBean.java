package com.david.algorithms;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xingzheng on 2015/11/25.
 */
public class AlgorithmBean implements Parcelable {
    public AlgorithmBean(@DrawableRes int id, String name) {
        this.id = id;
        this.name = name;
    }

    @DrawableRes
    public int id;
    public String name;
    public List<String> tags;

    public AlgorithmBean setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }
    public AlgorithmBean addTag(String tag){
        if(tags==null){
            tags=new ArrayList<>();
        }
        if(!tags.contains(tag)){
            tags.add(tag);
        }
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeStringList(this.tags);
    }

    protected AlgorithmBean(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.tags = in.createStringArrayList();
    }

    public static final Parcelable.Creator<AlgorithmBean> CREATOR = new Parcelable.Creator<AlgorithmBean>() {
        public AlgorithmBean createFromParcel(Parcel source) {
            return new AlgorithmBean(source);
        }

        public AlgorithmBean[] newArray(int size) {
            return new AlgorithmBean[size];
        }
    };
}
