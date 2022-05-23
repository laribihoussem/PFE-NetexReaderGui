package com.Netex.Reader.Gui.models.NetexSchema;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "TransportSubmode")
@XmlAccessorType(XmlAccessType.FIELD)
public class TransportSubmode {
    @XmlElement(name = "BusSubmode")
    private  String BusSubmode;
    @XmlElement(name = "RailSubmode")
    private String RailSubmode;
}
