package com.Netex.Reader.Gui.models.NetexSchema;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "Operator")
@XmlAccessorType(XmlAccessType.FIELD)
public class Operator {
    @XmlAttribute(name = "version")
    private String version;
    @XmlAttribute(name = "datasourceref")
    private String datasourceref;
    @XmlAttribute(name = "changed")
    private String changed;
    @XmlAttribute(name = "id")
    private String id;
    @XmlElement(name = "BrandingRef")
    private BrandingRef BrandingRef;
    @XmlElement(name = "Name")
    private String Name;
    @XmlElement(name = "ContactDetails")
    private ContactDetails ContactDetails;
    @XmlElement(name = "Address")
    private Address Address;
}
