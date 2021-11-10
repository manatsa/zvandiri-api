/*
 * Copyright 2015 Judge Muzinda.
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

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import zw.org.zvandiri.business.domain.util.RecordSource;

import javax.persistence.*;import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Judge Muzinda
 */
@MappedSuperclass
abstract public class BaseEntity implements Serializable {

    @Id
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    @JsonIgnore
    private User createdBy;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modified_by")
    @JsonIgnore
    private User modifiedBy;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private Date dateCreated;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateModified;
    private Boolean active = Boolean.TRUE;
    private Boolean deleted = Boolean.FALSE;
    @Enumerated
    private RecordSource recordSource = RecordSource.WEB_APP;

    @Transient
    private String creatorId;

    @Transient
    private String modifierId;


    public BaseEntity() {

    }

    public BaseEntity(String id) {
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(User modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public RecordSource getRecordSource() {
        return recordSource;
    }

    public void setRecordSource(RecordSource recordSource) {
        this.recordSource = recordSource;
    }

    public String getCreatorId() {
        return createdBy!=null?createdBy.getId():null;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getModifierId() {
        return modifiedBy!=null?modifiedBy.getId():null;
    }

    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof BaseEntity)) {
            return false;
        }
        return this.getId().equals(((BaseEntity) obj).getId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "BaseEntity{" + "id=" + id  + '}';
    }
    
}
