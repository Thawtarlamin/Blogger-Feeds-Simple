package com.thukuma.bloggerfeedssimple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.thukuma.feeds_lib.BloggerFeeds;
import com.thukuma.feeds_lib.Models.Models;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new BloggerFeeds(this, "http://sofiafoodparadise.blogspot.com/", new BloggerFeeds.onComplete() {
            @Override
            public void onComplete(Models models) {
                Log.d("my-test", "Title is: "+models.getTitle());
                Log.d("my-test", "Image is: "+models.getImage());
                Log.d("my-test", "Content is: "+models.getContent());
            }
        }, new BloggerFeeds.onError() {
            @Override
            public void onError(Exception e) {
                Log.d("my-test", "onError: "+e.getMessage());
            }
        });
    }
}