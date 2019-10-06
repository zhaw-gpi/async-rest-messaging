package ch.zhaw.gpi.external.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.gpi.external.resources.Demo;

/**
 * REST-Controller
 * 
 * @author scep
 */
@RestController
public class ExternalSystemRestController {

    @Autowired
    private AsyncMethods asyncMethods;
    
    /**
     * REST-Ressource für URL /myapi/v1/demo/ (POST)
     * 
     * @return HTTP-Response mit einem Status 200
     * @throws InterruptedException
     */
    @PostMapping(value = "/myapi/v1/demo/")
    public ResponseEntity<HttpStatus> addDemo(@RequestBody Demo demo) throws InterruptedException {
        // Eine andere Methode asynchron aufrufen, welche später dem aufrufenden System antwortet
        asyncMethods.waitAndSendResponse(demo);
        
        // Positiven Status zurückgeben
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);   
    }
}
