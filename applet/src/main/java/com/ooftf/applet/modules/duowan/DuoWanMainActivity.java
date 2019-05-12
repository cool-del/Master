package com.ooftf.applet.modules.duowan;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.applet.R;
import com.ooftf.applet.databinding.ActivityDuoWanMainBinding;
import com.ooftf.service.base.BaseActivity;
import com.ooftf.service.constant.RouterPath;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

@Route(path = RouterPath.Applet.Activity.DuoWanMain)
public class DuoWanMainActivity extends BaseActivity {
    ActivityDuoWanMainBinding binding;


    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DuowanMainViewHolder viewHolder = new DuowanMainViewHolder();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_duo_wan_main);
        binding.setViewHolder(viewHolder);
        DuoWanServiceManager.getDuoWanApiService().get(Constants.HOME_LIST)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .flatMap((Function<MainListResponse, ObservableSource<MainListResponse.DataBean>>) response -> Observable.fromIterable(response.getData()))
                .filter(dataBean -> "news".equals(dataBean.getType()))
                .toList()
                .subscribe((Consumer<List<MainListResponse.DataBean>>) viewHolder.items::addAll);
    }
}
