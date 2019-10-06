package ch.zhaw.gpi.external.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Demo
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Demo {
    String demoUrl, demoBody, demoBusinessKey;

    public String getDemoUrl() {
        return demoUrl;
    }

    public void setDemoUrl(String demoUrl) {
        this.demoUrl = demoUrl;
    }

    public String getDemoBody() {
        return demoBody;
    }

    public void setDemoBody(String demoBody) {
        this.demoBody = demoBody;
    }

    public String getDemoBusinessKey() {
        return demoBusinessKey;
    }

    public void setDemoBusinessKey(String demoBusinessKey) {
        this.demoBusinessKey = demoBusinessKey;
    }

    
    
}