package com.Netex.Reader.Gui.service;

import com.Netex.Reader.Gui.models.ConvertedFile;
import com.Netex.Reader.Gui.models.NetexSchema.Netex;
import com.Netex.Reader.Gui.repository.ConvertedFileRepo;
import com.Netex.Reader.Gui.repository.FileRepo;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.commons.io.FileUtils;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.json.*;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service("netex")
@Slf4j
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class ConvertFilesImpl implements ConvertFiles{
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private JobLauncher jobLauncher;
    private final FileRepo fileRepo;
    private final ConvertedFileRepo convertedFileRepo;


    @Override
    public void netexToXml(Exchange exchange) throws Exception{
        File file = exchange.getIn().getBody(File.class);
        jobLauncher.run(netexXmlJob(file), newExecution());
    }


    @Override
    public void netexToCsv(Exchange exchange) throws Exception {
        File file = exchange.getIn().getBody(File.class);
        jobLauncher.run(netexCsvJob(file), newExecution());

    }

    @Override
    public void fromNetex(Exchange exchange) throws Exception {
        File file = exchange.getIn().getBody(File.class);
        jobLauncher.run(netexJsonJob(file), newExecution());

    }
    public JobParameters newExecution() {
        Map<String, JobParameter> parameters = new HashMap<>();
        JobParameter parameter = new JobParameter(new Date());
        parameters.put("currentTime", parameter);
        return new JobParameters(parameters);
    }
    //@Bean
    public ItemReader<Netex> itemReader(File file) {
        String path =file.getAbsolutePath();
        log.info(path);
        Jaxb2Marshaller netexMarshaller = new Jaxb2Marshaller();
        netexMarshaller.setClassesToBeBound(Netex.class);


        return new StaxEventItemReaderBuilder<Netex>()
                .name("netexReader")
                .resource(new FileSystemResource(path))
                .addFragmentRootElements("PublicationDelivery")
                .unmarshaller(netexMarshaller)
                .build();
    }
    public Job netexXmlJob(File file) {
        String root="root";
        Step step1 = stepBuilderFactory.get("PublicationDelivery")
                .<Netex,Netex>chunk(1000000)
                .reader(itemReader(file))
                .writer(new ItemWriter<Netex>() {
                    @Override
                    public void write(List<? extends Netex> list) throws Exception {
                        log.info(list.toString());
                        String jsonStr = new GsonBuilder().setPrettyPrinting().create().toJson(list).toString();
                        int j = jsonStr.indexOf('{');
                        String jsonContent = jsonStr.substring(j);
                        JSONObject json = new JSONObject(jsonContent);
                        String xml = XML.toString(json);
                        String xmlContent = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<"+root+">" + xml + "</"+root+">";
                        String xmlIndented = format(xmlContent, true);
                        File convFile=null;
                        String fileName = file.getName();
                        int i = file.getName().lastIndexOf('.');
                        String name = file.getName().substring(0,i);
                        //log.info(name);
                        convFile = new File( "files/output/"+name+".xml" );
                        FileOutputStream fos = new FileOutputStream(convFile);
                        byte[] myBytes = xmlIndented.getBytes();
                        fos.write(myBytes);
                        fos.close();
                        com.Netex.Reader.Gui.models.File file1 = fileRepo.getFileByName(fileName);
                        ConvertedFile convertedFile =new ConvertedFile();
                        convertedFile.setName(convFile.getName());
                        convertedFile.setType("text/xml");
                        convertedFile.setData(myBytes);
                        convertedFile.setFile(file1);
                        convertedFileRepo.save(convertedFile);
                    }
                })
                .build();
        return jobBuilderFactory.get("netex-data-loader-job")
                .start(step1).build();

    }

    public Job netexCsvJob(File file) {
        Step step1 = stepBuilderFactory.get("PublicationDelivery")
                .<Netex,Netex>chunk(1000000)
                .reader(itemReader(file))
                .writer(new ItemWriter<Netex>() {
                    @Override
                    public void write(List<? extends Netex> list) throws Exception {
                        //log.info(list.toString());
                        String jsonStr = new GsonBuilder().setPrettyPrinting().create().toJson(list).toString();
                        /*log.info(jsonStr);
                        String[] members = jsonStr.split("\"members\"");
                        log.info(members[1])*/;
                        JSONTokener tokener = new JSONTokener(jsonStr);
                        JSONArray jsonArray = new JSONArray(tokener);
                        String csv = CDL.toString(jsonArray);
                        File convFile=null;
                        String fileName = file.getName();
                        int i = file.getName().lastIndexOf('.');
                        String name = file.getName().substring(0,i);
                        //log.info(name);
                        convFile = new File( "files/output/"+name+".csv" );
                        FileOutputStream fos = new FileOutputStream(convFile);
                        byte[] myBytes = csv.getBytes();
                        fos.write(myBytes);
                        fos.close();
                        com.Netex.Reader.Gui.models.File file1 = fileRepo.getFileByName(fileName);
                        ConvertedFile convertedFile =new ConvertedFile();
                        convertedFile.setName(convFile.getName());
                        convertedFile.setType("text/csv");
                        convertedFile.setData(myBytes);
                        convertedFile.setFile(file1);
                        convertedFileRepo.save(convertedFile);
                        /*String jsonStr= gson.toJson(list);
                        FileUtils.writeStringToFile(file, jsonStr, true);*/
                    }
                })
                .build();
        return jobBuilderFactory.get("netex-data-loader-job")
                .start(step1).build();
    }

    public Job netexJsonJob(File file) {
        Step step1 = stepBuilderFactory.get("PublicationDelivery")
                .<Netex,Netex>chunk(1000000)
                .reader(itemReader(file))
                .writer(new ItemWriter<Netex>() {
                    @Override
                    public void write(List<? extends Netex> list) throws Exception {
                        log.info(list.toString());
                        String jsonStr = new GsonBuilder().setPrettyPrinting().create().toJson(list).toString();
                        //log.info(jsonStr);
                        File convFile=null;
                        String fileName = file.getName();
                        int i = file.getName().lastIndexOf('.');
                        String name = file.getName().substring(0,i);
                        //log.info(name);
                        convFile = new File( "files/output/"+name+".json" );
                        FileOutputStream fos = new FileOutputStream(convFile);
                        byte[] myBytes = jsonStr.getBytes();
                        fos.write(myBytes);
                        fos.close();
                        com.Netex.Reader.Gui.models.File file1 = fileRepo.getFileByName(fileName);
                        ConvertedFile convertedFile =new ConvertedFile();
                        convertedFile.setName(convFile.getName());
                        convertedFile.setType("application/json");
                        convertedFile.setData(myBytes);
                        convertedFile.setFile(file1);
                        convertedFileRepo.save(convertedFile);
                        /*String jsonStr= gson.toJson(list);
                        FileUtils.writeStringToFile(file, jsonStr, true);*/
                    }
                })
                .build();
        return jobBuilderFactory.get("netex-data-loader-job")
                .start(step1).build();
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
}
