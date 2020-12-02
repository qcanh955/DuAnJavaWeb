package com.javaweb.repository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.javaweb.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findByNameContainingIgnoreCase(String nameprd);

	@Modifying
	@Transactional
	@Query(value = "UPDATE public.products\r\n"
			+ "	SET created_at=?, details=?, highlight=?, image=?, name=?, plsy=?, nxb=?, author=?, pagenum=?, new_product=?, price=?, sale=?, updated_at=?, id_category=?\r\n"
			+ "	WHERE id=?", nativeQuery = true)
	void update(Date created_at, String details, boolean highlight, String image, String name, int plsy, String nxb, String author, int pagenum, boolean new_product,
			double price, int sale, Date updated_at, Integer id);

	Optional<Product> findById(Integer id);

	@Query(value="select * from products where name@@ to_tsquery(?1)", nativeQuery = true)
	List<Product> findAllByName(String nameprd);

	@Query(value = "Select * from products where id_category = ?1", nativeQuery = true)
	List<Product> findAllByTL(List<Long> categoryid);

	@Query(value = "select distinct ca.id, name, plsy, nxb, author, pagenum, price, image, details,id_category, sale, highlight, new_product, updated_at, created_at from products p\r\n" + 
			"left join category ca on p.id_category = ca.id where 1=1 and name @@ to_tsquery(?1) and ca.id in (?2)", nativeQuery = true)
	List<Product> findAllByNameAndTL(String namesp, List<Long> categoryid);

}
