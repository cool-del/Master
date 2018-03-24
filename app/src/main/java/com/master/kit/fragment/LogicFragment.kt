package com.master.kit.fragment

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.service.base.BaseHomeFragment
import com.ooftf.service.bean.ScreenItemBean

/**
 * Created by master on 2017/9/26 0026.
 */
@Route(path = "/main/logic")
class LogicFragment : BaseHomeFragment() {
    override fun initData() {
        adapter.add(ScreenItemBean("/main/design"))
        adapter.add(ScreenItemBean("/main/viewPager"))
        adapter.add(ScreenItemBean("/main/signIn"))
        adapter.add(ScreenItemBean("/main/webView"))
        adapter.add(ScreenItemBean("/main/download"))
        adapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(): LogicFragment {
            val args = Bundle()
            val fragment = LogicFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
