package com.ouyang.androidstudytree

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_multi_thread.*
import java.util.concurrent.CountDownLatch

/**
 *多线程 demo
 * */
class MultiThreadActivity : AppCompatActivity() {

    companion object {
        var TAG = "CountDownLatchActivity"

        @JvmStatic
        var flag = true
    }

    var flag2 = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_thread)
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
        flag = true

        //android手机上测试没有效果
        btn_volatile.setOnClickListener {
            Thread(object : Runnable {
                override fun run() {
                    Log.i(TAG, "flagStart==" + flag2)
                    while (flag2) {

                    }
                    Log.i(TAG, "flagEnd==" + flag2)
                }
            }).start()
            Thread.sleep(2000)
            Thread(object : Runnable {
                override fun run() {
                    flag2 = false
                    Log.i(TAG, "flagChange==" + flag2)
                }
            }).start()
        }
//        btn_cas.setOnClickListener {
//            var a = 0
//            Thread(object : Runnable {
//                override fun run() {
//                    for (i in 0 until 10000) {
//
//                        while (true) {
//                            var temp = a
//                            var v = a + 1
//                            if (temp == a) {
//                                a = v
//                                break
//                            }
//                        }
//                        Log.i(TAG, "a==" + a)
//                    }
//
//                }
//            }).start()
//            Thread(object : Runnable {
//                override fun run() {
//                    for (i in 0 until 10000) {
//                        while (true) {
//                            var temp = a
//                            var v = a + 1
//                            if (temp == a) {
//                                a = v
//                                break
//                            }
//                        }
//                        Log.i(TAG, "a==" + a)
//                    }
//                }
//            }).start()
//        }
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