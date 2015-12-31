package au.com.zacher.builditbigger.endpoint;

import android.test.AndroidTestCase;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Brad on 31/12/2015.
 */
public class TestAsyncTask extends AndroidTestCase {
    public void testTask() throws InterruptedException {
        // task is async so we need to block until it finishes else the test will terminate before response is received
        final CountDownLatch signal = new CountDownLatch(1);

        BackendAsyncTask task = new BackendAsyncTask();
        task.execute(new IBackendAsyncTaskCallback() {
            @Override
            public void randomJokeReceived(String result)
            {
                assertNotNull(result);
                signal.countDown();
            }

            @Override
            public void errorReceived(String result)
            {
                fail();
                signal.countDown();
            }

            @Override
            public String getString(int id)
            {
                return TestAsyncTask.this.getContext().getString(id);
            }
        });

        // await returns false if the timer expires
        assertTrue(signal.await(5, TimeUnit.SECONDS));
    }
}
