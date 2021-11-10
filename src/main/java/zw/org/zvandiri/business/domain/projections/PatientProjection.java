package zw.org.zvandiri.business.domain.projections;


import org.springframework.data.rest.core.config.Projection;
import zw.org.zvandiri.business.domain.BaseEntity;
import zw.org.zvandiri.business.domain.Facility;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.util.Gender;
import zw.org.zvandiri.business.domain.util.PatientChangeEvent;

import java.util.Date;

/**
 * @author manatsachinyeruse@gmail.com
 */


@Projection(name="patient", types = {Patient.class})
public interface PatientProjection extends BaseProjection {

    String getId();

    String getName();

    Date getDateOfBirth();

    Gender getGender();

    String getLastContact();

    String getPrimaryClinicId();

    PatientChangeEvent getStatus();


}
