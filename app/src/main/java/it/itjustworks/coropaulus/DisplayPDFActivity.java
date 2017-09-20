package it.itjustworks.coropaulus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DisplayPDFActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_pdf);

        this.webView = (WebView) findViewById(R.id.webView);
        webViewMagic();
    }

    @Override
    protected void onResume() {
        super.onResume();
        webViewMagic();
    }

    private void webViewMagic() {
        Intent intent = getIntent();
        String url = intent.getStringExtra(MainActivity.PDF_URL);

        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                view.loadUrl(url);
                return true;
            }
        });
        this.webView.loadUrl("http://docs.google.com/viewerng/viewer?url=" + url);
    }
}
