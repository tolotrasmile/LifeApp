package com.example.admin.lifeapp

import android.support.v7.app.AppCompatActivity
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EActivity


@EActivity(R.layout.activity_main)
open class MainActivity : AppCompatActivity() {

    @Click(R.id.clickMe)
    open fun clickMe() {
    }
}
