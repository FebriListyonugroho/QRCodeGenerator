package com.example.qrcodegenerator

import android.graphics.Bitmap
import android.graphics.Point
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder

class MainActivity : AppCompatActivity() {

    private var TAG : String = "GenerateQrCode"
    private lateinit var edittxt : EditText
    private lateinit var qrimg :ImageView
    private lateinit var start : Button
    private lateinit var bitmap: Bitmap
    private lateinit var qrgEncoder: QRGEncoder
    private lateinit var inputvalue : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        qrimg = findViewById(R.id.qrcode)
        edittxt = findViewById(R.id.edittext)
        start = findViewById(R.id.createbtn)
        start.setOnClickListener { v ->

            inputvalue = edittxt.text.toString().trim()
            if (inputvalue.length > 0){
                val manager = getSystemService(WINDOW_SERVICE) as WindowManager
                val display = manager.defaultDisplay
                val point = Point()
                display.getSize(point)
                var width = point.x
                var height = point.y
                var smallerdimension = if (width < height) width else height
                smallerdimension = smallerdimension*3/4
                qrgEncoder = QRGEncoder(inputvalue, null, QRGContents.Type.TEXT,smallerdimension)

                bitmap = qrgEncoder.encodeAsBitmap()
                qrimg.setImageBitmap(bitmap)

            }else{
                edittxt.setError("Required")
            }

        }

    }
}
