package ch.zhaw.gpi.camunda.controller;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.gpi.camunda.resources.Dumo;

/**
 * REST-Controller
 * 
 * @author scep
 */
@RestController
public class CamundaRestController {

    @Autowired
    private RuntimeService runtimeService;
    
    /**
     * REST-Ressource für URL /testapi/v1/dumo/ (POST)
     * 
     * @return HTTP-Response mit einem Status 200
     */
    @PostMapping(value = "/testapi/v1/dumo/")
    public ResponseEntity<HttpStatus> addDumo(@RequestBody Dumo dumo) {        
        // Nachricht zurück an Prozessinstanz übergeben
        try {
            runtimeService.createMessageCorrelation("Antwort")
            .processInstanceBusinessKey(dumo.getDumoBusinessKey())
            .setVariable("zeitstempel", dumo.getDumoTime())
            .correlate();

            // Positiven Status zurückgeben
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);   
        } catch (Exception e) {
            // Negativen Status zurückgeben
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);   
        }
    }
}
