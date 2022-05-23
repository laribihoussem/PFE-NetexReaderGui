package com.Netex.Reader.Gui.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

@Entity
@Data
@NoArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;

    private String type;

    private String inputFormat;

    private String outputFormat;


    @ManyToOne
    @JoinColumn(name="destinationId")
    @JsonIgnore
    private Destination destination;

    @CreationTimestamp
    private Timestamp date_creation;

    @Lob
    private byte[] data;

    @ManyToOne
    @JoinColumn(name="userid")
    @JsonIgnore
    private Users users;

    @OneToOne(mappedBy = "file")
    private ConvertedFile convertedFile;

    @ManyToMany(fetch = EAGER)
    private List<Routes> routes;

    public File(String name, String type, byte[] data) {
        super();
        this.name = name;
        this.type = type;
        this.data = data;
    }




}
