package com.Netex.Reader.Gui.models.NetexSchema;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "Line")
@XmlAccessorType(XmlAccessType.FIELD)
public class Network {
    @XmlAttribute(name = "version")
    private String version;
    @XmlAttribute(name = "id")
    private String id;
    @XmlAttribute(name = "changed")
    private String changed ;
    @XmlElement(name = "Name")
    private String Name;
}
