package e.clement.a205enroot;

import android.os.AsyncTask;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Created by Clément on 25/03/2018.
 */

public class MyAsyncTask extends android.os.AsyncTask<String, Void, String> {

    // 1 - Implement listeners methods (Callback)
    public interface Listeners {
        void onPreExecute();
        void doInBackground();
        void onPostExecute(String success);
    }


    // 2 - Declare callback
    private final WeakReference<Listeners> callback;


    // 3 - Constructor
    public MyAsyncTask(Listeners callback){
        this.callback = new WeakReference<>(callback);
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.callback.get().onPreExecute(); // 4 - Call the related callback method
        Log.e("TAG", "AsyncTask is started.");
    }

    @Override
    protected void onPostExecute(String success) {
        super.onPostExecute(success);
        this.callback.get().onPostExecute(success); // 4 - Call the related callback method
        Log.e("TAG", "AsyncTask is finished.");
    }


    @Override
    protected String doInBackground(String... url) {
        this.callback.get().doInBackground(); // 4 - Call the related callback method
        Log.e("TAG", "AsyncTask doing some big work...");

        return GetHttpURLConnection.startHttpRequest(url[0]) ; // Appel de la tâche a effectuer en ARRIERE PLAN
    }
}