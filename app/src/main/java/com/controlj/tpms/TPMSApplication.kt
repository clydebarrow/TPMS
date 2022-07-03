package com.controlj.tpms

import android.app.Activity
import android.app.Application
import android.net.http.HttpResponseCache
import android.os.Build
import android.os.Bundle
import com.controlj.appframework.AndroidBackgroundServiceProvider
import com.controlj.backend.LogCatLogger
import com.controlj.framework.*
import com.controlj.logging.AndroidCrashLogger
import com.controlj.logging.CJLog
import com.controlj.logging.FileLogger
import com.controlj.logging.HttpLogger
import com.controlj.ui.ViewModelStore
import java.io.File
import java.io.IOException

class TPMSApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidDeviceInformation(this)
        AndroidFilePaths(this, "TPMS")
        CJLog.deviceId = "${DeviceInformation().appName}-${Build.DEVICE}"
        CJLog.isDebug = BuildConfig.DEBUG
        AndroidCrashLogger.init(this)
        Thread.setDefaultUncaughtExceptionHandler(AndroidCrashLogger)
        CJLog.versionString = DeviceInformation().appVersion
        CJLog.buildNumber = DeviceInformation().appBuild
        CJLog.packageName = DeviceInformation().appName
        ViewModelStore.setup(FilePaths().dataDirectory)

        val logfile = File(File(filesDir, "logs"), this.javaClass.simpleName + ".log")
        CJLog.add(FileLogger(logfile))
        if (CJLog.isDebug) {
            CJLog.add(LogCatLogger())
        }
        AndroidBackgroundServiceProvider(this)

        if (CJLog.isDebug)
            CJLog.add(HttpLogger())
        try {
            val httpCacheDir = File(cacheDir, "http")
            val httpCacheSize = (15 * 1024 * 1024).toLong() // 15 MiB
            HttpResponseCache.install(httpCacheDir, httpCacheSize)
        } catch (e: IOException) {
            CJLog.logException(e)
        }
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity) {
                ApplicationState.current.data = ApplicationState.Visible
            }

            override fun onActivityResumed(activity: Activity) {
                ApplicationState.current.data = ApplicationState.Active
            }

            override fun onActivityStarted(activity: Activity) {
                ApplicationState.current.data = ApplicationState.Visible
            }

            override fun onActivityStopped(activity: Activity) {
                ApplicationState.current.data = ApplicationState.Background
            }

            override fun onActivityDestroyed(activity: Activity) {
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            }
        })
        ApplicationState.current.data = ApplicationState.Background

    }

    override fun onTerminate() {
        super.onTerminate()
        ViewModelStore.save()
    }
}