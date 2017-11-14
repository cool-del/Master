package com.master.kit.testcase.retrofit

import android.content.Context
import android.graphics.BitmapFactory
import android.util.AttributeSet
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import com.master.kit.R
import com.master.kit.bean.PicCaptchaBean
import io.reactivex.android.schedulers.AndroidSchedulers
import tf.oof.com.service.base.BaseActivity

/**
 * Created by master on 2017/10/20 0020.
 */
class PicCaptchaLayout :RelativeLayout ,IEResponse<PicCaptchaBean>{
    override fun onAttachedToWindow() {
        Log.e("onAttachedToWindow", "onAttachedToWindow")
        super.onAttachedToWindow()
        picCaptchaRequest()
    }

    override fun onFinishInflate() {
        Log.e("onFinishInflate", "onFinishInflate")
        super.onFinishInflate()
    }

    init {
        post {
            Log.e("post", "post")
        }
    }
    private val dialog:EResponseDialog by lazy {
        EResponseDialog(activity)
    }
    override fun onLoading() {
        pic.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
    }

    private fun picCaptchaRequest() {
        ServiceHolder.service.picCaptcha().observeOn(AndroidSchedulers.mainThread())
                .subscribe(PicCaptchaObserver(this, activity))
    }
    override fun onError() {
        Log.e("onError", "onError")
        pic.visibility = View.VISIBLE
        progressBar.visibility = View.INVISIBLE
        pic.setImageResource(R.drawable.vector_net_error)
    }

    override fun onResponse() {
        pic.visibility = View.VISIBLE
        progressBar.visibility = View.INVISIBLE
    }

    override fun onResponseSuccess(bean: PicCaptchaBean) {
        bean.body?.let {
            val bytes = Base64.decode(
                    it.indentify, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            pic.setImageBitmap(bitmap)
        }
    }

    override fun onResponseFailOffSiteLogin(bean: PicCaptchaBean) {
        pic.setImageResource(R.drawable.vector_net_error)
        dialog.onResponseFailOffSiteLogin(bean)
    }

    override fun onResponseFailSessionOverdue(bean: PicCaptchaBean) {
        pic.setImageResource(R.drawable.vector_net_error)
        dialog.onResponseFailSessionOverdue(bean)
    }

    override fun onResponseFailMessage(bean: PicCaptchaBean) {
        Log.e("onResponseFailMessage", "onResponseFailMessage")
        pic.setImageResource(R.drawable.vector_net_error)
        //dialog.onResponseFailMessage(bean)
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)
    private var pic:ImageView
    private var progressBar:ProgressBar
    private var activity:BaseActivity
    init {
        LayoutInflater.from(context).inflate(R.layout.layout_pic_captcha,this)
        pic = findViewById(R.id.imageView)
        progressBar = findViewById(R.id.progressBar)
        activity = context as BaseActivity
        pic.setOnClickListener { picCaptchaRequest() }
    }

    class PicCaptchaObserver(viewResponse: IEResponse<PicCaptchaBean>, target: BaseActivity) : EControlViewObserver<PicCaptchaBean, BaseActivity>(viewResponse, target)
}