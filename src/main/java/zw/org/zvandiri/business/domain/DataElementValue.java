/*
 * Copyright 2017 Judge Muzinda.
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

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 *
 * @author Judge Muzinda
 */
@Entity
public class DataElementValue extends BaseEntity {
    
    @ManyToOne
    @JoinColumn(name = "facility")
    private Facility facility;
    @ManyToOne
    @JoinColumn(name = "period")
    private Period period;
    @ManyToOne
    @JoinColumn(name = "data_element")
    private DataElement dataElement;
    private Integer itemValue;

    @Transient
    private String facilityId;

    @Transient
    private String periodId;

    @Transient
    private String dataElementId;

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public DataElement getDataElement() {
        return dataElement;
    }

    public void setDataElement(DataElement dataElement) {
        this.dataElement = dataElement;
    }

    public Integer getItemValue() {
        return itemValue;
    }

    public void setItemValue(Integer itemValue) {
        this.itemValue = itemValue;
    }

    public String getFacilityId() {
        return facility!=null?facility.getId():null;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getPeriodId() {
        return period!=null?period.getId():null;
    }

    public void setPeriodId(String periodId) {
        this.periodId = periodId;
    }

    public String getDataElementId() {
        return dataElement!=null?dataElement.getId():null;
    }

    public void setDataElementId(String dataElementId) {
        this.dataElementId = dataElementId;
    }
}
