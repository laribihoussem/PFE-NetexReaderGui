package com.Netex.Reader.Gui.service;

import com.Netex.Reader.Gui.models.ConvertedFile;
import com.Netex.Reader.Gui.repository.ConvertedFileRepo;
import com.Netex.Reader.Gui.repository.FileRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.json.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Service("toCsv")
@Slf4j
@Transactional
@AllArgsConstructor
public class toCsv {

    private final FileRepo fileRepo;
    private final ConvertedFileRepo convertedFileRepo;


    public void jsonToCsv(Exchange exchange) throws IOException {
        File file = exchange.getIn().getBody(File.class);
        String name = file.getName();
        log.info(name);
        File convFile=null;
        Charset charset = StandardCharsets.UTF_8;
        try (InputStream in = new FileInputStream(file))
        {
            byte[] bytes = new byte[(int) file.length()];
            in.read(bytes);
            String json = new String(bytes, charset);
            int indexOfBegining=json.indexOf("[");
            String result=json.substring(indexOfBegining,json.length()-1);
            JSONTokener tokener = new JSONTokener(result);
            JSONArray jsonArray = new JSONArray(tokener);
            String csv = CDL.toString(jsonArray);
            int i = file.getName().lastIndexOf('.');
            String name1 = file.getName().substring(0,i);
            convFile = new File( "files/output/"+name1+".csv" );
            FileOutputStream fos = new FileOutputStream(convFile);
            byte[] myBytes = csv.getBytes();
            fos.write(myBytes);
            fos.close();
            com.Netex.Reader.Gui.models.File file1 = fileRepo.getFileByName(name);
            ConvertedFile convertedFile =new ConvertedFile();
            convertedFile.setName(convFile.getName());
            convertedFile.setType("text/csv");
            convertedFile.setData(myBytes);
            convertedFile.setFile(file1);
            convertedFileRepo.save(convertedFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void xmlToCsv(Exchange exchange) throws IOException {
        File file = exchange.getIn().getBody(File.class);
        String name = file.getName();
        log.info(name);
        File convFile=null;
        Charset charset = StandardCharsets.UTF_8;
        try (InputStream in = new FileInputStream(file))
        {
            byte[] bytes = new byte[(int) file.length()];
            in.read(bytes);
            String content = new String(bytes, charset);
            JSONObject json = XML.toJSONObject(content);
            String jsonString = json.toString(4);
            int indexOfBegining=jsonString.indexOf("[");
            String result=jsonString.substring(indexOfBegining,jsonString.length()-1);
            JSONTokener tokener = new JSONTokener(result);
            JSONArray jsonArray = new JSONArray(tokener);
            String csv = CDL.toString(jsonArray);
            int i = file.getName().lastIndexOf('.');
            String name1 = file.getName().substring(0,i);
            convFile = new File( "files/output/"+name1+".csv" );
            FileOutputStream fos = new FileOutputStream(convFile);
            byte[] myBytes = csv.getBytes();
            fos.write(myBytes);
            fos.close();
            com.Netex.Reader.Gui.models.File file1 = fileRepo.getFileByName(name);
            ConvertedFile convertedFile =new ConvertedFile();
            convertedFile.setName(convFile.getName());
            convertedFile.setType("text/csv");
            convertedFile.setData(myBytes);
            convertedFile.setFile(file1);
            convertedFileRepo.save(convertedFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
