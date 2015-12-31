package au.com.zacher.builditbigger.endpoint;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

import au.com.zacher.builditbigger.R;
import au.com.zacher.builditbigger.backend.myApi.MyApi;

/**
 * Created by Brad on 29/12/2015.
 */
public class BackendAsyncTask extends AsyncTask<IBackendAsyncTaskCallback, Void, String>
{
    private static MyApi myApiService = null;
    private IBackendAsyncTaskCallback callback;
    private boolean didError = false;

    @Override
    protected final String doInBackground(IBackendAsyncTaskCallback... params)
    {
        this.callback = params[0];

        if (BackendAsyncTask.myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                          .setRootUrl(this.callback.getString(R.string.server_url));
            myApiService = builder.build();
        }

        try {
            return BackendAsyncTask.myApiService.getRandomJoke().execute().getData();
        } catch (IOException e) {
            this.didError = true;
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (this.didError) {
            this.callback.errorReceived(result);
        } else {
            this.callback.randomJokeReceived(result);
        }
    }
}
