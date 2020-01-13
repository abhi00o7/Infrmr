package infrmrapp.buildappswithpaulo.com.infrmr.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import infrmrapp.buildappswithpaulo.com.infrmr.R;
import infrmrapp.buildappswithpaulo.com.infrmr.util.Util;

/**
 *
 * Created by Abhishek Kumar
 */


public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    ArrayList<Article> articles = new ArrayList<>();
    Context context;
    private OnItemClickListner itemClickListner;

    public ArticleAdapter(ArrayList<Article> articles, Context context) {
        this.articles = articles;
        this.context = context;
    }

    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View articleRow = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_row, parent, false);

        return new ViewHolder(articleRow);

    }

    @Override
    public void onBindViewHolder(final ArticleAdapter.ViewHolder holder, int position) {
        Article article = articles.get(position);

        holder.title.setText(article.getTitle());
        holder.description.setText(article.getDescription());
        holder.date.setText(Util.dateFormatted(article.getPublishedDate()));
        holder.author.setText(article.getAuthor());

        BitmapDrawable bitmapDrawable = (BitmapDrawable) holder.articleImage.getDrawable();

        Bitmap photo = bitmapDrawable.getBitmap();
        Palette.from(photo).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {

                int bgColor = palette.getMutedColor(ContextCompat.getColor(context,
                        android.R.color.black));

                holder.date.setBackgroundColor(bgColor);
                holder.author.setTextColor(bgColor);

            }
        });

        Picasso.with(context)
                .load(article.getImageUrl())
                .into(holder.articleImage);



    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void setOnClickListener(OnItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView articleImage;
        public TextView author, description, title, date;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this); //registering our view to receive clicks

            articleImage = itemView.findViewById(R.id.newsImageId);
            author = itemView.findViewById(R.id.author);
            description = itemView.findViewById(R.id.descriptionNews);
            title = itemView.findViewById(R.id.newsTitle);
            date = itemView.findViewById(R.id.date);
        }

        @Override
        public void onClick(View view) {
            itemClickListner.onItemClick(view, getAdapterPosition());
        }
    }

    public interface OnItemClickListner {
        void onItemClick(View view, int position);
    }
}
