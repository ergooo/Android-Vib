package jp.nicovideo.account.androidvib

import android.content.Context
import android.os.Bundle
import android.os.Vibrator
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this)
        setContentView(layout)

        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        
        val startButton = Button(this)
        startButton.setOnClickListener {
            PulseController().start(60, Note.Quarter, {
                    vibrator.vibrate(100)
            })
        }
        startButton.text = "start"

        val stopButton = Button(this)
        stopButton.setOnClickListener { vibrator.cancel() }
        stopButton.text = "stop"

        layout.addView(startButton)
        layout.addView(stopButton)

    }
}
