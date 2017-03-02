package com.ali.Repository;

import com.ali.model.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo,Long> {
    ProductInfo findByName(@Param(value = "name") String name);
}
