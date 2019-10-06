package ch.zhaw.gpi.camunda.delegates;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import ch.zhaw.gpi.camunda.resources.Demo;

/**
 * SendRequestDelegate
 */
@Named("sendRequestAdapter")
public class SendRequestDelegate implements JavaDelegate {

    private static final String REST_ENDPOINT = "http://localhost:8091/myapi/v1/demo/";

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Demo demo = new Demo();
        demo.setDemoBody("Ich bin der super-spannende Inhalt dieser Nachricht.");
        demo.setDemoBusinessKey(execution.getProcessBusinessKey());
        demo.setDemoUrl("http://localhost:8092/testapi/v1/dumo/");
        
        HttpEntity<Demo> httpEntity = new HttpEntity<Demo>(demo, headers);

        ResponseEntity<Object> responseEntity = new RestTemplate().postForEntity(REST_ENDPOINT, httpEntity, null);

        if(!responseEntity.getStatusCode().is2xxSuccessful()){
            throw new RuntimeException(responseEntity.getStatusCode().toString() + ": " + responseEntity.getBody());
        }
    }

    
}