package com.Netex.Reader.Gui.models.NetexSchema;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "ValidBetween")
public class ValidBetween {

    @XmlElement(name = "FromDate")
    private String FromDate;
}
