package com.tutorial.bikestores.sales.store;

import com.tutorial.bikestores.shared.DropdownDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Integer> {
    @Query("""
            SELECT new com.tutorial.bikestores.shared.DropdownDTO(s.id, CONCAT(s.name, ' - ', st.quantity, ' remaining'))
            FROM Store s
            JOIN s.stock st
            WHERE st.id.productId = :productId
            AND st.quantity > 0
            """)
    List<DropdownDTO> findAllStoreDropdowns(@Param("productId") Integer productId);
}
