package infrmrapp.buildappswithpaulo.com.infrmr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import infrmrapp.buildappswithpaulo.com.infrmr.data.Article;
import infrmrapp.buildappswithpaulo.com.infrmr.data.ArticleAdapter;
import infrmrapp.buildappswithpaulo.com.infrmr.data.ArticleData;
import infrmrapp.buildappswithpaulo.com.infrmr.data.ArticleListAsyncResponse;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArticleAdapter articleAdapter;
    /**
     *
     * Created by Abhishek Kumar
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         new ArticleData().getNewsList(new ArticleListAsyncResponse() {
             @Override
             public void processFinish(final ArrayList<Article> articles) {

                 recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                 articleAdapter = new ArticleAdapter(articles, getApplicationContext());

                 recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                 recyclerView.setAdapter(articleAdapter);

                 articleAdapter.setOnClickListener(new ArticleAdapter.OnItemClickListner() {
                     @Override
                     public void onItemClick(View view, int position) {

                         Article article = articles.get(position);

                         Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                         intent.putExtra("url", article.getNewsUrl());
                         startActivity(intent);

                     }
                 });



             }
         });
    }
}
