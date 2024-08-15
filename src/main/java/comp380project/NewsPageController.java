package comp380project;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class NewsPageController {
    @FXML
    private ListView<String> newsListView;
    
    private YahooFinanceNewsFetcher newsFetcher = new YahooFinanceNewsFetcher();
    
    public void initialize() {
        loadNews();
    }
    
    public void loadNews() {
        newsListView.getItems().clear();
        newsListView.getItems().addAll(newsFetcher.fetchNews());
    }
}
