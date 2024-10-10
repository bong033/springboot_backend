package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "packageId")
    private Package _package;

    @ManyToOne
    @JoinColumn(name = "nurseId")
    private Nurse nurse;

    @Column
    private String nextOfKinName;

    @Column
    private String nextOfKinPhone;

    @Column
    private String medicalRecords;

    @Column
    private String medicalPrescriptions;

    @Column
    private String additionalNotes;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;


}


