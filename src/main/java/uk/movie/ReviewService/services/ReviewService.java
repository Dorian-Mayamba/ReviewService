package uk.movie.ReviewService.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uk.movie.ReviewService.dtos.ReviewDTO;
import uk.movie.ReviewService.dtos.UpdateReviewDTO;
import uk.movie.ReviewService.entities.Review;
import uk.movie.ReviewService.repositories.ReviewRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public List<Review> getReviews(int movieId){
        return reviewRepository.findReviewByMovieId(movieId);
    }

    public Review makeReview(int userId, int movieId, Review newReview) {
        Review review = reviewRepository.findReviewByUserIdAndMovieId(
                userId,movieId
        ).orElse(newReview);
        return reviewRepository.save(review);
    }

    public void updateReview(int reviewId, UpdateReviewDTO reviewDTO){
        Review reviewToUpdate = reviewRepository.findById(reviewId).orElseThrow(
                ()-> new IllegalStateException("Could not find a review with the id "+ reviewId)
        );
        reviewToUpdate.setRating(reviewDTO.rating());
        reviewToUpdate.setReviewText(reviewDTO.reviewText());
        reviewRepository.save(reviewToUpdate);
    }

    public void deleteReviewById(int reviewId){
        Review reviewToDelete = reviewRepository.findById(reviewId).orElseThrow(
                ()-> new IllegalStateException("Could not find a review with the id "+ reviewId)
        );
        reviewRepository.delete(reviewToDelete);
    }
}
