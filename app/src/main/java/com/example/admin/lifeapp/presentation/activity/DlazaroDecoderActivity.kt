package com.example.admin.lifeapp.presentation.activity

import android.app.Activity
import android.graphics.PointF
import com.dlazaro66.qrcodereaderview.QRCodeReaderView
import com.example.admin.lifeapp.R
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.ViewById
import org.jetbrains.anko.toast

/**
 * Created by admin on 23/10/2017.
 */
@EActivity(R.layout.activity_decoder)
open class DlazaroDecoderActivity : Activity(), QRCodeReaderView.OnQRCodeReadListener {

    @ViewById(R.id.reader)
    lateinit var reader: QRCodeReaderView

    @AfterViews
    fun afterViews() {
        reader.setOnQRCodeReadListener(this)

        // Use this function to enable/disable decoding
        reader.setQRDecodingEnabled(true);

        // Use this function to change the autofocus interval (default is 5 secs)
        reader.setAutofocusInterval(2000L)

        // Use this function to enable/disable Torch
        reader.setTorchEnabled(true)

        // Use this function to set front camera preview
        reader.setFrontCamera()

        // Use this function to set back camera preview
        reader.setBackCamera()
    }

    override fun onQRCodeRead(text: String?, points: Array<out PointF>?) {
        toast(text.toString())
    }

    override fun onResume() {
        super.onResume()
        reader.startCamera()
    }

    override fun onPause() {
        super.onPause()
        reader.stopCamera()
    }


}