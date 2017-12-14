package tech.mabase.www.adapt;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class InstallService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_PARSER_IDENTIFY = "android.intent.action.PARSER_IDENTIFY";
    private static final String ACTION_SKILL_INSTALL = "android.intent.action.ADAPT_SKILL_INSTALL";

    public InstallService() {
        super("InstallService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_PARSER_IDENTIFY.equals(action)) {
                handleParserIdentify();
            }
        }
    }

    private void handleParserIdentify() {
        //This needs to pass android.intent.action.ADAPT_SKILL_IDENTIFY to Mycroft
        Log.i("Adapt Parser", "Parser identified");
    }
}
