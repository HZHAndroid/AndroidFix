package yfwl.cn.testfix.tools;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/8/21.
 * 创建人 ： Administrator
 * 功能描述 ：适配器宽度和高度工具类
 * 创建时间 ：2017/8/21
 */

public class HzhFixHWTool {

    /**
     * 适配
     *
     * @param view          需要适配的视图
     * @param fixFactorTool 适配因素工具类
     */
    public void fix(View view, HzhFixFactorTool fixFactorTool) {
        if (view != null && fixFactorTool != null) {
            fixWH(view.getLayoutParams(), fixFactorTool);
            fixMinMaxHW(view, fixFactorTool);
        }
    }

    /**
     * 适配宽度和高度
     *
     * @param layoutParams 控件属性
     */
    private void fixWH(ViewGroup.LayoutParams layoutParams, HzhFixFactorTool fixFactorTool) {
        if (layoutParams != null) {
            //高度
            int _height = layoutParams.height;
            if (_height != ViewGroup.LayoutParams.MATCH_PARENT
                    && _height != ViewGroup.LayoutParams.WRAP_CONTENT) {
                layoutParams.height = (int) fixFactorTool.getFixVerticalValue(_height);
            }
            //宽度
            int _width = layoutParams.width;
            if (_width != ViewGroup.LayoutParams.MATCH_PARENT &&
                    _width != ViewGroup.LayoutParams.WRAP_CONTENT) {
                layoutParams.width = (int) fixFactorTool.getFixHorizontalValue(_width);
            }
        }
    }

    /**
     * 适配最大宽度和高度
     *
     * @param view          需要适配的控件
     * @param fixFactorTool 适配因素工具类
     */
    private void fixMinMaxHW(View view, HzhFixFactorTool fixFactorTool) {
        int _minHeight = getMinHeight(view);
        int _minWidth = getMinWidth(view);
        int _maxHeight = getMaxHeight(view);
        int _maxWidth = getMaxWidth(view);

        if (_minHeight != -1) {
            view.setMinimumHeight((int) fixFactorTool.getFixVerticalValue(_minHeight));
        }

        if (_minWidth != -1) {
            view.setMinimumWidth((int) fixFactorTool.getFixHorizontalValue(_minWidth));
        }

        if (_maxHeight != -1) {
            setMaxHeight(view, (int) fixFactorTool.getFixVerticalValue(_maxHeight));
        }

        if (_maxWidth != -1) {
            setMaxWidth(view, (int) fixFactorTool.getFixHorizontalValue(_maxWidth));
        }
    }


    //最大宽度最小宽度相关start
    @SuppressLint("NewApi")
    public int getMinHeight(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            return view.getMinimumHeight();
        } else {
            try {
                Field minHeight = view.getClass().getField("mMinHeight");
                minHeight.setAccessible(true);
                return (Integer) minHeight.get(view);
            } catch (Exception e) {
            }
        }
        return -1;
    }

    @SuppressLint("NewApi")
    public int getMinWidth(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            return view.getMinimumWidth();
        try {
            Field minWidth = view.getClass().getField("mMinWidth");
            minWidth.setAccessible(true);
            return (Integer) minWidth.get(view);
        } catch (Exception ignore) {
        }
        return -1;
    }

    public int getMaxHeight(View view) {
        try {
            Method setMaxWidthMethod = view.getClass()
                    .getMethod("getMaxHeight");
            return (Integer) setMaxWidthMethod.invoke(view);
        } catch (Exception ignore) {
        }
        return -1;
    }

    public void setMaxHeight(View view, int val) {
        try {
            Method setMaxWidthMethod = view.getClass().getMethod(
                    "setMaxHeight", int.class);
            setMaxWidthMethod.invoke(view, val);
        } catch (Exception ignore) {
        }
    }

    public int getMaxWidth(View view) {
        try {
            Method setMaxWidthMethod = view.getClass().getMethod("getMaxWidth");
            return (Integer) setMaxWidthMethod.invoke(view);
        } catch (Exception ignore) {
        }
        return -1;
    }

    public void setMaxWidth(View view, int val) {
        try {
            Method setMaxWidthMethod = view.getClass().getMethod("setMaxWidth",
                    int.class);
            setMaxWidthMethod.invoke(view, val);
        } catch (Exception ignore) {
        }
    }
    //最大宽度最小宽度相关end

}
