package uk.movie.ReviewService.dtos;

public record ReviewDTO (
        int id,
        int userId,
        int movieId,
        float rating,
        String reviewText,
        String username
){}
