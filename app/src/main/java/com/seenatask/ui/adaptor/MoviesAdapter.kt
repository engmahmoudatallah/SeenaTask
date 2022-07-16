package com.seenatask.ui.adaptor

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.seenatask.BR
import com.seenatask.R
import com.seenatask.databinding.NewsItemBinding
import com.seenatask.model.ResultsItem
import com.seenatask.ui.activity.DetailsActivity
import com.seenatask.ui.dialog.FullScreenImage


class MoviesAdapter(
    private var list: List<ResultsItem?>?,
    mFragmentManager: FragmentManager,
) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolderClass>() {

    val supportFragmentManager: FragmentManager = mFragmentManager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val binding: NewsItemBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.news_item,
                parent,
                false
            )
        return ViewHolderClass(binding)

    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val model = list!![position]
        holder.bind(model)

        holder.rowRvRelatedProductsBinding.imgMovie.setOnClickListener {
            FullScreenImage.newInstance(model?.multimedia?.src.toString())
                .show(supportFragmentManager, "")
        }


        holder.itemView.setOnClickListener {
            val myIntent = Intent(it.context, DetailsActivity::class.java)
            myIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            val bundle = Bundle()
            bundle.putString("title", model?.displayTitle.toString())
            bundle.putString("published_by", model?.publicationDate.toString())
            bundle.putString("rating", model?.mpaaRating.toString())
            bundle.putString("summary", model?.summaryShort.toString())
            bundle.putString("date", model?.publicationDate.toString())
            bundle.putString("url", model?.multimedia?.src.toString())
            // bundle.putStringArray("images", model?.displayTitle!!.toTypedArray())
            myIntent.putExtras(bundle)
            it.context.startActivity(myIntent)
        }

    }


    override fun getItemCount(): Int {
        return if (list.isNullOrEmpty())
            0
        else
            list!!.size
    }


    class ViewHolderClass(binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var rowRvRelatedProductsBinding: NewsItemBinding = binding
        fun bind(obj: Any?) {
            rowRvRelatedProductsBinding.setVariable(BR.model, obj)
            rowRvRelatedProductsBinding.executePendingBindings()
        }


    }
}

