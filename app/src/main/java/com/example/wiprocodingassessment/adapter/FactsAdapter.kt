package com.example.wiprocodingassessment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wiprocodingassessment.R
import com.example.wiprocodingassessment.databinding.InflateFactsItemBinding
import com.example.wiprocodingassessment.models.FactsModel

class FactsAdapter:RecyclerView.Adapter<FactsAdapter.FactsViewHolder>() {
    private val myList:ArrayList<FactsModel.Row?> = ArrayList()

    inner class FactsViewHolder(private val inflateFactsItemBinding: InflateFactsItemBinding):RecyclerView.ViewHolder(inflateFactsItemBinding.root){
        fun onBind(data: FactsModel.Row?){
            inflateFactsItemBinding.txtTitle.text = data?.title
            inflateFactsItemBinding.txtDesc.text = data?.description
            data?.imageHref.let {img->
                inflateFactsItemBinding.imgAvatar.let { imageView ->
                    Glide.with(imageView.context).load(img?:R.drawable.default_image).error(R.drawable.default_image).into(imageView)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsViewHolder {

       val  mViewDataBinding :InflateFactsItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.inflate_facts_item, parent, false)
        return FactsViewHolder(mViewDataBinding)
    }

    override fun onBindViewHolder(holder: FactsViewHolder, position: Int) {
        holder.onBind(myList[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    fun updateList(myList:ArrayList<FactsModel.Row?>){
        this.myList.clear()
        this.myList.addAll(myList)
        notifyDataSetChanged()
    }
}