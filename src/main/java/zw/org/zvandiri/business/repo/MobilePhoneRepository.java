package zw.org.zvandiri.business.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import zw.org.zvandiri.business.domain.MobilePhone;

import java.util.Date;
import java.util.List;


/**
 *
 * @author manatsachinyeruse@gmail.com
 */


@Repository
public interface MobilePhoneRepository extends CrudRepository<MobilePhone, String> {

    @Query(value = "from MobilePhone  t where t.dateCreated between :start and :end")
    List<MobilePhone> findAll(@Param("start")  @DateTimeFormat(pattern = "dd-MM-yyyy") Date start,
                          @Param("end") @DateTimeFormat(pattern = "dd-MM-yyyy") Date end, Pageable pageable);

    @Query(value = "from MobilePhone t left join fetch t.cadre p left join  fetch p.province p where p.name=:province and t.dateCreated between :start and :end ")
    List<MobilePhone> findAllByProvince(@Param("start")  @DateTimeFormat(pattern = "dd-MM-yyyy")Date start,
                                    @Param("end") @DateTimeFormat(pattern = "dd-MM-yyyy") Date end,@Param("province") String province, Pageable pageable);

    @Query(value = "from MobilePhone t left join fetch t.cadre  p left join  fetch " +
            " p.district d where d.name=:district and t.dateCreated between :start and :end ")
    List<MobilePhone> findAllByDistrict(@Param("start")  @DateTimeFormat(pattern = "dd-MM-yyyy")Date start,
                                    @Param("end") @DateTimeFormat(pattern = "dd-MM-yyyy") Date end,@Param("district") String district, Pageable pageable);

    @Query(value = "from MobilePhone t left join fetch t.cadre  p left join  fetch  p.primaryClinic f  " +
            "where f.name=:facility and t.dateCreated between :start and :end ")
    List<MobilePhone> findAllByFacility(@Param("start")  @DateTimeFormat(pattern = "dd-MM-yyyy")Date start,
                                    @Param("end") @DateTimeFormat(pattern = "dd-MM-yyyy") Date end,@Param("facility") String facility, Pageable pageable);
}
