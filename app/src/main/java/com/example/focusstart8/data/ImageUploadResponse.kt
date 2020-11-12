package com.example.focusstart8.data

data class ImageUploadResponse(
    val data: Data,
    val success: Boolean,
    val status: Long
)

data class Data(
    val id: String,
    val title: Any? = null,
    val description: Any? = null,
    val datetime: Long,
    val type: String,
    val animated: Boolean,
    val width: Long,
    val height: Long,
    val size: Long,
    val views: Long,
    val bandwidth: Long,
    val vote: Any? = null,
    val favorite: Boolean,
    val nsfw: Any? = null,
    val section: Any? = null,
    val accountURL: Any? = null,
    val accountID: Long,
    val isAd: Boolean,
    val inMostViral: Boolean,
    val tags: List<Any?>,
    val adType: Long,
    val adURL: String,
    val inGallery: Boolean,
    val deletehash: String,
    val name: String,
    val link: String
)