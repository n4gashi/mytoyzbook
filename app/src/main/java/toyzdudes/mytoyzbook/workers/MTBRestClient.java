package toyzdudes.mytoyzbook.workers;

import com.loopj.android.http.*;

public class MTBRestClient {

    private static final String BASE_URL = "http://mytoyzbook.alwaysdata.net/webservice/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler)
    {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl)
    {
        return BASE_URL + relativeUrl;
    }

}
