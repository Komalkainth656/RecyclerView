package com.dgl114.assignment4;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

// 1. Add Room annotations as necessary to this file.
@Entity(tableName = "card")
public class Card {

    @PrimaryKey(autoGenerate = true)
    private long mId;

    @ColumnInfo(name = "mText")
    private String mText;

    @ColumnInfo(name = "mDetail")
    private String mDetail;

    public Card(String mText, String mDetail){
        this.mText = mText;
        this.mDetail = mDetail;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public String getDetail() {
        return mDetail;
    }

    public void setDetail(String detail) {
        mDetail = detail;
    }
}
