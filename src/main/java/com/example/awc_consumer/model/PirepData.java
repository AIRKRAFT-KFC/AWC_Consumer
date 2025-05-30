package com.example.awc_consumer.model;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PirepData {
    private Long pirepId;
    private String receiptTime;
    private Long obsTime;
    private String icaoId;
    private String acType;
    private Double lat;
    private Double lon;
    private String fltLvl;
    private String fltLvlType;
    private Integer temp;
    private Integer wdir;
    private Integer wspd;
    private String pirepType;
    private String rawOb;
}
