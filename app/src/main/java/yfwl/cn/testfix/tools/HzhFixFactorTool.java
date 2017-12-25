package yfwl.cn.testfix.tools;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/8/21.
 * 创建人 ： Administrator
 * 功能描述 ：适配因素工具类
 * 创建时间 ：2017/8/21
 */

public class HzhFixFactorTool {
    public static HzhFixFactorTool mFixFactorTool;//适配因素工具类

    private final int mDesignWidth = 800;//设计图纸的宽度
    private final int mDesignHeight = 1280;//设计图纸的高度
    private int mDesignDiagonal = 0;//设计图纸的斜边长

    private final int mWidthCount = 320;//宽度的份数
    private final int mHeightCount = 480;//高度的份数
    private final int mDiagonalCount = 577;//屏幕对角线的份数

    private int mCurrentWidth = 0;//当前的宽度
    private int mCurrentHeight = 0;//当前的高度
    private int mCurrentDiagonal = 0;//当前屏幕的斜边长

    private float mDesignWidthOne = 0;//设计图纸宽度每份大小
    private float mDesignHeightOne = 0;//设计图纸高度每份大小
    private float mDesignDiagonalOne = 0;//设计图纸斜边每份大小

    private float mCurrentWidthOne = 0;//当前屏幕宽度每份大小
    private float mCurrentHeightOne = 0;//当前屏幕高度每份大小
    private float mCurrentDiagonalOne = 0;//当前屏幕斜边每份大小

    private float mTextViewSize = 0;//TextView字体大小
    private float mEditTextSize = 0;//EditText字体大小

    private float mDensity = 0;
    private float mScaledDensity = 0;
    private float mDensityDpi = 0;


    public static HzhFixFactorTool newInstance(Context context) {
        if (mFixFactorTool == null) {
            mFixFactorTool = new HzhFixFactorTool();
            mFixFactorTool.resetConfig(context);
        }
        return mFixFactorTool;
    }


    //-----------------初始化相关start-----------------

    /**
     * 重置配置
     *
     * @param context 上下文
     */
    public void resetConfig(Context context) {
        //初始化屏幕宽度和高度
        int[] _screenSize = HzhScreenUtils.getScreenSize(context, true);
        if (_screenSize != null) {
            mCurrentWidth = _screenSize[0];
            mCurrentHeight = _screenSize[1];
        }
        float[] _scareParams = HzhScreenUtils.getScreenParams(context);
        mDensity = _scareParams[0];
        mDensityDpi = _scareParams[1];
        mScaledDensity = _scareParams[2];

        initFixFactor(mDesignWidth, mDesignHeight, mCurrentWidth, mCurrentHeight);
        initFontSize(context);
    }


    /**
     * 初始化适配器因素(分别获取宽度和高度每一份的大小)
     *
     * @param designWidth   设计图纸的宽度
     * @param designHeight  设计图纸的高度
     * @param currentWidth  当前的宽度
     * @param currentHeight 当前的高度
     */
    private void initFixFactor(int designWidth, int designHeight, int currentWidth, int currentHeight) {
        //宽度
        float _designWidthOne = designWidth * 1.0f / mWidthCount;//设计图纸宽度平均一份多少
        float _currentWidthOne = currentWidth * 1.0f / mWidthCount;//当前屏幕宽度平均一份多少
//        for (int i = 1; i <= mWidthCount; i++) {
////            System.out.println("hzh item " + (i * _designWidthOne));
//            System.out.println("hzh item " + (i * _currentWidthOne));
//        }

        //高度
        float _designHeightOne = designHeight * 1.0f / mHeightCount;
        float _currentHeightOne = currentHeight * 1.0f / mHeightCount;
//        for (int i = 1; i <= mHeightCount; i++) {
////            System.out.println("hzh item height" + (i * _designHeightOne));
//        }

        mDesignWidthOne = _designWidthOne;
        mDesignHeightOne = _designHeightOne;
        mCurrentWidthOne = _currentWidthOne;
        mCurrentHeightOne = _currentHeightOne;

        mDesignDiagonal = (int) Math.sqrt(Math.pow(designWidth, 2) + Math.pow(designHeight, 2));
        mCurrentDiagonal = (int) Math.sqrt(Math.pow(currentWidth, 2) + Math.pow(currentHeight, 2));

        mDesignDiagonalOne = mDesignDiagonal * 1.0f / mDiagonalCount;
        mCurrentDiagonalOne = mCurrentDiagonal * 1.0f / mDiagonalCount;
    }

    /**
     * 初始化控件字体，用于判断是否被设置过字体（没有的话，就不需要走字体适配流程）
     */
    private void initFontSize(Context context) {
        //获取TextView的字体大小
        TextView _tv = new TextView(context);
        mTextViewSize = _tv.getTextSize();
        //获取EditText的字体大小
        EditText _et = new EditText(context);
        mEditTextSize = _et.getTextSize();
    }

    //-----------------初始化相关start-----------------

    //--------对外方法start--------

    /**
     * 获取当前屏幕宽度(w)
     *
     * @return
     */
    public int getCurrentWidth() {
        return mCurrentWidth;
    }

    /**
     * 获取当前屏幕高度(h)
     *
     * @return
     */
    public int getCurrentHeight() {
        return mCurrentHeight;
    }

    /**
     * 获取设计图纸宽度(w)每一份大小
     *
     * @return
     */
    public float getDesignWidthOne() {
        return mDesignWidthOne;
    }

    /**
     * 获取设计图纸高度(h)每一份大小
     *
     * @return
     */
    public float getDesignHeightOne() {
        return mDesignHeightOne;
    }

    /**
     * 获取当前屏幕宽度(w)每一份大小
     *
     * @return
     */
    public float getCurrentWidthOne() {
        return mCurrentWidthOne;
    }

    /**
     * 获取当前屏幕高度(h)每一份大小
     *
     * @return
     */
    public float getCurrentHeightOne() {
        return mCurrentHeightOne;
    }

    /**
     * 获取设计图纸的斜边长度每一份大小
     *
     * @return
     */
    public float getDesignDiagonalOne() {
        return mDesignDiagonalOne;
    }

    /**
     * 获取当前屏幕的斜边长度每一份大小
     *
     * @return
     */
    public float getCurrentDiagonalOne() {
        return mCurrentDiagonalOne;
    }

    /**
     * 获取TextView的默认的字体大小
     *
     * @return
     */
    public float getDefaultTextViewSize() {
        return mTextViewSize;
    }

    /**
     * 获取EditText的默认的字体大小
     *
     * @return
     */
    public float getDefaultEditTextSize() {
        return mEditTextSize;
    }


    public float getDensity(){
        return mDensity;
    }

    public float getScaledDensity(){
        return mScaledDensity;
    }

    public float getDensityDpi(){
        return mDensityDpi;
    }


    /**
     * 获取适配后水平方向的值
     *
     * @param horizontalValue 未适配的水平方向的值
     * @return
     */
    public float getFixHorizontalValue(float horizontalValue) {
        if (mDesignWidthOne == 0) {
            return 0;
        }
        return horizontalValue / mDesignWidthOne * mCurrentWidthOne;
    }

    /**
     * 获取适配后垂直方向的值
     *
     * @param verticalValue 未适配的垂直方向的值
     * @return
     */
    public float getFixVerticalValue(float verticalValue) {
        if (mDesignHeightOne == 0) {
            return 0;
        }
        return verticalValue / mDesignHeightOne * mCurrentHeightOne;
    }

    /**
     * 获取适配的屏幕对角线的值
     *
     * @param diagonalValue 未适配的屏幕的对角线的值
     * @return
     */
    public float getFixDiagonalValue(float diagonalValue) {
        if (mDesignDiagonalOne == 0) {
            return 0;
        }
        return diagonalValue / mDesignDiagonalOne * mCurrentDiagonalOne;
    }


    //--------对外方法end--------
}
