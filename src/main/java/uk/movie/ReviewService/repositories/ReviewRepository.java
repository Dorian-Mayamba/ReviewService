package uk.movie.ReviewService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.movie.ReviewService.entities.Review;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findReviewByMovieId(int movieId);

    Optional<Review> findReviewByUserIdAndMovieId(int userId, int movieId);
}
