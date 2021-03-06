package com.zemoso.springbootassignment.dao;

import com.zemoso.springbootassignment.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "select * from book b where b.title like %:keyword% or b.author like %:keyword%", nativeQuery = true)
    List<Book> findByKeyword(@Param("keyword") String keyword);
}
