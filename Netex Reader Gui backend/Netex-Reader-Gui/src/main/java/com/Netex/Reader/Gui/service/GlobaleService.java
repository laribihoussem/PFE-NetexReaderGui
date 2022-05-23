package com.Netex.Reader.Gui.service;

import org.apache.camel.Exchange;

public interface GlobaleService {

    public String send(Exchange exchange);
    public void sendStatus(Exchange exchange);
}
