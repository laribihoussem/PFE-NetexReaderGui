package com.Netex.Reader.Gui.models.NetexSchema;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "StopPlace")
@XmlAccessorType(XmlAccessType.FIELD)
public class StopPlace {
    @XmlAttribute(name = "version")
    private String version;
    @XmlAttribute(name = "dataSourceRef")
    private String dataSourceRef;
    @XmlAttribute(name = "created")
    private String created ;
    @XmlAttribute(name = "changed")
    private String changed ;
    @XmlAttribute(name = "status")
    private String status ;
    @XmlAttribute(name = "derivedFromObjectRef")
    private String derivedFromObjectRef ;
    @XmlAttribute(name = "id")
    private String id;

    private keyList keyList;
    @XmlElement(name = "Name")
    private String Name;
    @XmlElement(name = "Centroid")
    private Centroid Centroid;
    @XmlElement(name = "PostalAddress")
    private PostalAddress PostalAddress;
    @XmlElement(name = "Landmark")
    private String Landmark;
    @XmlElement(name = "ParentSiteRef")
    private ParentSiteRef ParentSiteRef;
    @XmlElement(name = "entrances")
    private Entrances entrances;
    @XmlElement(name = "equipmentPlaces")
    private equipmentPlaces equipmentPlaces;
    @XmlElement(name = "StopPlaceType")
    private String StopPlaceType;
}
