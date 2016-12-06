package com.example.cozma.socializareusv.CustomTextViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by mihai on 29.04.2016.
 */
public class TextViewOpenSansLightItalic extends TextView {
    public TextViewOpenSansLightItalic(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSans-LightItalic.ttf"));
        this.setTextScaleX(1f);
    }
}
