package com.Netex.Reader.Gui.models.NetexSchema;
import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "Centroid")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostalAddress {
    @XmlAttribute(name = "version")
    private String version;
    @XmlAttribute(name = "id")
    private String id;

    @XmlElement(name = "AddressLine1")
    private String AddressLine1;
    @XmlElement(name = "Town")
    private String Town;
    @XmlElement(name = "PostalRegion")
    private String PostalRegion;
}
