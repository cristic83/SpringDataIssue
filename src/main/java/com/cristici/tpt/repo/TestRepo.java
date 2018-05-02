package com.cristici.tpt.repo;

import com.cristici.tpt.domain.CommercialData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepo extends JpaRepository<CommercialData, Long> {
}
