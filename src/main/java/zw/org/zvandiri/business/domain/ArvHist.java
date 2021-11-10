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

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author Judge Muzinda
 */
@Entity 
@Table(indexes = {
		@Index(name = "arv_hist_patient", columnList = "patient"),
		@Index(name = "arv_hist_arv_medicine", columnList = "arv_medicine"),
		@Index(name = "arv_hist_arv_medicine2", columnList = "arv_medicine2"),
		@Index(name = "arv_hist_arv_medicine3", columnList = "arv_medicine3"),
		@Index(name = "arv_hist_start_date", columnList = "startDate")
})

public class ArvHist extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "patient")
    @JsonIgnore
    //@Schema(description = "Name of the contact.",example = "Jessica Abigail", required = true)
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "arv_medicine")
    private ArvMedicine arvMedicine;
    @ManyToOne
    @JoinColumn(name = "arv_medicine2")
    private ArvMedicine arvMedicine2;
    @ManyToOne
    @JoinColumn(name = "arv_medicine3")
    private ArvMedicine arvMedicine3;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startDate;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date endDate;
    @Transient
    private String medicines;

    @Transient
    private String patientId;

    @Transient
    private String arvMedicineId;

    @Transient
    private String arvMedicine2Id;

    @Transient
    private String arvMedicine3Id;

    public ArvHist() {
    }

    public ArvHist(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public ArvMedicine getArvMedicine() {
        return arvMedicine;
    }

    public void setArvMedicine(ArvMedicine arvMedicine) {
        this.arvMedicine = arvMedicine;
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

	public ArvMedicine getArvMedicine2() {
		return arvMedicine2;
	}

	public void setArvMedicine2(ArvMedicine arvMedicine2) {
		this.arvMedicine2 = arvMedicine2;
	}

	public ArvMedicine getArvMedicine3() {
		return arvMedicine3;
	}

	public void setArvMedicine3(ArvMedicine arvMedicine3) {
		this.arvMedicine3 = arvMedicine3;
	}

    public String getPatientId() {
        return patient!=null?patient.getId():null;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getArvMedicineId() {
        return arvMedicine!=null?arvMedicine.getId():null;
    }

    public void setArvMedicineId(String arvMedicineId) {
        this.arvMedicineId = arvMedicineId;
    }

    public String getArvMedicine2Id() {
        return arvMedicine2!=null?arvMedicine2.getId():null;
    }

    public void setArvMedicine2Id(String arvMedicine2Id) {
        this.arvMedicine2Id = arvMedicine2Id;
    }

    public String getArvMedicine3Id() {
        return arvMedicine3!=null?arvMedicine3.getId():null;
    }

    public void setArvMedicine3Id(String arvMedicine3Id) {
        this.arvMedicine3Id = arvMedicine3Id;
    }

    public String getMedicines() {
		if (arvMedicine != null && StringUtils.isNotBlank(arvMedicine.getName())) {
			medicines = arvMedicine.getName();
		}
		if (arvMedicine2 != null && StringUtils.isNotBlank(arvMedicine2.getName())) {
			medicines += " + " + arvMedicine2.getName();
		}
		if (arvMedicine3 != null && StringUtils.isNotBlank(arvMedicine3.getName())) {
			medicines += " + " + arvMedicine3.getName();
		}
		return medicines;
	}
    
}
