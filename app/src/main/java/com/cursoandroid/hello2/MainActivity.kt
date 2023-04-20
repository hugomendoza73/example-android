package com.cursoandroid.hello2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.TextView
import java.util.Locale

@Suppress("DEPRECATED_IDENTITY_EQUALS")
class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var tts:TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tts = TextToSpeech(this, this)
        findViewById<Button>(R.id.btnPlay).setOnClickListener{speak()}

    }

    private fun speak(){
        val message: String = findViewById<TextView>(R.id.etMessage).text.toString()
        if (message.isEmpty()){
            findViewById<TextView>(R.id.tvStatus).text = getString(R.string.tts_isEmpty)
        }else{
            tts!!.speak(message, TextToSpeech.QUEUE_FLUSH, null, "")
            findViewById<TextView>(R.id.tvStatus).text = ""
        }

    }

    override fun onInit(status: Int) {
        if(status === TextToSpeech.SUCCESS){
            findViewById<TextView>(R.id.tvStatus).text = getString(R.string.tts_active)
            tts!!.language = Locale("ES")
        }else{
            findViewById<TextView>(R.id.tvStatus).text = getString(R.string.tts_noActive)
        }
    }

    override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}