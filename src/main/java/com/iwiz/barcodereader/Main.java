package com.iwiz.barcodereader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

public class Main extends Activity {

    TextView res;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        res= findViewById(R.id.barcode_result);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0){
            if(resultCode == CommonStatusCodes.SUCCESS){
                if(data!=null){
                    Barcode barcode= data.getParcelableExtra("barcode");
                    res.setText("Barcode value:" +barcode);
                }else {

                }
            }
        }
    }

    public  void scanBarcode(View v){
        Intent intent = new Intent(this, ScanBarcodeActivity.class);
        startActivityForResult(intent,0);
    }
}
