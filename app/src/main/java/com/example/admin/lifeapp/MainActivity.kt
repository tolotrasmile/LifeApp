package com.example.admin.lifeapp

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EActivity
import org.greenrobot.eventbus.EventBus
import com.example.admin.lifeapp.service.event.MessageEvent
import com.example.admin.lifeapp.service.preference.PreferencesHelper
import com.example.admin.lifeapp.service.preference.PreferencesHelper.set
import com.example.admin.lifeapp.service.preference.PreferencesHelper.get
import org.androidannotations.annotations.AfterViews
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.toast

@EActivity(R.layout.activity_main)
open class MainActivity : AppCompatActivity() {

    lateinit var prefs: SharedPreferences

    @AfterViews
    fun afterViews() {
        prefs = PreferencesHelper.defaultPrefs(this)
    }

    public override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    public override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Click(R.id.clickMe)
    open fun clickMe() {
        val count = prefs["count", 0]
        EventBus.getDefault().post(MessageEvent("Event $count"))
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent) {
        toast(event.message)
    }
}
