package com.iwiz.barcodereader

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.SurfaceHolder
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import kotlinx.android.synthetic.main.activity_scan_barcode.*
import java.io.IOException

class ScanBarcodeActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_barcode)

        cameraSource()
    }

    private fun cameraSource() {
        val barcodeDetector = BarcodeDetector.Builder(this).build()
        val cameraSource =
            CameraSource.Builder(this, barcodeDetector).setAutoFocusEnabled(true).setRequestedPreviewSize(1600, 1024)
                .build()

        caera_preview.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                if(ActivityCompat.checkSelfPermission(this@ScanBarcodeActivity, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                    return
                }
                try{
                    cameraSource.start(caera_preview.holder)
                }
                catch (e: IOException){
                    e.printStackTrace()
                }

            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                cameraSource.stop()
            }
        })


        barcodeDetector.setProcessor(object : Detector.Processor<Barcode> {
            override fun release() {

            }

            override fun receiveDetections(detections: Detector.Detections<Barcode>) {
                val sparseArray = detections.detectedItems

                if(sparseArray.size()>0){
                    val intent = Intent()
                    intent.putExtra("barcode", sparseArray.valueAt(0))
                    setResult(CommonStatusCodes.SUCCESS,intent)
                    finish()
                }
            }
        })
    }


}