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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@AllArgsConstructor
@Transactional
@Slf4j
@Service("GlobalService")
public class GlobaleServiceImpl implements GlobaleService{

    private final FileRepo fileRepo;
    private  final ConvertedFileRepo convertedFileRepo;
    private final RoutesRepo routesRepo;

    @Override
    public String send(Exchange exchange) {
        File file=exchange.getIn().getBody(File.class);
        exchange.getIn().setHeader(KafkaConstants.KEY,file.getName());
        return "http://localhost:8090/"+file.getName();
    }

    public void setHeader(Exchange exchange) {
        File file = exchange.getIn().getBody(File.class);
        String name = file.getName();
        //log.info(name);
        ConvertedFile file1 = convertedFileRepo.getConvertedFileByName(name);
        com.Netex.Reader.Gui.models.File file2= file1.getFile();
        //com.Netex.Reader.Gui.models.File file2 = fileRepo.getFileByName(name);
        Destination dest = file2.getDestination();
        String destName= dest.getName();
        exchange.setProperty("destination", destName);
    }

    @Override
    public void sendStatus(Exchange exchange) {
        String routeName = exchange.getProperty("RouteName", String.class);
        log.info(routeName);
        File file = exchange.getIn().getBody(File.class);
        String fileName = file.getName();
        log.info(fileName);
        Routes route = routesRepo.findRoutesByRouteName(routeName);
        if(routeName.equals("converting files")){
            com.Netex.Reader.Gui.models.File file1 = fileRepo.getFileByName(fileName);
            file1.getRoutes().add(route);
        } else if (routeName.equals("Sending File via SFTP and message to kafka")){
            ConvertedFile cf = convertedFileRepo.getConvertedFileByName(fileName);
            cf.getFile().getRoutes().add(route);
        }




    }
}
