/*
 * Copyright 2014 Judge Muzinda.
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
import org.springframework.stereotype.Repository;
import zw.org.zvandiri.business.domain.District;

import java.util.Date;
import java.util.List;

/**
 *
 * @author manatsachinyeruse@gmail.com
 */

@Repository
public interface DistrictRepo extends CrudRepository<District, String> {

    @Query(value = "from District  t ")
    List<District> findAll(Pageable pageable);

    @Query(value = "from District d left join fetch d.province p where p.name=:province ")
    List<District> findAllByProvince(@Param("province") String province, Pageable pageable);

    @Query(value = "from District d where d.name=:district  ")
    List<District> findAllByDistrict(@Param("district") String district, Pageable pageable);

}