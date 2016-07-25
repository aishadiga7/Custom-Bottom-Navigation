package retrofit.aishwarya.com.customview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by aishwarya on 20/7/16.
 */
public class BottomActionView extends LinearLayout {

    private static final String TAG = BottomActionView.class.getSimpleName();
    private LinearLayout mBottomBarContainerView;
    private int mNumOfIcons = 0;
    private static final int sDefaultXMLResourceNotFoundValue = 0;
    private static final String sButtonTag = "button";
    private int[] mBottomBarIconDrawables = null;
    private String[] mBottomBarTitles = null;
    private BottomActionBarClickListener mBottomActionBarClickListener;


    public BottomActionView(Context context) {
        super(context);
        init(null);
    }

    public BottomActionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            initXMLAttributeValues(attrs);
        }
        mBottomBarContainerView = (LinearLayout) inflate(getContext(), R.layout.bottom_bar_layout, this);
        mBottomBarContainerView.setBackgroundResource(R.color.colorAccent);
        mBottomBarContainerView.setWeightSum(mNumOfIcons);
        drawBottomBars();
    }

    private void drawBottomBars() {
        mBottomBarContainerView.removeAllViews();
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        for (int index = 0; index < mNumOfIcons; index++) {
            View button = layoutInflater.inflate(R.layout.bottom_action_bar_layout, null);
            button.setTag(sButtonTag);
            button.setId(ViewUtils.generateViewId());
            setWidth(button);
            setBottomBarTitle(index, button);
            setBottomBarIcon(index, button);
            setBottomBarClickListener(index, button);
            mBottomBarContainerView.addView(button);
        }
    }

    private void setWidth(View button) {
        LayoutParams param = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f);
        button.setLayoutParams(param);
    }

    public void setBottomBarClickListener(BottomActionBarClickListener bottomBarClickListener) {
        mBottomActionBarClickListener = bottomBarClickListener;
    }

    private void setBottomBarClickListener(int buttonPosition, View button) {
        final int position = buttonPosition;
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBottomActionBarClickListener != null) {
                    mBottomActionBarClickListener.onClicked(BottomActionView.this, position);
                }
            }
        });
    }

    private void setBottomBarIcon(int buttonPosition, View button) {
        if (mBottomBarIconDrawables != null) {
            try {
                int buttonIconDrawable = mBottomBarIconDrawables[buttonPosition];
                if (buttonIconDrawable != sDefaultXMLResourceNotFoundValue) {
                    ImageView imageView = (ImageView) button.findViewById(R.id.icon);
                    imageView.setBackgroundResource(buttonIconDrawable);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
    }

    private void setBottomBarTitle(int buttonPosition, View button) {
        String buttonText = "";
        if (mBottomBarTitles != null) {
            try {
                buttonText = mBottomBarTitles[buttonPosition];
            } catch (ArrayIndexOutOfBoundsException e) {
                buttonText = "";
            }
        }
        TextView titleTextView = (TextView) button.findViewById(R.id.button_title);
        titleTextView.setText(buttonText);
    }


    private void initXMLAttributeValues(AttributeSet attributeSet) {
        Resources resources = getContext().getResources();
        TypedArray styledAttrs = getContext().obtainStyledAttributes(attributeSet, R.styleable.BottomActionView);
        mNumOfIcons = styledAttrs.getInt(R.styleable.BottomActionView_num_of_icons, sDefaultXMLResourceNotFoundValue);
        Log.d(TAG, "No.Of.Buttons:" +mNumOfIcons);
        int bottomBarTitleStringsResId = styledAttrs.getResourceId(R.styleable.BottomActionView_icon_titles,sDefaultXMLResourceNotFoundValue);
        int bottomBarIconsArrayResId = styledAttrs.getResourceId(R.styleable.BottomActionView_icons,sDefaultXMLResourceNotFoundValue);
        if (bottomBarTitleStringsResId != sDefaultXMLResourceNotFoundValue)
            mBottomBarTitles = resources.getStringArray(bottomBarTitleStringsResId);
        TypedArray buttonIconsArray = null;
        if (bottomBarIconsArrayResId != sDefaultXMLResourceNotFoundValue) {
            buttonIconsArray = resources.obtainTypedArray(bottomBarIconsArrayResId);
            int length = buttonIconsArray.length();
            mBottomBarIconDrawables = new int[length];
            for (int i = 0; i < length; i++) {
                int iconResourceId = buttonIconsArray.getResourceId(i, sDefaultXMLResourceNotFoundValue);
                mBottomBarIconDrawables[i] = iconResourceId;
            }
        }
        styledAttrs.recycle();
        if (buttonIconsArray != null) {
            buttonIconsArray.recycle();
        }
    }


}
