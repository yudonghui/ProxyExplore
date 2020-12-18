package com.ydh.proxyexplore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEtInput;
    private Button mBtnCopy;
    private Button mBtnShowPaste;
    private LinearLayout mActivityMain;
    ClipboardManager clipboardManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
    }

    private void initView() {
        mEtInput = (EditText) findViewById(R.id.et_input);
        mBtnCopy = (Button) findViewById(R.id.btn_copy);
        mBtnShowPaste = (Button) findViewById(R.id.btn_show_paste);
        mActivityMain = (LinearLayout) findViewById(R.id.activity_main);

        mBtnCopy.setOnClickListener(this);
        mBtnShowPaste.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
        }
    }
}
