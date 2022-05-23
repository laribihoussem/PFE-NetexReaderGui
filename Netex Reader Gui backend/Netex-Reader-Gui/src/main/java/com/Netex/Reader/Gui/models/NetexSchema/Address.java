package com.Netex.Reader.Gui.models.NetexSchema;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "Address")
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {
    @XmlElement(name = "HouseNumber")
    private String HouseNumber;
    @XmlElement(name = "AddressLine1")
    private String AddressLine1;
    @XmlElement(name = "Street")
    private String Street;
    @XmlElement(name = "Town")
    private String Town;
    @XmlElement(name = "PostCode")
    private String PostCode;
    @XmlElement(name = "PostCodeExtension")
    private String PostCodeExtension;
}
