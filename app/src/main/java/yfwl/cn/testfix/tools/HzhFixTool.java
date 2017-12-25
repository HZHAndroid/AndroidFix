package yfwl.cn.testfix.tools;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/8/21.
 * 创建人 ： Administrator
 * 功能描述 ：适配工具类
 * 创建时间 ：2017/8/21
 */

public class HzhFixTool {
    private static HzhFixTool mHzhFixTool;//适配工具类
    private HzhFixFactorTool mHzhFixFactorTool;//适配因素工具类
    private HzhFixHWTool mHzhFixHWTool;//适配宽度和高度工具类
    private HzhFixSpacingTool mHzhFixSpacingTool;//适配间距工具类
    private HzhFixFontSizeTool mHzhFixFontSizeTool;//适配字体大小工具类

    private HzhFixTool(Context context) {
        mHzhFixFactorTool = HzhFixFactorTool.newInstance(context);
        mHzhFixHWTool = new HzhFixHWTool();
        mHzhFixSpacingTool = new HzhFixSpacingTool();
        mHzhFixFontSizeTool = new HzhFixFontSizeTool();
    }

    public static HzhFixTool newInstance(Context context) {
        if (mHzhFixTool == null) {
            mHzhFixTool = new HzhFixTool(context);
        }
        return mHzhFixTool;
    }


    //-----------------对外适配相关相关start-----------------

    /**
     * 适配
     * @param view 需要适配的视图
     */
    public void fix(View view) {
        if (view != null) {
            if (view instanceof ViewGroup) {
                fixAllView(view);
            } else {
                fixView(view);
            }
        }
    }

    /**
     * 适配单个视图
     *
     * @param itemView 需要适配的视图
     */
    private void fixView(View itemView) {
        mHzhFixHWTool.fix(itemView, mHzhFixFactorTool);
        mHzhFixSpacingTool.fix(itemView, mHzhFixFactorTool);
        mHzhFixFontSizeTool.fix(itemView, mHzhFixFactorTool);
    }

    /**
     * 适配所有视图
     *
     * @param groupView
     */
    private void fixAllView(View groupView) {
        if (groupView != null) {
            ViewGroup _viewGroup = (ViewGroup) groupView;
            fixView(_viewGroup);
            int _childCount = _viewGroup.getChildCount();
            for (int i = 0; i < _childCount; i++) {
                View _childView = _viewGroup.getChildAt(i);
                fix(_childView);
            }
        }
    }

    //-----------------对外适配相关相关end-----------------


}
