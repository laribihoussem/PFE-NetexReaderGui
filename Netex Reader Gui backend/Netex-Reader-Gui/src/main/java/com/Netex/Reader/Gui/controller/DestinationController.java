package com.Netex.Reader.Gui.controller;

import com.Netex.Reader.Gui.models.Destination;
import com.Netex.Reader.Gui.service.DestinationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/destination")
@CrossOrigin(origins = "http://localhost:4200/")
@AllArgsConstructor
public class DestinationController {

    private final DestinationService destinationService;

    @PostMapping()
    public ResponseEntity addDestination( @RequestBody Destination dest) throws IOException {
        return ResponseEntity.ok().body(destinationService.addDestination(dest));
    }

    @GetMapping("/all")
    public List<Destination> getAllDestination() {
        return destinationService.getDestinations();
    }
}
