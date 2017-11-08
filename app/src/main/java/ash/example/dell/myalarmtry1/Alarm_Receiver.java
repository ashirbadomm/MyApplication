package ash.example.dell.myalarmtry1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by DELL on 9/30/2017.
 */

public class Alarm_Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("Receiver","Yah");

        //fetch intent extra
        String get_your_string = intent.getExtras().getString("extra");

        Log.e("What is your String?",get_your_string);


        Intent service_intent = new Intent(context, RingtonePlayingService.class);

        //extra intent from main activity to ringtone playing service
        service_intent.putExtra("extra", get_your_string);


        context.startService(service_intent);
    }
}
