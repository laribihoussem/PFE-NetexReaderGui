package com.Netex.Reader.Gui.repository;

import com.Netex.Reader.Gui.models.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepo extends JpaRepository<Destination, Long> {
    Destination getDestinationByName(String name);
}
