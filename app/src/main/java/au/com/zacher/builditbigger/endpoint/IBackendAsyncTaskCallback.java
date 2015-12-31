package au.com.zacher.builditbigger.endpoint;

public interface IBackendAsyncTaskCallback {
    void randomJokeReceived(String result);
    void errorReceived(String result);
    String getString(int id);
}