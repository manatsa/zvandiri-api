package zw.org.zvandiri.business.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 *
 * @author Judge Muzinda
 */
@Entity
@Table(indexes = {
    @Index(name = "district_province", columnList = "province")
})
public class District extends BaseName {

    private static final long serialVersionUID = 1L;
    @ManyToOne
    @JoinColumn(name = "province")
    @JsonProperty("province")
    @RestResource(path = "districtProvince", rel = "districtProvince")
    private Province province;
    @OneToMany(mappedBy = "district", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<Facility> facilitys = new HashSet<>();
    @OneToMany(mappedBy = "district", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<SupportGroup> supportGroups = new HashSet<>();

    @Transient
    //@Value("#{province.id}")
    private String provinceId;

    public District() {
    }

    public District(String id) {
        super(id);
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Set<Facility> getFacilitys() {
        return facilitys;
    }

    public void setFacilitys(Set<Facility> facilitys) {
        this.facilitys = facilitys;
    }

    public Set<SupportGroup> getSupportGroups() {
        return supportGroups;
    }

    public void setSupportGroups(Set<SupportGroup> supportGroups) {
        this.supportGroups = supportGroups;
    }

    public String getProvinceId() {
        return province!=null?province.getId():null;
    }

    /*public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }*/
}
