package com.udacity.asteroidradar.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.domain.State
import com.udacity.asteroidradar.main.MainRecyclerViewAdapter

@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
    } else {
        imageView.setImageResource(R.drawable.ic_status_normal)
    }
}

@BindingAdapter("hazardDescription")
fun bindHazardDescription(imageView: ImageView, isHazardous: Boolean) {
    imageView.contentDescription = if (isHazardous) {
        imageView.resources.getString(R.string.potentially_hazardous_asteroid_image)
    } else {
        imageView.resources.getString(R.string.not_hazardous_asteroid_image)
    }
}

@BindingAdapter("pictureOfDayDescription")
fun bindPictureOfDayDescription(imageView: ImageView, title: String?) {
    imageView.contentDescription = if (!title.isNullOrEmpty()) {
        imageView.resources.getString(R.string.nasa_picture_of_day_content_description_format, title)
    } else {
        imageView.resources.getString(R.string.this_is_nasa_s_picture_of_day_showing_nothing_yet)
    }
}

@BindingAdapter("linkImage")
fun bindPoDImageLink(imageView: ImageView, imageUrl: String?) {
    if (imageUrl != null) {
        Picasso
            .with(imageView.context)
            .load(imageUrl)
            .placeholder(R.color.picture_of_day_placeholder)
            .into(imageView)
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Asteroid>?) {
    val adapter = recyclerView.adapter as MainRecyclerViewAdapter
    adapter.submitList(data)
}

@BindingAdapter("loaderState")
fun bindLoaderState(progressBar: ProgressBar, state: State?) {
    when (state) {
        State.LOADING -> progressBar.visibility = View.VISIBLE
        State.ERROR -> progressBar.visibility = View.GONE
        State.DONE -> progressBar.visibility = View.GONE
    }
}
