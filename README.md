# FFmpegCmdForAndroid
FFmpeg for android的封装库，ffmpeg版本：3.3.7,加入了libmp3lame,libfdk-aac,libx264等常见三方库的支持，支持MP3编码。
实现了ffmpeg在Android平台的封装，开发人员可以在Android平台使用ffmpeg命令完成音视频剪辑。
## Gradle
    dependencies {
       ...
       implementation 'com.ecnu:ffmpegcmd:1.0.0'
    }
## Usage
    String command="ffmpeg -i /storage/emulated/0/tempCut.mp3 -af volume=0.5 /storage/emulated/0/change.mp3";
    FFmpegCmd.ffmpegCmd(command, new FFmpegCmd.OnHandleListener() {
            @Override
            public void onBegin() {
                Log.e(TAG, "onBegin: " );
            }

            @Override
            public void onEnd(int result) {
                Log.e(TAG, "onEnd: " );
            }
        });
                
