package com.example.android.photoslistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.android.photoslistview.adapter.PhotoAdapter;
import com.example.android.photoslistview.models.PhotoBean;
import com.example.android.photoslistview.network.AsyncTaskListener;
import com.example.android.photoslistview.network.FetchPhotosAsyncTask;
import com.example.android.photoslistview.utils.AppConstants;
import com.example.android.photoslistview.utils.ParseUtility;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<PhotoBean> photosList = null;
    ListView listView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new FetchPhotosAsyncTask(listener).execute(AppConstants.BASE_URL);

        listView = (ListView) findViewById(R.id.listview);

    }

    AsyncTaskListener listener = new AsyncTaskListener() {
        @Override
        public void onSuccess(String response) {
            photosList = ParseUtility.parsePhotos(response);
            listView.setAdapter(new PhotoAdapter(MainActivity.this, photosList));
        }
    };
}
