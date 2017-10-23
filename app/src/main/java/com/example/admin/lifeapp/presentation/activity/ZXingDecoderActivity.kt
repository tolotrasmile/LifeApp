package com.example.admin.lifeapp.presentation.activity

import android.os.Bundle
import android.app.Activity
import android.widget.Toast
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView
import org.androidannotations.annotations.EActivity


/**
 * Created by admin on 23/10/2017.
 */
@EActivity
open class ZXingDecoderActivity : Activity(), ZXingScannerView.ResultHandler {

    private var mScannerView: ZXingScannerView? = null

    public override fun onCreate(state: Bundle?) {
        super.onCreate(state)

        // Programmatically initialize the scanner view
        mScannerView = ZXingScannerView(this)

        // Set the scanner view as the content view
        setContentView(mScannerView)
    }

    public override fun onResume() {
        super.onResume()
        // Register ourselves as a handler for scan results.
        mScannerView?.setResultHandler(this)
        mScannerView?.startCamera()          // Start camera on resume
    }

    public override fun onPause() {
        super.onPause()
        mScannerView?.stopCamera()           // Stop camera on pause
    }

    override fun handleResult(result: Result?) {

        Toast.makeText(this, result?.text, Toast.LENGTH_SHORT).show()
        mScannerView?.resumeCameraPreview(this)
    }

}