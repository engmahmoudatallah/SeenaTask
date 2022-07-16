package com.seenatask.ui.adaptor

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.seenatask.R
import com.squareup.picasso.Picasso


@BindingAdapter("loadimage")
fun bindingImage(userImageView: ImageView, imageUri: String?) {

    Picasso.get()
        .load(imageUri)
        .fit()
        .placeholder(R.drawable.ic_movie)
        .error(R.drawable.ic_cloud_off)
        .into(userImageView)
}