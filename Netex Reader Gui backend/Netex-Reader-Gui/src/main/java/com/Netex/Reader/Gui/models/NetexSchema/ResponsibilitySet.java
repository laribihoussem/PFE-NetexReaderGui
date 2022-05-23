package com.Netex.Reader.Gui.models.NetexSchema;

import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "ResponsibilitySet")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponsibilitySet {
    @XmlAttribute(name = "id")
    private String id;
    @XmlAttribute(name = "version")
    private String version;
    @XmlElement(name = "keyList")
    private List<keyList> keyList;
    @XmlElement(name = "Name")
    private String Name;
    @XmlElement(name = "roles")
    private Roles roles;
}
