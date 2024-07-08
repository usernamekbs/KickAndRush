package com.shop.entity;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shop.CoupangItems;
import org.springframework.transaction.annotation.Transactional;

public interface CoupangItemsRepository extends JpaRepository<CoupangItems, Long>{
//SELECT * FROM coupang_items i where i.coupang_id =:id limit 200", nativeQuery = true
//	@Query("SELECT i FROM CoupangItems i where i.coupang.id =:id") 
//	@Query(value="SELECT * FROM coupang_items i where i.coupang_id =:id limit 200", nativeQuery = true)
//	List<CoupangItems> findByCoupang(@Param("id") Long id); 

	@Query(value="SELECT DISTINCT i FROM CoupangItems i " 
	           + "LEFT JOIN FETCH i.contents LEFT JOIN FETCH i.attributes LEFT JOIN FETCH i.images LEFT JOIN FETCH i.contents.contentDetails"
	           + " WHERE i.coupang.id=:id") 
	List<CoupangItems> findAllByCoupang(@Param("id") Long id, PageRequest pageRequest);
//
//	@Query(value="SELECT DISTINCT i FROM CoupangItems i "
//			+ "LEFT JOIN FETCH i.contents LEFT JOIN FETCH i.attributes LEFT JOIN FETCH i.images LEFT JOIN FETCH i.contents.contentDetails"
//			+ " WHERE i.coupang.id=:id")
//	List<CoupangItems> findAllByCoupangLists(@Param("id") Long id,Pageable pageable);
//	@Query(value="SELECT DISTINCT i FROM CoupangItems i " 
//	           + "LEFT JOIN FETCH i.contents LEFT JOIN FETCH i.attributes LEFT JOIN FETCH i.images LEFT JOIN FETCH i.contents.contentDetails"
//	           + " WHERE i.coupang.id=:id") 
//	List<CoupangItems> findAllByCoupang(@Param("id") Long id,Pageable pageable);

	@Query(value="SELECT DISTINCT i FROM CoupangItems i "
			+ "LEFT JOIN FETCH i.contents LEFT JOIN FETCH i.attributes LEFT JOIN FETCH i.images LEFT JOIN FETCH i.contents.contentDetails"
			+ " WHERE i.coupang.id=:id")
	List<CoupangItems> findAllByCoupangLists(@Param("id") Long id,Pageable pageable);

	@Query("SELECT ci FROM CoupangItems ci WHERE ci.coupang.id = :coupangId AND ci.sendYn = 'N'")
	List<CoupangItems> findAllByCoupangId(@Param("coupangId") Long coupangId, Pageable itemLimit);

	@Modifying(clearAutomatically = true)//영속성 컨텍스트 초기화 하고 넣음
	@Query("UPDATE CoupangItems ci SET ci.sendYn='Y' WHERE ci.coupang.id = :coupangId AND ci.id = :coupangItemId")
	@Transactional
	void bulkUpdate(@Param("coupangId") Long coupangId,@Param("coupangItemId") Long coupangItemId);
	
} 
