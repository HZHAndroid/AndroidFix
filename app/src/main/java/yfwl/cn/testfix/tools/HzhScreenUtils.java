package yfwl.cn.testfix.tools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by zhy on 15/12/4.<br/>
 * form http://stackoverflow.com/questions/1016896/get-screen-dimensions-in-pixels/15699681#15699681
 * 屏幕相关属性
 */
public class HzhScreenUtils
{

    public static int getStatusBarHeight(Context context)
    {
        int result = 0;
        try
        {
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0)
            {
                result = context.getResources().getDimensionPixelSize(resourceId);
            }
        } catch (Resources.NotFoundException e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
	/**
	 * 获取底部 navigation bar 高度
	 * 
	 * @return
	 */
	private static int getNavigationBarHeight(Context context) {
		Resources resources = context.getResources();
		int resourceId = resources.getIdentifier("navigation_bar_height",
				"dimen", "android");
		int height = resources.getDimensionPixelSize(resourceId);
		return height;
	}


    public static int[] getScreenSize(Context context, boolean useDeviceSize)
    {

        int[] size = new int[2];

        WindowManager w = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display d = w.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        d.getMetrics(metrics);
        System.out.println("hzh metrics.densityDpi "+metrics.density);
// since SDK_INT = 1;
        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;

        if (!useDeviceSize)
        {
            size[0] = widthPixels;
            size[1] = heightPixels - getStatusBarHeight(context);

            return size;
        }

// includes window decorations (statusbar bar/menu bar)
        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17)
            try
            {
                widthPixels = (Integer) Display.class.getMethod("getRawWidth").invoke(d);
                heightPixels = (Integer) Display.class.getMethod("getRawHeight").invoke(d);
            } catch (Exception ignored)
            {
            }
// includes window decorations (statusbar bar/menu bar)
        if (Build.VERSION.SDK_INT >= 17)
            try
            {
                Point realSize = new Point();
                Display.class.getMethod("getRealSize", Point.class).invoke(d, realSize);
                widthPixels = realSize.x;
                heightPixels = realSize.y;
            } catch (Exception ignored)
            {
            }
        
        //2016-11-13新的修改
        
        if (navigationBarExist(((Activity)context))) {
			int navigationBarHeight = getNavigationBarHeight(context);
			heightPixels = heightPixels - navigationBarHeight;
		}
        size[0] = widthPixels;
        size[1] = heightPixels;

        
        return size;
    }

    /**
     * 获取屏幕参数
     * @param context 上下文
     * @return
     */
    public static float[] getScreenParams(Context context){
        float[] _screenParams = new float[3];
        WindowManager w = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display d = w.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        d.getMetrics(metrics);

        _screenParams[0] = metrics.density;
        _screenParams[1] = metrics.densityDpi;
        _screenParams[2] = metrics.scaledDensity;

        return _screenParams;
    }

    
    /** 
     * 判断是否有底部导航栏此方法在模拟器还是在真机都是完全正确 
     * 
     * @param activity 
     * @return 
     */  
    @SuppressLint("NewApi")
	public static boolean navigationBarExist(Activity activity) {  
        WindowManager windowManager = activity.getWindowManager();  
        Display d = windowManager.getDefaultDisplay();  
  
        DisplayMetrics realDisplayMetrics = new DisplayMetrics();  
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {  
            d.getRealMetrics(realDisplayMetrics);  
        }  
  
        int realHeight = realDisplayMetrics.heightPixels;  
        int realWidth = realDisplayMetrics.widthPixels;  
  
        DisplayMetrics displayMetrics = new DisplayMetrics();  
        d.getMetrics(displayMetrics);  
  
        int displayHeight = displayMetrics.heightPixels;  
        int displayWidth = displayMetrics.widthPixels;  
  
        return (realWidth - displayWidth) > 0 || (realHeight - displayHeight) > 0;  
    }  
    
    
}
