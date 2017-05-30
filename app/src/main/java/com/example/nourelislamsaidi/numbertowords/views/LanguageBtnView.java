package com.example.nourelislamsaidi.numbertowords.views;

import android.content.Context;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nourelislamsaidi.numbertowords.R;

/**
 * Created by nourelislam.saidi on 05/07/2017.
 */

public class LanguageBtnView extends FrameLayout {
    private TextView mTitleTv;
    private ImageView mIconImgV;

    public LanguageBtnView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public LanguageBtnView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LanguageBtnView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public LanguageBtnView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    protected void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_language_btn, this);

        mTitleTv = (TextView) findViewById(R.id.language_btn_title);
        mIconImgV = (ImageView) findViewById(R.id.language_btn_icon);
    }

    /**
     * Set Text value to button
     * @param textValue of type String
     */
    public void setText(String textValue) {
        mTitleTv.setText(textValue);
    }

    /**
     * Set icon of button
     * @param icon from drawable
     */
    public void setIcon(int icon) {
        mIconImgV.setImageResource(icon);
    }


}
