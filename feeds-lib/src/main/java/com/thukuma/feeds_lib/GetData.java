package com.thukuma.feeds_lib;

import android.content.Context;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.thukuma.feeds_lib.Models.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetData {
    private Context context;
    private String url;
    private JSONObject jsonObject = null;
    private BloggerFeeds.onComplete onComplete;
    public BloggerFeeds.onError onError;

    public GetData(Context context, String url, BloggerFeeds.onComplete onComplete, BloggerFeeds.onError onError) {
        this.context = context;
        this.url = url;
        this.onComplete = onComplete;
        this.onError = onError;
        AndroidNetworking.initialize(context);
        getData(url,onComplete,onComplete);
    }

    private void getData(String url, BloggerFeeds.onComplete onComplete, BloggerFeeds.onComplete onComplete1) {

        AndroidNetworking.get(url+"feeds/posts/default?max-results=500")
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        String contents,titles,Imgs;
                        try {
                            jsonObject = XML.toJSONObject(response);
                            String str = jsonObject.getString("feed");
                            JSONObject obj = new JSONObject(str);
                            JSONArray array = obj.getJSONArray("entry");
                            for (int i=0;array.length()>i;i++){
                                JSONObject o = array.getJSONObject(i);
                                Document doc = Jsoup.parse(o.getString("content"));
                                Elements e = doc.select("div");
                                String content = e.text();
                                contents = content.replace("<\\/a><\\/div>","")
                                        .replace("<\\/div>","")
                                        .replace("http:\\/\\/sofiafoodparadise.blogspot.com\"}","")
                                        .replace("Sofia","")
                                        .replace("<\\/a>","")
                                        .replace("<\\/span>","")
                                        .replace("<\\/i>","")
                                        .replace("PlayNow","")
                                        .replace("Click here","")
                                        .replace("\\","")
                                        .replace("}","");
                                Document docs = Jsoup.parse(o.getString("title"));
                                Elements es = docs.select("body");
                                String title = es.text();
                                JSONObject object = new JSONObject(title);
                                titles = object.getString("content");
                                String html = o.toString();
                                Document document = Jsoup.parse(html);
                                Elements elements = document.select("img");
                                for (Element Img : elements ){
                                    Imgs = Img.attr("src")
                                            .replace("://","://")
                                            .replace("//","/")
                                            .replace("\"","")
                                            .replace("\\","");
                                    if (!titles.isEmpty()&&!contents.isEmpty()&&!Imgs.isEmpty()){
                                        Models models = new Models();
                                        models.setContent(Rabbit.zg2uni(contents));
                                        models.setImage(Imgs);
                                        models.setTitle(Rabbit.zg2uni(titles));
                                        onComplete.onComplete(models);
                                    }

                                }
                            }
                        } catch (JSONException e) {
                            onError.onError(e);
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        onError.onError(anError);
                        Log.d("my-test", "onError: "+anError.getMessage());
                    }
                });

    }

}
