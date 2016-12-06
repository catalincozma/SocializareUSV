package com.example.cozma.socializareusv.CustomTextViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import com.example.cozma.socializareusv.R;


/**
 * Created by catalin on 01.07.2016.
 */
public class BorderEditTextMultiLines extends EditText {


    public BorderEditTextMultiLines(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf"));
        this.setTextScaleX(1f);
        this.setTextColor(getResources().getColor(R.color.full_black));
        this.setHintTextColor(getResources().getColor(R.color.main_gray));
        /*this.setMaxLines(true);*/
        this.setInputType(this.getInputType());

        int originalDrawable = R.drawable.custom_edit_text;
        int sdk = android.os.Build.VERSION.SDK_INT;
        int jellyBean = android.os.Build.VERSION_CODES.JELLY_BEAN;
        if (sdk < jellyBean) {
            this.setBackgroundDrawable(getResources().getDrawable(originalDrawable));
        } else {
            this.setBackground(getResources().getDrawable(originalDrawable));
        }


    }


}
