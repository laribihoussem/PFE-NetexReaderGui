package com.Netex.Reader.Gui.models.NetexSchema;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "SchematicMap")
@XmlAccessorType(XmlAccessType.FIELD)
public class SchematicMap {
    @XmlAttribute(name = "version")
    private String version;
    @XmlAttribute(name = "id")
    private String id;
    @XmlElement(name = "Name")
    private String Name;
    @XmlElement(name = "ImageUri")
    private String ImageUri;
    @XmlElement(name = "DepictedObjectRef")
    private DepictedObjectRef DepictedObjectRef;
}
