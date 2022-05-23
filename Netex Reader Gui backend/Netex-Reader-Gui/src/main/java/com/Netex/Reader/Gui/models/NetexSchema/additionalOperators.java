package com.Netex.Reader.Gui.models.NetexSchema;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "OperatorRef")
@XmlAccessorType(XmlAccessType.FIELD)
public class additionalOperators {

    @XmlElement(name = "OperatorRef")
    private OperatorRef OperatorRef;
}
