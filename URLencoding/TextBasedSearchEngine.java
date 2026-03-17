
// TextBasedSearchEngine.java:
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class TextBasedSearchEngine {
    private String searchEngine;

    public TextBasedSearchEngine(String searchEngine) {
        this.searchEngine = searchEngine;
    }

    public void doSearch(String queryString) {
        try {
            // Search engines expect the query in the URL, not a POST body.
            URL url = new URL(searchEngine + queryString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            int responseCode = connection.getResponseCode();
            InputStream stream = responseCode >= 400
                    ? connection.getErrorStream()
                    : connection.getInputStream();

            System.out.println("HTTP " + responseCode);

            if (stream == null) {
                return;
            }

            try (BufferedReader input = new BufferedReader(
                    new InputStreamReader(stream))) {
                String inputLine;
                while ((inputLine = input.readLine()) != null) {
                    System.out.println(inputLine);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        QueryStringFormatter formatter = new QueryStringFormatter("https://postman-echo.com/get");
        formatter.addQuery("newwindow", "1");
        formatter.addQuery("q", "Xingchen Chu & Rajkumar Buyya");
        // Send the encoded query to an echo endpoint.
        TextBasedSearchEngine search = new TextBasedSearchEngine(formatter.getEngine());
        search.doSearch(formatter.getQueryString());
    }
}
