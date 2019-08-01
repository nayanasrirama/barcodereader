package com.iwiz.barcodereader

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.vision.barcode.Barcode
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        barcode_result.text = "Barcode Result"
    }

    fun scanBarcode(v : View){
        val intent = Intent(this, ScanBarcodeActivity::class.java)
        startActivityForResult(intent,0)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 0){
            if(resultCode == CommonStatusCodes.SUCCESS){
                if(data!=null){
                    val barcode = data.getParcelableExtra<Barcode>("barcode")
                    barcode_result.text = "Barcode value:${barcode.displayValue}"
                }else{
                    barcode_result.text = "no barcode found"
                }
            }
        }
    }

}
