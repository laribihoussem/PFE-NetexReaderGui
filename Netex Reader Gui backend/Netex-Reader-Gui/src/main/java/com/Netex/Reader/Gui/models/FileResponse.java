package com.Netex.Reader.Gui.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
public class FileResponse {
    private long id;

    private String type;

    private String name;

    private String date_creation;


    public FileResponse() {

    }

    public FileResponse(long id, String type, String name, String date_creation) {
        super();
        this.id = id;
        this.type = type;
        this.name = name;
        this.date_creation=date_creation;
    }

}
