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

import zw.org.zvandiri.business.domain.util.CondomUse;
import zw.org.zvandiri.business.domain.util.YesNo;

/**
 *
 * @author Judge Muzinda
 */
@Entity
public class SrhHist extends BaseEntity {
 
    @ManyToOne
    @JoinColumn(name = "patient")
    @JsonIgnore
    private Patient patient;
    private Integer ageStartMen;
    private Integer bleedHowOften;
    private Integer bleeddays;
    @Enumerated
    private YesNo sexualIntercourse;
    @Enumerated
    private YesNo sexuallyActive;
    @Enumerated
    private CondomUse condomUse;
    @Enumerated
    private YesNo birthControl;

    @Transient
    private String patientId;

    public SrhHist() {
    }

    public SrhHist(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Integer getAgeStartMen() {
        return ageStartMen;
    }

    public void setAgeStartMen(Integer ageStartMen) {
        this.ageStartMen = ageStartMen;
    }

    public Integer getBleedHowOften() {
        return bleedHowOften;
    }

    public void setBleedHowOften(Integer bleedHowOften) {
        this.bleedHowOften = bleedHowOften;
    }

    public Integer getBleeddays() {
        return bleeddays;
    }

    public void setBleeddays(Integer bleeddays) {
        this.bleeddays = bleeddays;
    }

    public YesNo getSexualIntercourse() {
        return sexualIntercourse;
    }

    public void setSexualIntercourse(YesNo sexualIntercourse) {
        this.sexualIntercourse = sexualIntercourse;
    }

    public YesNo getSexuallyActive() {
        return sexuallyActive;
    }

    public void setSexuallyActive(YesNo sexuallyActive) {
        this.sexuallyActive = sexuallyActive;
    }

    public CondomUse getCondomUse() {
        return condomUse;
    }

    public void setCondomUse(CondomUse condomUse) {
        this.condomUse = condomUse;
    }

    public YesNo getBirthControl() {
        return birthControl;
    }

    public void setBirthControl(YesNo birthControl) {
        this.birthControl = birthControl;
    }

    public String getPatientId() {
        return patient!=null?patient.getId():null;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}
