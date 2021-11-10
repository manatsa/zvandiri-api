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

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author Judge Muzinda
 */
@Entity
@Table(indexes = {
		@Index(name = "family_patient", columnList = "patient"),
		@Index(name = "family_orphan_status", columnList = "orphan_status")
})
public class Family extends BaseEntity {
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "patient")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "orphan_status")
    private OrphanStatus orphanStatus;
    private Integer numberOfSiblings;

    @Transient
    private String patientId;

    @Transient
    private String orphanStatusId;

    public Family() {
    }

    public Family(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public OrphanStatus getOrphanStatus() {
        return orphanStatus;
    }

    public void setOrphanStatus(OrphanStatus orphanStatus) {
        this.orphanStatus = orphanStatus;
    }

    public Integer getNumberOfSiblings() {
        return numberOfSiblings;
    }

    public void setNumberOfSiblings(Integer numberOfSiblings) {
        this.numberOfSiblings = numberOfSiblings;
    }

    public String getPatientId() {
        return patient!=null?patient.getId():null;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getOrphanStatusId() {
        return orphanStatus!=null?orphanStatus.getId():null;
    }

    public void setOrphanStatusId(String orphanStatusId) {
        this.orphanStatusId = orphanStatusId;
    }
}
