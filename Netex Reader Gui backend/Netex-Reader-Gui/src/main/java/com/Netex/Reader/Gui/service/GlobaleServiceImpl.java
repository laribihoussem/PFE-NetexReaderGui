package com.Netex.Reader.Gui.service;

import com.Netex.Reader.Gui.models.ConvertedFile;
import com.Netex.Reader.Gui.models.Destination;
import com.Netex.Reader.Gui.models.Routes;
import com.Netex.Reader.Gui.repository.ConvertedFileRepo;
import com.Netex.Reader.Gui.repository.FileRepo;
import com.Netex.Reader.Gui.repository.RoutesRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.component.kafka.KafkaConstants;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Transactional
@Slf4j
@Service("GlobalService")
public class GlobaleServiceImpl implements GlobaleService{

    private final FileRepo fileRepo;
    private  final ConvertedFileRepo convertedFileRepo;
    private final RoutesRepo routesRepo;
    private SimpMessageSendingOperations messagingTemplate;



    /*
    *Send Message to kafka
    */
    @Override
    public String send(Exchange exchange) {
        File file=exchange.getIn().getBody(File.class);
        exchange.getIn().setHeader(KafkaConstants.KEY,file.getName());
        return "http://localhost:8090/"+file.getName();
    }


    /*
     *Set header for apache Camel
     */
    public void setHeader(Exchange exchange) {
        File file = exchange.getIn().getBody(File.class);
        String name = file.getName();
        ConvertedFile file1 = convertedFileRepo.getConvertedFileByName(name);
        com.Netex.Reader.Gui.models.File file2= file1.getFile();
        Destination dest = file2.getDestination();
        String destName= dest.getName();
        exchange.setProperty("destination", destName);
    }


    /*
     *Send status to database
     */
    @Override
    public void sendStatus(Exchange exchange) {
        String routeName = exchange.getProperty("RouteName", String.class);
        File file = exchange.getIn().getBody(File.class);
        String fileName = file.getName();
        Routes route = routesRepo.findRoutesByRouteName(routeName);
        if(routeName.equals("converting files")){
            com.Netex.Reader.Gui.models.File file1 = fileRepo.getFileByName(fileName);
            file1.getRoutes().add(route);
        } else if (routeName.equals("Sending File via SFTP and message to kafka")){
            ConvertedFile cf = convertedFileRepo.getConvertedFileByName(fileName);
            cf.getFile().getRoutes().add(route);
        }
    }



    /*
     *Sending notification
     */
    private Map<String, String> progress = new HashMap<String, String>();
    public void notify1(Exchange exchange) {
        String fileName= (String) exchange.getIn().getHeaders().get(KafkaConstants.KEY);
        String link = exchange.getIn().getBody(String.class);
        progress.put("file Name", fileName);
        progress.put("link", link);
        ConvertedFile f1= convertedFileRepo.getConvertedFileByName(fileName);
        long id = f1.getFile().getUsers().getId();
        progress.put("id", String.valueOf(id));
        log.info(link);
        messagingTemplate.convertAndSend("/topic/progress", this.progress);
    }
}
