package ch.zhaw.gpi.camunda.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Dumo
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dumo {
    String dumoTime, dumoBusinessKey;

    public String getDumoTime() {
        return dumoTime;
    }

    public void setDumoTime(String dumoTime) {
        this.dumoTime = dumoTime;
    }

    public String getDumoBusinessKey() {
        return dumoBusinessKey;
    }

    public void setDumoBusinessKey(String dumoBusinessKey) {
        this.dumoBusinessKey = dumoBusinessKey;
    }
    
}