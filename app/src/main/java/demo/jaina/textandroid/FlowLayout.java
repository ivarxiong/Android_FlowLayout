package demo.jaina.textandroid;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by kuma on 2018/11/13.
 */

public class FlowLayout extends FrameLayout {

    //行间距
    private int rowPadding = 0;
    //item 间间距
    private int columPadding = 0;
    //朝向
    private int flowGravity;

    public FlowLayout(@NonNull Context context) {
        super(context);
    }

    public FlowLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout, defStyleAttr, 0);
        rowPadding = a.getDimensionPixelSize(R.styleable.FlowLayout_row_padding, 0);
        columPadding = a.getDimensionPixelSize(R.styleable.FlowLayout_colum_padding, 0);
    }

    public void setFlowGravity(int gravity) {
        this.flowGravity = gravity;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取控件 可布局宽度
        int allowWidth = MeasureSpec.getSize(widthMeasureSpec);
        //子控件个数
        int childCount = getChildCount();
        //当前控件剩余可布局宽度
        int currentWidth = 0;
        //当前控件高度
        int currentTop = 0;
        //上个控件的left x值
        int lastLeft = 0;
        int i = 0;
        int j = 0;
        do {
            if(childCount == 0) break;
            View child = getChildAt(i);
            int width = child.getMeasuredWidth();
            //子控件测量宽度大于 控件宽度时 使用 整个控件宽度
            if(width > (allowWidth - rowPadding)) {
                width = allowWidth - rowPadding;
            }
            int height = child.getMeasuredHeight();
            //子控件测量宽度大于 控件剩余布局宽度时 换行
            if(currentWidth - width - rowPadding >= 0) {
                lastLeft = lastLeft + width + rowPadding;
                currentWidth = currentWidth - width - rowPadding;
                i ++;
            } else {
                currentWidth = allowWidth;
                lastLeft = 0;
                currentTop = currentTop + height + columPadding;
            }
            j ++;
        } while (i < childCount);
        setMeasuredDimension(widthMeasureSpec, MeasureSpec.makeMeasureSpec(currentTop, MeasureSpec.EXACTLY));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        layoutChildren(left, top, right, bottom);

    }

    void layoutChildren(int left, int top, int right, int bottom) {
        int childCount = getChildCount();
        int layoutWidth = getMeasuredWidth();
        int currentWidth = 0;
        int currentTop = 0;
        int lastHeight = 0;

        if((flowGravity & Gravity.BOTTOM) == Gravity.BOTTOM) {
            currentTop = getMeasuredHeight();
        }
        int lastLeft = 0;
        if((flowGravity & Gravity.RIGHT) == Gravity.RIGHT) {
            lastLeft = getMeasuredWidth();
        }

        int i = 0;
        int j = 0;
        do {
            if(childCount == 0) break;
            View child = getChildAt(i);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            if(width > (layoutWidth - rowPadding)) {
                currentWidth = 0;
                if((flowGravity & Gravity.RIGHT) == Gravity.RIGHT) {
                    lastLeft = layoutWidth;
                } else {
                    lastLeft = 0;
                }
                if(i != 0) {
                    if((flowGravity & Gravity.BOTTOM) == Gravity.BOTTOM) {
                        currentTop = currentTop - lastHeight - columPadding;
                    } else {
                        currentTop = currentTop + lastHeight + columPadding;
                    }
                }
                int layoutTop = currentTop;
                int layoutLeft = 0;
                if((flowGravity & Gravity.BOTTOM) == Gravity.BOTTOM) {
                    layoutTop = currentTop - height;
                }
                if((flowGravity & Gravity.RIGHT) == Gravity.RIGHT) {
                    layoutLeft = lastLeft - width;
                }
                child.layout(layoutLeft, layoutTop,layoutLeft + width, layoutTop + height);
                lastHeight = height;
                i ++;
            }else if(currentWidth - width - rowPadding >= 0) {
                int layoutTop = currentTop;
                int layoutLeft = lastLeft;
                lastHeight = height;
                if((flowGravity & Gravity.BOTTOM) == Gravity.BOTTOM) {
                    layoutTop = currentTop - height;
                }
                if((flowGravity & Gravity.RIGHT) == Gravity.RIGHT) {
                    layoutLeft = lastLeft - width;
                }
                child.layout(layoutLeft, layoutTop,layoutLeft + width, layoutTop + height);

                if((flowGravity & Gravity.RIGHT) == Gravity.RIGHT) {
                    lastLeft = lastLeft - width - rowPadding;
                } else {
                    lastLeft = lastLeft + width + rowPadding;
                }
                currentWidth = currentWidth - width - rowPadding;
                i ++;
            } else {
                currentWidth = layoutWidth;
                if((flowGravity & Gravity.RIGHT) == Gravity.RIGHT) {
                    lastLeft = layoutWidth;
                } else {
                    lastLeft = 0;
                }
                if(i != 0) {
                    if((flowGravity & Gravity.BOTTOM) == Gravity.BOTTOM) {
                        currentTop = currentTop - lastHeight - columPadding;
                    } else {
                        currentTop = currentTop + lastHeight + columPadding;
                    }
                }
            }
            j ++;
            if(j > 200) break;
        } while (i < childCount);
    }

}
