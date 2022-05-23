package com.Netex.Reader.Gui.models.NetexSchema;
import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "BrandingRef")
@XmlAccessorType(XmlAccessType.FIELD)
public class BrandingRef {
    @XmlAttribute(name = "ref")
    private String ref;
}
