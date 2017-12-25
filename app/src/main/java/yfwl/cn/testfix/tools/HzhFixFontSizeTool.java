package yfwl.cn.testfix.tools;

import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/8/21.
 * 创建人 ： Administrator
 * 功能描述 ：适配字体大小工具类
 * 创建时间 ：2017/8/21
 */

public class HzhFixFontSizeTool {
    /**
     * 适配字体大小
     *
     * @param view          需要适配的视图
     * @param fixFactorTool 适配因素工具类
     */
    public void fix(View view, HzhFixFactorTool fixFactorTool) {
        if (view != null && fixFactorTool != null) {
            fixTextSize(view, fixFactorTool);
        }
    }

    /**
     * 适配字体大小
     *
     * @param view          需要适配的视图
     * @param fixFactorTool 适配因素工具类
     */
    private void fixTextSize(View view, HzhFixFactorTool fixFactorTool) {
        if (view instanceof EditText) {
            EditText _et = (EditText) view;
            float _editTextSize = _et.getTextSize();
            //判断是否跟默认的一致，不一致则被设置过，需要适配,反之不适配
            if (fixFactorTool.getDefaultEditTextSize() != _editTextSize) {
                _et.setIncludeFontPadding(false);
                _et.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        fixFactorTool.getFixDiagonalValue(_editTextSize)-fixFactorTool.getCurrentDiagonalOne()*fixFactorTool.getScaledDensity());
            }
        } else if (view instanceof TextView) {
            TextView _tv = (TextView) view;
            float _textViewSize = _tv.getTextSize();
            //判断是否跟默认的一致，不一致则被设置过，需要适配,反之不适配
            if (fixFactorTool.getDefaultTextViewSize() != _textViewSize) {
                _tv.setIncludeFontPadding(false);
                _tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        fixFactorTool.getFixDiagonalValue(_textViewSize));
            }
        }
    }
}
