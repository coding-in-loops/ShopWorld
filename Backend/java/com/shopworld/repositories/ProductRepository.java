package com.shopworld.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopworld.entities.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByIsActiveTrue();

	Page<Product> findByIsActiveTrue(Pageable pageable);

	List<Product> findByCategory(String category);

	List<Product> findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCase(String ch, String ch2);

	Page<Product> findByCategory(Pageable pageable, String category);

	Page<Product> findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCase(String ch, String ch2,
			Pageable pageable);

	Page<Product> findByisActiveTrueAndTitleContainingIgnoreCaseOrCategoryContainingIgnoreCase(String ch, String ch2,
			Pageable pageable);
}
