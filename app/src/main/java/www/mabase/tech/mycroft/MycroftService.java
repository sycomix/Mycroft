package www.mabase.tech.mycroft;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

/**
 * This service ensures that Mycroft is always running and always available. It also offers a simple CLI for the user.
 */

public class MycroftService extends Service {
    public MycroftService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    /*
    For this version of Android, I am stuck making the notification a read only
    text display. Once 7.0 becomes more prolific I will be able to add inline
    edit text, allowing for Mycroft CLI interaction
     */
    @Override
    public void onCreate() {
        Toast.makeText(this,"Mycroft service started", Toast.LENGTH_SHORT).show();

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.assistant)
                        .setContentTitle("Mycroft")
                        .setContentText("Hello, User");

        startForeground(1225, mBuilder.build());
        //Start voice service
    }


    @Override
    public void onDestroy() {
        Toast.makeText(this, "Mycroft service terminated", Toast.LENGTH_SHORT).show();
    }
}
