package com.Netex.Reader.Gui.repository;

import com.Netex.Reader.Gui.models.ConvertedFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConvertedFileRepo extends JpaRepository<ConvertedFile,Long> {
    public ConvertedFile getConvertedFileByName(String name);

}
