package zw.org.zvandiri.business.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import zw.org.zvandiri.business.domain.ActionTaken;
import zw.org.zvandiri.business.domain.Bicycle;
import zw.org.zvandiri.business.domain.Cadre;
import zw.org.zvandiri.business.domain.Patient;

import java.util.Date;
import java.util.List;

@Repository
@RepositoryRestResource
public interface BicycleRepo extends CrudRepository<Bicycle, String> {

    @Query(value = "from Bicycle  t where t.dateCreated between :start and :end")
    List<Bicycle> findAll(@Param("start")  @DateTimeFormat(pattern = "dd-MM-yyyy") Date start,
                              @Param("end") @DateTimeFormat(pattern = "dd-MM-yyyy") Date end, Pageable pageable);

    @Query(value = "from Bicycle t left join fetch t.cadre p left join  fetch  p.primaryClinic f  inner join fetch " +
            "f.district d left join fetch d.province p where p.name=:province and t.dateCreated between :start and :end ")
    List<Cadre> findAllByProvince(@Param("start")  @DateTimeFormat(pattern = "dd-MM-yyyy")Date start,
                                        @Param("end") @DateTimeFormat(pattern = "dd-MM-yyyy") Date end,@Param("province") String province, Pageable pageable);

    @Query(value = "from Bicycle t left join fetch t.cadre  p left join  fetch  p.primaryClinic f  inner join fetch" +
            " f.district d where d.name=:district and t.dateCreated between :start and :end ")
    List<Bicycle> findAllByDistrict(@Param("start")  @DateTimeFormat(pattern = "dd-MM-yyyy")Date start,
                                        @Param("end") @DateTimeFormat(pattern = "dd-MM-yyyy") Date end,@Param("district") String district, Pageable pageable);

    @Query(value = "from Bicycle t left join fetch t.cadre  p left join  fetch  p.primaryClinic f  " +
            "where f.name=:facility and t.dateCreated between :start and :end ")
    List<Bicycle> findAllByFacility(@Param("start")  @DateTimeFormat(pattern = "dd-MM-yyyy")Date start,
                                        @Param("end") @DateTimeFormat(pattern = "dd-MM-yyyy") Date end,@Param("facility") String facility, Pageable pageable);
}
