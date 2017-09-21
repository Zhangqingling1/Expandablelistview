package com.aqinga.yuekaolianxi;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/9/2019:51
 */

public class MyView extends LinearLayout {

    private LinearLayout layout;
    private TextView text;
    private TypedArray array;
    private String title;
    private float size;
    private int color;

    public MyView(Context context) {
        super(context);
        init(context,null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void init(Context context,AttributeSet attrs){
        View inflate = inflate(context,R.layout.myview,this);
        layout = (LinearLayout) inflate.findViewById(R.id.line);
        text = (TextView) findViewById(R.id.text_view);
        if (attrs==null){
            return;
        }
        init1(context,attrs);
        selected();

    }
    public void init1(Context context,AttributeSet attrs){
        if (attrs==null){
            return;
        }
        array = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        title = array.getString(R.styleable.MyView_title_text);
        size = array.getDimension(R.styleable.MyView_title_size, 20);
        color = array.getColor(R.styleable.MyView_title_color, Color.BLACK);

    }
    public void selected(){
        text.setText(title);
        text.setTextColor(color);
        text.setTextSize(size);
    }


}
