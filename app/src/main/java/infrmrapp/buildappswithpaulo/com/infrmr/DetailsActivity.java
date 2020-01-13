package infrmrapp.buildappswithpaulo.com.infrmr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        webView = (WebView) findViewById(R.id.webview);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String url = bundle.getString("url");

            webView.loadUrl(url);
        }




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
