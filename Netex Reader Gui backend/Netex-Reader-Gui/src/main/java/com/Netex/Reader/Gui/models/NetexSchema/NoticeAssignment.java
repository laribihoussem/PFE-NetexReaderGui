package com.Netex.Reader.Gui.models.NetexSchema;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "NoticeAssignment")
@XmlAccessorType(XmlAccessType.FIELD)
public class NoticeAssignment {
    @XmlAttribute(name = "version")
    private String version;
    @XmlAttribute(name = "ref")
    private String ref;
    @XmlAttribute(name = "order")
    private String order;
    @XmlElement(name = "NoticeRef")
    private NoticeRef NoticeRef;
}
