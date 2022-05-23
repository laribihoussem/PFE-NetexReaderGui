package com.Netex.Reader.Gui.models.NetexSchema;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "Roles")
@XmlAccessorType(XmlAccessType.FIELD)
public class Roles {
    @XmlElement(name = "ResponsibilityRoleAssignment")
    private ResponsibilityRoleAssignment ResponsibilityRoleAssignment;
}
