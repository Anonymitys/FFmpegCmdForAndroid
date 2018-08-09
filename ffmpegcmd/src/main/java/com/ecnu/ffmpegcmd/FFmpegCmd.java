package com.ecnu.ffmpegcmd;

import android.util.Log;

import java.util.Arrays;

public class FFmpegCmd {

    public interface OnHandleListener{
        void onBegin();
        void onEnd(int result);
    }

    static {
        System.loadLibrary("native-lib");
        System.loadLibrary("avcodec-57");
        System.loadLibrary("avdevice-57");
        System.loadLibrary("avfilter-6");
        System.loadLibrary("avformat-57");
        System.loadLibrary("avutil-55");
        System.loadLibrary("postproc-54");
        System.loadLibrary("swresample-2");
        System.loadLibrary("swscale-4");
    }
    private static final String TAG="FFmpegCmd";
    public static void ffmpegCmd(final String cmd, final OnHandleListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (listener!=null){
                    listener.onBegin();
                }
                String[] cmds=cmd.split(" ");
                Log.e(TAG, "ffmpegCmd: "+ Arrays.toString(cmds));
               int result= ffmpegrun(cmds.length,cmds);
                if (listener!=null){
                    listener.onEnd(result);
                }
            }
        }).start();

    }
    private native static int ffmpegrun(int cmdLen,String[] cmds);
}
