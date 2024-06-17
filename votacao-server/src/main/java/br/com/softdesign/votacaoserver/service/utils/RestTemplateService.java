package br.com.softdesign.votacaoserver.service.utils;


import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateService {

    @Autowired
    private RestTemplate rt;

    public ResponseEntity<?> get(String url, String opToken, Class<?> clazz){
        return get(url, new HttpHeaders(), Strings.isNotBlank(opToken) ? opToken : null , clazz);
    }

    public ResponseEntity<?> get(String url, HttpHeaders headers, String opToken, Class<?> clazz){
        if(Strings.isNotBlank(opToken)){
            headers.setBearerAuth(opToken);
        }

        return rt.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), clazz);
    }

    public ResponseEntity<?> getForObject(String url, HttpHeaders headers, Class<?> clazz, String opToken){
        if(Strings.isNotBlank(opToken)){
            headers.setBearerAuth(opToken);
        }

        return rt.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), clazz);
    }

    public ResponseEntity<?> post(String url, Object body, Class<?> clazz){
        return post(url, body, new HttpHeaders(), null, clazz);
    }

    protected ResponseEntity<?> post(String url, Object body, HttpHeaders headers, Class<?> clazz){
        return post(url, body, headers, null, clazz);
    }

    public ResponseEntity<?> post(String url, Object body, HttpHeaders headers, String opToken, Class<?> clazz){
        if(Strings.isNotBlank(opToken)){
            headers.setBearerAuth(opToken);
        }

        return rt.exchange(url, HttpMethod.POST, body != null ? new HttpEntity<>(body, headers) : new HttpEntity<>(headers), clazz);
    }
}
