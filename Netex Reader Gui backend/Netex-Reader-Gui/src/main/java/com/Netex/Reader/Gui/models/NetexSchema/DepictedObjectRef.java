package com.Netex.Reader.Gui.models.NetexSchema;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "DepictedObjectRef")
@XmlAccessorType(XmlAccessType.FIELD)
public class DepictedObjectRef {
    @XmlAttribute(name = "version")
    private String version;
    @XmlAttribute(name = "ref")
    private String ref;
}
