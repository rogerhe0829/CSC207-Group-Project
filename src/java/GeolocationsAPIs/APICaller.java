package GeolocationsAPIs;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import io.github.cdimascio.dotenv.Dotenv;

/**
CALLS THE API WITH THE ENCODED ADDRESS FROM USERLOCAITON CALSS AN RETUNR THE RAW JSON
 */
public class APICaller {

    //NEW CLIENT REQUEST
    private final OkHttpClient client = new OkHttpClient();

    //API KEY VARIABLE
    private final String apiKey;

    //BASE URL FOR GMAPS API GEOCODING
    private static final String BASE_URL = "https://maps.googleapis.com/maps/api/geocode/json?address=";

    public APICaller() {

        //LOAD API KEY FROM ENV
        Dotenv dotenv = Dotenv.load();
        String key = dotenv.get("IPDATA_API_KEY");

        //API KEY ATTRIBUTE
        this.apiKey = key;
    }

    /**
     CREATES THE FINAL URL TO CALL THE API, AN THROWS AN IOEXCEPTION IF THE REUQEST IS BAD.
     THIS CLASS WILL ALSO RETURN THE RAW JSON FILE FROM THE GEOCOIDNG.
     */
    public String getJson(String encodedLocation) throws IOException {

        // CONSTRUCT THE FINAL URL
        String url = BASE_URL + encodedLocation + "&key=" + this.apiKey;

        //MAIN REQUEST
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {

            // IF REQUEST IF NOT 200 AND IF 200
            if (!response.isSuccessful()) {
                throw new IOException("API Request Failed. Code: " + response.code() + " URL: " + url);
            }

            //RETURN JSON BODY STRING
            if (response.body() == null) {
                return "{}"; // RETURN EMPTY JSON IF NULL
            }
            return response.body().string();
        }
    }
}
