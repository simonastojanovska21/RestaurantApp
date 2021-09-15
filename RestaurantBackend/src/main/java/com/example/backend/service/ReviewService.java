package com.example.backend.service;

import com.example.backend.model.Review;
import com.example.backend.model.dto.ReviewDto;
import com.example.backend.model.exceptions.EmptyDataException;
import com.example.backend.model.exceptions.MissingFieldException;
import com.example.backend.model.exceptions.UserNotFoundException;

import java.util.Optional;

public interface ReviewService {
    Optional<Review> addNewReview(ReviewDto reviewDto) throws UserNotFoundException, MissingFieldException, EmptyDataException;
}
