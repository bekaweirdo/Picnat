package com.picnat.core.locale

import com.picnat.core.R

enum class Language(
    var shortName: String,
    var displayName: String,
    var prefix: String,
    var serverPrefix: String,
    var imageId: Int
){
    EN(
        shortName = "EN",
        displayName = "English",
        prefix = "en",
        serverPrefix = "en-US",
        imageId = R.drawable.language_english_flag
    ),
    GE(
        shortName = "ქარ",
        displayName = "ქართული",
        prefix = "ka",
        serverPrefix = "ka-GE",
        imageId = R.drawable.language_georgian_flag
    ),
    FR(
        shortName = "FR",
        displayName = "Français",
        prefix = "fr",
        serverPrefix = "fr-FR",
        imageId = R.drawable.language_french_flag
    )
}