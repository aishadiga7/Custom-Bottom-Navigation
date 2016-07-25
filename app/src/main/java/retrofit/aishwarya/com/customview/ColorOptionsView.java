package retrofit.aishwarya.com.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by aishwarya on 12/7/16.
 */
public class ColorOptionsView extends LinearLayout {
    private View mValue;
    private ImageView mImage;


    public ColorOptionsView(Context context) {
        super(context);
    }

    public ColorOptionsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ColorOptionsView, 0, 0);
        String titleText = typedArray.getString(R.styleable.ColorOptionsView_titleText);
        int valueColor = typedArray.getColor(R.styleable.ColorOptionsView_valueColor, ContextCompat.getColor(context, R.color.colorAccent));
        typedArray.recycle();
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_color_options, this, true);
        TextView title = (TextView) getChildAt(0);
        title.setText(titleText);
        mValue = getChildAt(1);
        mValue.setBackgroundColor(valueColor);
        mImage = (ImageView) getChildAt(2);
    }

    public void setValueColor(int color) {
        mValue.setBackgroundColor(color);
    }

    public void setImageVisible(boolean visible) {
        mImage.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
}
