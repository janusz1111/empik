package Repository;

import sun.net.www.http.HttpClient;

import java.net.http.HttpRequest;

public class NPPRep {
    private final static String BASE_REQUEST_URL = "http://api.nbp.pl/api/exchangerates/tables/C";
    private final HttpClient httpClient;

    public NBPRep() {
        httpClient = HttpClients.createDefault();//należy coś dodać do pom.xml

    }
    public  String getResponse() {
        HttpGet httpGet = new HttpGet(BASE_REQUEST_URL);
        try {
            HttpReques respomse =httpClient.execute()
        }

    }

}
