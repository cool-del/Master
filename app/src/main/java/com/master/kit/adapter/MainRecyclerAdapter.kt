package com.master.kit.adapter

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.master.kit.R
import com.master.kit.bean.ScreenItemBean
import tf.oof.com.service.base.BaseActivity
import tf.oof.com.service.base.CategoryRecyclerAdapter
import java.lang.reflect.InvocationTargetException


/**
 * Created by master on 2017/9/25 0025.
 */

open class MainRecyclerAdapter(private var baseActivity: BaseActivity, internal var stickyView: TextView) : CategoryRecyclerAdapter<ScreenItemBean, MainRecyclerAdapter.RecyclerHolder>() {
    override fun getCategory(position: Int)=getItem(position).category

    //LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
    var inflater: LayoutInflater = LayoutInflater.from(baseActivity)
    override fun onCreateViewHolderSecondary(parent: ViewGroup, viewType: Int): RecyclerHolder {
        val view = inflater.inflate(R.layout.item_recyclerview_main, parent, false)
        return RecyclerHolder(view)
    }


    override fun onBindViewHolderSecondary(holder: RecyclerHolder, position: Int) {
        val bean = getItem(position)
        when {
            position == 0 -> holder.title.visibility = View.VISIBLE//第一个title必显示
            bean.category == getItem(position - 1).category -> holder.title.visibility = View.GONE//如果和上一个属于同一个类别，不显示
            else -> holder.title.visibility = View.VISIBLE//如果和上一个不属于同一个类别，显示
        }
        holder.title.text = bean.category
        holder.describe.text = bean.describe
        holder.name.text = bean.name + "(" + bean.clz.simpleName + ")"
        holder.icon.setImageResource(bean.icon)
        if (bean.isIssue) {
            holder.issue.visibility = View.VISIBLE
        } else {
            holder.issue.visibility = View.INVISIBLE
        }

        holder.itemView.setOnClickListener {
            if (Activity::class.java.isAssignableFrom(bean.clz)) {
                baseActivity.startActivity(bean.clz)
            } else if (Dialog::class.java.isAssignableFrom(bean.clz)) {
                try {
                    val dialog = bean.clz.getConstructor(Context::class.java).newInstance(baseActivity) as Dialog
                    dialog.show()
                } catch (e: InstantiationException) {
                    e.printStackTrace()
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                } catch (e: InvocationTargetException) {
                    e.printStackTrace()
                } catch (e: NoSuchMethodException) {
                    e.printStackTrace()
                }

            }
        }
    }
    class RecyclerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name)
        var describe: TextView = itemView.findViewById(R.id.describe)
        var icon: ImageView = itemView.findViewById(R.id.icon)
        var issue: ImageView = itemView.findViewById(R.id.issue)
        var title: TextView = itemView.findViewById(R.id.stickyView)

    }
}
