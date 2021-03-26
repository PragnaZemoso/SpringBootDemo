package com.zemoso.springbootassignment.service;

import com.zemoso.springbootassignment.entity.Book;
import com.zemoso.springbootassignment.entity.Review;

import java.util.List;

public interface BookService {
    public List<Book> findAllBooks();

    public Book findById(int theId);

    public void save(Book theBook);

    public void deleteById(int theId);

    List<Book> findByKeyword(String keyword);

    List<Review> findReviewsByBookId(int theId);

    List<Review> findAllReviews();

    void saveReview(Review curReview);
}
