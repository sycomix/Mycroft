package www.mabase.tech.mycroft;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class InstallReceiver extends BroadcastReceiver {

    public final String TAG = "Mycroft Core";
    private static final String ACTION_PARSER_IDENTIFY = "android.intent.action.PARSER_IDENTIFY";

    @Override
    public void onReceive(Context context, Intent intent) {
        //Get package ID and send explicit  intent
        //Save pid in database for package removed
        Log.i(TAG, "Install broadcast received by NAVCORE");

        Bundle b = intent.getExtras();
        int uid = b.getInt(Intent.EXTRA_UID);
        String[] packages = context.getPackageManager().getPackagesForUid(uid);


        //Build an intent explicitly for the newly installed package.
        Intent i = new Intent();
        i.setAction(ACTION_PARSER_IDENTIFY);
        i.setClassName(packages[0], packages[0]+".InstallService");
        Log.i(TAG,ACTION_PARSER_IDENTIFY);
        Log.i(TAG,packages[0]+".InstallService.class");

        //This prevents Mycroft from crashing if there isn't a receiving intent
        try {
            context.startService(i);
            Log.i(TAG,"PARSER_IDENTIFY intent sent");
        }catch(Exception e){
            Log.e(TAG, "There is no receiving engine");
        }

    }
}
