package ash.example.dell.myalarmtry1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    //Alarm manager
    AlarmManager alarm_manager;
    TimePicker alarm_timePicker;
    TextView UpdateText;
    Context context;
    PendingIntent pending_intent;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.context = this;

        //alarm manager
        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //timepicker
        alarm_timePicker = (TimePicker) findViewById(R.id.timePicker);

        //updatetext
        UpdateText = (TextView) findViewById(R.id.UpdateText);

        //pending instance
        final Calendar calender = Calendar.getInstance();

        //Intent
        final Intent my_Intent = new Intent(this.context, Alarm_Receiver.class);

        //On Button
        Button on = (Button) findViewById(R.id.on);
        //on onclicklistener

        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calender.set(Calendar.HOUR_OF_DAY, alarm_timePicker.getHour());
                calender.set(Calendar.MINUTE, alarm_timePicker.getHour());

                int hour = alarm_timePicker.getHour();
                int minute = alarm_timePicker.getMinute();

                String hour_string = String.valueOf(hour);
                String minute_string = String.valueOf(minute);

                if (hour > 12) {
                    hour_string = String.valueOf(hour-12);
                }

                if (minute < 10) {
                    minute_string = "0" + String.valueOf(minute);
                }

                set_alarm_text("Alarm ON!Set to:"+ hour_string + ":" + minute_string);

                //extra intent
                my_Intent.putExtra("extra", "ON");

                //pending intent
                pending_intent = PendingIntent.getBroadcast(MainActivity.this, 0, my_Intent, PendingIntent.FLAG_UPDATE_CURRENT);

                //set alarm manager
                alarm_manager.set(AlarmManager.RTC_WAKEUP, calender.getTimeInMillis(), pending_intent);

            }
        });

        //Off Button
        Button off = (Button) findViewById(R.id.off);
        //off onclicklistener

        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                set_alarm_text("Alarm OFF");

                //stop alarm
                alarm_manager.cancel(pending_intent);

                //extra intent
                my_Intent.putExtra("extra", "OFF");

                //stop ringtone
                sendBroadcast(my_Intent);

            }
        });


    }

    private void set_alarm_text(String output) {
        UpdateText.setText(output);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
