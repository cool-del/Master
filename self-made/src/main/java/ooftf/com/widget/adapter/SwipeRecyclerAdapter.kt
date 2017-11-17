package ooftf.com.widget.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter
import ooftf.com.widget.R
import ooftf.com.widget.bean.SwipeBean
import javax.inject.Inject

/**
 * Created by 99474 on 2017/11/7 0007.
 */
class SwipeRecyclerAdapter : RecyclerSwipeAdapter<SwipeRecyclerAdapter.ViewHolder> {
    var context: Context

    @Inject constructor(context: Context) {
        this.context = context
        inflater = LayoutInflater.from(context)
    }

    var body = ArrayList<SwipeBean>()
    override fun getItemCount(): Int {
        return body.size
    }

    override fun getSwipeLayoutResourceId(position: Int): Int {
        return R.id.swipeLayout
    }

    var inflater: LayoutInflater
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_swiper, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.content.text = body[position].position.toString()
        mItemManger.bindView(holder.itemView, position)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var content = itemView.findViewById<TextView>(R.id.content)
    }
}
