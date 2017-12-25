package yfwl.cn.testfix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import yfwl.cn.testfix.tools.HzhFixTool;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        View _rootView = LayoutInflater.from(this).inflate(R.layout.activity_main,null);
        setContentView(_rootView);

      final  TextView _testTv = (TextView) this.findViewById(R.id.testtv);
//        _testTv.post(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("hzh "+_testTv.getWidth()+" "+_testTv.getHeight()+" "+_testTv.getTextSize());
//            }
//        });

        System.out.println("hzh "+_testTv.getWidth()+" "+_testTv.getHeight()+" "+_testTv.getTextSize());

        HzhFixTool.newInstance(this).fix(_rootView);

        Toast.makeText(this,_testTv.getTextSize()+"",Toast.LENGTH_LONG).show();
    }

    /**
     * 四射五入取整
     *
     * @param val
     * @return
     */
    private int fiveInAll(float val) {
        return Math.round(val);
    }
}
