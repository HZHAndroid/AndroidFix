package yfwl.cn.testfix.tools;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/8/21.
 * 创建人 ： Administrator
 * 功能描述 ：适配间距工具类
 * 创建时间 ：2017/8/21
 */

public class HzhFixSpacingTool {

    /**
     * 适配
     *
     * @param view          需要适配的视图
     * @param fixFactorTool 适配因素工具类
     */
    public void fix(View view, HzhFixFactorTool fixFactorTool) {
        if (view != null && fixFactorTool != null) {
            fixPadding(view, fixFactorTool);
            fixMargin(view.getLayoutParams(), fixFactorTool);
        }
    }

    /**
     * 适配padding
     *
     * @param view 需要适配的视图
     */
    private void fixPadding(View view, HzhFixFactorTool fixFactorTool) {
        int sPaddingLeft = view.getPaddingLeft();
        int sPaddingTop = view.getPaddingTop();
        int sPaddingRight = view.getPaddingRight();
        int sPaddingBottom = view.getPaddingBottom();

        boolean sIsNeedSet = false;

        if (sPaddingLeft != 0) {
            sPaddingLeft = (int) fixFactorTool.getFixHorizontalValue(sPaddingLeft);
            sIsNeedSet = true;
        }

        if (sPaddingTop != 0) {
            sPaddingTop = (int) fixFactorTool.getFixVerticalValue(sPaddingTop);
            sIsNeedSet = true;
        }

        if (sPaddingRight != 0) {
            sPaddingRight = (int) fixFactorTool.getFixHorizontalValue(sPaddingRight);
            sIsNeedSet = true;
        }

        if (sPaddingBottom != 0) {
            sPaddingBottom = (int) fixFactorTool.getFixVerticalValue(sPaddingBottom);
            sIsNeedSet = true;
        }

        if (sIsNeedSet) {
            view.setPadding(sPaddingLeft, sPaddingTop, sPaddingRight,
                    sPaddingBottom);
        }

    }

    /**
     * 适配margin
     *
     * @param layoutParams 控件属性
     */
    private void fixMargin(ViewGroup.LayoutParams layoutParams, HzhFixFactorTool fixFactorTool) {
        if (layoutParams != null && layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams _marginLayoutParams = ((ViewGroup.MarginLayoutParams) layoutParams);
            int _leftMargin = _marginLayoutParams.leftMargin;
            if (_leftMargin != 0) {
                _marginLayoutParams.leftMargin = (int) fixFactorTool.getFixHorizontalValue(_leftMargin);
            }
            int _topMargin = _marginLayoutParams.topMargin;
            if (_topMargin != 0) {
                _marginLayoutParams.topMargin = (int) fixFactorTool.getFixVerticalValue(_topMargin);
            }
            int _rightMargin = _marginLayoutParams.rightMargin;
            if (_rightMargin != 0) {
                _marginLayoutParams.rightMargin = (int) fixFactorTool.getFixHorizontalValue(_rightMargin);
            }
            int _bottomMargin = _marginLayoutParams.bottomMargin;
            if (_bottomMargin != 0) {
                _marginLayoutParams.bottomMargin = (int) fixFactorTool.getFixVerticalValue(_bottomMargin);
            }
        }
    }
}
