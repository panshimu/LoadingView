package com.miaozi.loadingview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

/**
 * created by panshimu
 * on 2019/8/26
 */
public class LoadingView extends RelativeLayout {
    private int mCircleRadius = 25;
    private CircleView mLeftCircle;
    private CircleView mCenterCircle;
    private CircleView mRightCircle;
    private int mDuration = 350;
    private AnimatorSet mLeftAnimatorSet;
    private AnimatorSet mRightAnimatorSet;
    private int mTranslationX = 100;
    private boolean isAnimation = false;

    public LoadingView(Context context) {
        this(context,null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout();
        post(new Runnable() {
            @Override
            public void run() {
                isAnimation = true;
                startLeftAnimation();
            }
        });
    }

    private void initLayout() {

        mLeftCircle = new CircleView(getContext());
        mLeftCircle.setColor(Color.RED);
        RelativeLayout.LayoutParams leftCircleParams = new RelativeLayout.LayoutParams(mCircleRadius,mCircleRadius);
        mLeftCircle.setLayoutParams(leftCircleParams);
        addView(mLeftCircle);

        mRightCircle = new CircleView(getContext());
        mRightCircle.setColor(Color.YELLOW);
        RelativeLayout.LayoutParams rightCircleParams = new RelativeLayout.LayoutParams(mCircleRadius,mCircleRadius);
        mRightCircle.setLayoutParams(rightCircleParams);
        addView(mRightCircle);

        mCenterCircle = new CircleView(getContext());
        mCenterCircle.setColor(Color.BLUE);
        RelativeLayout.LayoutParams centerCircleParams = new RelativeLayout.LayoutParams(mCircleRadius,mCircleRadius);
        mCenterCircle.setLayoutParams(centerCircleParams);
        addView(mCenterCircle);

    }

    private void startLeftAnimation(){

        Log.d("TAG","startLeftAnimation");
        if(mLeftAnimatorSet == null) {
            ObjectAnimator leftAnimation = ObjectAnimator.ofFloat(mLeftCircle, "translationX", 0, mTranslationX - mCircleRadius);
            ObjectAnimator rightAnimation = ObjectAnimator.ofFloat(mRightCircle, "translationX", 0, -(mTranslationX - mCircleRadius));
            mLeftAnimatorSet = new AnimatorSet();
            mLeftAnimatorSet.playTogether(leftAnimation, rightAnimation);
            mLeftAnimatorSet.setDuration(mDuration);
            mLeftAnimatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    if(isAnimation) {
                        startRightAnimation();
                    }
                }
            });
        }
        mLeftAnimatorSet.start();

    }
    private void startRightAnimation(){
        Log.d("TAG","startRightAnimation");
        if(mRightAnimatorSet == null) {
            ObjectAnimator leftAnimation = ObjectAnimator.ofFloat(mLeftCircle, "translationX", mTranslationX - mCircleRadius, 0);
            ObjectAnimator rightAnimation = ObjectAnimator.ofFloat(mRightCircle, "translationX", -(mTranslationX - mCircleRadius), 0);
            mRightAnimatorSet = new AnimatorSet();
            mRightAnimatorSet.playTogether(leftAnimation, rightAnimation);
            mRightAnimatorSet.setDuration(mDuration);
            mRightAnimatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    if(isAnimation) {
                        setColor(mLeftCircle);
                        setColor(mRightCircle);
                        setColor(mCenterCircle);
                        startLeftAnimation();
                    }
                }
            });
        }
        mRightAnimatorSet.start();
    }

    public void show(){
        if(getVisibility() == GONE) {
            setVisibility(VISIBLE);
            isAnimation = true;
            startLeftAnimation();
        }
    }
    public void hide(){
        if(getVisibility() == VISIBLE) {
            isAnimation = false;
            setVisibility(GONE);
        }
    }

    private void setColor(CircleView circleView){
        switch (circleView.getColor()){
            case Color.RED:
                circleView.setColor(Color.YELLOW);
                break;
            case Color.YELLOW:
                circleView.setColor(Color.BLUE);
                break;
            case Color.BLUE:
                circleView.setColor(Color.RED);
                break;
        }
    }
}
