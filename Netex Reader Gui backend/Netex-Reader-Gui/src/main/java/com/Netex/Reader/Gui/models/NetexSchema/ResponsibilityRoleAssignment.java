package com.Netex.Reader.Gui.models.NetexSchema;
import lombok.Data;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "ResponsibilityRoleAssignment")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponsibilityRoleAssignment {
    @XmlAttribute(name = "id")
    private String id;
    @XmlAttribute(name = "version")
    private String version;
    @XmlElement(name = "TypeOfResponsibilityRoleRef")
    private TypeOfResponsibilityRoleRef TypeOfResponsibilityRoleRef;
}
