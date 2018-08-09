#include <jni.h>
#include <string>
#include "android/log.h"


#define LOGI(FORMAT,...) __android_log_print(ANDROID_LOG_INFO,"ecnu_hq",FORMAT,##__VA_ARGS__);
extern "C"{
#include "libavcodec/avcodec.h"
#include "ffmpeg.h"
}

extern "C"
JNIEXPORT void JNICALL
Java_com_ecnu_ffmpegcmd_FFmpegCmd_n_1test(JNIEnv *env, jobject instance) {

    // TODO
    av_register_all();
    AVCodec *c_temp = av_codec_next(NULL);
    while (c_temp != NULL)
    {
        switch (c_temp->type)
        {
            case AVMEDIA_TYPE_VIDEO:
                LOGI("[Video]:%s", c_temp->name);
                break;
            case AVMEDIA_TYPE_AUDIO:
                LOGI("[Audio]:%s", c_temp->name);
                break;
            default:
                LOGI("[Other]:%s", c_temp->name);
                break;
        }
        c_temp = c_temp->next;
    }


}

extern "C"
JNIEXPORT jint JNICALL
Java_com_ecnu_ffmpegcmd_FFmpegCmd_ffmpegrun(JNIEnv *env, jobject instance, jint cmdLen,
                                            jobjectArray cmds) {

    // TODO
    char *argCmd[cmdLen] ;
    jstring buf[cmdLen];
    LOGI("length=%d",cmdLen);

    for (int i = 0; i < cmdLen; ++i) {
        buf[i] = static_cast<jstring>(env->GetObjectArrayElement(cmds, i));
        char *string = const_cast<char *>(env->GetStringUTFChars(buf[i], JNI_FALSE));
        argCmd[i] = string;
        LOGI("argCmd=%s",argCmd[i]);
    }

    ffmpeg_run(cmdLen, argCmd);

    return 0;

}
