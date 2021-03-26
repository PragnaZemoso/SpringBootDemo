package com.zemoso.springbootassignment.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    @NotNull(message = "'Title' is required")
    private String title;

    @Column(name = "author")
    @NotNull(message = "'Author' is required")
    private String author;

    @Column(name = "publisher")
    @NotNull(message = "'Publisher' is required")
    private String publisher;

    @Column(name = "pages")
    @NotNull(message = "'Pages' is required")
    @Min(value = 10, message = "We accept a book of min '10' pages")
    @Max(value = 999, message = "Maximum no of pages should be '999'")
    private Integer pages;

    @Column(name = "stock")
    @NotNull(message = "'Stock' is required")
    @Min(value = 1, message = "At least '1' book is required")
    @Max(value = 100, message = "Stock limit is '100' only")
    private Integer stock;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private List<Review> reviews;

    public Book() {
    }

    /**
     * Convenience method to add Reviews to Book
     */
    public void add(Review theReview) {
        if (reviews == null)
            reviews = new ArrayList<>();
        reviews.add(theReview);
    }


}
