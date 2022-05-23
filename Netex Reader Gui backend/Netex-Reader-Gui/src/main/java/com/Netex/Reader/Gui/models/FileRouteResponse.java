package com.Netex.Reader.Gui.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class FileRouteResponse {
    private String fileName;

    private String inputFormat;

    private String outputFormat;

    private List<Routes> routes;


    public void convert(File file) {
        this.fileName= file.getName();
        this.outputFormat= file.getOutputFormat();
        this.inputFormat= file.getInputFormat();
        this.routes=file.getRoutes();
    }

}
