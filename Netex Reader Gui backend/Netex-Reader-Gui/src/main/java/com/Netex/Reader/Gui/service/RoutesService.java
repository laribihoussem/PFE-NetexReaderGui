package com.Netex.Reader.Gui.service;

import com.Netex.Reader.Gui.models.FileRouteResponse;
import com.Netex.Reader.Gui.models.Routes;

import java.util.List;

public interface RoutesService {

    public Routes saveRoute(Routes route) ;
    public List<FileRouteResponse> getFilesList();
}
