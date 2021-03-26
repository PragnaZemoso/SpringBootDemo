package com.zemoso.springbootassignment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int reviewID;

    @Column(name = "comment")
    private String comment;

    @Column(name = "book_id")
    private int bookId;

    public Review(int reviewID, @Valid @NotNull(message = "Comment is required") @Size(min = 10, max = 200) String comment, int bookId) {
        this.reviewID = reviewID;
        this.comment = comment;
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewID=" + reviewID +
                ", comment='" + comment + '\'' +
                '}';
    }
}
