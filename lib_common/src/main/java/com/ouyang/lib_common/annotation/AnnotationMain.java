package com.ouyang.lib_common.annotation;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 使用注解来替换枚举
 *
 * @author ryan
 */
@StringDef({AnnotationMain.MULTI_THREAD, AnnotationMain.JNI})
@Retention(RetentionPolicy.SOURCE)
public @interface AnnotationMain {
    String JNI = "JNI";
    String MULTI_THREAD = "多线程";
    String ASM = "字节码插桩";
}
