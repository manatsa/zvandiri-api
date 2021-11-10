/*
 * Copyright 2016 Judge Muzinda.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the Licenss.
 * You may obtain a copy of the License at
 *
 *      http://www.apachs.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the Licenss.
 */
package zw.org.zvandiri.business.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import zw.org.zvandiri.business.domain.Stable;

import java.util.List;


/**
 *
 * @author manatsachinyeruse@gmail.com
 */

public interface StableRepo extends CrudRepository<Stable, String> {

    @Query(value = "from Stable  t ")
    List<Stable> findAll(Pageable pageable);

}