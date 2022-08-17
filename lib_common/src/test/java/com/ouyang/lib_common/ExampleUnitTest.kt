package com.ouyang.lib_common

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    companion object {
        val TAG = "ExampleUnitTest"
    }

    var flag2 = true

    @Test
    fun addition_isCorrect() {
//        volatileTest()

    }

    private fun test(args: Array<String>) {
        var a = "a" as Int
        var counts = Array(26) { 0 }
        for ((index, name) in args.withIndex()) {

        }
    }

    /**
     *volatile关键字测试
     * */
    private fun volatileTest() {
        Thread(object : Runnable {
            override fun run() {
                println("flagStart==" + flag2)
                while (flag2) {
                    //                    println("flag==" + flag2)
                }
                println("flagEnd==" + flag2)
            }
        }).start()
        Thread.sleep(2000)
        Thread(object : Runnable {
            override fun run() {
                flag2 = false
                println("flagChange==" + flag2)
            }
        }).start()
    }
}