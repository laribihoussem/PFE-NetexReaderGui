package com.Netex.Reader.Gui.models.NetexSchema;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "AccessibilityAssessment")
@XmlAccessorType(XmlAccessType.FIELD)
public class AccessibilityAssessment {
    @XmlAttribute(name = "version")
    private String version;
    @XmlAttribute(name = "id")
    private String id;

    @XmlElement(name = "MobilityImpaired")
    private String MobilityImpaired;
    @XmlElement(name = "MobilityImpairedAccess")
    private String MobilityImpairedAccess;
    @XmlElement(name = "limitations")
    private Limitations limitations;
}
