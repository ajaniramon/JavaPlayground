package xyz.ajanicorp.playground.configuration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * Created by Ajani on 04/11/2017.
 */
public class HttpHeadersProvider {
    public static HttpHeaders GetJsonHeaders(){
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
