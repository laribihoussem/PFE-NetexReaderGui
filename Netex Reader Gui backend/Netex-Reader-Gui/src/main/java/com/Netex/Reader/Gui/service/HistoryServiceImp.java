package com.Netex.Reader.Gui.service;

import com.Netex.Reader.Gui.models.ConvertedFile;
import com.Netex.Reader.Gui.models.File;
import com.Netex.Reader.Gui.models.UserFileDto;
import com.Netex.Reader.Gui.repository.ConvertedFileRepo;
import com.Netex.Reader.Gui.repository.FileRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryServiceImp implements HistoryService {
 private final FileRepo fileRepo;
 private final ConvertedFileRepo convertedFileRepo;

 @Override
 public List<UserFileDto> getHistory() {
     List<ConvertedFile> fileList= convertedFileRepo.findAll();
     List<UserFileDto> userFileDtos= fileList.stream().map(file -> {

         UserFileDto userFileDto = new UserFileDto();
         userFileDto.convert(file);
         return userFileDto;
     }).collect(Collectors.toList());
     return userFileDtos;
 }


}
