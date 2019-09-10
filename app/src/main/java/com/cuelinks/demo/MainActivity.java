package com.cuelinks.demo;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cuelinks.CuelinksListener;
import com.cuelinks.CuelinksMovementMethod;
import com.cuelinks.CuelinksSpan;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Yadnyesh on 2019-08-27.
 */
public class MainActivity extends AppCompatActivity implements CuelinksListener {

    private static final String TAG = "MainActivity";
    @BindView(R.id.subIdInputLayout)
    protected TextInputLayout mSubIdInputLayout;
    @BindView(R.id.subIdEditText)
    protected TextInputEditText mSubIdEditText;
    @BindView(R.id.normalTextInputLayout)
    protected TextInputLayout mNormalTextInputLayout;
    @BindView(R.id.normalTextEditText)
    protected TextInputEditText mNormalTextEditText;
    @BindView(R.id.htmlTextInputLayout)
    protected TextInputLayout mHtmlTextInputLayout;
    @BindView(R.id.htmlTextEditText)
    protected TextInputEditText mHtmlTextEditText;
    @BindView(R.id.normalTextView)
    protected TextView mNormalTextView;
    @BindView(R.id.htmlTextView)
    protected TextView mHtmlTextView;

    private Unbinder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binder = ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonGetText)
    void onGetNormalText() {
        if (mNormalTextEditText.getText() == null) return;
        mNormalTextView.setText(mNormalTextEditText.getText());
        mNormalTextView.setMovementMethod(CuelinksMovementMethod.getInstance(MainActivity.this, getSubId()));
    }

    @OnClick(R.id.buttonGetOverrideText)
    void onGetOverrideText() {
        if (mNormalTextEditText.getText() == null) return;
        mNormalTextView.setText(mNormalTextEditText.getText());
        mNormalTextView.setMovementMethod(CuelinksMovementMethod.getInstance(MainActivity.this, this, getSubId()));
    }

    @OnClick(R.id.buttonGetHtmlText)
    void onGetHtmlText() {
        if (mHtmlTextEditText.getText() == null) return;
        mHtmlTextView.setText(CuelinksSpan.affiliateHrefUrlsFromHtml(mHtmlTextEditText.getText(), mHtmlTextView, getSubId()));
    }

    @OnClick(R.id.buttonGetOverrideHtmlText)
    void onGetHtmlOverrideText() {
        if (mHtmlTextEditText.getText() == null) return;
        mHtmlTextView.setText(CuelinksSpan.affiliateHrefUrlsFromHtml(mHtmlTextEditText.getText(), mHtmlTextView, this, getSubId()));
    }

    @Override
    public void openUrl(String url, Context context) {
        Log.e(TAG, "openUrl: " + url);
        Toast.makeText(this, url, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binder.unbind();
    }

    /**
     * @return Array of comma separated Sub Ids
     */
    private String[] getSubId() {
        if (mSubIdEditText.getText() == null) return null;
        String[] subId = null;
        if (!TextUtils.isEmpty(mSubIdEditText.getText())) {
            subId = mSubIdEditText.getText().toString().split("\\s*,\\s*");
        }
        return subId;
    }
}
