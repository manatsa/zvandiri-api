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
package zw.org.zvandiri.business.repo;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import zw.org.zvandiri.business.domain.Patient;
import zw.org.zvandiri.business.domain.projections.PatientProjection;
import zw.org.zvandiri.business.domain.util.PatientChangeEvent;

import java.util.List;

/**
 *
 * @author manatsachinyeruse@gmail.com
 */

@Repository
@RepositoryRestResource(
        collectionResourceRel = "/patients",
        path = "patients"
)
public interface PatientRepo extends CrudRepository<Patient, String> {

    @Query(value = "from Patient  p ")
    List<Patient> findAll(Pageable pageable);

    @Parameter(name = "status",description = "The status of the patient in CAPITAL LETTERS." +
            " These are  DECEASED, LOST_TO_FOLOWUP, GRADUATED, CHANGE_LOCATION, OPT_OUT, ACTIVE, REINSTATED, HIGHLY_EXPOSED_TO_POSITIVE and OTHER.")
    @Query(value = "from Patient  p  where p.status=:status ")
    List<Patient> findAllByStatus(@Param("status") PatientChangeEvent status, Pageable pageable);

    @Parameter(name = "province", description = "Province name to limit the scope of the search to a specific province.")
    @Query(value = "from Patient  p left join  fetch  p.primaryClinic f  inner join fetch f.district d left join fetch d.province p where p.name=:province ")
    List<Patient> findAllByProvince(@Param("province") String province, Pageable pageable);

    @Parameter(name = "district", description = "District name to limit the scope of the search to a specific district.")
    @Query(value = "from Patient  p left join  fetch  p.primaryClinic f  inner join fetch f.district d where d.name=:district ")
    List<Patient> findAllByDistrict(@Param("district") String district, Pageable pageable);

    @Parameter(name = "facility", description = "Facility name to limit the scope of the search to a specific facility.")
    @Query(value = "from Patient  p left join  fetch  p.primaryClinic f  where f.name=:facility ")
    List<Patient> findAllByFacility(@Param("facility") String facility, Pageable pageable);

    
}