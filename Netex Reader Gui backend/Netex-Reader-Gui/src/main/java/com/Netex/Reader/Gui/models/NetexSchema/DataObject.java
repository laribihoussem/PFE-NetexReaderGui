package com.Netex.Reader.Gui.models.NetexSchema;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "dataObjects")
//@XmlAccessorType(XmlAccessType.FIELD)
public class DataObject {

    private String name;
    @XmlElement(name = "CompositeFrames")
    private CompositeFrame CompositeFrames;
}
