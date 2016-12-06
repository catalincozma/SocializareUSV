package com.example.cozma.socializareusv.CustomTextViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by mihai on 29.04.2016.
 */
public class TextViewOpenSansExtraBold extends TextView {
    public TextViewOpenSansExtraBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-ExtraBold.ttf"));
        this.setTextScaleX(1f);
    }
}
