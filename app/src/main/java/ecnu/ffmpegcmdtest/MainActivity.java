package ecnu.ffmpegcmdtest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ecnu.ffmpegcmd.FFmpegCmd;

public class MainActivity extends AppCompatActivity {

    private FFmpegCmd cmd;
    private static final String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       cmd=new FFmpegCmd();

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
    }


    public void test(View view) {
        //cmd.n_test();
        String command="ffmpeg -i /storage/emulated/0/coomaan/tempCut.mp3 -af volume=0.5 /storage/emulated/0/coomaan/change.mp3";
       // String command="ffmpeg -version";
        // String command="ffmpeg -i /storage/emulated/0/coomaan/cm20180503002.mp3 -ss 0 -t 8 -acodec copy /storage/emulated/0/coomaan/cutchange.mp3" ;
       // String command="ffmpeg -i %s -i %s -filter_complex amix=inputs=2:duration=first -strict -2 %s";
       // String comands=String.format(command,"/storage/emulated/0/coomaan/tempCut.mp3","/storage/emulated/0/coomaan/current.wav","/storage/emulated/0/coomaan/changemix.aac");
        cmd.ffmpegCmd(command, new FFmpegCmd.OnHandleListener() {
            @Override
            public void onBegin() {
                Log.e(TAG, "test: begin" );
            }

            @Override
            public void onEnd(int result) {
                Log.e(TAG, "test: finished" );
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){

                }else {
                    Toast.makeText(this,"request failed",Toast.LENGTH_SHORT).show();
                }
        }
    }
}
