package com.kstreamsmongodb.kstreamsmongodb.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "subscriptions")
public class Subscription {

    @Id
    private String name;
    private Date generation_date = new Date();
    private Date insertionDate;
    private String dataDesc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getGeneration_date() {
        return generation_date;
    }

    public void setGeneration_date(Date generation_date) {
        this.generation_date = generation_date;
    }

    public Date getInsertionDate() {
        return insertionDate;
    }

    public void setInsertionDate(Date insertionDate) {
        this.insertionDate = insertionDate;
    }

    public String getDataDesc() {
        return dataDesc;
    }

    public void setDataDesc(String dataDesc) {
        this.dataDesc = dataDesc;
    }
}
