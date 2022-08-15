package com.ouyang.androidstudytree

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ouyang.androidstudytree.databinding.ActivityJniBinding

class JNIActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJniBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityJniBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example of a call to a native method
//        binding.sampleText.text = stringFromJNI()
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
//    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
//            System.loadLibrary("native-lib")
        }
    }
}