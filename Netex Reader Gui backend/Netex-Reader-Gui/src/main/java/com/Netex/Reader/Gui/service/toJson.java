package com.Netex.Reader.Gui.service;

import com.Netex.Reader.Gui.models.ConvertedFile;
import com.Netex.Reader.Gui.models.Destination;
import com.Netex.Reader.Gui.repository.ConvertedFileRepo;
import com.Netex.Reader.Gui.repository.DestinationRepo;
import com.Netex.Reader.Gui.repository.FileRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;

import org.apache.camel.Predicate;
import org.json.CDL;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.*;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@Service("toJson")
@Transactional
@Slf4j
@AllArgsConstructor
public class toJson {

    private final FileRepo fileRepo;
    private final ConvertedFileRepo convertedFileRepo;

    public void xmlToJson(Exchange exchange) throws IOException {

        File file = exchange.getIn().getBody(File.class);
        String name = file.getName();
        log.info(name);
        String jsonString= null;
        File convFile=null;
        Charset charset = StandardCharsets.UTF_8;

        try (InputStream in = new FileInputStream(file))
        {
            byte[] bytes = new byte[(int) file.length()];
            in.read(bytes);

            String content = new String(bytes, charset);
            //log.info(content);
            JSONObject json = XML.toJSONObject(content);
            jsonString = json.toString(4);
            //log.info(jsonString);
            int i = file.getName().lastIndexOf('.');
            String name1 = file.getName().substring(0,i);
            //log.info(name1);
            convFile = new File( "files/output/"+name1+".json" );
            FileOutputStream fos = new FileOutputStream(convFile);
            byte[] myBytes = jsonString.getBytes();
            fos.write(myBytes);
            fos.close();
            com.Netex.Reader.Gui.models.File file1 = fileRepo.getFileByName(name);
            ConvertedFile convertedFile =new ConvertedFile();
            convertedFile.setName(convFile.getName());
            convertedFile.setType("application/json");
            convertedFile.setData(myBytes);
            convertedFile.setFile(file1);
            convertedFileRepo.save(convertedFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*public void csvToJson(Exchange exchange) throws IOException {
        File file = exchange.getIn().getBody(File.class);
        String name = file.getName();
        log.info(name);
        File convFile=null;
        Charset charset = StandardCharsets.UTF_8;
        try (InputStream in = new FileInputStream(file))
        {
            byte[] bytes = new byte[(int) file.length()];
            in.read(bytes);
            String csv = new String(bytes, charset);
            log.info(csv);
            String json = CDL.toJSONArray(csv).toString(4);
            int i = file.getName().lastIndexOf('.');
            String name1 = file.getName().substring(0,i);
            log.info(name1);
            convFile = new File( "files/output/"+name1+".json" );
            FileOutputStream fos = new FileOutputStream(convFile);
            byte[] myBytes = json.getBytes();
            fos.write(myBytes);
            fos.close();

            *//*String destination = "output";
            exchange.setProperty("destination",destination);*//*
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public void csvToJson(Exchange exchange) throws IOException {
        File file = exchange.getIn().getBody(File.class);
        String name = file.getName();
        //log.info(name);
        CsvSchema bootstrap = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        MappingIterator<Map<?, ?>> mappingIterator =  csvMapper.reader().forType(Map.class).with(bootstrap).readValues(file);
        List<Map<?, ?>> list = mappingIterator.readAll();
        String json = new GsonBuilder().setPrettyPrinting().create().toJson(list).toString();
        File convFile=null;
        int i = file.getName().lastIndexOf('.');
        String name1 = file.getName().substring(0,i);
        //log.info(name1);
        convFile = new File( "files/output/"+name1+".json" );
        FileOutputStream fos = new FileOutputStream(convFile);
        byte[] myBytes = json.getBytes();
        fos.write(myBytes);
        fos.close();
        com.Netex.Reader.Gui.models.File file1 = fileRepo.getFileByName(name);
        ConvertedFile convertedFile =new ConvertedFile();
        convertedFile.setName(convFile.getName());
        convertedFile.setType("application/json");
        convertedFile.setData(myBytes);
        convertedFile.setFile(file1);
        convertedFileRepo.save(convertedFile);

    }

    public void setHeader(Exchange exchange) {
        File file = exchange.getIn().getBody(File.class);
        String name = file.getName();
        com.Netex.Reader.Gui.models.File file1 = fileRepo.getFileByName(name);
        String inputFormat = file1.getInputFormat();
        String outputFormat = file1.getOutputFormat();
        Destination dest = file1.getDestination();
        String destName= dest.getName();

        exchange.setProperty("inputFormat",inputFormat);
        exchange.setProperty("outputFormat",outputFormat);
        exchange.setProperty("destination", destName);
    }
}
