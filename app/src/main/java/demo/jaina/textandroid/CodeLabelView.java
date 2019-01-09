package demo.jaina.textandroid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * Created by kuma on 2018/12/4.
 */

public class CodeLabelView extends FrameLayout {

    public CodeLabelView(Context context) {
        super(context);
    }

    public CodeLabelView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CodeLabelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_code_label_view, this, true);
    }

}
