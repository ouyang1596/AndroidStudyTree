#include <jni.h>
#include <string>
#include<sys/mman.h>
#include<stdio.h>
#include <iostream>

using namespace std;

extern "C" JNIEXPORT jstring JNICALL
Java_com_ouyang_androidstudytree_JNIActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    int a = 10;
    int *p = &a;

    cout << "hello world" << endl;

    return env->NewStringUTF(hello.c_str());
}
