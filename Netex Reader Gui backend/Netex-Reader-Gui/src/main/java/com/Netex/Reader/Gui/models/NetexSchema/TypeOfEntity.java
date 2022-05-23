package com.Netex.Reader.Gui.models.NetexSchema;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "TypeOfEntity")
@XmlAccessorType(XmlAccessType.FIELD)
public class TypeOfEntity {
    @XmlAttribute(name = "id")
    private String id;
    @XmlAttribute(name = "version")
    private String version;
    @XmlElement(name = "Name")
    private String Name;
}
