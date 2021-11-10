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

import lombok.ToString;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;
import zw.org.zvandiri.business.domain.util.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Judge Muzinda
 */
@ToString
@Entity
@Table(indexes = {
		@Index(name = "contact_patient", columnList = "patient"),
		@Index(name = "contact_internal_referral", columnList = "internal_referral"),
		@Index(name = "contact_external_referral", columnList = "external_referral"),
		@Index(name = "contact_contact_date", columnList = "contactDate"),
		@Index(name = "contact_location", columnList = "location"),
		@Index(name = "contact_period", columnList = "period"),
		@Index(name = "contact_position", columnList = "position")
})
public class Contact extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "patient")
    private Patient patient;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date contactDate;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date nextClinicAppointmentDate;
    @Enumerated
    private CareLevel careLevel;
    @ManyToOne
    @JoinColumn(name = "location")
    private Location location;
    @Enumerated
    private ContactPhoneOption contactPhoneOption;
    private Integer numberOfSms;
    @ManyToOne
    @JoinColumn(name = "position")
    private Position position;
    @Enumerated
    private Reason reason;
    private String otherReason;
    @ManyToOne
    @JoinColumn(name = "period")
    private Period period;
    @ManyToOne
    @JoinColumn(name = "internal_referral")
    private InternalReferral internalReferral;
    @ManyToOne
    @JoinColumn(name = "external_referral")
    private ExternalReferral externalReferral;
    @Enumerated
    private FollowUp followUp;
    @Column(columnDefinition = "text")
    private String subjective;
    @Column(columnDefinition = "text")
    private String objective;
    @Enumerated
    private DifferentiatedService differentiatedService;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "contact_lab_service", joinColumns = {
        @JoinColumn(name = "contact_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "lab_service_id", nullable = false)})
    private Set<LabTask> labTasks = new HashSet<>();
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "contact_clinical_assessment", joinColumns = {
        @JoinColumn(name = "contact_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "assessment_id", nullable = false)})
    private Set<Assessment> clinicalAssessments = new HashSet<>();
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "contact_non_clinical_assessment", joinColumns = {
        @JoinColumn(name = "contact_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "assessment_id", nullable = false)})
    private Set<Assessment> nonClinicalAssessments = new HashSet<>();
    @Column(columnDefinition = "text")
    private String plan;
    @ManyToOne
    @JoinColumn(name = "action_taken")
    private ActionTaken actionTaken;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "contact_stable", joinColumns = {
        @JoinColumn(name = "contact_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "stable_id", nullable = false)})
    private Set<Stable> stables = new HashSet<>();
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "contact_enhanced", joinColumns = {
        @JoinColumn(name = "contact_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "enhanced_id", nullable = false)})
    private Set<Enhanced> enhanceds = new HashSet<>();
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "contact_service_offered", joinColumns = {
        @JoinColumn(name = "contact_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "service_offered_id", nullable = false)})
    private Set<ServiceOffered> serviceOffereds = new HashSet<>();
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "parent")
    private Contact parent;
    @ManyToOne
    @JoinColumn(name = "referred_person")
    @JsonIgnore
    private User referredPerson;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date lastClinicAppointmentDate;
    private YesNo attendedClinicAppointment;
    @Column(name = "`open`")
    private Boolean open = Boolean.FALSE;
    @Column(columnDefinition = "text")
    private String defaultMessage;
    @Transient
    private String currentElement;
    @Transient
    private UserLevel userLevel;
    @Transient
    private District district;
    @Transient
    private Province province;
    @Formula("(Select c.care_level From contact c where c.patient = patient order by c.contact_date desc limit 1,1)")
    private Integer previousCareLevel;
    @Transient
    private CareLevel currentCareLevel;
    @Enumerated
    private VisitOutcome visitOutcome;
    @Column(name = "referred_person_id")
    private String referredPersonId;
    @Transient
    private InvestigationTest viralLoad;
    @Transient
    private InvestigationTest cd4Count;


    @Transient
    private String patientId;

    @Transient
    private String locationId;

    @Transient
    private String positionId;

    @Transient
    private String parentId;

    @Transient
    private String periodId;

    public Contact(Patient patient) {
        this.patient = patient;
    }

    public Contact() {
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getContactDate() {
        return contactDate;
    }

    public void setContactDate(Date contactDate) {
        this.contactDate = contactDate;
    }

    public CareLevel getCareLevel() {
        return careLevel;
    }

    public void setCareLevel(CareLevel careLevel) {
        this.careLevel = careLevel;
    }

    public Date getNextClinicAppointmentDate() {
        return nextClinicAppointmentDate;
    }

    public void setNextClinicAppointmentDate(Date nextClinicAppointmentDate) {
        this.nextClinicAppointmentDate = nextClinicAppointmentDate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Reason getReason() {
        return reason;
    }

    public void setReason(Reason reason) {
        this.reason = reason;
    }

    public InternalReferral getInternalReferral() {
        return internalReferral;
    }

    public void setInternalReferral(InternalReferral internalReferral) {
        this.internalReferral = internalReferral;
    }

    public ExternalReferral getExternalReferral() {
        return externalReferral;
    }

    public void setExternalReferral(ExternalReferral externalReferral) {
        this.externalReferral = externalReferral;
    }

    public FollowUp getFollowUp() {
        return followUp;
    }

    public void setFollowUp(FollowUp followUp) {
        this.followUp = followUp;
    }

    public String getSubjective() {
        return subjective;
    }

    public void setSubjective(String subjective) {
        this.subjective = subjective;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public Set<Assessment> getClinicalAssessments() {
        return clinicalAssessments;
    }

    public void setClinicalAssessments(Set<Assessment> clinicalAssessments) {
        this.clinicalAssessments = clinicalAssessments;
    }

    public Set<Assessment> getNonClinicalAssessments() {
        return nonClinicalAssessments;
    }

    public void setNonClinicalAssessments(Set<Assessment> nonClinicalAssessments) {
        this.nonClinicalAssessments = nonClinicalAssessments;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public ActionTaken getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(ActionTaken actionTaken) {
        this.actionTaken = actionTaken;
    }

    public Set<Stable> getStables() {
        return stables;
    }

    public void setStables(Set<Stable> stables) {
        this.stables = stables;
    }

    public Set<Enhanced> getEnhanceds() {
        return enhanceds;
    }

    public void setEnhanceds(Set<Enhanced> enhanceds) {
        this.enhanceds = enhanceds;
    }

    public Contact getParent() {
        return parent;
    }

    public void setParent(Contact parent) {
        this.parent = parent;
    }

    public String getCurrentElement() {
        return currentElement;
    }

    public void setCurrentElement(String currentElement) {
        this.currentElement = currentElement;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public User getReferredPerson() {
        return referredPerson;
    }

    public void setReferredPerson(User referredPerson) {
        this.referredPerson = referredPerson;
    }

    public Date getLastClinicAppointmentDate() {
        return lastClinicAppointmentDate;
    }

    public void setLastClinicAppointmentDate(Date lastClinicAppointmentDate) {
        this.lastClinicAppointmentDate = lastClinicAppointmentDate;
    }

    public YesNo getAttendedClinicAppointment() {
        return attendedClinicAppointment;
    }

    public void setAttendedClinicAppointment(YesNo attendedClinicAppointment) {
        this.attendedClinicAppointment = attendedClinicAppointment;
    }

    public Boolean getOpen() {
        if (open == null) {
            return Boolean.TRUE;
        }
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public ContactPhoneOption getContactPhoneOption() {
        return contactPhoneOption;
    }

    public void setContactPhoneOption(ContactPhoneOption contactPhoneOption) {
        this.contactPhoneOption = contactPhoneOption;
    }

    public Integer getNumberOfSms() {
        return numberOfSms;
    }

    public void setNumberOfSms(Integer numberOfSms) {
        this.numberOfSms = numberOfSms;
    }

    public Set<ServiceOffered> getServiceOffereds() {
        return serviceOffereds;
    }

    public void setServiceOffereds(Set<ServiceOffered> serviceOffereds) {
        this.serviceOffereds = serviceOffereds;
    }

    public UserLevel getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(UserLevel userLevel) {
        this.userLevel = userLevel;
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

    public Integer getPreviousCareLevel() {
        return previousCareLevel;
    }

    public CareLevel getCurrentCareLevel() {
        if (previousCareLevel != null) {
            return CareLevel.get(previousCareLevel + 1);
        }
        return null;
    }

    public String getReferredPersonId() {
        return referredPersonId;
    }

    public void setReferredPersonId(String referredPersonId) {
        this.referredPersonId = referredPersonId;
    }

    public InvestigationTest getViralLoad() {
        return viralLoad;
    }

    public void setViralLoad(InvestigationTest viralLoad) {
        this.viralLoad = viralLoad;
    }

    public InvestigationTest getCd4Count() {
        return cd4Count;
    }

    public void setCd4Count(InvestigationTest cd4Count) {
        this.cd4Count = cd4Count;
    }

    public VisitOutcome getVisitOutcome() {
        return visitOutcome;
    }

    public void setVisitOutcome(VisitOutcome visitOutcome) {
        this.visitOutcome = visitOutcome;
    }

    public Set<LabTask> getLabTasks() {
        return labTasks;
    }

    public void setLabTasks(Set<LabTask> labTasks) {
        this.labTasks = labTasks;
    }

    public DifferentiatedService getDifferentiatedService() {
        return differentiatedService;
    }

    public void setDifferentiatedService(DifferentiatedService differentiatedService) {
        this.differentiatedService = differentiatedService;
    }

    public String getOtherReason() {
        return otherReason;
    }

    public void setOtherReason(String otherReason) {
        this.otherReason = otherReason;
    }

    public String getPatientId() {
        return patient!=null?patient.getId():null;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getLocationId() {
        return location!=null?location.getId():null;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getPositionId() {
        return position!=null?position.getId():null;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getParentId() {
        return parent!=null?parent.getId():null;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getPeriodId() {
        return periodId;
    }

    public void setPeriodId(String periodId) {
        this.periodId = periodId;
    }

    @Override
    public String toString() {
        return super.toString().concat("Contact{" + "patient=" + patient + ", contactDate=" + contactDate + ", nextClinicAppointmentDate=" + nextClinicAppointmentDate + ", careLevel=" + careLevel + ", location=" + location + ", contactPhoneOption=" + contactPhoneOption + ", numberOfSms=" + numberOfSms + ", position=" + position + ", reason=" + reason + ", otherReason=" + otherReason + ", period=" + period + ", internalReferral=" + internalReferral + ", externalReferral=" + externalReferral + ", followUp=" + followUp + ", subjective=" + subjective + ", objective=" + objective + ", differentiatedService=" + differentiatedService + ", labTasks=" + labTasks + ", clinicalAssessments=" + clinicalAssessments + ", nonClinicalAssessments=" + nonClinicalAssessments + ", plan=" + plan + ", actionTaken=" + actionTaken + ", stables=" + stables + ", enhanceds=" + enhanceds + ", serviceOffereds=" + serviceOffereds + ", parent=" + parent + ", referredPerson=" + referredPerson + ", lastClinicAppointmentDate=" + lastClinicAppointmentDate + ", attendedClinicAppointment=" + attendedClinicAppointment + ", open=" + open + ", defaultMessage=" + defaultMessage + ", currentElement=" + currentElement + ", userLevel=" + userLevel + ", district=" + district + ", province=" + province + ", previousCareLevel=" + previousCareLevel + ", currentCareLevel=" + currentCareLevel + ", visitOutcome=" + visitOutcome + ", referredPersonId=" + referredPersonId + ", viralLoad=" + viralLoad + ", cd4Count=" + cd4Count + '}');
    }

}
