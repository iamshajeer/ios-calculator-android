package com.droidev.app.ioscalculator.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.droidev.app.ioscalculator.R;
import com.droidev.app.ioscalculator.manager.FontManager;

/**
 * Created by shajeer on 19/1/17.
 */
public class CalcTextView extends TextView {

    private static final String TAG = CalcTextView.class.getSimpleName();

    public CalcTextView(Context context) {
        this(context, null);
    }

    public CalcTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalcTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        if (isInEditMode()) return;

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.AnyFontTextView);

        if (ta != null) {
            String fontAsset = ta.getString(R.styleable.AnyFontTextView_typefaceAsset);

            if (!TextUtils.isEmpty(fontAsset)) {
                Typeface tf = FontManager.getInstance(context).getFont(fontAsset);
                int style = Typeface.NORMAL;
                float size = getTextSize();

                if (getTypeface() != null) {
                    style = getTypeface().getStyle();
                }
                if (tf != null) {
                    setTypeface(tf, style);
                }
            }
        }
        setLineSpacing(3, 1);
    }

}
