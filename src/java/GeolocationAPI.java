import io.ipdata.client.Ipdata;
from .env import GEO

public class GeolocationAPI {

    URL url = new URL("https://api.ipdata.co");
    IpdataService ipdataService = Ipdata.builder().url(url)
            .key(GEO).get();


}
