package com.picnat.core.locale

import com.picnat.core.data.shared_preferences.SharedPreferencesManagerImpl

class LocaleManager(private val sharedPreference: SharedPreferencesManagerImpl) {

    var languageOptions: List<Language> = listOf(Language.EN,Language.GE,Language.FR)
    var selectedLanguage: Language
        get() = getSavedLanguage() ?: Language.EN
        set(value) {
            saveLanguage(value)
        }

    private fun getSavedLanguage(): Language? {
        val languageCode = sharedPreference.getString(KEY_APP_LANGUAGE,null)
        return Language.values().firstOrNull{ language ->
            language.prefix == languageCode
        }
    }

    private fun saveLanguage(language: Language) {
        sharedPreference.writeString(KEY_APP_LANGUAGE, language.prefix)
    }

    companion object {
        private const val KEY_APP_LANGUAGE = "lang_sherf"
    }
}