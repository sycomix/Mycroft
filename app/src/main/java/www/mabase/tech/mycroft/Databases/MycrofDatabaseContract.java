package www.mabase.tech.mycroft.Databases;

import android.provider.BaseColumns;

/**
 * Created by Chris on 10/25/2017.
 */

public class MycrofDatabaseContract {

    private MycrofDatabaseContract() {}

    public static class MycroftDatabase implements BaseColumns {
        //This is the .voc Table
        public static final String VOCAB_TABLE_NAME = "vocab";
        //This is the regex table
        public static final String REGEX_TABLE_NAME = "regex";
        //This is the intent table
        public static final String INTENT_TABLE_NAME = "intent";


        //These skills are shared by both tables
        public static final String COLUMN_NAME_SKILL = "skill";
        //The filename
        public static final String COLUMN_NAME_FILENAME = "fileName";
        /*
        For .voc, the variable is a vocab file itself. For regex, the variable is the regex string.
         */
        public static final String COLUMN_NAME_VARIABLE = "variable";
        //This is whether its option, required, or otherwise
        public static final String COLUMN_NAME_OPTIONAL = "optional";
        //This is what order it needs to be filled in
        public static final String COLUMN_NAME_ORDER = "order";
        //This is what skill intent it relates to
        public static final String COLUMN_NAME_INTENT = "intent";
    }


}
