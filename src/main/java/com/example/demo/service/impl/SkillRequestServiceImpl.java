package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SkillRequest;
import com.example.demo.repository.SkillRequestRepository;
import com.example.demo.service.SkillRequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillRequestServiceImpl implements SkillRequestService {

    @Autowired
    private SkillRequestRepository skillRequestRepository;

    @Override
    public SkillRequest createRequest(SkillRequest request) {
        if (request.getUser() == null || request.getUser().getId() == null) {
            throw new IllegalArgumentException("User is required");
        }
        if (request.getSkill() == null || request.getSkill().getId() == null) {
            throw new IllegalArgumentException("Skill is required");
        }
        if (request.getUrgencyLevel() == null || request.getUrgencyLevel().trim().isEmpty()) {
            throw new IllegalArgumentException("Urgency level is required");
        }
        
        String urgencyLevel = request.getUrgencyLevel().toUpperCase();
        if (!urgencyLevel.matches("^(LOW|MEDIUM|HIGH|CRITICAL)$")) {
            throw new IllegalArgumentException("Urgency level must be LOW, MEDIUM, HIGH, or CRITICAL");
        }
        request.setUrgencyLevel(urgencyLevel);
        
        request.setActive(true);
        return skillRequestRepository.save(request);
    }

    @Override
    public SkillRequest getRequestById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid skill request ID");
        }
        return skillRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill request not found with id: " + id));
    }

    @Override
    public List<SkillRequest> getRequestsByUser(Long userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }
        return skillRequestRepository.findByUserId(userId);
    }

    @Override
    public List<SkillRequest> getAllRequests() {
        return skillRequestRepository.findAll();
    }

    @Override
    public SkillRequest updateRequest(Long id, SkillRequest requestDetails) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid skill request ID");
        }
        
        SkillRequest request = getRequestById(id);
        
        if (requestDetails.getUrgencyLevel() != null) {
            String urgencyLevel = requestDetails.getUrgencyLevel().toUpperCase().trim();
            if (urgencyLevel.isEmpty()) {
                throw new IllegalArgumentException("Urgency level cannot be empty");
            }
            if (!urgencyLevel.matches("^(LOW|MEDIUM|HIGH|CRITICAL)$")) {
                throw new IllegalArgumentException("Urgency level must be LOW, MEDIUM, HIGH, or CRITICAL");
            }
            request.setUrgencyLevel(urgencyLevel);
        }
        return skillRequestRepository.save(request);
    }

    @Override
    public void deactivateRequest(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid skill request ID");
        }
        
        SkillRequest request = getRequestById(id);
        request.setActive(false);
        skillRequestRepository.save(request);
    }
}