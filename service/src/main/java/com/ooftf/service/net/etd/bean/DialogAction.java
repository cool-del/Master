package com.ooftf.service.net.etd.bean;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author ooftf
 * @date 2018/9/24 0024
 **/
public class DialogAction<T> implements ObservableTransformer<T, T> {
    Activity activity;
    ProgressDialog progressDialog;
    CharSequence message;

    public DialogAction(Activity activity) {
        this.activity = activity;
        message = "加载中...";
    }

    public DialogAction(Activity activity, CharSequence message) {
        this.activity = activity;
        this.message = message;
    }

    @Override
    public ObservableSource apply(Observable upstream) {
        return upstream.doOnSubscribe(disposable -> {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(activity);
                progressDialog.setMessage(message);

            }
            progressDialog.show();
        }).doOnTerminate(() -> {
            if (progressDialog != null) {
                progressDialog.dismiss();

            }
        });
    }
}
