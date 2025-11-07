package com.triggr.repository;

import com.triggr.model.SignupRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SignupRequestRepository extends MongoRepository<SignupRequest, String> {
}