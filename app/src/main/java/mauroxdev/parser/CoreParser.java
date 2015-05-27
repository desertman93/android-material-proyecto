package mauroxdev.parser;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import mauroxdev.models.post;

/**
 * Created by Mauricio on 21-05-2015.
 */
public class CoreParser {
    private static final String TAG = "CoreParser";


    public ArrayList<post> proccesPost(String html, String base) {

        ArrayList<post> postarray = new ArrayList<post>();
        //String safe = Jsoup.clean(html, Whitelist.relaxed());

        Document doc = Jsoup.parse(html, base);
        Elements content = doc.select("div.content");
        Elements table = content.select("table");
        //Elements items = table.select("td");
        String safe = table.toString().replaceAll("&nbsp;", " ");
        Elements items = Jsoup.parse(safe, base).select("td");
        for (org.jsoup.nodes.Element item : items) {
            if (!item.text().isEmpty()) {

                String imgUrl = item.select("img").attr("abs:src");
                Log.d(TAG, "td=" + item.toString());
                post post = new post();
                post.setTitulo(item.text());
                Log.i(TAG, "Titulos=" + item.text());
                post.setUrl(item.select("a").attr("href").toString());
                post.setImg(imgUrl);
                postarray.add(post);
                Log.d(TAG, "img_url=" + imgUrl);
            }

        }

        return postarray;

    }

}
