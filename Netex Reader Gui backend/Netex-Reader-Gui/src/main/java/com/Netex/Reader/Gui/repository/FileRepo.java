package com.Netex.Reader.Gui.repository;

import com.Netex.Reader.Gui.models.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FileRepo extends JpaRepository<File, Long> {
    @Query(value ="select * from File f where f.userId=?1", nativeQuery = true)
    List<File> getFileByUsersOrderByDate_creation(long id);

    File getFileByName(String name);



     /*@Query(value ="select * from File f where f.name=?1", nativeQuery = true)
    File getFileByName(String name);*/
}
