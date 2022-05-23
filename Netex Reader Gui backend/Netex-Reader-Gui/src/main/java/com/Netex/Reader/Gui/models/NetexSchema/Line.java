package com.Netex.Reader.Gui.models.NetexSchema;
import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "Line")
@XmlAccessorType(XmlAccessType.FIELD)
public class Line {
    @XmlAttribute(name = "version")
    private String version;
    @XmlAttribute(name = "datasourceref")
    private String datasourceref;
    @XmlAttribute(name = "created")
    private String created ;
    @XmlAttribute(name = "changed")
    private String changed ;
    @XmlAttribute(name = "status")
    private String status ;
    @XmlAttribute(name = "derivedFromObjectRef")
    private String derivedFromObjectRef ;
    @XmlAttribute(name = "id")
    private String id;

    @XmlElement(name = "ValidBetween")
    private ValidBetween ValidBetween;
    @XmlElement(name = "Name")
    private String Name;
    @XmlElement(name = "ShortName")
    private String ShortName;
    @XmlElement(name = "TransportMode")
    private String TransportMode;
    @XmlElement(name = "TransportSubmode")
    private TransportSubmode TransportSubmode;
    @XmlElement(name = "PublicCode")
    private String PublicCode;
    @XmlElement(name = "PrivateCode")
    private String PrivateCode;
    @XmlElement(name = "OperatorRef")
    private OperatorRef OperatorRef;
    @XmlElement(name = "additionalOperators")
    private additionalOperators additionalOperators;
    @XmlElement(name = "TypeOfLineRef")
    private TypeOfLineRef TypeOfLineRef;
    @XmlElement(name = "RepresentedByGroupRef")
    private RepresentedByGroupRef RepresentedByGroupRef;
    @XmlElement(name = "Presentation")
    private Presentation Presentation;
    @XmlElement(name = "AlternativePresentation")
    private AlternativePresentation AlternativePresentation;
    @XmlElement(name = "AccessibilityAssessment")
    private AccessibilityAssessment AccessibilityAssessment;
    @XmlElement(name = "noticeAssignments")
    private NoticeAssignments noticeAssignments;
}
