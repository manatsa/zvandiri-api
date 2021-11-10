/*
 * Copyright 2016 Judge Muzinda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package zw.org.zvandiri.business.domain;

import java.util.Date;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Judge Muzinda
 */
@Entity 
@Table(indexes = {
		@Index(name = "hiv_co_infection_item_patient", columnList = "patient", unique = false),
		@Index(name = "hiv_co_infection_item_hiv_co_infection", columnList = "hiv_co_infection"),
		@Index(name = "hiv_co_infection_item_infection_date", columnList = "infectionDate")
})
public class HivConInfectionItem extends BaseEntity {
    
    @ManyToOne
    @JoinColumn(name = "patient")
    @JsonIgnore
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "hiv_co_infection")
    private HivCoInfection hivCoInfection;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date infectionDate;
    private String medication;
    private String resolution;

    @Transient
    private String patientId;

    @Transient
    private String hivCoInfectionId;

    public HivConInfectionItem() {
    }

    public HivConInfectionItem(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public HivCoInfection getHivCoInfection() {
        return hivCoInfection;
    }

    public void setHivCoInfection(HivCoInfection hivCoInfection) {
        this.hivCoInfection = hivCoInfection;
    }

    public Date getInfectionDate() {
        return infectionDate;
    }

    public void setInfectionDate(Date infectionDate) {
        this.infectionDate = infectionDate;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getPatientId() {
        return patient!=null?patient.getId():null;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getHivCoInfectionId() {
        return hivCoInfection!=null?hivCoInfection.getId():null;
    }

    public void setHivCoInfectionId(String hivCoInfectionId) {
        this.hivCoInfectionId = hivCoInfectionId;
    }
}
