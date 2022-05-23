package com.Netex.Reader.Gui.repository;

import com.Netex.Reader.Gui.models.Routes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutesRepo extends JpaRepository<Routes, Long > {

    Routes findRoutesByRouteName(String Name);
}
