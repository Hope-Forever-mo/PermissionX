package com.permissionx.app

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.permissionx.runtimelibrary.PermissionX

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.maskeCallBtn).setOnClickListener {
            PermissionX.request(this,
                Manifest.permission.CALL_PHONE){ allGranted,deniedList ->
                if (allGranted){
                    call()
                }else{
                    Toast.makeText(this,"YOu denied $deniedList", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun call(){
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        }catch (e: SecurityException){
            e.printStackTrace()
        }
    }
}