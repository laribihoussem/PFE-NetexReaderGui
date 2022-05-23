package com.Netex.Reader.Gui.models.NetexSchema;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "TariffZoneRef")
@XmlAccessorType(XmlAccessType.FIELD)
public class TariffZoneRef {
    @XmlAttribute(name = "ref")
    private String ref;
}
