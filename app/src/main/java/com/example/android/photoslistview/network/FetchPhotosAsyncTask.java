package com.example.android.photoslistview.network;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Dell I7 on 9/8/2016.
 */

/**
 * This class makes an api call to get
 * list of the data from given url
 */
public class FetchPhotosAsyncTask extends AsyncTask<String, Void, String> {

    AsyncTaskListener mAsyncTaskListener = null;
    String response = null;

    public FetchPhotosAsyncTask(AsyncTaskListener listener){
        mAsyncTaskListener = listener;
    }

    private static final String LOG_TAG = FetchPhotosAsyncTask.class.getSimpleName();

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {
            if (strings == null) {
                return null;
            }
            URL url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }

            response = buffer.toString();
            Log.e(LOG_TAG, response);

        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, e.getMessage());

        } catch (IOException e){
            urlConnection.disconnect();
            Log.e(LOG_TAG, e.getMessage());

        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    Log.e(LOG_TAG, e.getMessage());
                }
            }
        }

        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mAsyncTaskListener.onSuccess(response);
    }
}
