package com.Netex.Reader.Gui.service;

import com.Netex.Reader.Gui.models.Destination;
import com.Netex.Reader.Gui.repository.DestinationRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DestinationServiceImpl implements DestinationService {
    private final DestinationRepo destinationRepo;

    @Override
    public String addDestination(Destination dest) {
        destinationRepo.save(dest);
        return "destination saved successfully";
    }

    @Override
    public List<Destination> getDestinations() {
        return  destinationRepo.findAll();
    }
}
