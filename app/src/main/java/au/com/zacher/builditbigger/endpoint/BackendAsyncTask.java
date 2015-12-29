package au.com.zacher.builditbigger.endpoint;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

import au.com.zacher.builditbigger.backend.myApi.MyApi;

/**
 * Created by Brad on 29/12/2015.
 */
public class BackendAsyncTask extends AsyncTask<Context, Void, String>
{
    private static MyApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Context... params)
    {
        if (BackendAsyncTask.myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                          .setRootUrl("http://10.0.2.2:8080/_ah/api"); // TODO - this needs to change for release
            myApiService = builder.build();
        }

        this.context = params[0];

        try {
            return BackendAsyncTask.myApiService.getRandomJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
