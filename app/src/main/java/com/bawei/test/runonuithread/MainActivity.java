package com.bawei.test.runonuithread;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * autour: 曾光春
 * date: 2016/12/2 19:20
 * update: 2016/12/2
 * Activity.runOnUIThread(Runnable)
 */
public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private Activity mActivity;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        init();
    }

    private void init() {
        mActivity = this;
        mTextView = (TextView) findViewById(R.id.textView);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(new OnClickListenerImpl());//点击事件
        mTextView.post(new Runnable() {
            @Override
            public void run() {
                mTextView.setText("post,TextView展示");
            }
        });
    }

   /* public final void runOnUiThread(Runnable action) {
        throw new RuntimeException("Stub!");
    }*/
    private class OnClickListenerImpl implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            new Thread(){//创建线程
                public void run() {
                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTextView.setText("我的名字,曾光春");
                        }
                    });
                };
            }.start();
        }
    }
}
