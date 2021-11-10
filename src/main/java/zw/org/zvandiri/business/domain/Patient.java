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

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Resource;
import javax.persistence.*;

import lombok.ToString;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Formula;
import org.springframework.beans.factory.annotation.Configurable;
import zw.org.zvandiri.business.domain.util.DisabilitySeverity;
import zw.org.zvandiri.business.domain.util.Gender;
import zw.org.zvandiri.business.domain.util.PatientChangeEvent;

import zw.org.zvandiri.business.util.DateUtil;

/**
 *
 * @author Judge Muzinda
 */
@Entity
@ToString
@Configurable(preConstruction = true)
@Table(indexes = {
		@Index(name = "patient_first_name_last_name", columnList = "firstName, lastName"),
		@Index(name = "patient_status", columnList = "status"),
		@Index(name = "patient_primary_clinic", columnList = "primary_clinic"),
		@Index(name = "patient_support_group", columnList = "support_group"),
		@Index(name = "patient_created_by", columnList = "created_by")
		})
public class Patient extends GenericPatient {

    @OneToMany(mappedBy = "patient", cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    private Set<PatientDisability> disabilityCategorys = new HashSet<>();
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private Set<PatientHistory> patientHistories = new HashSet<>();
    @Transient
    private int age = 0;
    @Transient
    private String name;
    @Formula("(Select c.id From cat_detail c where c.patient = id)")
    private String catId;
    @Formula("(Select i.result From investigation_test i where i.patient = id and i.test_type = 0 order by i.date_created desc limit 0,1)")
    private Integer viralLoad;
    @Formula("(Select i.result From investigation_test i where i.patient = id and i.test_type = 1 order by i.date_created desc limit 0,1)")
    private Integer cd4Count;
    @Formula("(Select concat(a1.name, ', ', a2.name, ', ', a3.name) From arv_hist a inner join arv_medicine a1 on a1.id=a.arv_medicine inner join arv_medicine a2 on a2.id=a.arv_medicine2 inner join arv_medicine a3 on a3.id=a.arv_medicine3 where a.patient = id order by a.start_date desc limit 0,1)")
    private String currentArvRegimen;
    @Formula("(Select p.severity From patient_disability p where p.patient = id order by p.date_screened desc limit 0,1)")
    private Integer disabilitySeverity;

    @Formula("(Select c.id From contact c where c.patient = id order by c.date_created desc limit 0,1)")
    private String lastContact;

    public String getLastContact() {
        return lastContact;
    }


    public String getName() {
        return getFirstName() + (getMiddleName() != null && !getMiddleName().equals("") ? " " + getMiddleName() : "") + " " + getLastName();
    }

    public Set<PatientDisability> getDisabilityCategorys() {
        return disabilityCategorys;
    }

    public void setDisabilityCategorys(Set<PatientDisability> disabilityCategorys) {
        this.disabilityCategorys = disabilityCategorys;
    }

    public int getAge() {
        if (getDateOfBirth() == null) {
            return 0;
        }
        Calendar birthCalendar = Calendar.getInstance();
        birthCalendar.setTime(getDateOfBirth());
        Calendar todayCalendar = Calendar.getInstance();
        age = todayCalendar.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);
        if (todayCalendar.get(Calendar.MONTH) < birthCalendar.get(Calendar.MONTH)) {
            age--;
        } else if (todayCalendar.get(Calendar.MONTH) == birthCalendar.get(Calendar.MONTH)
                && todayCalendar.get(Calendar.DAY_OF_MONTH) < birthCalendar.get(Calendar.DAY_OF_MONTH)) {
            age--;
        }
        return age;
    }

    public String getPic() {
        if (getGender() == null) {
            return "/resources/images/noimage.gif";
        } else if (getGender().equals(Gender.MALE)) {
            return "/resources/images/male.gif";
        } else if (getGender().equals(Gender.FEMALE)) {
            return "/resources/images/female.gif";
        }
        return "/resources/images/noimage.gif";
    }

    public String getDateJoin() {
        if (getDateJoined() == null) {
            return "";
        }
        return DateUtil.getStringFromDate(getDateJoined());
    }

    public Boolean getPatientStatus() {
        if (getStatus() == null || getStatus().equals(PatientChangeEvent.ACTIVE)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getMother() {
        return getName() + " dob " + DateUtil.zimDate(getDateOfBirth());
    }

    public Set<PatientHistory> getPatientHistories() {
        return patientHistories;
    }

    public void setPatientHistories(Set<PatientHistory> patientHistories) {
        this.patientHistories = patientHistories;
    }

    public Integer getViralLoad() {
        return viralLoad != null ? viralLoad : 0;
    }

    public Integer getCd4Count() {
        return cd4Count != null ? cd4Count : 0;
    }

    public void add(PatientDisability item, Patient patient) {
        disabilityCategorys.add(item);
        item.setPatient(patient);
    }

    public String getCurrentArvRegimen() {
        return currentArvRegimen;
    }

    public Integer getDisabilitySeverity() {
        return disabilitySeverity;
    }

    public DisabilitySeverity getDisabilityStatus() {
        if (disabilitySeverity != null) {
            return DisabilitySeverity.get(disabilitySeverity + 1);
        }
        return null;
    }



}
