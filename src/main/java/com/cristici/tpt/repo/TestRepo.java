package com.cristici.tpt.repo;

import com.cristici.tpt.domain.CommercialData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepo extends JpaRepository<CommercialData, Long> {
}
