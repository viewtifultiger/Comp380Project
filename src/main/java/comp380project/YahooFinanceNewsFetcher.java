package comp380project;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class YahooFinanceNewsFetcher {

    public List<String> fetchNews() {
        List<String> newsList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://finance.yahoo.com/").get();
            Elements newsElements = doc.select("li.js-stream-content");

            for (Element element : newsElements) {
                String headline = element.select("h3").text();
                newsList.add(headline);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newsList;
    }
}