package com.Netex.Reader.Gui.models.NetexSchema;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "limitations")
@XmlAccessorType(XmlAccessType.FIELD)
public class Limitations {
    @XmlElement(name = "AccessibilityLimitation")
    private AccessibilityLimitation AccessibilityLimitation;
}
