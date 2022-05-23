package com.Netex.Reader.Gui.models.NetexSchema;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "StopPlaceEntrance")
public class StopPlaceEntrance {
    @XmlAttribute(name = "version")
    private String version;
    @XmlAttribute(name = "dataSourceRef")
    private String dataSourceRef;
    @XmlAttribute(name = "id")
    private String id;
    @XmlElement(name = "keyList")
    private keyList keyList;
    @XmlElement(name = "Name")
    private String Name;
    @XmlElement(name = "ShortName")
    private String ShortName;
    @XmlElement(name = "PrivateCode")
    private String PrivateCode;
    @XmlElement(name = "Centroid")
    private Centroid Centroid;
    @XmlElement(name = "IsEntry")
    private String IsEntry;
    @XmlElement(name = "IsExit")
    private String IsExit;
}
