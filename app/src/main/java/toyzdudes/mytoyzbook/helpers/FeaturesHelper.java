package toyzdudes.mytoyzbook.helpers;

import android.content.Context;
import android.net.ConnectivityManager;

public class FeaturesHelper {

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

}
