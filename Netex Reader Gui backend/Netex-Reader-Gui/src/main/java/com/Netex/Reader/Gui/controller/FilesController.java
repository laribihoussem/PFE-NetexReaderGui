package com.Netex.Reader.Gui.controller;

import com.Netex.Reader.Gui.models.FileResponse;
import com.Netex.Reader.Gui.models.FileRouteResponse;
import com.Netex.Reader.Gui.service.ConvertFiles;
import com.Netex.Reader.Gui.service.RoutesService;
import lombok.extern.slf4j.Slf4j;
import com.Netex.Reader.Gui.models.File;
import com.Netex.Reader.Gui.repository.FileRepo;
import com.Netex.Reader.Gui.repository.UserRepo;
import com.Netex.Reader.Gui.service.FileServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@Slf4j
@RequestMapping("/api/files")
@CrossOrigin(origins = "http://localhost:4200/")
public class FilesController {

    private final FileServiceImp fileService;
    private final UserRepo userRepo;
    private final FileRepo fileRepo;
    private final ConvertFiles convertFiles;
    private final RoutesService routesService;
    @Autowired
    public FilesController(FileServiceImp fileService, UserRepo userRepo, FileRepo fileRepo, ConvertFiles convertFiles, RoutesService routesService) {
        this.fileService = fileService;
        this.userRepo=userRepo;
        this.fileRepo = fileRepo;
        this.convertFiles = convertFiles;
        this.routesService = routesService;
    }

    /*@PostMapping("/example")
    public ResponseEntity<String> uploadexample(@RequestParam("file") MultipartFile  file) throws IOException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(convertFiles.xmlToJson(file));
    }*/
    @GetMapping("/routes")
    public List<FileRouteResponse> getFilesByroute() {
        return routesService.getFilesList();
    }


    @PostMapping("/{id}/{inputFormat}/{outputFormat}/{destination}")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile  file, @PathVariable("id") long id,
                                             @PathVariable("inputFormat") String  inputFormat, @PathVariable("outputFormat") String  outputFormat,
                                             @PathVariable("destination") String  destination) throws IOException {
        return fileService.store(file,id,inputFormat,outputFormat, destination);
    }
    @PostMapping
    public ResponseEntity<String> visualiseFile(@RequestParam("file") MultipartFile  file) throws IOException {
        return fileService.visualise(file);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFile(@PathVariable long id) throws IOException{
        File file = fileService.getFileById(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment: filename=\"" + file.getName() + "\"")
                .body(file.getData());

    }

    @GetMapping("/user/{id}")
    public List<FileResponse> getFileByuser(@PathVariable long id) {
        return fileService.getFileByUser(id);
    }

    @GetMapping("/list")
    public List<FileResponse> getFileList(){
        return fileService.getFileList();
    }

 /*   @PostMapping("/{id}")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile  file, @PathVariable("id") long id) throws Exception {
        try {
            String filePath = fileService.saveFile(file);
            File fileup = new File(filePath,file.getContentType());
            Users user = userRepo.findById(id);
            fileup.setUsers(user);
            fileRepo.save(fileup);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        }catch (Exception e) {

            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }*/



}
