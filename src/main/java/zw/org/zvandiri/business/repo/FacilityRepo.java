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
package zw.org.zvandiri.business.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import zw.org.zvandiri.business.domain.Facility;

import java.util.Date;
import java.util.List;

/**
 *
 * @author manatsachinyeruse@gmail.com
 */

public interface FacilityRepo extends CrudRepository<Facility, String> {

    @Query(value = "from Facility  t")
    List<Facility> findAll(Pageable pageable);

    @Query(value = "from Facility  f  inner join fetch " +
            "f.district d left join fetch d.province p where p.name=:province and f.dateCreated between :start and :end ")
    List<Facility> findAllByProvince(@Param("start")  @DateTimeFormat(pattern = "dd-MM-yyyy")Date start,
                                    @Param("end") @DateTimeFormat(pattern = "dd-MM-yyyy") Date end,@Param("province") String province, Pageable pageable);

    @Query(value = "from Facility  f  inner join fetch" +
            " f.district d where d.name=:district and f.dateCreated between :start and :end ")
    List<Facility> findAllByDistrict(@Param("start")  @DateTimeFormat(pattern = "dd-MM-yyyy")Date start,
                                    @Param("end") @DateTimeFormat(pattern = "dd-MM-yyyy") Date end,@Param("district") String district, Pageable pageable);

    @Query(value = "from Facility  f  " +
            "where f.name=:facility and f.dateCreated between :start and :end ")
    List<Facility> findAllByFacility(@Param("start")  @DateTimeFormat(pattern = "dd-MM-yyyy")Date start,
                                    @Param("end") @DateTimeFormat(pattern = "dd-MM-yyyy") Date end,@Param("facility") String facility, Pageable pageable);
}
