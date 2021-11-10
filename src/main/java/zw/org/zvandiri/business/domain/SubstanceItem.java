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

import zw.org.zvandiri.business.domain.util.DrugIntervention;
import zw.org.zvandiri.business.domain.util.YesNo;
import zw.org.zvandiri.business.util.DateUtil;

/**
 *
 * @author Judge Muzinda
 */
@Entity 
@Table(indexes = {
		@Index(name = "substance_item_patient", columnList = "patient"),
		@Index(name = "substance_item_substance", columnList = "substance"),
		@Index(name = "substance_item_created_by", columnList = "created_by")
})
public class SubstanceItem extends BaseEntity {
    
    @ManyToOne
    @JoinColumn(name = "patient")
    @JsonIgnore
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "substance")
    private Substance substance;
    @Enumerated
    private YesNo current;
    @Enumerated
    private YesNo past;
    private String typeAmount;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startDate;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date endDate;
    @Enumerated
    private DrugIntervention drugIntervention;
    private String howOften;
    private String reason;
    @Enumerated
    private YesNo problematic;
    @Transient
    private String duration;

    @Transient
    private String patientId;

    @Transient
    private String substanceId;

    public SubstanceItem() {
    }

    public SubstanceItem(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Substance getSubstance() {
        return substance;
    }

    public void setSubstance(Substance substance) {
        this.substance = substance;
    }

    public YesNo getCurrent() {
        return current;
    }

    public void setCurrent(YesNo current) {
        this.current = current;
    }

    public YesNo getPast() {
        return past;
    }

    public void setPast(YesNo past) {
        this.past = past;
    }

    public String getTypeAmount() {
        return typeAmount;
    }

    public void setTypeAmount(String typeAmount) {
        this.typeAmount = typeAmount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public DrugIntervention getDrugIntervention() {
        return drugIntervention;
    }

    public void setDrugIntervention(DrugIntervention drugIntervention) {
        this.drugIntervention = drugIntervention;
    }

    public String getHowOften() {
        return howOften;
    }

    public void setHowOften(String howOften) {
        this.howOften = howOften;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public YesNo getProblematic() {
        return problematic;
    }

    public void setProblematic(YesNo problematic) {
        this.problematic = problematic;
    }

    public String getDuration() {
        if (startDate == null || endDate == null) {
            return "";
        }
        return DateUtil.restFmt.format(startDate)+ "-"+ endDate != null ? DateUtil.restFmt.format(endDate) : "" ;
    }


    public String getPatientId() {
        return patient!=null?patient.getId():null;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getSubstanceId() {
        return substance!=null?substance.getId():null;
    }

    public void setSubstanceId(String substanceId) {
        this.substanceId = substanceId;
    }
}
