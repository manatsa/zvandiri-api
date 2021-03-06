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

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author Judge Muzinda
 */
@Entity 
@Table(indexes = {
		@Index(name = "support_group_district", columnList = "district"),
		@Index(name = "support_group_created_by", columnList = "created_by")
})
public class SupportGroup extends BaseName {
    
    @ManyToOne
    @JoinColumn(name = "district")
    private District district;
    @Transient
    private Province province;
    @JsonIgnore
    @OneToMany(mappedBy = "supportGroup", cascade = CascadeType.REMOVE)
    private Set<Patient> patients = new HashSet<>();

    @Transient
    private String districtId;

    public SupportGroup() {
    }

    public SupportGroup(String id) {
        super(id);
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    public String getDistrictId() {
        return district!=null?district.getId():null;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }
}
