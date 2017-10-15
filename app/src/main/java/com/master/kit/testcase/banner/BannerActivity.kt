package com.master.kit.testcase.banner

import android.os.Bundle
import butterknife.ButterKnife
import com.master.kit.R
import com.master.kit.engine.imageloader.ImageLoaderFactory
import com.master.kit.testcase.retrofit.EControlViewObserver
import com.master.kit.testcase.retrofit.IEResponse
import com.master.kit.testcase.retrofit.ServiceHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_banner.*
import tf.oof.com.service.base.BaseSlidingActivity


class BannerActivity : BaseSlidingActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner)
        ButterKnife.bind(this)
        requestBanner()
        responseLayout.setOnRetryListener { requestBanner() }
    }

    private fun requestBanner() {
        ServiceHolder
                .service
                .getBanner("1","2")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(HomeObserver(responseLayout,this))
    }
    override fun onStart() {
        super.onStart()
        banner.stopCycle()
    }

    override fun onStop() {
        super.onStop()
        banner.stopCycle()
    }

    companion object {
        private val TGA = Thread.currentThread().stackTrace[1].className
    }

    class HomeObserver(responseView: IEResponse, target: BannerActivity) : EControlViewObserver<BannerBean, BannerActivity>(responseView, target) {
        override fun onResponseSuccess(bean: BannerBean) {
            super.onResponseSuccess(bean)
            val list = bean.body.picList.map { it.picUrl }
            getTarget()?.banner!!.setUris(list) { url, view -> ImageLoaderFactory.create().display(getTarget(), url, view) }
        }
    }
}