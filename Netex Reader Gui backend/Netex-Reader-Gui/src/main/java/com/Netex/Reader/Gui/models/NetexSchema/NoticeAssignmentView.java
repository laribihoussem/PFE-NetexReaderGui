package com.Netex.Reader.Gui.models.NetexSchema;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "NoticeAssignmentView")
@XmlAccessorType(XmlAccessType.FIELD)
public class NoticeAssignmentView {
    @XmlAttribute(name = "ref")
    private String ref;
    @XmlAttribute(name = "order")
    private String order;
    @XmlElement(name = "Mark")
    private String Mark;
    @XmlElement(name = "UrlMark")
    private String UrlMark;
    @XmlElement(name = "TypeOfNoticeRef")
    private TypeOfNoticeRef TypeOfNoticeRef;
}
