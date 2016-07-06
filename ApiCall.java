
package com.wireless.cse5345.easyvisit;
        import android.os.AsyncTask;
        import java.net.*;
        import java.io.*;
        import android.util.*;
/**
 * Created by anushasridharan on 6/29/16.
 */
public abstract class ApiCall extends AsyncTask {
    private String result;
    protected Long doInBackground(URL... urls) {
        int count = urls.length;
        long totalSize = 0;
        StringBuilder resultBuilder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            try {
                // Read all the text returned by the server
                InputStreamReader reader = new InputStreamReader(urls[i].openStream());
                BufferedReader in = new BufferedReader(reader);
                String resultPiece;
                while ((resultPiece = in.readLine()) != null) {
                    resultBuilder.append(resultPiece);
                }
                in.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // if cancel() is called, leave the loop early
            if (isCancelled()) {
                break;
            }
        }
        // save the result
        this.result = resultBuilder.toString();
        return totalSize;
    }
    protected void onProgressUpdate(Integer... progress) {
        // update progress here
    }
    // called after doInBackground finishes
    protected void onPostExecute(Long result) {
        Log.v("result, yay!", this.result);
    }
}

