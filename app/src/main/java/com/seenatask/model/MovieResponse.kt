package com.seenatask.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(

    @field:SerializedName("copyright")
    val copyright: String? = null,

    @field:SerializedName("has_more")
    val hasMore: Boolean? = null,

    @field:SerializedName("results")
    val results: List<ResultsItem?>? = null,

    @field:SerializedName("num_results")
    val numResults: Int? = null,

    @field:SerializedName("status")
    val status: String? = null,
)

data class ResultsItem(

    @field:SerializedName("multimedia")
    val multimedia: Multimedia? = null,

    @field:SerializedName("date_updated")
    val dateUpdated: String? = null,

    @field:SerializedName("display_title")
    val displayTitle: String? = null,

    @field:SerializedName("link")
    val link: Link? = null,

    @field:SerializedName("publication_date")
    val publicationDate: String? = null,

    @field:SerializedName("summary_short")
    val summaryShort: String? = null,

    @field:SerializedName("critics_pick")
    val criticsPick: Int? = null,

    @field:SerializedName("byline")
    val byline: String? = null,

    @field:SerializedName("headline")
    val headline: String? = null,

    @field:SerializedName("mpaa_rating")
    val mpaaRating: String? = null,

    @field:SerializedName("opening_date")
    val openingDate: String? = null,
)

data class Multimedia(

    @field:SerializedName("src")
    val src: String? = null,

    @field:SerializedName("width")
    val width: Int? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("height")
    val height: Int? = null,
)

data class Link(

    @field:SerializedName("suggested_link_text")
    val suggestedLinkText: String? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("url")
    val url: String? = null,
)
