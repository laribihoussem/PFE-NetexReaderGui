package com.Netex.Reader.Gui.models.NetexSchema;
import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Quay")
public class Quay {
    @XmlAttribute(name = "version")
    private String version;
    @XmlAttribute(name = "dataSourceRef")
    private String dataSourceRef;
    @XmlAttribute(name = "created")
    private String created ;
    @XmlAttribute(name = "changed")
    private String changed ;
    @XmlAttribute(name = "derivedFromObjectRef")
    private String derivedFromObjectRef ;
    @XmlAttribute(name = "id")
    private String id;

    @XmlElement(name = "keyList")
    private keyList keyList;
    @XmlElement(name = "Name")
    private String Name;
    @XmlElement(name = "Description")
    private String Description;
    @XmlElement(name = "PrivateCode")
    private String PrivateCode;
    @XmlElement(name = "Centroid")
    private Centroid Centroid;
    @XmlElement(name = "PostalAddress")
    private PostalAddress PostalAddress;
    @XmlElement(name = "AccessibilityAssessment")
    private AccessibilityAssessment AccessibilityAssessment;
    @XmlElement(name = "TransportMode")
    private String TransportMode;
    @XmlElement(name = "tariffZones")
    private TariffZones tariffZones;
    @XmlElement(name = "PublicCode")
    private String PublicCode;
    @XmlElement(name = "ParentQuayRef")
    private ParentSiteRef ParentQuayRef;
    @XmlElement(name = "destinations")
    private Destinations destinations;
}
