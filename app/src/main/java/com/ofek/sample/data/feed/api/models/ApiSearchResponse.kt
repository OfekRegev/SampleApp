package com.ofek.sample.data.feed.api.models

import com.google.gson.annotations.SerializedName

data class ApiSearchResponse(
    @SerializedName("hits") val hits : List<ApiSearchPostItem>,
    @SerializedName("nbHits") val nbHits : Int,
    @SerializedName("page") val page : Int,
    @SerializedName("nbPages") val nbPages : Int,
    @SerializedName("hitsPerPage") val hitsPerPage : Int,
    @SerializedName("exhaustiveNbHits") val exhaustiveNbHits : Boolean,
    @SerializedName("exhaustiveTypo") val exhaustiveTypo : Boolean,
    @SerializedName("query") val query : String,
    @SerializedName("params") val params : String,
    @SerializedName("processingTimeMS") val processingTimeMS : Int
)