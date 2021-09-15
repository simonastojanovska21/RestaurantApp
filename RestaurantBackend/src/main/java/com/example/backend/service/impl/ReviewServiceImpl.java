package com.example.backend.service.impl;

import com.example.backend.model.Review;
import com.example.backend.model.User;
import com.example.backend.model.dto.ReviewDto;
import com.example.backend.model.exceptions.EmptyDataException;
import com.example.backend.model.exceptions.InvalidUsernameException;
import com.example.backend.model.exceptions.MissingFieldException;
import com.example.backend.model.exceptions.UserNotFoundException;
import com.example.backend.repository.ReviewRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Optional<Review> addNewReview(ReviewDto reviewDto) throws UserNotFoundException, MissingFieldException, EmptyDataException {
        if(reviewDto.getStars()==null || reviewDto.getUsername() ==null || reviewDto.getDescription()==null)
            throw new MissingFieldException();
        if(reviewDto.getStars() ==0 || reviewDto.getUsername().isEmpty() || reviewDto.getDescription().isEmpty() )
            throw new EmptyDataException();
        User user=this.userRepository.findByUsername(reviewDto.getUsername()).orElseThrow(UserNotFoundException::new);
        Review review= new Review(reviewDto.getStars(),reviewDto.getDescription(),user);
        return Optional.of(this.reviewRepository.save(review));
    }
}
