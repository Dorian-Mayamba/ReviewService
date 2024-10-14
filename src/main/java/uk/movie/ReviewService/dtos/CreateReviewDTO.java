package uk.movie.ReviewService.dtos;

public record CreateReviewDTO(float rating
,String reviewText, String username) {
}
