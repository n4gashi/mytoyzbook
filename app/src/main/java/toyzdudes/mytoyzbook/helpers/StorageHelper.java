package toyzdudes.mytoyzbook.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public class StorageHelper {

    public static final String SHARED_PREFS_FILENAME = "UserPrefs";

    // region SHARED PREFERENCES RELATED

    public static String getStringSharedPref(Context context, String name)
    {
        SharedPreferences preferences = context.getSharedPreferences(StorageHelper.SHARED_PREFS_FILENAME, Context.MODE_PRIVATE);
        return preferences.getString(name, "");
    }

    public static int getIntSharedPref(Context context, String name)
    {
        SharedPreferences preferences = context.getSharedPreferences(StorageHelper.SHARED_PREFS_FILENAME, Context.MODE_PRIVATE);
        return preferences.getInt(name, 0);
    }

    private static SharedPreferences.Editor getEditorSharedPrefs(Context context, String name)
    {
        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences settings = context.getSharedPreferences(StorageHelper.SHARED_PREFS_FILENAME, Context.MODE_PRIVATE);
        return settings.edit();
    }

    public static boolean editSharedPref(Context context, String name, boolean value)
    {
        SharedPreferences.Editor editor = getEditorSharedPrefs(context, name);
        editor.putBoolean(name, value);
        return editor.commit();
    }

    public static boolean editSharedPref(Context context, String name, String value)
    {
        SharedPreferences.Editor editor = getEditorSharedPrefs(context, name);
        editor.putString(name, value);
        return editor.commit();
    }

    public static boolean editSharedPref(Context context, String name, int value)
    {
        SharedPreferences.Editor editor = getEditorSharedPrefs(context, name);
        editor.putInt(name, value);
        return editor.commit();
    }

    public static boolean editSharedPref(Context context, String name, float value)
    {
        SharedPreferences.Editor editor = getEditorSharedPrefs(context, name);
        editor.putFloat(name, value);
        return editor.commit();
    }

    public static boolean editSharedPref(Context context, String name, long value)
    {
        SharedPreferences.Editor editor = getEditorSharedPrefs(context, name);
        editor.putLong(name, value);
        return editor.commit();
    }

    public static boolean editSharedPref(Context context, String name, Set<String> value)
    {
        SharedPreferences.Editor editor = getEditorSharedPrefs(context, name);
        editor.putStringSet(name, value);
        return editor.commit();
    }

    // endregion

}
