package com.ooftf.master.source.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.service.base.BaseListFragment
import com.ooftf.service.bean.ScreenItemBean

/**
 *
 * Created by master on 2017/9/26 0026.
 */
@Route(path = "/source/fragment/source")
class SourceFragment : BaseListFragment() {
    override fun initData() {
        adapter.add((ScreenItemBean("/source/activity/rxJava", "RxJava源码分析")))
        adapter.add((ScreenItemBean("/source/activity/okHttp", "OkHttp未完成")))
        adapter.add((ScreenItemBean("/source/activity/proxy", "动态代理")))
        adapter.notifyDataSetChanged()
    }
}
