package com.Netex.Reader.Gui.models.NetexSchema;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TypeOfResponsibilityRoleRef")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class TypeOfResponsibilityRoleRef {
    @XmlAttribute(name = "ref")
    private String ref;

}
