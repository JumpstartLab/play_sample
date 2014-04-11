import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class generate_requests {
  public static void main(String[] args) throws Exception {
    String            rawUrl    = (args.length > 0 ? args[0] : "http://localhost:4567/ping");
    URL               url       = new URL(rawUrl);
    generate_requests requester = new generate_requests(url);

    long startTime = System.nanoTime();
    for(int i = 0; i < 10; ++i) {
      int response = requester.makeRequest();
      if(response != 200) {
        System.err.println("Expected 200 response, got " + response);
        System.exit(1);
      }
      long endTime = System.nanoTime();
      float seconds = (endTime - startTime) / 1000000000L;
      System.out.println("Request " + i + "responded after " + seconds + " seconds");
    }
  }

  URL url;
  public generate_requests(URL url) {
    this.url = url;
  }

  public int makeRequest() throws Exception {
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    // con.setRequestMethod("GET");
    // con.setRequestProperty("User-Agent", "Mozilla/5.0");
    return con.getResponseCode();
  }
}
