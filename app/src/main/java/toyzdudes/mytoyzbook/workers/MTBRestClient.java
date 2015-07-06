package toyzdudes.mytoyzbook.workers;

import android.content.Context;
import android.widget.Toast;

import com.loopj.android.http.*;

import toyzdudes.mytoyzbook.helpers.DebugHelper;
import toyzdudes.mytoyzbook.helpers.FeaturesHelper;

public class MTBRestClient {

    private static final String BASE_URL = "http://mytoyzbook.alwaysdata.net/webservice/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(Context context, String url, RequestParams params, AsyncHttpResponseHandler responseHandler)
    {
        if(FeaturesHelper.isNetworkAvailable(context)){
            client.get(getAbsoluteUrl(url), params, responseHandler);
        }else{
            Toast.makeText(context, "Probleme de connexion", Toast.LENGTH_SHORT).show();
        }
    }

    private static String getAbsoluteUrl(String relativeUrl)
    {
        return BASE_URL + relativeUrl;
    }

}
