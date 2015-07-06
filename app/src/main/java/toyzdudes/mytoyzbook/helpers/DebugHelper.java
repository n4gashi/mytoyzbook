package toyzdudes.mytoyzbook.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpStatus;

import toyzdudes.mytoyzbook.App;
import toyzdudes.mytoyzbook.R;

public class DebugHelper
{
    public static void log(char level, Object msg)
    {

        String className = Thread.currentThread().getStackTrace()[3].getFileName();
        className = className.substring(0, className.length()-5);
        String tag = App.LOG_TAG + "." + className + "." + Thread.currentThread().getStackTrace()[3].getMethodName();
        String message = (msg instanceof String) ? (String)msg : String.valueOf(msg);

        if(App.MODE_DEBUG == 1)
        {
            if(level == 'v')
            {
                Log.v(tag, message);
            }
            else if(level == 'd')
            {
                Log.d(tag, message);
            }
            else if(level == 'i')
            {
                Log.i(tag, message);
            }
            else if(level == 'w')
            {
                Log.w(tag, message);
            }
            else if(level == 'e')
            {
                Log.e(tag, message);
            }
            else
            {
                Log.wtf(tag, message);
            }
        }
    }

    public static void makeToast(Context context, String msg, int length)
    {
        Toast toast = Toast.makeText(context, msg, length);
        toast.show();
    }

    public static void makeToast(Context context, String msg)
    {
        makeToast(context, msg, Toast.LENGTH_SHORT);
    }

    public static boolean isNetworkConnected(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    public static boolean handleStatusCodes(Context context, int code)
    {
        if(code == HttpStatus.SC_OK)
        {
            return true;
        }
        else if(code == HttpStatus.SC_UNAUTHORIZED)
        {
            DebugHelper.makeToast(context, context.getResources().getString(R.string.code_unauthorized));
        }
        else
        {
            DebugHelper.makeToast(context, context.getResources().getString(R.string.unhandled_exception));
        }

        return false;
    }

}
