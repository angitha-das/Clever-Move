package com.example.angitha.mygame.utils

import android.content.Context
import android.content.pm.PackageManager
import android.text.TextUtils
import android.util.Log

import com.google.firebase.remoteconfig.FirebaseRemoteConfig

import com.example.angitha.mygame.utils.Constants.KEY_CURRENT_VERSION
import com.example.angitha.mygame.utils.Constants.KEY_UPDATE_REQUIRED
import com.example.angitha.mygame.utils.Constants.KEY_UPDATE_URL

class ForceUpdateChecker internal constructor(private val context: Context,
                                              private val onUpdateNeededListener: OnUpdateNeededListener?) {

    fun check() {
        val remoteConfig = FirebaseRemoteConfig.getInstance()

        if (remoteConfig.getBoolean(KEY_UPDATE_REQUIRED)) {
            val currentVersion = remoteConfig.getString(KEY_CURRENT_VERSION)
            val appVersion = getAppVersion(context)
            val updateUrl = remoteConfig.getString(KEY_UPDATE_URL)
            if (onUpdateNeededListener != null) {
                if (!TextUtils.equals(currentVersion, appVersion)) {
                    onUpdateNeededListener.isUpdateNeeded(true)
                    onUpdateNeededListener.onUpdateNeeded(updateUrl)
                } else {
                    onUpdateNeededListener.isUpdateNeeded(false)
                }
            }
        } else {
            onUpdateNeededListener!!.isUpdateNeeded(false)
        }
    }

    private fun getAppVersion(context: Context): String {
        var result = ""

        try {
            result = context.packageManager
                    .getPackageInfo(context.packageName, 0)
                    .versionName
            result = result.replace("[a-zA-Z]|-".toRegex(), "")
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e(TAG, e.message)
        }

        return result
    }

    interface OnUpdateNeededListener {
        fun onUpdateNeeded(updateUrl: String)
        fun isUpdateNeeded(isUpdateNeeded: Boolean)
    }

    class Builder internal constructor(private val context: Context) {
        private var onUpdateNeededListener: OnUpdateNeededListener? = null

        fun onUpdateNeeded(onUpdateNeededListener: OnUpdateNeededListener): Builder {
            this.onUpdateNeededListener = onUpdateNeededListener
            return this
        }

        private fun build(): ForceUpdateChecker {
            return ForceUpdateChecker(context, onUpdateNeededListener)
        }

        fun check(): ForceUpdateChecker {
            val forceUpdateChecker = build()
            forceUpdateChecker.check()

            return forceUpdateChecker
        }
    }

    companion object {

        private val TAG = ForceUpdateChecker::class.java.simpleName

        fun with(context: Context): Builder {
            return Builder(context)
        }
    }
}
