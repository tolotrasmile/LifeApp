package com.example.admin.lifeapp.presentation.activity

import android.app.Activity
import android.os.Bundle
import com.example.admin.lifeapp.service.event.MessageEvent
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView
import org.androidannotations.annotations.EActivity
import org.greenrobot.eventbus.EventBus

/**
 * Created by admin on 23/10/2017.
 */
@EActivity
open class ZXingDecoderActivity : Activity(), ZXingScannerView.ResultHandler {

    private var reader: ZXingScannerView? = null

    public override fun onCreate(state: Bundle?) {
        super.onCreate(state)

        // Programmatically initialize the scanner view
        reader = ZXingScannerView(this)

        // Set the scanner view as the content view
        setContentView(reader)
    }

    public override fun onResume() {
        super.onResume()

        // Register ourselves as a handler for scan results.
        reader?.setResultHandler(this)

        // Start camera on resume
        reader?.startCamera()
    }

    public override fun onPause() {
        super.onPause()

        // Stop camera on pause
        reader?.stopCamera()
    }

    override fun handleResult(result: Result?) {
        EventBus.getDefault().post(MessageEvent(result?.text.toString()))
        reader?.resumeCameraPreview(this)
    }

}