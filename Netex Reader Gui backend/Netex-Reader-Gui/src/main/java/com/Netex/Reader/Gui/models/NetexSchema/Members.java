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

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@XmlRootElement(name = "members")
@XmlAccessorType(XmlAccessType.FIELD)
public class Members {
    @XmlElement(name = "OrganisationalUnit")
    private List<OrganisationalUnit> OrganisationalUnit;
    @XmlElement(name = "Notice")
    private List<Notice> Notice;
    @XmlElement(name = "ResponsibilitySet")
    private List<ResponsibilitySet> ResponsibilitySet;
    @XmlElement(name = "TypeOfEntity")
    private List<TypeOfEntity> TypeOfEntity;
    @XmlElement(name = "Operator")
    private List<Operator> Operator;
    @XmlElement(name = "SchematicMap")
    private List<SchematicMap> SchematicMap;
    @XmlElement(name = "Line")
    private List<Line> Line;
    @XmlElement(name = "GroupOfLines")
    private List<GroupOfLines> GroupOfLines;
    @XmlElement(name = "AvailabilityCondition")
    private List<AvailabilityCondition> AvailabilityCondition;
    @XmlElement(name = "Network")
    private List<Network> Network;
    @XmlElement(name = "LineRef")
    private LineRef LineRef;
    @XmlElement(name = "StopPlace")
    private List<StopPlace> StopPlace;
    @XmlElement(name = "Quay")
    private List<Quay> Quay;
    @XmlElement(name = "StopPlaceEntrance")
    private List<StopPlaceEntrance> StopPlaceEntrance;
}
