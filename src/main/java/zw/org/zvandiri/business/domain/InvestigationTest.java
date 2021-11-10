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
package zw.org.zvandiri.business.domain;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import zw.org.zvandiri.business.domain.util.TestType;

/**
 *
 * @author Judge Muzinda
 */
@Entity 
@Table(indexes = {
		@Index(name = "investigation_test_patient", columnList = "patient"),
		@Index(name = "investigation_test_dateTaken", columnList = "dateTaken"),
})

public class InvestigationTest extends TestResult {

    @Enumerated
    private TestType testType;
    @Transient
    private String viralLoadSuppressionStatus;

    public InvestigationTest() {
    }

    public InvestigationTest(Patient patient, TestType testType) {
        super(patient);
        this.testType = testType;
    }

    public TestType getTestType() {
        return testType;
    }

    public void setTestType(TestType testType) {
        this.testType = testType;
    }

    public String getViralLoadSuppressionStatus() {
        if (testType.equals(TestType.VIRAL_LOAD)) {
            return (getResult() != null && getResult() < 1000) ? "Suppressed" : "Unsuppressed";
        }
        return null;
    }
}
