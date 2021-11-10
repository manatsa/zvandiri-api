/*
 * Copyright 2017 User.
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
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import zw.org.zvandiri.business.domain.ActionTaken;

import java.util.List;

/**
 *
 * @author manatsachinyeruse@gmail.com
 */
@Repository
@RepositoryRestResource
public interface ActionTakenRepo extends CrudRepository<ActionTaken, String> {

    @Query(value = "from ActionTaken  t")
    List<ActionTaken> findAll(Pageable pageable);


}
