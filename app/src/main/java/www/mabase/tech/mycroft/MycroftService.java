package www.mabase.tech.mycroft;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

/**
 * This service ensures that Mycroft is always running and always available. It also offers a simple CLI for the user.
 */

public class MycroftService extends Service {

    private static final String ACTION_PARSE_FINISHED = "android.intent.action.PARSE_FINISHED";
    private static final String ACTION_MYCROFT_PARSE = "android.intent.action.MYCROFT_PARSE";

    public MycroftService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //This service needs to be able to 'hear' the responses from parsers
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_PARSE_FINISHED.equals(action)) {
                handleParserFinished(intent.getStringExtra("parser"), intent.getStringExtra("response"));
            } else if (ACTION_MYCROFT_PARSE.equals(action)) {
                parseUtterance(intent.getStringExtra("utterance"));
            }
        }

        return START_STICKY;
    }

    //This counts the number of responding parsers. When all parsers have responded, it weighs the
    //responses. Perhaps it should be take with the IBinder. This is here instead of an
    //IntentService because it needs to count up all of the responses, which is a persistent variable
    private void handleParserFinished(String id, String response){
        /*
        takeParseInfo[n][0] = id;
        takeParseInfo[n][1] = response
        if(noAllIn){
            return;
        } else {
            decision = decide(takeParseInfo);
            executeSkill(decision);
        }
         */
    }

    //This is catching MyscroftActivity CLI and VoiceService utterances for parsing.
    private void parseUtterance(String param1) {
        //This needs to be changed so it trys to bound the service if unbound
        if (!mBound) return;
        // Create and send a message to the service, using a supported 'what' value
        Message msg = Message.obtain(null, 1, 0, 0);
        try {
            mService.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    /*
    For this version of Android, I am stuck making the notification a read only
    text display. Once 7.0 becomes more prolific I will be able to add inline
    edit text, allowing for Mycroft CLI interaction
     */
    @Override
    public void onCreate() {
        Toast.makeText(this, "Mycroft service started", Toast.LENGTH_SHORT).show();

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.assistant)
                        .setContentTitle("Mycroft")
                        .setContentText("Hello, User");

        startForeground(1225, mBuilder.build());
        //Start always listening voice service

        /*
        Here it should bind up to 4 parser services
        request from the core database all parser package names.
         */
        String ADAPT_PACKAGE = "tech.mabase.www.adapt";
        String ADAPT_CLASS = "AdaptParser";
        try {
            Intent parserInit = new Intent();
            //This should allow dynamic package names
            parserInit.setComponent(ComponentName.createRelative(ADAPT_PACKAGE,ADAPT_PACKAGE+"."+ADAPT_CLASS));
            bindService(parserInit, mConnection, Context.BIND_AUTO_CREATE);
        } catch (Exception e) {
            Log.e("MycroftService", "Couldn't seem to bind Adapt");
        }
    }

    Messenger mService = null;
    /** Flag indicating whether we have called bind on the service. */
    boolean mBound;


    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            // This is called when the connection with the service has been
            // established, giving us the object we can use to
            // interact with the service.  We are communicating with the
            // service using a Messenger, so here we get a client-side
            // representation of that from the raw IBinder object.
            mService = new Messenger(service);
            mBound = true;
        }

        public void onServiceDisconnected(ComponentName className) {
            // This is called when the connection with the service has been
            // unexpectedly disconnected -- that is, its process crashed.
            mService = null;
            mBound = false;
        }
    };


    @Override
    public void onDestroy() {

        /*
        Here it should unbind all active parser services
        for(selectedServices){
            unbind(service[i]);
        }
         */
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }


        Toast.makeText(this, "Mycroft service terminated", Toast.LENGTH_SHORT).show();
    }
}
