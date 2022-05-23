package com.Netex.Reader.Gui.service;

import com.Netex.Reader.Gui.models.Destination;

import java.util.List;

public interface DestinationService {
    public String addDestination(Destination dest);

    public List<Destination> getDestinations();
}
