package com.Netex.Reader.Gui.models.NetexSchema;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Notice")
public class Notice {
    @XmlAttribute(name = "id")
    private String id;
    @XmlAttribute(name = "version")
    private String version;
    @XmlElement(name = "Name")
    private String Name;
    @XmlElement(name = "Text")
    private String Text;
    @XmlElement(name = "TypeOfNoticeRef")
    private TypeOfNoticeRef TypeOfNoticeRef;
}
