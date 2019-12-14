package com.bai.psychedelic.cloudmusic.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bai.psychedelic.cloudmusic.R;

public class InputView extends FrameLayout {
    private int inputIcon;
    private String inputHint;
    private boolean isPassword;
    private View mView;
    private ImageView mIvIcon;
    private EditText mEtInput;
    public InputView(Context context) {
        super(context);
        init(context,null);
    }

    public InputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);

    }

    public InputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);

    }

    public InputView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attr){
        if (attr == null) return;
        @SuppressLint("CustomViewStyleable")
        TypedArray typedArray = context.obtainStyledAttributes(attr, R.styleable.inputView);
        inputIcon = typedArray.getResourceId(R.styleable.inputView_input_icon, R.mipmap.logo);
        inputHint = typedArray.getString(R.styleable.inputView_input_hint);
        isPassword = typedArray.getBoolean(R.styleable.inputView_is_password,false);
        typedArray.recycle();
        mView = LayoutInflater.from(context).inflate(R.layout.input_view, this, false);
        mIvIcon = mView.findViewById(R.id.iv_icon);
        mEtInput = mView.findViewById(R.id.et_input);

        mIvIcon.setImageResource(inputIcon);
        mEtInput.setHint(inputHint);
        mEtInput.setInputType(isPassword? InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD : InputType.TYPE_CLASS_PHONE);

        addView(mView);
    }

    /**
     * 返回输入内容
     * @return
     */
    public String getInputStr(){
        return mEtInput.getText().toString().trim();
    }
}
