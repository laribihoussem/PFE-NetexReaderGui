package com.Netex.Reader.Gui.service;

import com.Netex.Reader.Gui.models.*;
import com.Netex.Reader.Gui.repository.FileRepo;
import com.Netex.Reader.Gui.repository.RoutesRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoutesServiceImp implements RoutesService{

    private final RoutesRepo routesRepo;
    private final FileRepo fileRepo;

    @Override
    public Routes saveRoute(Routes route) {
        return routesRepo.save(route);
    }

    @Override
    public List<FileRouteResponse> getFilesList() {
        List<File> fileList = fileRepo.findAll();
        List<FileRouteResponse> fileRoute = fileList.stream().map(file -> {
            FileRouteResponse fileRouteResponse = new FileRouteResponse();
            fileRouteResponse.convert(file);
            return fileRouteResponse;
        }).collect(Collectors.toList());
        return fileRoute;
    }
   /* public List<UserFileDto> getHistory() {
        List<ConvertedFile> fileList= convertedFileRepo.findAll();
        List<UserFileDto> userFileDtos= fileList.stream().map(file -> {

            UserFileDto userFileDto = new UserFileDto();
            userFileDto.convert(file);
            return userFileDto;
        }).collect(Collectors.toList());
        return userFileDtos;
    }*/
}
