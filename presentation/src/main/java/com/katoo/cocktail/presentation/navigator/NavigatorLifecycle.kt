package com.katoo.cocktail.presentation.navigator

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class NavigatorLifecycle(
    app: Application
) : Application.ActivityLifecycleCallbacks {

    var activity: AppCompatActivity? = null

    init {
        app.registerActivityLifecycleCallbacks(this)
    }

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
        activity = p0 as? AppCompatActivity
    }

    override fun onActivityStarted(p0: Activity) {
        activity = p0 as? AppCompatActivity
    }

    override fun onActivityResumed(p0: Activity) {
        activity = p0 as? AppCompatActivity
    }

    override fun onActivityPaused(p0: Activity) {
        // Do nothing
    }

    override fun onActivityStopped(p0: Activity) {
        // Do nothing
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
        // Do nothing
    }

    override fun onActivityDestroyed(p0: Activity) {
        activity = null
    }
}