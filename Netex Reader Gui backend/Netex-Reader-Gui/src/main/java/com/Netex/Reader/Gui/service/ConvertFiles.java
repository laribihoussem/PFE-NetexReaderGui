package com.Netex.Reader.Gui.service;

import org.apache.camel.Exchange;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ConvertFiles {
   public void netexToXml(Exchange exchange)throws Exception;
   public void netexToCsv(Exchange exchange) throws Exception;
   public void fromNetex(Exchange exchange) throws Exception;



}
