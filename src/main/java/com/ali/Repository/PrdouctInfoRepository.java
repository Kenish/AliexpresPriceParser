package com.ali.Repository;

import com.ali.model.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrdouctInfoRepository extends JpaRepository<ProductInfo,Long> {
}
