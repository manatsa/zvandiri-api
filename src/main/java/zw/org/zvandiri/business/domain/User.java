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

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import zw.org.zvandiri.business.domain.util.Gender;
import zw.org.zvandiri.business.domain.util.UserLevel;
import zw.org.zvandiri.business.domain.util.UserType;


/**
 *
 * @author Judge Muzinda
 */
@Entity
@Table(name = "user", indexes = {
		@Index(name = "user_user_name", columnList = "userName"),
		@Index(name = "user_user_province", columnList = "province"),
		@Index(name = "user_user_district", columnList = "district")
})
public class User extends BaseEntity {

    @Enumerated
    private Gender gender;
    private static final long serialVersionUID = 1L;
    private String password;
    @Transient
    private String confirmPassword;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String userName;
    @Enumerated
    private UserType userType = UserType.ZVANDIRI_STAFF;
    @Enumerated
    private UserLevel userLevel;
    @ManyToOne
    @JoinColumn(name = "province")
    private Province province;
    @ManyToOne
    @JoinColumn(name = "district")
    private District district;
    @Transient
    private String displayName;
    @Transient
    private String roles;
    @Transient
    private String currentElement;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {
        @JoinColumn(name = "user_id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "role_id", nullable = false)})
    private Set<UserRole> userRoles = new HashSet<>();

    @Transient
    private String districtId;

    @Transient
    private String provinceId;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public String getDisplayName() {
        return lastName + " " + firstName;
    }
    
    public Set<UserRole> getDisplayUserRole() {
        return userRoles;
    }

    public String getRoles() {
        return this.userRoles.stream().map(userRole -> userRole.getName()).collect(Collectors.joining(","));
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public UserLevel getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(UserLevel userLevel) {
        this.userLevel = userLevel;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }    

    public String getCurrentElement() {
        return currentElement;
    }

    public void setCurrentElement(String currentElement) {
        this.currentElement = currentElement;
    }

    public String getDistrictId() {
        return district!=null?district.getId():null;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getProvinceId() {
        return province!=null?province.getId():null;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    @Override
    public String toString()
    {
       return "FirstName:"+firstName+"\tLastName:"+lastName+"\tUsername:"+userName+"\tDistrict :"+district+"\tProvince :"+province+"\tRoles :"+getRoles();
    }


}
