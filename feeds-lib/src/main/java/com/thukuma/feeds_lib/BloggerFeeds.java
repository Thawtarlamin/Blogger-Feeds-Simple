package com.thukuma.feeds_lib;

import android.content.Context;

import com.thukuma.feeds_lib.Models.Models;


public class BloggerFeeds {
    private Context context;
    private String url;

    public BloggerFeeds(Context context, String url,onComplete onComplete,onError onError) {
        this.context = context;
        this.url = url;
        new GetData(context,url,onComplete,onError);
    }

    public interface onComplete{
        void onComplete(Models models);
    }
    public interface onError{
        void onError(Exception e);
    }
}
