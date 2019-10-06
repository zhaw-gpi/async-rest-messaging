package ch.zhaw.gpi.external.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import ch.zhaw.gpi.external.resources.Demo;
import ch.zhaw.gpi.external.resources.Dumo;

/**
 * AsyncMethods
 */
@Component
public class AsyncMethods {

    @Async
    public void waitAndSendResponse(Demo demo) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        Dumo dumo = new Dumo();
        dumo.setDumoTime(formatter.format(date));
        dumo.setDumoBusinessKey(demo.getDemoBusinessKey());
        
        HttpEntity<Dumo> httpEntity = new HttpEntity<Dumo>(dumo, headers);

        ResponseEntity<Object> responseEntity = new RestTemplate().postForEntity(demo.getDemoUrl(), httpEntity, null);

        if(!responseEntity.getStatusCode().is2xxSuccessful()){
            throw new RuntimeException(responseEntity.getStatusCode().toString() + ": " + responseEntity.getBody());
        }
        
        System.out.println("Key und Nachricht: " + demo.getDemoBusinessKey() + " & " + demo.getDemoBody());
    }
}