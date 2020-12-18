package com.ydh.proxyexplore.observermodel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ydh.proxyexplore.R;
import com.ydh.proxyexplore.test.AnnotionUtils;
import com.ydh.proxyexplore.test.onClick;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ydh on 2020/12/18
 * 观察者模式使用率之所以比较高，是因为它在Android中能达到一个十分明显的解耦效果。
 * 让观察者和被观察者逻辑分开。使得UI层和业务逻辑清晰。更加利于我们拓展代码。
 * <p>
 * 例如：
 * 观察者：OnClickListener；
 * 被观察者：Button；
 * 订阅（或注册）：setOnClickListener()。
 */
public class ObserverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observer);
        AnnotionUtils.injectView(this);
        //观察者
        IObserver<String> iObserver = new IObserver<String>() {

            @Override
            public void onSucess(String o) {
                Log.e("成功了", o + "");
            }

            @Override
            public void onFail(String o) {
                Log.e("失败了", o + "");
            }
        };
        //被观察者
        IObservable iObservable = new IObservable() {
            @Override
            public void subscrible(IObserver t) {
                t.onSucess("中国");
                t.onFail("服务器错误");
            }
        };
        //订阅
        iObservable.subscrible(iObserver);
    }

    @onClick({R.id.tv_change})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_change:

                break;
        }
    }


}
