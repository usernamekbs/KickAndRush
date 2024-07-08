package com.shop.entity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shop.Coupang;
import com.shop.CoupangItems;
import com.shop.dto.CoupangDto;
import com.shop.dto.CoupangFlatDto;

public interface CoupangRepository extends JpaRepository<Coupang, Long>{
	
//	@Query(value="SELECT c FROM Coupang c JOIN c.items JOIN c.items.attributes JOIN c.items.images JOIN c.items.contents JOIN c.items.contents.contentDetails")
//	List<Coupang> findAllByCoupang();
//	
//	@Query(value="SELECT c FROM Coupang c INNER JOIN c.items INNER JOIN c.items.attributes INNER  JOIN c.items.images INNER  JOIN c.items.contents INNER JOIN c.items.contents.contentDetails WHERE c.id =:id")
//	CoupangFlatDto findByCoupang(@Param("id") long id);


//	@EntityGraph(value = "Coupang.items", type = EntityGraph.EntityGraphType.FETCH)
//    @Query("SELECT c FROM Coupang c")
//    Page<Coupang> findAllByCoupang(Pageable pageable);
//
//    @EntityGraph(value = "Coupang.items", type = EntityGraph.EntityGraphType.FETCH)
//    @Query("SELECT c FROM Coupang c WHERE c.id = :id")
//    CoupangFlatDto findByCoupang(@Param("id") long id);
	
	// 엔티티 그래프를 사용하여 페이징 처리된 Coupang 엔티티 목록 조회
//	@Query(value="SELECT c FROM Coupang c JOIN c.items JOIN c.items.attributes JOIN c.items.images JOIN c.items.contents JOIN c.items.contents.contentDetails")
//	List<Coupang> findAllByCoupang();
	
	@Query(value="SELECT c FROM Coupang c INNER JOIN c.items INNER JOIN c.items.attributes INNER  JOIN c.items.images INNER  JOIN c.items.contents INNER JOIN c.items.contents.contentDetails WHERE c.id =:id")
	CoupangFlatDto findByCoupang(@Param("id") long id);

	List<Coupang> findTop2ByOrderByIdAsc();
}
