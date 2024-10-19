
package com.example.proyectoud1_metapi.Model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

/*Clase que represante la propeidad "Measurement" de las obras*/
@Generated("jsonschema2pojo")
public class Measurement implements Serializable {

    private String elementName;
    private String elementDescription;
    private com.example.proyectoud1_metapi.Model.ElementMeasurements elementMeasurements;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Measurement() {
    }

    public Measurement(String elementName, String elementDescription, com.example.proyectoud1_metapi.Model.ElementMeasurements elementMeasurements) {
        super();
        this.elementName = elementName;
        this.elementDescription = elementDescription;
        this.elementMeasurements = elementMeasurements;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getElementDescription() {
        return elementDescription;
    }

    public void setElementDescription(String elementDescription) {
        this.elementDescription = elementDescription;
    }

    public ElementMeasurements getElementMeasurements() {
        return elementMeasurements;
    }

    public void setElementMeasurements(ElementMeasurements elementMeasurements) {
        this.elementMeasurements = elementMeasurements;
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
        sb.append(Measurement.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("elementName");
        sb.append('=');
        sb.append(((this.elementName == null)?"<null>":this.elementName));
        sb.append(',');
        sb.append("elementDescription");
        sb.append('=');
        sb.append(((this.elementDescription == null)?"<null>":this.elementDescription));
        sb.append(',');
        sb.append("elementMeasurements");
        sb.append('=');
        sb.append(((this.elementMeasurements == null)?"<null>":this.elementMeasurements));
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
