package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Nurse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nurseId;

    @OneToOne
    @JoinColumn(name="userId",referencedColumnName = "userId")
    private User user;

    @Column
    private  String licenceNo;

    @Column
    private String expertise;

    @Column
    private String gender;

}
