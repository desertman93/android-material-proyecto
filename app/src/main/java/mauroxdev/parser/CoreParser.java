package mauroxdev.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Mauricio on 21-05-2015.
 */
public class CoreParser {


    public static void main(String[] args) throws IOException {

        Document doc = Jsoup.connect("http://en.wikipedia.org/").get();
        Elements newsHeadlines = doc.select("#mp-itn b a");

    }

}
