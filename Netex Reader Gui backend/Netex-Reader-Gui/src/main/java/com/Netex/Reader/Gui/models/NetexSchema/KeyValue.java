package com.Netex.Reader.Gui.models.NetexSchema;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "KeyValue")
@XmlAccessorType(XmlAccessType.FIELD)
public class KeyValue {
    @XmlElement(name = "Key")
    private String Key;
    @XmlElement(name = "Value")
    private String Value;
}
