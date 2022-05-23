package com.Netex.Reader.Gui.models.NetexSchema;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "ContactDetails")
@XmlAccessorType(XmlAccessType.FIELD)
public class ContactDetails {
    @XmlElement(name = "ContactPerson")
    private String ContactPerson;
    @XmlElement(name = "Email")
    private String Email;
    @XmlElement(name = "Phone")
    private String Phone;
    @XmlElement(name = "Url")
    private String Url;
    @XmlElement(name = "FurtherDetails")
    private String FurtherDetails;
}
