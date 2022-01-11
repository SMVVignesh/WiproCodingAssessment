package com.example.wiprocodingassessment.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.wiprocodingassessment.models.FactsModel

class FactsModelDiffUtils:DiffUtil.ItemCallback<FactsModel.Row>() {
    override fun areItemsTheSame(oldItem: FactsModel.Row, newItem: FactsModel.Row): Boolean {
        return oldItem.title == newItem.title &&  oldItem.description == newItem.description &&  oldItem.imageHref == newItem.imageHref
    }

    override fun areContentsTheSame(oldItem: FactsModel.Row, newItem: FactsModel.Row): Boolean {
        return oldItem.title == newItem.title &&  oldItem.description == newItem.description &&  oldItem.imageHref == newItem.imageHref
    }


}