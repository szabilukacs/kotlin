package com.zoltanlorinczi.project_retrofit.manager

import android.content.Context
import android.content.SharedPreferences

/**
 * Contains SharedPreferences related utility methods.
 *
 * Author:  Zoltan Lorinczi
 * Date:    9/8/2021
 */
class SharedPreferencesManager(context: Context) {

    private val SHARED_PREFERENCES_NAME = "ThreeTrackerSharedPreferences"
    private var preferences: SharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    companion object {
        const val KEY_TOKEN = "SHARED_PREFERENCES_KEY_TOKEN"
        const val KEY_ID = "SHARED_PREFERENCES_KEY_ID"
        const val KEY_LAST_NAME = "SHARED_PREFERENCES_KEY_LAST_NAME"
        const val KEY_FIRST_NAME = "SHARED_PREFERENCES_KEY_FIRST_NAME"
        const val KEY_EMAIL = "SHARED_PREFERENCES_KEY_EMAIL"
        const val KEY_TYPE = "SHARED_PREFERENCES_KEY_TYPE"
        const val KEY_LOCATION = "SHARED_PREFERENCES_KEY_LOCATION"
        const val KEY_PHONE_NUMBER = "SHARED_PREFERENCES_KEY_PHONE_NUMBER"
        const val KEY_DEPARTMENT_ID = "SHARED_PREFERENCES_DEPARTMENT_ID"
        const val KEY_IMAGE = "SHARED_PREFERENCES_KEY_IMAGE"
    }

    fun putStringValue(key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }

    fun getStringValue(key: String, defaultValue: String): String? {
        return preferences.getString(key, defaultValue)
    }
    fun putIntValue(key: String, value: Int) {
        preferences.edit().putInt(key, value).apply()
    }
    fun getIntValue(key: String, defaultValue: Int): Int? {
        return preferences.getInt(key, defaultValue)
    }
}