
package com.example.proyectoud1_metapi.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.example.proyectoud1_metapi.Model.Measurement;
import com.example.proyectoud1_metapi.Model.Tag;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@Generated("jsonschema2pojo")
@JacksonXmlRootElement(localName = "Artpiece")
/*Clase que represanta un objeto de una obra de arte obtenida de la API*/
public class ArtPiece implements Serializable {
    @JacksonXmlElementWrapper(useWrapping = false)

    private Integer objectID;
    private Boolean isHighlight;
    private String accessionNumber;
    private String accessionYear;
    private Boolean isPublicDomain;
    private String primaryImage;
    private String primaryImageSmall;
    private List<Object> additionalImages = new ArrayList<Object>();
    private Object constituents;
    private String department;
    private String objectName;
    private String title;
    private String culture;
    private String period;
    private String dynasty;
    private String reign;
    private String portfolio;
    private String artistRole;
    private String artistPrefix;
    private String artistDisplayName;
    private String artistDisplayBio;
    private String artistSuffix;
    private String artistAlphaSort;
    private String artistNationality;
    private String artistBeginDate;
    private String artistEndDate;
    private String artistGender;
    @JsonProperty("artistWikidata_URL")
    private String artistWikidataURL;
    @JsonProperty("artistULAN_URL")
    private String artistULANURL;
    private String objectDate;
    private Integer objectBeginDate;
    private Integer objectEndDate;
    private String medium;
    private String dimensions;
    private List<Measurement> measurements = new ArrayList<Measurement>();
    private String creditLine;
    private String geographyType;
    private String city;
    private String state;
    private String county;
    private String country;
    private String region;
    private String subregion;
    private String locale;
    private String locus;
    private String excavation;
    private String river;
    private String classification;
    private String rightsAndReproduction;
    private String linkResource;
    private String metadataDate;
    private String repository;
    private String objectURL;
    private List<Tag> tags = new ArrayList<Tag>();
    @JsonProperty("objectWikidata_URL")
    private String objectWikidataURL;
    private Boolean isTimelineWork;
    private String galleryNumber;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public ArtPiece() {
    }

    public ArtPiece(Integer objectID, Boolean isHighlight, String accessionNumber, String accessionYear, Boolean isPublicDomain, String primaryImage, String primaryImageSmall, List<Object> additionalImages, Object constituents, String department, String objectName, String title, String culture, String period, String dynasty, String reign, String portfolio, String artistRole, String artistPrefix, String artistDisplayName, String artistDisplayBio, String artistSuffix, String artistAlphaSort, String artistNationality, String artistBeginDate, String artistEndDate, String artistGender, String artistWikidataURL, String artistULANURL, String objectDate, Integer objectBeginDate, Integer objectEndDate, String medium, String dimensions, List<Measurement> measurements, String creditLine, String geographyType, String city, String state, String county, String country, String region, String subregion, String locale, String locus, String excavation, String river, String classification, String rightsAndReproduction, String linkResource, String metadataDate, String repository, String objectURL, List<Tag> tags, String objectWikidataURL, Boolean isTimelineWork, String galleryNumber) {
        super();
        this.objectID = objectID;
        this.isHighlight = isHighlight;
        this.accessionNumber = accessionNumber;
        this.accessionYear = accessionYear;
        this.isPublicDomain = isPublicDomain;
        this.primaryImage = primaryImage;
        this.primaryImageSmall = primaryImageSmall;
        this.additionalImages = additionalImages;
        this.constituents = constituents;
        this.department = department;
        this.objectName = objectName;
        this.title = title;
        this.culture = culture;
        this.period = period;
        this.dynasty = dynasty;
        this.reign = reign;
        this.portfolio = portfolio;
        this.artistRole = artistRole;
        this.artistPrefix = artistPrefix;
        this.artistDisplayName = artistDisplayName;
        this.artistDisplayBio = artistDisplayBio;
        this.artistSuffix = artistSuffix;
        this.artistAlphaSort = artistAlphaSort;
        this.artistNationality = artistNationality;
        this.artistBeginDate = artistBeginDate;
        this.artistEndDate = artistEndDate;
        this.artistGender = artistGender;
        this.artistWikidataURL = artistWikidataURL;
        this.artistULANURL = artistULANURL;
        this.objectDate = objectDate;
        this.objectBeginDate = objectBeginDate;
        this.objectEndDate = objectEndDate;
        this.medium = medium;
        this.dimensions = dimensions;
        this.measurements = measurements;
        this.creditLine = creditLine;
        this.geographyType = geographyType;
        this.city = city;
        this.state = state;
        this.county = county;
        this.country = country;
        this.region = region;
        this.subregion = subregion;
        this.locale = locale;
        this.locus = locus;
        this.excavation = excavation;
        this.river = river;
        this.classification = classification;
        this.rightsAndReproduction = rightsAndReproduction;
        this.linkResource = linkResource;
        this.metadataDate = metadataDate;
        this.repository = repository;
        this.objectURL = objectURL;
        this.tags = tags;
        this.objectWikidataURL = objectWikidataURL;
        this.isTimelineWork = isTimelineWork;
        this.galleryNumber = galleryNumber;
    }

    public Integer getObjectID() {
        return objectID;
    }

    public void setObjectID(Integer objectID) {
        this.objectID = objectID;
    }

    public Boolean getIsHighlight() {
        return isHighlight;
    }

    public void setIsHighlight(Boolean isHighlight) {
        this.isHighlight = isHighlight;
    }

    public String getAccessionNumber() {
        return accessionNumber;
    }

    public void setAccessionNumber(String accessionNumber) {
        this.accessionNumber = accessionNumber;
    }

    public String getAccessionYear() {
        return accessionYear;
    }

    public void setAccessionYear(String accessionYear) {
        this.accessionYear = accessionYear;
    }

    public Boolean getIsPublicDomain() {
        return isPublicDomain;
    }

    public void setIsPublicDomain(Boolean isPublicDomain) {
        this.isPublicDomain = isPublicDomain;
    }

    public String getPrimaryImage() {
        return primaryImage;
    }

    public void setPrimaryImage(String primaryImage) {
        this.primaryImage = primaryImage;
    }

    public String getPrimaryImageSmall() {
        return primaryImageSmall;
    }

    public void setPrimaryImageSmall(String primaryImageSmall) {
        this.primaryImageSmall = primaryImageSmall;
    }

    public List<Object> getAdditionalImages() {
        return additionalImages;
    }

    public void setAdditionalImages(List<Object> additionalImages) {
        this.additionalImages = additionalImages;
    }

    public Object getConstituents() {
        return constituents;
    }

    public void setConstituents(Object constituents) {
        this.constituents = constituents;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public String getReign() {
        return reign;
    }

    public void setReign(String reign) {
        this.reign = reign;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    public String getArtistRole() {
        return artistRole;
    }

    public void setArtistRole(String artistRole) {
        this.artistRole = artistRole;
    }

    public String getArtistPrefix() {
        return artistPrefix;
    }

    public void setArtistPrefix(String artistPrefix) {
        this.artistPrefix = artistPrefix;
    }

    public String getArtistDisplayName() {
        return artistDisplayName;
    }

    public void setArtistDisplayName(String artistDisplayName) {
        this.artistDisplayName = artistDisplayName;
    }

    public String getArtistDisplayBio() {
        return artistDisplayBio;
    }

    public void setArtistDisplayBio(String artistDisplayBio) {
        this.artistDisplayBio = artistDisplayBio;
    }

    public String getArtistSuffix() {
        return artistSuffix;
    }

    public void setArtistSuffix(String artistSuffix) {
        this.artistSuffix = artistSuffix;
    }

    public String getArtistAlphaSort() {
        return artistAlphaSort;
    }

    public void setArtistAlphaSort(String artistAlphaSort) {
        this.artistAlphaSort = artistAlphaSort;
    }

    public String getArtistNationality() {
        return artistNationality;
    }

    public void setArtistNationality(String artistNationality) {
        this.artistNationality = artistNationality;
    }

    public String getArtistBeginDate() {
        return artistBeginDate;
    }

    public void setArtistBeginDate(String artistBeginDate) {
        this.artistBeginDate = artistBeginDate;
    }

    public String getArtistEndDate() {
        return artistEndDate;
    }

    public void setArtistEndDate(String artistEndDate) {
        this.artistEndDate = artistEndDate;
    }

    public String getArtistGender() {
        return artistGender;
    }

    public void setArtistGender(String artistGender) {
        this.artistGender = artistGender;
    }

    public String getArtistWikidataURL() {
        return artistWikidataURL;
    }

    public void setArtistWikidataURL(String artistWikidataURL) {
        this.artistWikidataURL = artistWikidataURL;
    }

    public String getArtistULANURL() {
        return artistULANURL;
    }

    public void setArtistULANURL(String artistULANURL) {
        this.artistULANURL = artistULANURL;
    }

    public String getObjectDate() {
        return objectDate;
    }

    public void setObjectDate(String objectDate) {
        this.objectDate = objectDate;
    }

    public Integer getObjectBeginDate() {
        return objectBeginDate;
    }

    public void setObjectBeginDate(Integer objectBeginDate) {
        this.objectBeginDate = objectBeginDate;
    }

    public Integer getObjectEndDate() {
        return objectEndDate;
    }

    public void setObjectEndDate(Integer objectEndDate) {
        this.objectEndDate = objectEndDate;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    public String getCreditLine() {
        return creditLine;
    }

    public void setCreditLine(String creditLine) {
        this.creditLine = creditLine;
    }

    public String getGeographyType() {
        return geographyType;
    }

    public void setGeographyType(String geographyType) {
        this.geographyType = geographyType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getLocus() {
        return locus;
    }

    public void setLocus(String locus) {
        this.locus = locus;
    }

    public String getExcavation() {
        return excavation;
    }

    public void setExcavation(String excavation) {
        this.excavation = excavation;
    }

    public String getRiver() {
        return river;
    }

    public void setRiver(String river) {
        this.river = river;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getRightsAndReproduction() {
        return rightsAndReproduction;
    }

    public void setRightsAndReproduction(String rightsAndReproduction) {
        this.rightsAndReproduction = rightsAndReproduction;
    }

    public String getLinkResource() {
        return linkResource;
    }

    public void setLinkResource(String linkResource) {
        this.linkResource = linkResource;
    }

    public String getMetadataDate() {
        return metadataDate;
    }

    public void setMetadataDate(String metadataDate) {
        this.metadataDate = metadataDate;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getObjectURL() {
        return objectURL;
    }

    public void setObjectURL(String objectURL) {
        this.objectURL = objectURL;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getObjectWikidataURL() {
        return objectWikidataURL;
    }

    public void setObjectWikidataURL(String objectWikidataURL) {
        this.objectWikidataURL = objectWikidataURL;
    }

    public Boolean getIsTimelineWork() {
        return isTimelineWork;
    }

    public void setIsTimelineWork(Boolean isTimelineWork) {
        this.isTimelineWork = isTimelineWork;
    }

    public String getGalleryNumber() {
        return galleryNumber;
    }

    public void setGalleryNumber(String galleryNumber) {
        this.galleryNumber = galleryNumber;
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
        sb.append(ArtPiece.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("objectID");
        sb.append('=');
        sb.append(((this.objectID == null)?"<null>":this.objectID));
        sb.append(',');
        sb.append("isHighlight");
        sb.append('=');
        sb.append(((this.isHighlight == null)?"<null>":this.isHighlight));
        sb.append(',');
        sb.append("accessionNumber");
        sb.append('=');
        sb.append(((this.accessionNumber == null)?"<null>":this.accessionNumber));
        sb.append(',');
        sb.append("accessionYear");
        sb.append('=');
        sb.append(((this.accessionYear == null)?"<null>":this.accessionYear));
        sb.append(',');
        sb.append("isPublicDomain");
        sb.append('=');
        sb.append(((this.isPublicDomain == null)?"<null>":this.isPublicDomain));
        sb.append(',');
        sb.append("primaryImage");
        sb.append('=');
        sb.append(((this.primaryImage == null)?"<null>":this.primaryImage));
        sb.append(',');
        sb.append("primaryImageSmall");
        sb.append('=');
        sb.append(((this.primaryImageSmall == null)?"<null>":this.primaryImageSmall));
        sb.append(',');
        sb.append("additionalImages");
        sb.append('=');
        sb.append(((this.additionalImages == null)?"<null>":this.additionalImages));
        sb.append(',');
        sb.append("constituents");
        sb.append('=');
        sb.append(((this.constituents == null)?"<null>":this.constituents));
        sb.append(',');
        sb.append("department");
        sb.append('=');
        sb.append(((this.department == null)?"<null>":this.department));
        sb.append(',');
        sb.append("objectName");
        sb.append('=');
        sb.append(((this.objectName == null)?"<null>":this.objectName));
        sb.append(',');
        sb.append("title");
        sb.append('=');
        sb.append(((this.title == null)?"<null>":this.title));
        sb.append(',');
        sb.append("culture");
        sb.append('=');
        sb.append(((this.culture == null)?"<null>":this.culture));
        sb.append(',');
        sb.append("period");
        sb.append('=');
        sb.append(((this.period == null)?"<null>":this.period));
        sb.append(',');
        sb.append("dynasty");
        sb.append('=');
        sb.append(((this.dynasty == null)?"<null>":this.dynasty));
        sb.append(',');
        sb.append("reign");
        sb.append('=');
        sb.append(((this.reign == null)?"<null>":this.reign));
        sb.append(',');
        sb.append("portfolio");
        sb.append('=');
        sb.append(((this.portfolio == null)?"<null>":this.portfolio));
        sb.append(',');
        sb.append("artistRole");
        sb.append('=');
        sb.append(((this.artistRole == null)?"<null>":this.artistRole));
        sb.append(',');
        sb.append("artistPrefix");
        sb.append('=');
        sb.append(((this.artistPrefix == null)?"<null>":this.artistPrefix));
        sb.append(',');
        sb.append("artistDisplayName");
        sb.append('=');
        sb.append(((this.artistDisplayName == null)?"<null>":this.artistDisplayName));
        sb.append(',');
        sb.append("artistDisplayBio");
        sb.append('=');
        sb.append(((this.artistDisplayBio == null)?"<null>":this.artistDisplayBio));
        sb.append(',');
        sb.append("artistSuffix");
        sb.append('=');
        sb.append(((this.artistSuffix == null)?"<null>":this.artistSuffix));
        sb.append(',');
        sb.append("artistAlphaSort");
        sb.append('=');
        sb.append(((this.artistAlphaSort == null)?"<null>":this.artistAlphaSort));
        sb.append(',');
        sb.append("artistNationality");
        sb.append('=');
        sb.append(((this.artistNationality == null)?"<null>":this.artistNationality));
        sb.append(',');
        sb.append("artistBeginDate");
        sb.append('=');
        sb.append(((this.artistBeginDate == null)?"<null>":this.artistBeginDate));
        sb.append(',');
        sb.append("artistEndDate");
        sb.append('=');
        sb.append(((this.artistEndDate == null)?"<null>":this.artistEndDate));
        sb.append(',');
        sb.append("artistGender");
        sb.append('=');
        sb.append(((this.artistGender == null)?"<null>":this.artistGender));
        sb.append(',');
        sb.append("artistWikidataURL");
        sb.append('=');
        sb.append(((this.artistWikidataURL == null)?"<null>":this.artistWikidataURL));
        sb.append(',');
        sb.append("artistULANURL");
        sb.append('=');
        sb.append(((this.artistULANURL == null)?"<null>":this.artistULANURL));
        sb.append(',');
        sb.append("objectDate");
        sb.append('=');
        sb.append(((this.objectDate == null)?"<null>":this.objectDate));
        sb.append(',');
        sb.append("objectBeginDate");
        sb.append('=');
        sb.append(((this.objectBeginDate == null)?"<null>":this.objectBeginDate));
        sb.append(',');
        sb.append("objectEndDate");
        sb.append('=');
        sb.append(((this.objectEndDate == null)?"<null>":this.objectEndDate));
        sb.append(',');
        sb.append("medium");
        sb.append('=');
        sb.append(((this.medium == null)?"<null>":this.medium));
        sb.append(',');
        sb.append("dimensions");
        sb.append('=');
        sb.append(((this.dimensions == null)?"<null>":this.dimensions));
        sb.append(',');
        sb.append("measurements");
        sb.append('=');
        sb.append(((this.measurements == null)?"<null>":this.measurements));
        sb.append(',');
        sb.append("creditLine");
        sb.append('=');
        sb.append(((this.creditLine == null)?"<null>":this.creditLine));
        sb.append(',');
        sb.append("geographyType");
        sb.append('=');
        sb.append(((this.geographyType == null)?"<null>":this.geographyType));
        sb.append(',');
        sb.append("city");
        sb.append('=');
        sb.append(((this.city == null)?"<null>":this.city));
        sb.append(',');
        sb.append("state");
        sb.append('=');
        sb.append(((this.state == null)?"<null>":this.state));
        sb.append(',');
        sb.append("county");
        sb.append('=');
        sb.append(((this.county == null)?"<null>":this.county));
        sb.append(',');
        sb.append("country");
        sb.append('=');
        sb.append(((this.country == null)?"<null>":this.country));
        sb.append(',');
        sb.append("region");
        sb.append('=');
        sb.append(((this.region == null)?"<null>":this.region));
        sb.append(',');
        sb.append("subregion");
        sb.append('=');
        sb.append(((this.subregion == null)?"<null>":this.subregion));
        sb.append(',');
        sb.append("locale");
        sb.append('=');
        sb.append(((this.locale == null)?"<null>":this.locale));
        sb.append(',');
        sb.append("locus");
        sb.append('=');
        sb.append(((this.locus == null)?"<null>":this.locus));
        sb.append(',');
        sb.append("excavation");
        sb.append('=');
        sb.append(((this.excavation == null)?"<null>":this.excavation));
        sb.append(',');
        sb.append("river");
        sb.append('=');
        sb.append(((this.river == null)?"<null>":this.river));
        sb.append(',');
        sb.append("classification");
        sb.append('=');
        sb.append(((this.classification == null)?"<null>":this.classification));
        sb.append(',');
        sb.append("rightsAndReproduction");
        sb.append('=');
        sb.append(((this.rightsAndReproduction == null)?"<null>":this.rightsAndReproduction));
        sb.append(',');
        sb.append("linkResource");
        sb.append('=');
        sb.append(((this.linkResource == null)?"<null>":this.linkResource));
        sb.append(',');
        sb.append("metadataDate");
        sb.append('=');
        sb.append(((this.metadataDate == null)?"<null>":this.metadataDate));
        sb.append(',');
        sb.append("repository");
        sb.append('=');
        sb.append(((this.repository == null)?"<null>":this.repository));
        sb.append(',');
        sb.append("objectURL");
        sb.append('=');
        sb.append(((this.objectURL == null)?"<null>":this.objectURL));
        sb.append(',');
        sb.append("tags");
        sb.append('=');
        sb.append(((this.tags == null)?"<null>":this.tags));
        sb.append(',');
        sb.append("objectWikidataURL");
        sb.append('=');
        sb.append(((this.objectWikidataURL == null)?"<null>":this.objectWikidataURL));
        sb.append(',');
        sb.append("isTimelineWork");
        sb.append('=');
        sb.append(((this.isTimelineWork == null)?"<null>":this.isTimelineWork));
        sb.append(',');
        sb.append("galleryNumber");
        sb.append('=');
        sb.append(((this.galleryNumber == null)?"<null>":this.galleryNumber));
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

    public String toShortString(){
        return String.format("Titulo: %s \nAutor: %s\nAño: %s\nMedio: %s\nImagen: %s", getTitle(),
                getArtistDisplayName(),getObjectDate(),getMedium(),getPrimaryImage());
    }
}
