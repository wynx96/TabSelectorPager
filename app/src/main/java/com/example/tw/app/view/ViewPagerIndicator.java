package com.example.tw.app.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.tw.app.R;
import com.example.tw.app.util.ArgbUtils;
import com.example.tw.app.util.ResUtils;

/**
 * Created by 18348 on 2016/8/18.
 */
public class ViewPagerIndicator extends LinearLayout {

    private static final String TAG = "ViewPagerIndicator";

    private int checkedColor;
    private int uncheckedColor;
    private int checkedWidth;

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    private ArgbUtils argbUtils;

    public ViewPagerIndicator(Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = getResources().obtainAttributes(attrs, R.styleable.ViewPagerIndicator);

        checkedColor = typedArray.getColor(R.styleable.ViewPagerIndicator_checked_color, getResources().getColor(R.color.red));
        uncheckedColor = typedArray.getColor(R.styleable.ViewPagerIndicator_unchecked_color, getResources().getColor(R.color.gray));
        checkedWidth = typedArray.getDimensionPixelSize(R.styleable.ViewPagerIndicator_checked_width, getResources().getDimensionPixelSize(R.dimen.checked_width));

        typedArray.recycle();

        init();
    }

    private void init() {
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER);
        argbUtils = ArgbUtils.getInstance(checkedColor, uncheckedColor);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public void setupViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        this.pagerAdapter = this.viewPager.getAdapter();
        viewPager.addOnPageChangeListener(onPageChangeListener);
        addDotList();
    }

    private int getDotWidth(float positionOffset) {
        return (int) Math.max(ResUtils.dp2Pxf(getContext(), 6), checkedWidth * (1 - positionOffset));
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            ImageView view = (ImageView) getChildAt(position);
            setImageViewParams(view, argbUtils.evaluate(positionOffset), getDotWidth(positionOffset));
            int childCount = getChildCount();
            if (position + 1 > 0 && position + 1 < childCount) {
                view = (ImageView) getChildAt(position + 1);
                setImageViewParams(view, argbUtils.evaluate(1f - positionOffset), getDotWidth(1f - positionOffset));
            }

        }


        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private static final void setImageViewParams(ImageView imageView, int color, int width) {
        GradientDrawable gradientDrawable = (GradientDrawable) imageView.getDrawable();
        gradientDrawable.setColor(color);
        imageView.getLayoutParams().width = width;
        imageView.requestLayout();
    }

    private void addDotList() {
        int count = pagerAdapter.getCount();
        for (int i = 0; i < count; i++) {
            ImageView dotImage = new ImageView(getContext());
            dotImage.setImageResource(R.drawable.dot_gray);
            dotImage.setScaleType(ImageView.ScaleType.FIT_XY);
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.leftMargin = ResUtils.dp2Px(getContext(), 10);
            this.addView(dotImage, params);
        }

        if (count > 0) {
            onPageChangeListener.onPageSelected(viewPager.getCurrentItem());
        }
    }

    public void unset() {
        if (this.viewPager != null) {
            this.viewPager.removeOnPageChangeListener(onPageChangeListener);
        }
    }

}
