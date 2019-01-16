package com.ooftf.widget.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter

/**
 * Created by 99474 on 2017/12/24 0024.
 */
class UltraPagerAdapter(supportFragmentManager: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentPagerAdapter(supportFragmentManager) {
    override fun getItem(position: Int): androidx.fragment.app.Fragment {
        return when (position) {
            0 -> ARouter.getInstance().build("/applet/fragment/app").navigation() as androidx.fragment.app.Fragment
            1 -> ARouter.getInstance().build("/debug/fragment/debug").navigation() as androidx.fragment.app.Fragment
            2 -> ARouter.getInstance().build("/source/fragment/source").navigation() as androidx.fragment.app.Fragment
            3 -> ARouter.getInstance().build("/other/fragment/other").navigation() as androidx.fragment.app.Fragment
            4 -> ARouter.getInstance().build("/widget/fragment/widget").navigation() as androidx.fragment.app.Fragment
            else -> ARouter.getInstance().build("/widget/fragment/widget").navigation() as androidx.fragment.app.Fragment
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "app"
            1 -> "debug"
            2 -> "source"
            3 -> "other"
            4 -> "widget"
            else -> "widget"
        }
    }

    fun getScrollView(parent: View, index: Int): View {
        return parent.findViewWithTag<View>(getPageTitle(index))
    }

    override fun getCount(): Int {
        return 5
    }
}