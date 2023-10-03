package com.example.ponchomovies.core.app

import com.example.ponchomovies.core.app.Constants.DEFAULT_SYSTEM_LOCALE_LANG
import com.example.ponchomovies.core.app.Constants.ARABIC_LOCALE_LANG
import com.example.ponchomovies.core.app.Constants.ENGLISH_LOCALE_LANG
import com.example.ponchomovies.core.app.Constants.SPAIN_LOCALE_LANG
import io.paperdb.Paper
import java.util.Locale

object AppPreferences {
    //region LanguageConfig
    //key
    const val APP_LANG = "AppLang"

    fun setLocale(lang: String) {
        Paper.book().write(APP_LANG, lang)
    }

    fun getLocale(): String {
        return when (Paper.book().read(APP_LANG, DEFAULT_SYSTEM_LOCALE_LANG)!!) {
            DEFAULT_SYSTEM_LOCALE_LANG -> {
                Locale.getDefault().language
            }

            ARABIC_LOCALE_LANG -> {
                ARABIC_LOCALE_LANG
            }

            ENGLISH_LOCALE_LANG -> {
                ENGLISH_LOCALE_LANG
            }

            SPAIN_LOCALE_LANG -> {
                SPAIN_LOCALE_LANG
            }

            else -> {
                ENGLISH_LOCALE_LANG
            }
        }
    }

    fun getSelectedLanguage(): String {
        return Paper.book().read(APP_LANG, Constants.DEFAULT_SYSTEM_LOCALE_LANG)!!
    }

    //endregion

    //region Theme

    const val APP_THEME = "AppTheme"

 /*   fun setTheme(theme: AppTheme) {
        Paper.book().write(APP_THEME, theme)
    }

    fun getTheme(): AppTheme {
        return Paper.book().read(APP_THEME, AppTheme.Default)!!
    }*/

    //endregion
}