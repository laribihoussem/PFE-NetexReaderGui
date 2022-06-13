package com.Netex.Reader.Gui.camel.routes;

import com.Netex.Reader.Gui.service.GlobaleServiceImpl;
import com.Netex.Reader.Gui.service.toCsv;
import org.apache.camel.Predicate;
import org.apache.camel.builder.PredicateBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component("Netex-Camel")
public class FileRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        Predicate p1 = header("inputFormat").isEqualTo("json");
        Predicate p2 = header("inputFormat").isEqualTo("xml");
        Predicate p3 = header("inputFormat").isEqualTo("csv");
        Predicate p4 = header("outputFormat").isEqualTo("xml");
        Predicate p5 = header("outputFormat").isEqualTo("json");
        Predicate p6 = header("outputFormat").isEqualTo("csv");
        Predicate p7 = header("inputFormat").isEqualTo("netex");

        Predicate SNCF = header("destination").isEqualTo("SNCF");
        Predicate STIF = header("destination").isEqualTo("STIF");
        Predicate RATP = header("destination").isEqualTo("RATP");

        Predicate jsonToXml = PredicateBuilder.and(p1, p4);
        Predicate jsonToCsv = PredicateBuilder.and(p1, p6);
        Predicate xmlToJson = PredicateBuilder.and(p2, p5);
        Predicate xmlToCsv = PredicateBuilder.and(p2, p6);
        Predicate csvToJson = PredicateBuilder.and(p3, p5);
        Predicate csvToXml = PredicateBuilder.and(p3, p4);
        Predicate netexToJson = PredicateBuilder.and(p7, p5);
        Predicate netexToXml = PredicateBuilder.and(p7, p4);
        Predicate netexToCsv = PredicateBuilder.and(p7, p6);

        /*from("file:files/input")
                .to("bean:toJson?method=xmlToJson");
                .to("file:files/output");*//**//*
                .choice().when(simple("${header.destination} == 'output'")).to("file:files/output");*/



        from("file:files/input?delete=true")
                .description("converting files")
                .delay(simple("${random(2000,5000)}"))
                .setProperty("RouteName", simple("converting files"))
                .doTry()
                .routeId("1")
                .to("bean:toJson?method=setHeader")
                .choice()
                .when(jsonToXml)
                    .to("bean:toXml?method=jsonToXml")
                .when(jsonToCsv)
                    .to("bean:toCsv?method=jsonToCsv")
                .when(xmlToJson)
                    .to("bean:toJson?method=xmlToJson")
                .when(xmlToCsv)
                    .to("bean:toCsv?method=xmlToCsv")
                .when(csvToJson)
                    .to("bean:toJson?method=csvToJson")
                .when(csvToXml)
                    .to("bean:toXml?method=csvToXml")
                .when(netexToJson)
                    .to("bean:netex?method=fromNetex")
                .when(netexToXml)
                    .to("bean:netex?method=netexToXml")
                .when(netexToCsv)
                    .to("bean:netex?method=netexToCsv")
                .otherwise().to("file:files/output")
                .end()
                .to("bean:GlobalService?method=sendStatus")
                .endDoTry()
                .doCatch(Exception.class)
                .log("Something went wrong")
                /*.doFinally().to("bean:GlobalService?method=sendStatus")*/;


        from("file:files/output")
                .description("sending Files via SFTP and messages to kafka")
                .setProperty("RouteName", simple("Sending File via SFTP and message to kafka"))
                .delay(simple("${random(3000,5000)}"))
                /*.doTry()*/
                .routeId("Sending")
                .to("bean:GlobalService?method=setHeader")
                .choice()
                    .when(SNCF)
                        .to("sftp://tester@192.168.1.12:2222//RebexTinySftpServer//data//SNCF?password=password")
                        .to("bean:GlobalService?method=send")
                        .to("kafka:SNCF?brokers=localhost:9092")
                    .when(STIF)
                        .to("sftp://tester@192.168.1.12:2222//RebexTinySftpServer//data//STIF?password=password")
                        .to("bean:GlobalService?method=send")
                        .to("kafka:STIF?brokers=localhost:9092")
                    .when(RATP)
                        .to("sftp://tester@192.168.1.12:2222//RebexTinySftpServer//data//RATP?password=password")
                        .to("bean:GlobalService?method=send")
                        .to("kafka:RATP?brokers=localhost:9092")
                    .otherwise().log("Something is not correct" )
                .end()
                .to("bean:GlobalService?method=sendStatus");
                /*.endDoTry()
                .doCatch(Exception.class)
                .log("Something went wrong");*/

        from("kafka:RATP?brokers=localhost:9092")
                .bean(GlobaleServiceImpl.class, "notify1");
        from("kafka:SNCF?brokers=localhost:9092")
                .bean(GlobaleServiceImpl.class, "notify1");
        from("kafka:STIF?brokers=localhost:9092")
                .bean(GlobaleServiceImpl.class, "notify1");


        from("file:files/RATP")
                .bean(toCsv.class, "convert");



    }
}
