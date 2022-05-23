package com.Netex.Reader.Gui.models.NetexSchema;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "destinations")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Destinations {
    @XmlElement(name = "DestinationDisplayView")
    private DestinationDisplayView DestinationDisplayView;
}
