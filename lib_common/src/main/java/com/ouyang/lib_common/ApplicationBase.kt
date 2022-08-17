package com.ouyang.lib_common

import android.app.Application

class ApplicationBase : Application() {
    companion object {
        //在 JVM 平台，如果使用 @JvmStatic 注解，你可以将伴生对象的成员生成为真正的静态方法和字段
        @JvmStatic
        var instance: ApplicationBase? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}