package com.Netex.Reader.Gui.models.NetexSchema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "frames")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Frames {
    @XmlElement(name = "GeneralFrame")
    private List<GeneralFrame> GeneralFrame;
}
