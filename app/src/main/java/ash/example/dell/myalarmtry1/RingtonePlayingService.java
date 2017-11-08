package ash.example.dell.myalarmtry1;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by DELL on 9/30/2017.
 */

public class RingtonePlayingService extends Service {

    MediaPlayer media_song;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.i("LocalService", "Received Start Id" + startId + ":" + intent);

        String state = intent.getExtras().getString("extra");

        Log.e("Ringtone State:extra is", state);

        switch (state) {
            case "alarm on":
                startId = 1;
                break;
            case "alarm off":
                startId = 0;
                Log.e("Start ID is", state);
                break;
            default:
                startId = 1;
                break;
        }









        media_song = MediaPlayer.create(this, R.raw.ash);
        media_song.start();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "On Destroy Called", Toast.LENGTH_SHORT).show();
    }

}
