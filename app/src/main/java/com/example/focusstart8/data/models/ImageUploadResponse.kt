package com.example.focusstart8.data.models

data class ImageUploadResponse(
    val data: Data,
    val success: Boolean,
    val status: Long
)

data class Data(
    val id: String,
    val title: String? = null,
    val description: String? = null,
    val datetime: Long,
    val type: String,
    val animated: Boolean,
    val width: Long,
    val height: Long,
    val size: Long,
    val views: Long,
    val bandwidth: Long,
    val vote: String? = null,
    val favorite: Boolean,
    val nsfw: Boolean? = null,
    val section: String? = null,
    val accountURL: String? = null,
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