package edu.temple.coroutineconversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    //TODO (Refactor to replace Thread code with coroutines)

    lateinit var cakeImageView: ImageView

    val handler = Handler(Looper.getMainLooper(), Handler.Callback {
        cakeImageView.alpha = it.what / 100f
        true
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cakeImageView = findViewById(R.id.imageView)

        //Create a coroutine scope
        val scope = CoroutineScope(Job() + Dispatchers.Default )
        //scope launch
        findViewById<Button>(R.id.revealButton).setOnClickListener {
            scope.launch {
                Thread {
                    repeat(100) {
                        handler.sendEmptyMessage(it)
                        Thread.sleep(40)
                    }
                }.start()
            }
        }
    }
}