package uk.movie.ReviewService.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Timestamp;


@Table(name = "reviews")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Review {
    @Id
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "movie_id")
    private int movieId;
    @Column(name = "rating")
    private float rating;
    @Column(name = "review_text")
    private String reviewText;
    @Column(name = "username")
    private String username;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updated_at;
}
