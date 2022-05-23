package com.Netex.Reader.Gui.models.NetexSchema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "GeneralFrame")
@XmlAccessorType(XmlAccessType.FIELD)
public class GeneralFrame {
    @XmlElement(name = "TypeOfFrameRef")
    private String TypeOfFrameRef;

    @XmlElement(name = "members")
    private Members members;
}
