package infrmrapp.buildappswithpaulo.com.infrmr.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import infrmrapp.buildappswithpaulo.com.infrmr.controller.AppController;

/**
 * https://newsapi.org/v2/everything?q=bitcoin&from=2019-12-13&sortBy=publishedAt&apiKey=c16d333b1e2444d0b26c0f93d3c01297
 * /**
 *  *
 *  * Created by Abhishek Kumar
 *  */


public class ArticleData {

    ArrayList<Article> articles = new ArrayList<>();

    public void getNewsList(final ArticleListAsyncResponse callBack) {

        String url = "https://newsapi.org/v1/articles?source=techcrunch&apiKey=c16d333b1e2444d0b26c0f93d3c01297";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray articleArray = response.getJSONArray("articles");

                    for (int i = 0; i < articleArray.length(); i++) {
                        //create article object
                        JSONObject articleObject = articleArray.getJSONObject(i);
                        Article article = new Article();

                        article.setAuthor(articleObject.getString("author"));
                        article.setTitle(articleObject.getString("title"));
                        article.setDescription(articleObject.getString("description"));
                        article.setImageUrl(articleObject.getString("urlToImage"));
                        article.setPublishedDate(articleObject.getString("publishedAt"));
                        article.setNewsUrl(articleObject.getString("url"));

                        articles.add(article);



                    }
                    if (null != callBack) callBack.processFinish(articles);






                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }
}
