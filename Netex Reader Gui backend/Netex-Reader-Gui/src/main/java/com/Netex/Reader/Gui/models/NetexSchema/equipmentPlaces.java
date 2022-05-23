package com.Netex.Reader.Gui.models.NetexSchema;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "equipmentPlaces")
@Data
public class equipmentPlaces {
    @XmlElement(name = "EquipmentPlaceRef")
    private EquipmentPlaceRef EquipmentPlaceRef;
}
