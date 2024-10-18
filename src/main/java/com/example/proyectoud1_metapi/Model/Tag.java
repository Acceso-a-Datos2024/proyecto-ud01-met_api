
package com.example.proyectoud1_metapi.Model;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Generated("jsonschema2pojo")
public class Tag {

    private String term;
    @JsonProperty("AAT_URL")
    private String aatUrl;
    @JsonProperty("Wikidata_URL")
    private String wikidataURL;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Tag() {
    }

    public Tag(String term, String aatUrl, String wikidataURL) {
        super();
        this.term = term;
        this.aatUrl = aatUrl;
        this.wikidataURL = wikidataURL;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getAatUrl() {
        return aatUrl;
    }

    public void setAatUrl(String aatUrl) {
        this.aatUrl = aatUrl;
    }

    public String getWikidataURL() {
        return wikidataURL;
    }

    public void setWikidataURL(String wikidataURL) {
        this.wikidataURL = wikidataURL;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Tag.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("term");
        sb.append('=');
        sb.append(((this.term == null)?"<null>":this.term));
        sb.append(',');
        sb.append("aatUrl");
        sb.append('=');
        sb.append(((this.aatUrl == null)?"<null>":this.aatUrl));
        sb.append(',');
        sb.append("wikidataURL");
        sb.append('=');
        sb.append(((this.wikidataURL == null)?"<null>":this.wikidataURL));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
