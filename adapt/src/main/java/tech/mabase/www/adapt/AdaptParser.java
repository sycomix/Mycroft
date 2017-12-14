package tech.mabase.www.adapt;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

public class AdaptParser extends Service {
    public AdaptParser() {
    }

    /**
     * Target we publish for clients to send messages to IncomingHandler.
     */
    final Messenger mMessenger = new Messenger(new IncomingHandler());

    /**
     * When binding to the service, we return an interface to our messenger
     * for sending messages to the service.
     */
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(), "binding", Toast.LENGTH_SHORT).show();
        return mMessenger.getBinder();
    }

    /** Command to the service to display a message */
    static final int MSG_PARSE = 1;

    /**
     * Handler of incoming messages from Mycroft.
     */
    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_PARSE:
                    Toast.makeText(getApplicationContext(), "utterance received", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }


    public void onParse(String utterance){
        /*
        tokenize
        compare
        buildIntents
        return Intents to MycroftService
         */
    }

    /*
    When this parser is created, it should request from Core all of the database information needed
    to properly do a parse. Then the service should stay active until Mycroft Cores foreground
    service is shut down. This way it can immediately respond to a parse, without needing to load
    the parse data each time.
     */
    @Override
    public void onCreate(){

    }
}
