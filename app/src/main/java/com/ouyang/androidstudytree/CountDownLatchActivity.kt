package com.ouyang.androidstudytree

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_count_down_latch.*
import java.util.concurrent.CountDownLatch

/**
 *CountDownLatch demo
 * */
class CountDownLatchActivity : AppCompatActivity() {
    companion object {
        var TAG = "CountDownLatchActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count_down_latch)
        btn_count_down_latch.setOnClickListener {
            var c = CountDownLatch(4)
            WaiterThread("WaiterThread_1", c).start()
            WaiterThread("WaiterThread_2", c).start()

            Thread.sleep(2000)

            WorkerThread("WorkerThread_1", c).start()
            WorkerThread("WorkerThread_2", c).start()
            WorkerThread("WorkerThread_3", c).start()
            WorkerThread("WorkerThread_4", c).start()
        }
    }

    class WaiterThread(name: String, var c: CountDownLatch) :
        Thread(name) {
        override fun run() {
            super.run()
            Log.i(TAG, "$name is start")
            try {
                c.await()
            } catch (e: InterruptedException) {
            }
            Log.i(TAG, "$name is end")
        }
    }

    class WorkerThread(name: String, var c: CountDownLatch) :
        Thread(name) {
        override fun run() {
            super.run()
            Log.i(TAG, "$name is start")
            try {
                sleep(3000)
                Log.i(TAG, "$name is end")
            } catch (e: InterruptedException) {
            }
            c.countDown()
        }
    }
}