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
        
        var thread: PulseThread? = null
        
        val startButton = Button(this)
        startButton.setOnClickListener {
            thread?.stop()
            thread = PulseThread(60, Note.Quarter, {
                    vibrator.vibrate(100)
            })
            thread?.start()
            
        }
        startButton.text = "start"

        val stopButton = Button(this)
        stopButton.setOnClickListener { thread?.stop() }
        stopButton.text = "stop"

        layout.addView(startButton)
        layout.addView(stopButton)

    }
}
