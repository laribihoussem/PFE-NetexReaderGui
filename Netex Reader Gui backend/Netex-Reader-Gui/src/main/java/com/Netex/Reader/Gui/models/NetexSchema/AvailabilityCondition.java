package com.Netex.Reader.Gui.models.NetexSchema;
import lombok.Data;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "ConditionedObjectRef")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class AvailabilityCondition {
    @XmlAttribute(name = "version")
    private String version;
    @XmlAttribute(name = "id")
    private String id;
    @XmlElement(name = "ConditionedObjectRef")
    private ConditionedObjectRef ConditionedObjectRef;
    @XmlElement(name = "FromDate")
    private String FromDate;
    @XmlElement(name = "ToDate")
    private String ToDate;
}
