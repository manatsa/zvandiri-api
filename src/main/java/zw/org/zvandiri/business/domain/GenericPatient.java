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
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import zw.org.zvandiri.business.domain.util.Gender;
import zw.org.zvandiri.business.domain.util.HIVDisclosureLocation;
import zw.org.zvandiri.business.domain.util.PatientChangeEvent;
import zw.org.zvandiri.business.domain.util.TransmissionMode;
import zw.org.zvandiri.business.domain.util.YesNo;
import zw.org.zvandiri.business.util.StringUtils;

/**
 *
 * @author Judge Muzinda
 */
@MappedSuperclass
public class GenericPatient extends BaseEntity {

    private String firstName;
    private String middleName;
    private String lastName;
    @Enumerated
    private Gender gender;
    @Enumerated
    private YesNo consentToMHealth;
    @ManyToOne
    @JoinColumn(name = "period")
    private Period period;
    private String address;
    private String address1;
    private String mobileNumber;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateOfBirth;
    @ManyToOne
    @JoinColumn(name = "education")
    private Education education;
    @ManyToOne
    @JoinColumn(name = "education_level")
    private EducationLevel educationLevel;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateJoined;
    @ManyToOne
    @JoinColumn(name = "referer")
    private Referer referer;
    
    @ManyToOne
    @JoinColumn(name = "primary_clinic")
    private Facility primaryClinic;
    @ManyToOne
    @JoinColumn(name = "support_group")
    private SupportGroup supportGroup;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateTested;
    @Enumerated
    private HIVDisclosureLocation hIVDisclosureLocation;
    @Enumerated
    private YesNo disability;
    @Enumerated
    private YesNo cat;
    @Enumerated
    private YesNo youngMumGroup;
    @Enumerated
    private YesNo youngDadGroup;
    @Enumerated
    private YesNo selfPrimaryCareGiver;

    @ManyToOne
    @JoinColumn(name = "relationship")
    private Relationship relationship;
    private String secondaryMobileNumber;
    @Enumerated
    private YesNo mobileOwner;
    private String ownerName;
    @ManyToOne
    @JoinColumn(name = "mobile_owner_relation")
    private Relationship mobileOwnerRelation;
    @Enumerated
    private YesNo ownSecondaryMobile;
    private String secondaryMobileOwnerName;
    @ManyToOne
    @JoinColumn(name = "secondary_mobileowner_relation")
    private Relationship secondaryMobileownerRelation;
    @Enumerated
    private TransmissionMode transmissionMode;
    @Enumerated
    private YesNo hivStatusKnown;
    private String consentForm;
    private String mhealthForm;
    private YesNo hei = YesNo.NO;
    @Transient
    private Boolean heuReg;
    @ManyToOne
    @JoinColumn(name = "mother_of_hei")
    private Patient motherOfHei;
    @Enumerated
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private PatientChangeEvent status = PatientChangeEvent.ACTIVE;

    @Transient
    private String periodId;

    @Transient
    private String educationId;

    @Transient
    private String educationLevelId;

    @Transient
    private String referrerId;

    @Transient
    private String relationshipId;

    @Transient
    private String mobileOwnerRelationId;

    @Transient
    private String primaryClinicId;

    @Transient
    private String supportGroupId;

    @Transient
    private String secondaryMobileownerRelationId;

    @Transient
    private String motherOfHeiId;

    @Temporal(TemporalType.DATE)
    private Date statusChangeDate;
    @OneToMany(mappedBy = "patient", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JsonIgnore
    private Set<Dependent> dependents = new HashSet<>();
    @OneToMany(mappedBy = "patient", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<MedicalHist> medicalHists = new HashSet<>();
    @OneToMany(mappedBy = "patient", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<ChronicInfectionItem> chronicInfectionItems = new HashSet<>();
    @OneToMany(mappedBy = "patient", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<HivConInfectionItem> hivConInfectionItems = new HashSet<>();
    @OneToMany(mappedBy = "patient", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<ArvHist> arvHists = new HashSet<>();
    @OneToMany(mappedBy = "patient", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<MentalHealthItem> mentalHealthItems = new HashSet<>();
    @OneToMany(mappedBy = "patient", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<SrhHist> srhHists = new HashSet<>();
    @OneToMany(mappedBy = "patient", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<ObstercHist> obstercHists = new HashSet<>();
    @OneToMany(mappedBy = "patient", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<SocialHist> socialHists = new HashSet<>();
    @OneToMany(mappedBy = "patient", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<SubstanceItem> substanceItems = new HashSet<>();
    @OneToMany(mappedBy = "patient", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<Family> familys = new HashSet<>();
    @OneToMany(mappedBy = "patient", cascade = {CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Set<Contact> contacts = new HashSet<>();
    @OneToMany(mappedBy = "patient", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<EidTest> eidTests = new HashSet<>();
    @OneToMany(mappedBy = "patient", cascade = {CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Set<Referral> referrals = new HashSet<>();
    @OneToMany(mappedBy = "patient", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<InvestigationTest> investigationTests = new HashSet<>();
    @OneToMany(mappedBy = "patient", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<CatDetail> catDetails = new HashSet<>();
    @OneToMany(mappedBy = "patient", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<Mortality> mortalitys = new HashSet<>();
    @OneToMany(mappedBy = "patient", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<MentalHealthScreening> mentalHealthScreenings = new HashSet<>();
    @OneToMany(mappedBy = "patient", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<TbIpt> tbIpts = new HashSet<>();
    /*
     new fields
     */
    @ManyToOne
    @JoinColumn(name = "reason_for_not_reachingolevel")
    private ReasonForNotReachingOLevel reasonForNotReachingOLevel;
    private String refererName;
    private String oINumber;
    @Column(unique = true)
    private String patientNumber;

    public String getFirstName() {
        return StringUtils.toCamelCase2(firstName);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return StringUtils.toCamelCase2(middleName);
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return StringUtils.toCamelCase2(lastName);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public YesNo getConsentToMHealth() {
        return consentToMHealth;
    }

    public void setConsentToMHealth(YesNo consentToMHealth) {
        this.consentToMHealth = consentToMHealth;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Referer getReferer() {
        return referer;
    }

    public void setReferer(Referer referer) {
        this.referer = referer;
    }

    public Facility getPrimaryClinic() {
        return primaryClinic;
    }

    public void setPrimaryClinic(Facility primaryClinic) {
        this.primaryClinic = primaryClinic;
    }

    public SupportGroup getSupportGroup() {
        return supportGroup;
    }

    public void setSupportGroup(SupportGroup supportGroup) {
        this.supportGroup = supportGroup;
    }

    public Date getDateTested() {
        return dateTested;
    }

    public void setDateTested(Date dateTested) {
        this.dateTested = dateTested;
    }

    public HIVDisclosureLocation gethIVDisclosureLocation() {
        return hIVDisclosureLocation;
    }

    public void sethIVDisclosureLocation(HIVDisclosureLocation hIVDisclosureLocation) {
        this.hIVDisclosureLocation = hIVDisclosureLocation;
    }

    public YesNo getDisability() {
        return disability;
    }

    public void setDisability(YesNo disability) {
        this.disability = disability;
    }

    public YesNo getCat() {
        return cat;
    }

    public void setCat(YesNo cat) {
        this.cat = cat;
    }

    public YesNo getYoungMumGroup() {
        return youngMumGroup;
    }

    public void setYoungMumGroup(YesNo youngMumGroup) {
        this.youngMumGroup = youngMumGroup;
    }

    public YesNo getYoungDadGroup() {
        return youngDadGroup;
    }

    public void setYoungDadGroup(YesNo youngDadGroup) {
        this.youngDadGroup = youngDadGroup;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    public String getSecondaryMobileNumber() {
        return secondaryMobileNumber;
    }

    public void setSecondaryMobileNumber(String secondaryMobileNumber) {
        this.secondaryMobileNumber = secondaryMobileNumber;
    }

    public YesNo getMobileOwner() {
        return mobileOwner;
    }

    public void setMobileOwner(YesNo mobileOwner) {
        this.mobileOwner = mobileOwner;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Relationship getMobileOwnerRelation() {
        return mobileOwnerRelation;
    }

    public void setMobileOwnerRelation(Relationship mobileOwnerRelation) {
        this.mobileOwnerRelation = mobileOwnerRelation;
    }

    public YesNo getOwnSecondaryMobile() {
        return ownSecondaryMobile;
    }

    public void setOwnSecondaryMobile(YesNo ownSecondaryMobile) {
        this.ownSecondaryMobile = ownSecondaryMobile;
    }

    public String getSecondaryMobileOwnerName() {
        return secondaryMobileOwnerName;
    }

    public void setSecondaryMobileOwnerName(String secondaryMobileOwnerName) {
        this.secondaryMobileOwnerName = secondaryMobileOwnerName;
    }

    public Relationship getSecondaryMobileownerRelation() {
        return secondaryMobileownerRelation;
    }

    public void setSecondaryMobileownerRelation(Relationship secondaryMobileownerRelation) {
        this.secondaryMobileownerRelation = secondaryMobileownerRelation;
    }

    public TransmissionMode getTransmissionMode() {
        return transmissionMode;
    }

    public void setTransmissionMode(TransmissionMode transmissionMode) {
        this.transmissionMode = transmissionMode;
    }

    public YesNo getHivStatusKnown() {
        return hivStatusKnown;
    }

    public void setHivStatusKnown(YesNo hivStatusKnown) {
        this.hivStatusKnown = hivStatusKnown;
    }

    public String getConsentForm() {
        return consentForm;
    }

    public void setConsentForm(String consentForm) {
        this.consentForm = consentForm;
    }

    public String getMhealthForm() {
        return mhealthForm;
    }

    public void setMhealthForm(String mhealthForm) {
        this.mhealthForm = mhealthForm;
    }

    public Set<Dependent> getDependents() {
        return dependents;
    }

    public void setDependents(Set<Dependent> dependents) {
        this.dependents = dependents;
    }

    public Set<MedicalHist> getMedicalHists() {
        return medicalHists;
    }

    public void setMedicalHists(Set<MedicalHist> medicalHists) {
        this.medicalHists = medicalHists;
    }

    public Set<ChronicInfectionItem> getChronicInfectionItems() {
        return chronicInfectionItems;
    }

    public void setChronicInfectionItems(Set<ChronicInfectionItem> chronicInfectionItems) {
        this.chronicInfectionItems = chronicInfectionItems;
    }

    public Set<HivConInfectionItem> getHivConInfectionItems() {
        return hivConInfectionItems;
    }

    public void setHivConInfectionItems(Set<HivConInfectionItem> hivConInfectionItems) {
        this.hivConInfectionItems = hivConInfectionItems;
    }

    public Set<ArvHist> getArvHists() {
        return arvHists;
    }

    public void setArvHists(Set<ArvHist> arvHists) {
        this.arvHists = arvHists;
    }

    public Set<MentalHealthItem> getMentalHealthItems() {
        return mentalHealthItems;
    }

    public void setMentalHealthItems(Set<MentalHealthItem> mentalHealthItems) {
        this.mentalHealthItems = mentalHealthItems;
    }

    public Set<SrhHist> getSrhHists() {
        return srhHists;
    }

    public void setSrhHists(Set<SrhHist> srhHists) {
        this.srhHists = srhHists;
    }

    public Set<ObstercHist> getObstercHists() {
        return obstercHists;
    }

    public void setObstercHists(Set<ObstercHist> obstercHists) {
        this.obstercHists = obstercHists;
    }

    public Set<SocialHist> getSocialHists() {
        return socialHists;
    }

    public void setSocialHists(Set<SocialHist> socialHists) {
        this.socialHists = socialHists;
    }

    public Set<SubstanceItem> getSubstanceItems() {
        return substanceItems;
    }

    public void setSubstanceItems(Set<SubstanceItem> substanceItems) {
        this.substanceItems = substanceItems;
    }

    public Set<Family> getFamilys() {
        return familys;
    }

    public void setFamilys(Set<Family> familys) {
        this.familys = familys;
    }

    public Set<Mortality> getMortalitys() {
        return mortalitys;
    }

    public void setMortalitys(Set<Mortality> mortalitys) {
        this.mortalitys = mortalitys;
    }

    public PatientChangeEvent getStatus() {
        return status;
    }

    public void setStatus(PatientChangeEvent status) {
        this.status = status;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public Date getStatusChangeDate() {
        return statusChangeDate;
    }

    public void setStatusChangeDate(Date statusChangeDate) {
        this.statusChangeDate = statusChangeDate;
    }

    public ReasonForNotReachingOLevel getReasonForNotReachingOLevel() {
        return reasonForNotReachingOLevel;
    }

    public void setReasonForNotReachingOLevel(ReasonForNotReachingOLevel reasonForNotReachingOLevel) {
        this.reasonForNotReachingOLevel = reasonForNotReachingOLevel;
    }

    public String getRefererName() {
        return refererName;
    }

    public void setRefererName(String refererName) {
        this.refererName = refererName;
    }

    public String getoINumber() {
        return oINumber;
    }

    public void setoINumber(String oINumber) {
        this.oINumber = oINumber;
    }

    public YesNo getHei() {
        return hei == null ? YesNo.NO : hei;
    }

    public void setHei(YesNo hei) {
        this.hei = hei;
    }

    public Patient getMotherOfHei() {
        return motherOfHei;
    }

    public void setMotherOfHei(Patient motherOfHei) {
        this.motherOfHei = motherOfHei;
    }

    public Boolean getHeuReg() {
        if (getHei().equals(YesNo.YES) && getMotherOfHei() == null) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Set<EidTest> getEidTests() {
        return eidTests;
    }

    public void setEidTests(Set<EidTest> eidTests) {
        this.eidTests = eidTests;
    }

    public YesNo getSelfPrimaryCareGiver() {
        return selfPrimaryCareGiver;
    }

    public void setSelfPrimaryCareGiver(YesNo selfPrimaryCareGiver) {
        this.selfPrimaryCareGiver = selfPrimaryCareGiver;
    }

    public Set<Referral> getReferrals() {
        return referrals;
    }

    public void setReferrals(Set<Referral> referrals) {
        this.referrals = referrals;
    }

    public Set<InvestigationTest> getInvestigationTests() {
        return investigationTests;
    }

    public void setInvestigationTests(Set<InvestigationTest> investigationTests) {
        this.investigationTests = investigationTests;
    }


    public String getPeriodId() {
        return period!=null?period.getId():null;
    }

    public void setPeriodId(String periodId) {
        this.periodId = periodId;
    }

    public String getEducationId() {
        return education!=null?education.getId():null;
    }

    public void setEducationId(String educationId) {
        this.educationId = educationId;
    }

    public String getEducationLevelId() {
        return educationLevel!=null?educationLevel.getId():null;
    }

    public void setEducationLevelId(String educationLevelId) {
        this.educationLevelId = educationLevelId;
    }

    public String getReferrerId() {
        return referer!=null?referer.getId():null;
    }

    public void setReferrerId(String referrerId) {
        this.referrerId = referrerId;
    }

    public String getRelationshipId() {
        return relationship!=null?relationship.getId():null;
    }

    public void setRelationshipId(String relationshipId) {
        this.relationshipId = relationshipId;
    }

    public String getMobileOwnerRelationId() {
        return mobileOwnerRelation!=null?mobileOwnerRelation.getId():null;
    }

    public void setMobileOwnerRelationId(String mobileOwnerRelationId) {
        this.mobileOwnerRelationId = mobileOwnerRelationId;
    }

    public String getPrimaryClinicId() {
        return primaryClinic!=null?primaryClinic.getId():null;
    }

    public void setPrimaryClinicId(String primaryClinicId) {
        this.primaryClinicId = primaryClinicId;
    }

    public String getSupportGroupId() {
        return supportGroup!=null?supportGroup.getId():null;
    }

    public void setSupportGroupId(String supportGroupId) {
        this.supportGroupId = supportGroupId;
    }

    public String getSecondaryMobileownerRelationId() {
        return secondaryMobileownerRelation!=null?secondaryMobileownerRelation.getId():null;
    }

    public void setSecondaryMobileownerRelationId(String secondaryMobileownerRelationId) {
        this.secondaryMobileownerRelationId = secondaryMobileownerRelationId;
    }

    public String getMotherOfHeiId() {
        return motherOfHei!=null?motherOfHei.getId():null;
    }

    public void setMotherOfHeiId(String motherOfHeiId) {
        this.motherOfHeiId = motherOfHeiId;
    }

    public Set<CatDetail> getCatDetails() {
        return catDetails;
    }

    public void setCatDetails(Set<CatDetail> catDetails) {
        this.catDetails = catDetails;
    }

    public Set<MentalHealthScreening> getMentalHealthScreenings() {
        return mentalHealthScreenings;
    }

    public void setMentalHealthScreenings(Set<MentalHealthScreening> mentalHealthScreenings) {
        this.mentalHealthScreenings = mentalHealthScreenings;
    }

    public Set<TbIpt> getTbIpts() {
        return tbIpts;
    }

    public void setTbIpts(Set<TbIpt> tbIpts) {
        this.tbIpts = tbIpts;
    }

    public void add(InvestigationTest item, Patient patient) {
        investigationTests.add(item);
        item.setPatient(patient);
    }

    public void add(CatDetail item, Patient patient) {
        catDetails.add(item);
        item.setPatient(patient);
    }

    public void add(Referral item, Patient patient) {
        referrals.add(item);
        item.setPatient(patient);
    }

    public void add(EidTest item, Patient patient) {
        eidTests.add(item);
        item.setPatient(patient);
    }

    public void add(Dependent item, Patient patient) {
        dependents.add(item);
        item.setPatient(patient);
    }

    public void add(MedicalHist item, Patient patient) {
        medicalHists.add(item);
        item.setPatient(patient);
    }

    public void add(ChronicInfectionItem item, Patient patient) {
        chronicInfectionItems.add(item);
        item.setPatient(patient);
    }

    public void add(MentalHealthItem item, Patient patient) {
        mentalHealthItems.add(item);
        item.setPatient(patient);
    }

    public void add(HivConInfectionItem item, Patient patient) {
        hivConInfectionItems.add(item);
        item.setPatient(patient);
    }

    public void add(ArvHist item, Patient patient) {
        arvHists.add(item);
        item.setPatient(patient);
    }

    public void add(ObstercHist item, Patient patient) {
        obstercHists.add(item);
        item.setPatient(patient);
    }

    public void add(SocialHist item, Patient patient) {
        socialHists.add(item);
        item.setPatient(patient);
    }

    public void add(SrhHist item, Patient patient) {
        srhHists.add(item);
        item.setPatient(patient);
    }

    public void add(SubstanceItem item, Patient patient) {
        substanceItems.add(item);
        item.setPatient(patient);
    }

    public void add(Contact item, Patient patient) {
        //contacts.add(item);
        item.setPatient(patient);
    }

    public void add(Family item, Patient patient) {
        familys.add(item);
        item.setPatient(patient);
    }
    
    public void add(Mortality item, Patient patient) {
        mortalitys.add(item);
        item.setPatient(patient);
    }
    
    public void add(TbIpt item, Patient patient) {
        tbIpts.add(item);
        item.setPatient(patient);
    }
    
    public void add(MentalHealthScreening item, Patient patient) {
        mentalHealthScreenings.add(item);
        item.setPatient(patient);
    }

    public String getPatientNumber() {
        return patientNumber;
    }

    public void setPatientNumber(String patientNumber) {
        this.patientNumber = patientNumber;
    }
}
