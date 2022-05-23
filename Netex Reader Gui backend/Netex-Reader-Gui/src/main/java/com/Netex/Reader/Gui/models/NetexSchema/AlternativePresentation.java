package com.Netex.Reader.Gui.models.NetexSchema;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "AlternativePresentation")
@XmlAccessorType(XmlAccessType.FIELD)
public class AlternativePresentation {
    @XmlElement(name = "ColourName")
    private String ColourName;
    @XmlElement(name = "TextColour")
    private String TextColour;
}
