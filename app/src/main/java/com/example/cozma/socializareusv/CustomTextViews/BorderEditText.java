package com.example.cozma.socializareusv.CustomTextViews;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.EditText;

import com.example.cozma.socializareusv.R;


/**
 * Created by mihai on 03.05.2016.
 */
public class BorderEditText extends EditText {

    int sdk = android.os.Build.VERSION.SDK_INT;
    int jellyBean = android.os.Build.VERSION_CODES.JELLY_BEAN;
    int originalDrawable = R.drawable.custom_edit_text;
    int focusDrawable = R.drawable.custom_edit_text_focus;

    public BorderEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-Regular.ttf"));
//        this.setTextScaleX(1f);
        this.setTextColor(getResources().getColor(R.color.full_black));
        this.setHintTextColor(getResources().getColor(R.color.main_gray));
        this.setSingleLine(true);
        this.setInputType(this.getInputType());
        this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);

        if (sdk < jellyBean) {
            this.setBackgroundDrawable(getResources().getDrawable(originalDrawable));
        } else {
            this.setBackground(getResources().getDrawable(originalDrawable));
        }
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if(focused) {
            setFocused();
        } else {
            setUnfocused();
        }
    }

    private void setUnfocused() {
        if (sdk < jellyBean) {
            this.setBackgroundDrawable(getResources().getDrawable(originalDrawable));
        } else {
            this.setBackground(getResources().getDrawable(originalDrawable));
        }
    }

    private void setFocused() {
        if (sdk < jellyBean) {
            this.setBackgroundDrawable(getResources().getDrawable(focusDrawable));
        } else {
            this.setBackground(getResources().getDrawable(focusDrawable));
        }
    }


}
