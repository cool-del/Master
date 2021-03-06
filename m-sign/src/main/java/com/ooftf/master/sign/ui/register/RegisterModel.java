package com.ooftf.master.sign.ui.register;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ooftf.service.engine.router.assist.SignAssistBean;
import com.ooftf.service.engine.router.service.IMultiSignService;

import javax.inject.Inject;

import io.reactivex.Observable;

public class RegisterModel implements RegisterContract.IModel {
    @Autowired
    IMultiSignService multiAccountService;

    @Inject
    public RegisterModel() {
        ARouter.getInstance().inject(this);
    }

    @Override
    public Observable<SignAssistBean> register(String channelId, String username, String password) {
        return multiAccountService.getService(channelId).register(username, password).toObservable();
    }

    @Override
    public String getChannelName(String channelId) {
        return multiAccountService.getChannelName(channelId);
    }
}
