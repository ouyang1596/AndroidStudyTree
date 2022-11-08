package com.ouyang.lib_common.annotation;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 使用注解来替换枚举
 *
 * @author ryan
 */
@StringDef({AnnotationJetpack.PAGING})
@Retention(RetentionPolicy.SOURCE)
public @interface AnnotationJetpack {
    String PAGING = "Paging";
}
