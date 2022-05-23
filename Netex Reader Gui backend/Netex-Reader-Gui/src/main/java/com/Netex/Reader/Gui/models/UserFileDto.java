package com.Netex.Reader.Gui.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;


@Data
@NoArgsConstructor
@Slf4j
public class UserFileDto {

    private String inputFormat;

    private String outputFormat;

    private String fileName;

    private String userEmail;

    private String convertedFileName;

    private String destinationName;

    private String creationDate;

    public UserFileDto(String inputFormat, String outputFormat, String name,String creationDate, String email, String convertedFileName, String destinationName) {
        super();
        this.inputFormat=inputFormat;
        this.outputFormat= outputFormat;
        this.fileName = name;
        this.creationDate=creationDate;
        this.userEmail=email;
        this.convertedFileName=convertedFileName;
        this.destinationName= destinationName;
    }

    public void convert(ConvertedFile file) {
        this.fileName= file.getFile().getName();
        this.outputFormat= file.getFile().getOutputFormat();
        this.inputFormat= file.getFile().getInputFormat();
        this.convertedFileName=file.getName();
        this.userEmail= file.getFile().getUsers().getEmail();
        String[] date = file.getFile().getDate_creation().toString().split(" ");
        this.creationDate= date[0];
        this.destinationName= file.getFile().getDestination().getName();
    }

}
