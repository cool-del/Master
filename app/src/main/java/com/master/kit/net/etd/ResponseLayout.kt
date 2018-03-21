package com.master.kit.net.etd

import android.app.Activity
import android.content.Context
import android.support.v7.app.AlertDialog
import android.util.AttributeSet
import com.master.kit.bean.BaseBean
import com.master.kit.activity.MainActivity
import com.ooftf.hi.view.HiResponseLayout
/**
 * Created by master on 2017/10/12 0012.
 */
class ResponseLayout : HiResponseLayout<BaseBean>, ResponseView<BaseBean> {
    val activity: Activity by lazy {
        if (context is Activity) {
            context as Activity
        } else {
            throw IllegalAccessException("EResponseLayout 的context必须是 Activity")
        }

    }
    override fun onResponseSuccess(bean: BaseBean) {

    }
    override fun onResponseFailOffSiteLogin(bean: BaseBean) {
        showToMainDialog(bean)
    }

    override fun onResponseFailSessionOverdue(bean: BaseBean) {
        showToMainDialog(bean)
    }

    override fun onResponseFailMessage(bean: BaseBean) {
        AlertDialog
                .Builder(activity)
                .setMessage(bean.info)
                .setPositiveButton("确定", { dialog, _ -> dialog.dismiss() })
                .show()
    }

    private fun showToMainDialog(bean: BaseBean) {
        AlertDialog
                .Builder(activity)
                .setMessage(bean.info)
                .setNeutralButton("返回首页", { _, _ -> activity.startActivity(MainActivity.getStartIntent(activity)) })
                .show()
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)
}