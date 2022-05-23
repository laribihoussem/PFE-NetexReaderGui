package com.Netex.Reader.Gui.models.NetexSchema;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@Data
//@XmlRootElement(name = "CompositeFrames")
@XmlAccessorType(XmlAccessType.FIELD)
public class CompositeFrame {

    private String groupId;
    private String artifactId;
    private String scope;

    @XmlElement(name = "frames")
    private Frames frames;


}
