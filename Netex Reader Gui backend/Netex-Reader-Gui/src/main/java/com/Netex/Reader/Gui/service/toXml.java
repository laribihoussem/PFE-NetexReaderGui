package com.Netex.Reader.Gui.service;

import com.Netex.Reader.Gui.models.ConvertedFile;
import com.Netex.Reader.Gui.repository.ConvertedFileRepo;
import com.Netex.Reader.Gui.repository.FileRepo;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.xml.serialize.XMLSerializer;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.apache.xml.serialize.OutputFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service("toXml")
@Slf4j
@Transactional
@AllArgsConstructor
public class toXml {

    public final FileRepo fileRepo;
    private final ConvertedFileRepo convertedFileRepo;

    public void jsonToXml(Exchange exchange) throws IOException {

        File file = exchange.getIn().getBody(File.class);
        String name = file.getName();
        log.info(name);
        File convFile=null;
        String root="root";
        Charset charset = StandardCharsets.UTF_8;

        try (InputStream in = new FileInputStream(file))
        {
            byte[] bytes = new byte[(int) file.length()];
            in.read(bytes);

            String content = new String(bytes, charset);
            //log.info(content);
            JSONObject json = new JSONObject(content);
            String xml = XML.toString(json);
            //log.info(xml);
            String xmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<"+root+">" + xml + "</"+root+">";
            //log.info(xmlContent);
            String xmlIndented = format(xmlContent, true);
            //log.info(xmlIndented);
            int i = file.getName().lastIndexOf('.');
            String name1 = file.getName().substring(0,i);
            //log.info(name1);
            convFile = new File( "files/output/"+name1+".xml" );
            FileOutputStream fos = new FileOutputStream(convFile);
            byte[] myBytes = xmlIndented.getBytes();
            fos.write(myBytes);
            fos.close();
            com.Netex.Reader.Gui.models.File file1 = fileRepo.getFileByName(name);
            ConvertedFile convertedFile =new ConvertedFile();
            convertedFile.setName(convFile.getName());
            convertedFile.setType("text/xml");
            convertedFile.setData(myBytes);
            convertedFile.setFile(file1);
            convertedFileRepo.save(convertedFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void csvToXml(Exchange exchange) throws IOException, ParserConfigurationException, SAXException {

        File file = exchange.getIn().getBody(File.class);
        String name = file.getName();
        log.info(name);
        File convFile=null;
        String root="root";
        Charset charset = StandardCharsets.UTF_8;
        CsvSchema bootstrap = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        MappingIterator<Map<?, ?>> mappingIterator =  csvMapper.reader().forType(Map.class).with(bootstrap).readValues(file);
        List<Map<?, ?>> list = mappingIterator.readAll();
        String json = new GsonBuilder().setPrettyPrinting().create().toJson(list).toString();
        JSONArray json1 = new JSONArray(json);
        String xml = XML.toString(json1);
        log.info(xml);
        String xmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<"+root+">" + xml + "</"+root+">";
        log.info(xmlContent);
        String xmlIndented = format(xmlContent, true);
        log.info(xmlIndented);
        int i = file.getName().lastIndexOf('.');
        String name1 = file.getName().substring(0,i);
        log.info(name1);
        convFile = new File( "files/output/"+name1+".xml" );
        FileOutputStream fos = new FileOutputStream(convFile);
        byte[] myBytes = xmlIndented.getBytes();
        fos.write(myBytes);
        fos.close();
        com.Netex.Reader.Gui.models.File file1 = fileRepo.getFileByName(name);
        ConvertedFile convertedFile =new ConvertedFile();
        convertedFile.setName(convFile.getName());
        convertedFile.setType("text/xml");
        convertedFile.setData(myBytes);
        convertedFile.setFile(file1);
        convertedFileRepo.save(convertedFile);
    }

    public static String format(String xml, Boolean ommitXmlDeclaration) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = db.parse(new InputSource(new StringReader(xml)));
        OutputFormat format = new OutputFormat(doc);
        format.setIndenting(true);
        format.setIndent(2);
        format.setOmitXMLDeclaration(ommitXmlDeclaration);
        format.setLineWidth(Integer.MAX_VALUE);
        Writer outxml = new StringWriter();
        XMLSerializer serializer = new XMLSerializer(outxml, format);
        serializer.serialize(doc);
        return outxml.toString();
    }

    /*public void csvToXml(Exchange exchange) throws IOException {

        File file = exchange.getIn().getBody(File.class);
        String name = file.getName();
        log.info(name);
        File convFile=null;
        String root="root";
        Charset charset = StandardCharsets.UTF_8;

        try (InputStream in = new FileInputStream(file))
        {
            byte[] bytes = new byte[(int) file.length()];
            in.read(bytes);
            String csv = new String(bytes, charset);
            log.info(csv);
            String json = CDL.toJSONArray(csv).toString(4);
            log.info(json);
            JSONArray json1 = new JSONArray(json);
            String xml = XML.toString(json1);
            log.info(xml);
            String xmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<"+root+">" + xml + "</"+root+">";
            log.info(xmlContent);
            int i = file.getName().lastIndexOf('.');
            String name1 = file.getName().substring(0,i);
            log.info(name1);
            convFile = new File( "files/output/"+name1+".xml" );
            FileOutputStream fos = new FileOutputStream(convFile);
            byte[] myBytes = xmlContent.getBytes();
            fos.write(myBytes);
            fos.close();
            *//*String destination = "output";
            exchange.setProperty("destination",destination);*//*
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
