package com.zemoso.springbootassignment.controller;

import com.zemoso.springbootassignment.entity.Book;
import com.zemoso.springbootassignment.entity.Review;
import com.zemoso.springbootassignment.service.BookService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * add initBinder() method to trim all white spaces
     * resolve whitespace validation issue
     *
     * @param theWebDataBinder
     * @return
     * @InitBinder - pre-process every String form data
     */
    @InitBinder
    public void initBinder(WebDataBinder theWebDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        theWebDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/list")
    public String showAllBooks(Model theModel, String keyword) {
        if (keyword != null) {
            theModel.addAttribute("booksMA", bookService.findByKeyword(keyword));
        } else {
            theModel.addAttribute("booksMA", bookService.findAllBooks());
        }
        return "books/list-books";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Book newBook = new Book();
        theModel.addAttribute("bookMA", newBook);
        return "books/book-entry-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("bookMA") Book theBook, BindingResult theBindingResult) {
        //System.out.println("BBBBBBBBBBBB" + theBindingResult.hasErrors());
        if (theBindingResult.hasErrors())
            return "books/book-entry-form";
        else {
            bookService.save(theBook);
            return "redirect:/books/list";
        }
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("bookId") int theId, Model theModel) {

        Book theBook = bookService.findById(theId);

        theModel.addAttribute("bookMA", theBook);

        return "books/book-entry-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("bookId") int theId) {

        bookService.deleteById(theId);

        return "redirect:/books/list";
    }


    @GetMapping("/reviews")
    public String showAllReviews(Model theModel) {
        List<Review> reviews = bookService.findAllReviews();
        theModel.addAttribute("reviews", reviews);
        return "books/list-reviews";
    }

    @GetMapping("/reviews-by-bookId")
    public String showAllReviewsByBookId(Model theModel, @RequestParam("bookId") int theId) throws RuntimeException {
        List<Review> reviews = bookService.findReviewsByBookId(theId);
        theModel.addAttribute("bookId", theId);

        String bookTitle = bookService.findById(theId).getTitle();
        theModel.addAttribute("bookTitle",bookTitle);

        if (reviews.isEmpty())
            return "reviews-not-found";
        theModel.addAttribute("reviews", reviews);
        return "books/list-reviews-bookId";
    }

    @GetMapping("/showFormToGiveReview")
    public String showFormToGiveReview(@RequestParam("bookId") int theId, Model theModel) {
        String bookTitle = bookService.findById(theId).getTitle();
        theModel.addAttribute("bookTitle",bookTitle);

        theModel.addAttribute("bookId", theId);
        return "books/review-entry-form";
    }

    @PostMapping("/reviews/save")
    public String saveReview(@RequestParam("bookId") Integer theBookId, String comment) {
        System.out.println("bbbbbbbb" + theBookId);
        System.out.println("CCCCCCCCCCCCC" + comment);
        Review curReview = new Review(0, comment, theBookId);
        bookService.saveReview(curReview);
        return "redirect:/books/reviews";
    }
}