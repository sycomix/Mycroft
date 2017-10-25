package www.mabase.tech.mycroft;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.security.spec.ECField;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submit(View v){

        EditText input = (EditText)findViewById(R.id.utterance);
        TextView output = (TextView)findViewById(R.id.output);

        output.setText(input.getText().toString());

        /*
        Send a parse intent for all parsers. Later this will be a for loop(?) but for now, just work with Adapt
         */
        Intent parse = new Intent();
        parse.setAction("android.intent.action.ADAPT_PARSE");
        startService(parse);
    }

    // This queries all packages to make sure all parsers are installed, Then it queries
    // to make sure all skills are installed
    public List isIntentAvailable(Context context, String action){

        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent(action);
        List<ResolveInfo> list =
                packageManager.queryIntentServices(intent,
                        PackageManager.MATCH_DEFAULT_ONLY);

        return list;
    }

    public void update(View v) {
        final String IDENTIFY = "android.intent.action.PARSER_IDENTIFY";

        List list = isIntentAvailable(this.getApplicationContext(), IDENTIFY);
        int size = list.size();

        Log.i("Mycroft Core", "Querying package manager: Size "+size);
        for(int i = 0; i < size; i++){
            Log.i("Mycroft Core", list.toString());
        }
    }
}
