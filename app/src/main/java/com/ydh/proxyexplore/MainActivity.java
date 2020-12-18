package com.ydh.proxyexplore;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ydh.proxyexplore.observermodel.ObserverActivity;
import com.ydh.proxyexplore.test.AnnotionUtils;
import com.ydh.proxyexplore.test.InjectView;
import com.ydh.proxyexplore.test.onClick;

import java.lang.reflect.Proxy;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @InjectView(R.id.et_input)
    EditText mEtInput;
    @InjectView(R.id.btn_copy)
    Button mBtnCopy;
    @InjectView(R.id.btn_show_paste)
    Button mBtnShowPaste;
    @InjectView(R.id.btn_skip)
    Button mBtnSkip;
    @InjectView(R.id.activity_main)
    LinearLayout mActivityMain;
    ClipboardManager clipboardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnnotionUtils.injectView(this);
        initView();
        ClipboardHook.hookService(this);
        clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
    }

    //申请权限
    private void requestAlertWindowPermission() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, 1);
    }

    private void initView() {

        mBtnCopy.setOnClickListener(this);
        mBtnShowPaste.setOnClickListener(this);
        mBtnSkip.setOnClickListener(this);
    }

    @onClick({R.id.tv_observer, R.id.btn_copy, R.id.btn_show_paste, R.id.btn_skip})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_observer:
                startActivity(ObserverActivity.class);
                break;
            case R.id.btn_copy:

                String input = mEtInput.getText().toString().trim();
                if (TextUtils.isEmpty(input)) {
                    Toast.makeText(this, "input不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                //复制
                ClipData clip = ClipData.newPlainText("simple text", mEtInput.getText().toString());
                clipboardManager.setPrimaryClip(clip);
                break;
            case R.id.btn_show_paste:
                //黏贴
                clip = clipboardManager.getPrimaryClip();
                if (clip != null && clip.getItemCount() > 0) {
                    mEtInput.setText(clip.getItemAt(0).getText() + "");
                }
                break;
            case R.id.btn_skip:
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
                break;
        }
    }

    private void startActivity(Class aClass) {
        startActivity(new Intent(this, aClass));
    }
}
