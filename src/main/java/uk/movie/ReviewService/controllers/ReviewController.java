package uk.movie.ReviewService.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uk.movie.ReviewService.dtos.CreateReviewDTO;
import uk.movie.ReviewService.dtos.ReviewDTO;
import uk.movie.ReviewService.dtos.UpdateReviewDTO;
import uk.movie.ReviewService.entities.Review;
import uk.movie.ReviewService.services.ReviewService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/reviews")
public class ReviewController {
    private final ModelMapper modelMapper;
    private final ReviewService reviewService;

    @Operation(summary = "Get review method")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get the list of reviews",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Review.class)
                            ))
                            }
            ),
            @ApiResponse(responseCode = "500", description = "An error has occurred",
                    content = @Content),
    })
    @GetMapping("{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewDTO> findAllById(@PathVariable int movieId){
        List<Review> reviews = reviewService.getReviews(movieId);
        return modelMapper.map(reviews, new TypeToken<List<ReviewDTO>>() {}.getType());
    }

    @Operation(summary = "Create review method")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Make a review to a movie",
                    content = { @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Review.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "500", description = "An error has occurred",
                    content = @Content),
    })
    @PostMapping("{userId}/{movieId}")
    public ReviewDTO makeReview(@PathVariable int userId, @PathVariable int movieId, @RequestBody CreateReviewDTO createReviewDTO){
        Review newReview = modelMapper.map(createReviewDTO, Review.class);
        newReview.setUserId(userId);
        newReview.setMovieId(movieId);
        return modelMapper.map(reviewService.makeReview(userId,movieId,newReview), ReviewDTO.class);
    }

    @Operation(summary = "Update a review method")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update a movie review"
            ),
            @ApiResponse(responseCode = "500", description = "An error has occurred",
                    content = @Content),
    })
    @PutMapping("{reviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateReview(@PathVariable int reviewId, @RequestBody UpdateReviewDTO updateReviewDTO){
        reviewService.updateReview(reviewId,updateReviewDTO);
    }

    @Operation(summary = "Delete a review method")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete a movie review"
            ),
            @ApiResponse(responseCode = "500", description = "An error has occurred",
                    content = @Content),
    })
    @DeleteMapping("{reviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReview(@PathVariable int reviewId){
        reviewService.deleteReviewById(reviewId);
    }

}
