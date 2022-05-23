package com.Netex.Reader.Gui.models.NetexSchema;
import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "GroupOfLines")
@XmlAccessorType(XmlAccessType.FIELD)
public class GroupOfLines {
    @XmlAttribute(name = "version")
    private String version;
    @XmlAttribute(name = "responsibilitySetRef")
    private String responsibilitySetRef;
    @XmlAttribute(name = "created")
    private String created ;
    @XmlAttribute(name = "changed")
    private String changed ;
    @XmlAttribute(name = "status")
    private String status ;
    @XmlAttribute(name = "id")
    private String id;
    @XmlElement(name = "keyList")
    private keyList keyList;
    @XmlElement(name = "Name")
    private String Name;
    @XmlElement(name = "PrivateCode")
    private String PrivateCode;
    @XmlElement(name = "members")
    private Members members;
    @XmlElement(name = "TransportMode")
    private String TransportMode;
}
