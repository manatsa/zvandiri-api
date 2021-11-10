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

import java.util.Set;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import zw.org.zvandiri.business.domain.util.AbuseOutcome;
import zw.org.zvandiri.business.domain.util.AbuseType;
import zw.org.zvandiri.business.domain.util.YesNo;

/**
 *
 * @author Judge Muzinda
 */
@Entity
@Table(indexes = {
		@Index(name = "social_hist_patient", columnList = "patient"),
		@Index(name = "social_hist_created_by", columnList = "created_by")
})
public class SocialHist extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "patient")
    @JsonIgnore
    private Patient patient;
    private String liveWith;
    @ManyToOne
    @JoinColumn(name = "relationship")
    private Relationship relationship;
    @Enumerated
    private YesNo abuse;
    @Enumerated
    private YesNo dosclosure;
    @Enumerated
    private YesNo feelSafe;
    @Enumerated
    private AbuseOutcome abuseOutcome;
    @ElementCollection(fetch = FetchType.EAGER)
//    @JoinColumn(name = "abuse_types")
    @JsonIgnore
    @Enumerated
    private Set<AbuseType> abuseTypes;
    private String socialSupport;
    private String lossOfSignificantRelationships;

    @Transient
    private String patientId;
    @Transient
    private String relationshipId;

    public SocialHist() {
    }

    public SocialHist(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public AbuseOutcome getAbuseOutcome() {
        return abuseOutcome;
    }

    public void setAbuseOutcome(AbuseOutcome abuseOutcome) {
        this.abuseOutcome = abuseOutcome;
    }

    public String getLiveWith() {
        return liveWith;
    }

    public void setLiveWith(String liveWith) {
        this.liveWith = liveWith;
    }

    public YesNo getAbuse() {
        return abuse;
    }

    public void setAbuse(YesNo abuse) {
        this.abuse = abuse;
    }

    public YesNo getDosclosure() {
        return dosclosure;
    }

    public void setDosclosure(YesNo dosclosure) {
        this.dosclosure = dosclosure;
    }

    public YesNo getFeelSafe() {
        return feelSafe;
    }

    public void setFeelSafe(YesNo feelSafe) {
        this.feelSafe = feelSafe;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    public Set<AbuseType> getAbuseTypes() {
        return abuseTypes;
    }

    public void setAbuseTypes(Set<AbuseType> abuseTypes) {
        this.abuseTypes = abuseTypes;
    }

    public String getSocialSupport() {
        return socialSupport;
    }

    public void setSocialSupport(String socialSupport) {
        this.socialSupport = socialSupport;
    }

    public String getLossOfSignificantRelationships() {
        return lossOfSignificantRelationships;
    }

    public void setLossOfSignificantRelationships(String lossOfSignificantRelationships) {
        this.lossOfSignificantRelationships = lossOfSignificantRelationships;
    }

    public String getPatientId() {
        return patient!=null?patient.getId():null;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getRelationshipId() {
        return relationship!=null?relationship.getId():null;
    }

    public void setRelationshipId(String relationshipId) {
        this.relationshipId = relationshipId;
    }
}
