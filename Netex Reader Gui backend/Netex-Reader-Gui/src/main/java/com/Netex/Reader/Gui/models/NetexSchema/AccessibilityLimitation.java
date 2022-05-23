package com.Netex.Reader.Gui.models.NetexSchema;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "AccessibilityLimitation")
@XmlAccessorType(XmlAccessType.FIELD)
public class AccessibilityLimitation {
    @XmlElement(name = "WheelchairAccess")
    private String WheelchairAccess;
    @XmlElement(name = "AudibleSignsAvailable")
    private String AudibleSignsAvailable;
    @XmlElement(name = "VisualSignsAvailable")
    private String VisualSignsAvailable;

}
