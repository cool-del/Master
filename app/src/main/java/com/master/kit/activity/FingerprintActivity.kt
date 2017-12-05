package com.master.kit.activity

import android.content.Context
import android.hardware.fingerprint.FingerprintManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat
import android.support.v4.os.CancellationSignal
import android.widget.Toast
import com.master.kit.R
import kotlinx.android.synthetic.main.activity_fingerprint.*
import tf.oof.com.service.base.BaseActivity
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent



class FingerprintActivity : BaseActivity() {
    lateinit var fingerprintManager:FingerprintManagerCompat
    lateinit var cancellationSignal:CancellationSignal
    lateinit var callback:FingerprintManagerCompat.AuthenticationCallback
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fingerprint)
        fingerprintManager = FingerprintManagerCompat.from(this)
        if(!fingerprintManager.isHardwareDetected){
            describe.text = "你的手机暂不支持指纹识别"
        }else if (!fingerprintManager.hasEnrolledFingerprints()){
            describe.text = "你的手机还没有录入指纹"
        }else{
            describe.text = "可以指纹识别"
        }
        cancellationSignal = CancellationSignal()
        callback = object : FingerprintManagerCompat.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: FingerprintManagerCompat.AuthenticationResult?) {
                super.onAuthenticationSucceeded(result)
                Toast.makeText(this@FingerprintActivity, "onAuthenticationSucceeded", Toast.LENGTH_SHORT).show()
                finish()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Toast.makeText(this@FingerprintActivity, "onAuthenticationFailed", Toast.LENGTH_SHORT).show()
            }

            override fun onAuthenticationError(errMsgId: Int, errString: CharSequence?) {
                super.onAuthenticationError(errMsgId, errString)
                Toast.makeText(this@FingerprintActivity, "onAuthenticationError", Toast.LENGTH_SHORT).show()
            }

            override fun onAuthenticationHelp(helpMsgId: Int, helpString: CharSequence?) {
                super.onAuthenticationHelp(helpMsgId, helpString)
                Toast.makeText(this@FingerprintActivity, "onAuthenticationHelp", Toast.LENGTH_SHORT).show()
            }

        }

        button.setOnClickListener {
            openFingerPrintSettingPage(this)
        }
    }

    override fun onStart() {
        super.onStart()
        startFingerprint()
    }

    override fun onStop() {
        super.onStop()
        cancellationSignal.cancel()
    }
    private fun startFingerprint() {
        fingerprintManager.authenticate(null, 0, cancellationSignal, callback, Handler())
    }

    private val ACTION_SETTING = "android.settings.SETTINGS"

    fun openFingerPrintSettingPage(context: Context) {
        val intent = Intent(ACTION_SETTING)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
        }

    }
}
