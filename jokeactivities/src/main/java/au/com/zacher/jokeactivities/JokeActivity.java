package au.com.zacher.jokeactivities;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity
{
    public static final String JOKE_INTENT_EXTRA = "JOKE_INTENT_EXTRA";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_joke);

        ActionBar bar = this.getSupportActionBar();
        assert bar != null;
        bar.setDisplayHomeAsUpEnabled(true);

        // get the joke from the intent
        Intent i = this.getIntent();
        String joke = i.getStringExtra(JokeActivity.JOKE_INTENT_EXTRA);

        // update the view
        TextView jokeTextView = (TextView)this.findViewById(R.id.joke_text);
        jokeTextView.setText(joke);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
