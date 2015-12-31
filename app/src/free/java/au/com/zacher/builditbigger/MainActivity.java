package au.com.zacher.builditbigger;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import au.com.zacher.builditbigger.endpoint.BackendAsyncTask;
import au.com.zacher.builditbigger.endpoint.IBackendAsyncTaskCallback;
import au.com.zacher.jokeactivities.JokeActivity;


public class MainActivity extends ActionBarActivity implements IBackendAsyncTaskCallback {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Talks to the endpoint, gets a joke and displays it
     */
    public void tellJoke(View v){
        BackendAsyncTask task = new BackendAsyncTask();
        task.execute(this);
    }

    @Override
    public void randomJokeReceived(String joke) {
        Intent i    = new Intent(this, JokeActivity.class);
        i.putExtra(JokeActivity.JOKE_INTENT_EXTRA, joke);
        this.startActivity(i);
    }

    @Override
    public void errorReceived(String error) {
        this.randomJokeReceived(error);
    }
}
