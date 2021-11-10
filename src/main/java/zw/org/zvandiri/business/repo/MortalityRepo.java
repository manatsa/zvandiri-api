/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.org.zvandiri.business.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import zw.org.zvandiri.business.domain.Mortality;

import java.util.Date;
import java.util.List;


/**
 *
 * @author manatsachinyeruse@gmail.com
 */

public interface MortalityRepo extends CrudRepository<Mortality, String> {

    @Query(value = "from Mortality  t where t.dateCreated between :start and :end")
    List<Mortality> findAll(@Param("start")  @DateTimeFormat(pattern = "dd-MM-yyyy") Date start,
                          @Param("end") @DateTimeFormat(pattern = "dd-MM-yyyy") Date end, Pageable pageable);

    @Query(value = "from Mortality t left join fetch t.patient p left join  fetch  p.primaryClinic f  inner join fetch " +
            "f.district d left join fetch d.province p where p.name=:province and t.dateCreated between :start and :end ")
    List<Mortality> findAllByProvince(@Param("start")  @DateTimeFormat(pattern = "dd-MM-yyyy")Date start,
                                    @Param("end") @DateTimeFormat(pattern = "dd-MM-yyyy") Date end,@Param("province") String province, Pageable pageable);

    @Query(value = "from Mortality t left join fetch t.patient  p left join  fetch  p.primaryClinic f  inner join fetch" +
            " f.district d where d.name=:district and t.dateCreated between :start and :end ")
    List<Mortality> findAllByDistrict(@Param("start")  @DateTimeFormat(pattern = "dd-MM-yyyy")Date start,
                                    @Param("end") @DateTimeFormat(pattern = "dd-MM-yyyy") Date end,@Param("district") String district, Pageable pageable);

    @Query(value = "from Mortality t left join fetch t.patient  p left join  fetch  p.primaryClinic f  " +
            "where f.name=:facility and t.dateCreated between :start and :end ")
    List<Mortality> findAllByFacility(@Param("start")  @DateTimeFormat(pattern = "dd-MM-yyyy")Date start,
                                    @Param("end") @DateTimeFormat(pattern = "dd-MM-yyyy") Date end,@Param("facility") String facility, Pageable pageable);
}
