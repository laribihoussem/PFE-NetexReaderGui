package com.Netex.Reader.Gui.service;

import com.Netex.Reader.Gui.models.Destination;
import com.Netex.Reader.Gui.models.File;
import com.Netex.Reader.Gui.models.FileResponse;
import com.Netex.Reader.Gui.models.Users;
import com.Netex.Reader.Gui.repository.DestinationRepo;
import com.Netex.Reader.Gui.repository.FileRepo;
import com.Netex.Reader.Gui.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
public class FileServiceImp {
    private  final FileRepo fileRepo;
    private final UserRepo userRepo;
    private final DestinationRepo destinationRepo;

    @Autowired
    public FileServiceImp(FileRepo fileRepo, UserRepo userRepo, DestinationRepo destinationRepo) {
        this.fileRepo = fileRepo;
        this.userRepo = userRepo;
        this.destinationRepo = destinationRepo;
    }

  /* public String saveFile(MultipartFile file) throws Exception {
        String folder = "files/";
        byte[] bytes=file.getBytes();
        Path path = Paths.get(folder+file.getOriginalFilename());
        String filePath= path.toString();
        Files.write(Paths.get(folder+file.getOriginalFilename()).toAbsolutePath(),file.getBytes());
        return filePath;
    }
*/
    public ResponseEntity<String> store(MultipartFile file, long id, String inputFormat, String outputFormat, String destination) throws IOException {
        String fileType= file.getContentType();
        log.info(fileType.split("/")[1]);
        log.info(inputFormat);
        if(fileType.split("/")[1].equals(inputFormat) || inputFormat.equals("netex")) {
            String fileName = file.getOriginalFilename();
            Users user = userRepo.findById(id);
            Destination dest = destinationRepo.getDestinationByName(destination);
            File fileDb = new File(fileName, file.getContentType(), file.getBytes());
            fileDb.setUsers(user);
            fileDb.setInputFormat(inputFormat);
            fileDb.setOutputFormat(outputFormat);
            fileDb.setDestination(dest);
            String content = new String(file.getBytes(), StandardCharsets.UTF_8);
            //log.info(content);
            fileRepo.save(fileDb);
            java.io.File convFile = new java.io.File( "files/input/"+fileName);
            FileOutputStream fos = new FileOutputStream(convFile);
            byte[] myBytes = content.getBytes();
            fos.write(myBytes);
            fos.close();
            return  ResponseEntity.status(HttpStatus.OK)
                    .body("File saved successfuly");
        }else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("type not correct");


    }
    public ResponseEntity<String> visualise(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        File fileDb = new File(fileName, file.getContentType(), file.getBytes());
        String content = new String(file.getBytes(), StandardCharsets.UTF_8);
        String contentType = file.getContentType();
        return  ResponseEntity.status(HttpStatus.OK)
                .body(content);
    }


    public File getFileById(long id) {

        Optional<File> fileOptional = fileRepo.findById(id);

        if(fileOptional.isPresent()) {
            return fileOptional.get();
        }
        return null;
    }

    public List<FileResponse> getFileByUser(long id) {
            return fileRepo.getFileByUsersOrderByDate_creation(id).stream().map(this::mapToFileResponse).collect(Collectors.toList());
    }

    public List<FileResponse> getFileList(){
        return fileRepo.findAll().stream().map(this::mapToFileResponse).collect(Collectors.toList());
    }


    private FileResponse mapToFileResponse(File fileDb) {
        return new FileResponse(fileDb.getId(), fileDb.getType(), fileDb.getName(), fileDb.getDate_creation().toString().split(" ")[0]
        );
    }

   /* private userFileDto mapToUserFileResponse(File fileDb, Users user) {
        return new userFileDto(fileDb.getType(), fileDb.getName(), fileDb.getDate_creation(), user.getEmail(),user.getFirstName()
        );
    }*/

}
