package com.example.awc_consumer.model;

import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetarData {
    private String dataType;
    private String collectionTimestamp;
    private String observationTime;
    private String icaoCode;
    private String name;
    private Double lat;
    private Double lon;
    private Integer elev;
    private Integer temp;
    private Integer dewp;
    private Integer wdir;
    private Integer wspd;
    private String visib;
    private Integer altim;
    private List<Cloud> clouds;
    private String rawObservation;
    private String source;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Cloud {
        private String coverage;
        private Integer altitude;
    }
}
