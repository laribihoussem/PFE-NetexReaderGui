package com.Netex.Reader.Gui.models.NetexSchema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name="PublicationDelivery")
@ToString
public class Netex {
    @XmlElement(name = "PublicationTimestamp")
    private String PublicationTimestamp;

    @XmlElement(name = "ParticipantRef")
    private String ParticipantRef;

    private DataObject dataObjects;

    public DataObject getDataObjects() {
        return dataObjects;
    }

    public void setDataObjects(DataObject dataObjects) {
        this.dataObjects = dataObjects;
    }

    //private DataObject dataObject;

    public String getPublicationTimestamp() {
        return PublicationTimestamp;
    }

    public void setPublicationTimestamp(String publicationTimestamp) {
        PublicationTimestamp = publicationTimestamp;
    }

    public String getParticipantRef() {
        return ParticipantRef;
    }

    public void setParticipantRef(String participantRef) {
        ParticipantRef = participantRef;
    }

    /*public DataObject getDataObject() {
        return dataObject;
    }

    public void setDataObject(DataObject dataObject) {
        this.dataObject = dataObject;
    }*/
}
