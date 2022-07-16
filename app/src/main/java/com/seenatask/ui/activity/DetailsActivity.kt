package com.seenatask.ui.activity

import android.annotation.SuppressLint
import android.text.Html
import android.util.Log
import android.view.View
import com.seenatask.base.BaseActivity
import com.seenatask.databinding.ActivityDetailsBinding
import com.seenatask.ui.dialog.FullScreenImage
import com.squareup.picasso.Picasso

class DetailsActivity : BaseActivity() {
    lateinit var binding: ActivityDetailsBinding

    /**
     * for support onCreate method for all subClasses
     */
    private val TAG = "DetailsActivity"

    @SuppressLint("SetTextI18n", "NewApi")
    override fun initializeComponents() {

        title?.text = "Movie details"
        val bundle = intent.extras

        val title = bundle?.getString("title")
        val publishedBy = bundle?.getString("published_by")
        val rating = bundle?.getString("rating")
        val summary = bundle?.getString("summary")
        val date = bundle?.getString("date")
        val url = bundle?.getString("url")

        Log.d(TAG, "initializeComponents: $url")

        Picasso.get().load(url).fit().into(binding.imgMovie)
        binding.tvTitle.text =
            Html.fromHtml("Title  <font color='gray'>$title</font>", Html.FROM_HTML_MODE_COMPACT)
        binding.tvPublishedBy.text =
            Html.fromHtml("Published By  <font color='gray'>$publishedBy</font>",
                Html.FROM_HTML_MODE_COMPACT)
        binding.tvRating.text =
            Html.fromHtml("Rating  <font color='gray'>$rating</font>",
                Html.FROM_HTML_MODE_COMPACT)

        binding.tvSummary.text =
            Html.fromHtml("Summary <font color='gray'>$summary</font>",
                Html.FROM_HTML_MODE_COMPACT)

        binding.tvDate.text =
            Html.fromHtml("Date <font color='gray'>$date</font>",
                Html.FROM_HTML_MODE_COMPACT)



        binding.imgMovie.setOnClickListener {
            FullScreenImage.newInstance(url.toString())
                .show(supportFragmentManager, "")

        }
    }

    /**
     * used with ViewBinding to return binding.getRoot() from subClasses
     *
     * @return the ViewBinding root
     */
    override fun getLayoutRoot(): View {
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    /**
     * @return true if you need support toolbar in subClass or return false if not
     */
    override fun isToolBarEnabled(): Boolean = true

    /**
     * @return true if you need support back in toolbar in subClass or return false if not
     */
    override fun isEnableBack(): Boolean = true

    /**
     * @return true if you need support bottomBar in subClass or return false if not
     */
    override fun isBottomNavBarEnabled(): Boolean = false

    /**
     * @return true if you need support recyclerView in subClass or return false if not
     */
    override fun isRecyclerViewEnabled(): Boolean = false
}