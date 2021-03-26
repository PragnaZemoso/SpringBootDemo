package com.zemoso.springbootassignment.service;

import com.zemoso.springbootassignment.dao.BookRepository;
import com.zemoso.springbootassignment.dao.ReviewRepository;
import com.zemoso.springbootassignment.entity.Book;
import com.zemoso.springbootassignment.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private ReviewRepository reviewRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, ReviewRepository reviewRepository) {
        this.bookRepository = bookRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(int theId) {
        Optional<Book> result = bookRepository.findById(theId);
        Book tempBook = null;
        if (result.isPresent())
            tempBook = result.get();
        else
            throw new RuntimeException("Book NOT FOUND with id: " + theId);
        return tempBook;
    }

    @Override
    public void save(Book theBook) {
        bookRepository.save(theBook);
    }

    @Override
    public void deleteById(int theId) {
        bookRepository.deleteById(theId);
    }

    // Get Books by keyword
    public List<Book> findByKeyword(String keyword) {
        return bookRepository.findByKeyword(keyword);
    }

    @Override
    public List<Review> findReviewsByBookId(int theId) throws RuntimeException {
        Optional<Book> theBook = bookRepository.findById(theId);
        List<Review> reviews = theBook.get().getReviews();
        return reviews;
    }

    @Override
    public List<Review> findAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public void saveReview(Review curReview) {
        reviewRepository.save(curReview);
    }
}
