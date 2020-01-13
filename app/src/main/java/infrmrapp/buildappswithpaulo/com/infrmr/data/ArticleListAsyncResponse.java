package infrmrapp.buildappswithpaulo.com.infrmr.data;

import java.util.ArrayList;

/**
 *
 * Created by Abhishek Kumar
 */


public interface ArticleListAsyncResponse {
    void processFinish(ArrayList<Article> articles);
}
