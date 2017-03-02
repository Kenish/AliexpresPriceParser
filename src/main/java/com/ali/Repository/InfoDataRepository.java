package com.ali.Repository;

import com.ali.model.InfoData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoDataRepository extends JpaRepository<InfoData,Long> {
}
