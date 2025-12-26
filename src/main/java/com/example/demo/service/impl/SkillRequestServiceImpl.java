package com.example.demo.service.impl;

import com.example.demo.exception.*;
import com.example.demo.model.SkillRequest;
import com.example.demo.repository.SkillRequestRepository;
import com.example.demo.service.SkillRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SkillRequestServiceImpl implements SkillRequestService {
    
    @Autowired
    private SkillRequestRepository skillRequestRepository;
    
    @Override
    public SkillRequest createRequest(SkillRequest request) {
        // Validate required fields
        if (request.getUser() == null || request.getUser().getId() == null) {
            throw new ValidationException("User is required for skill request");
        }
        if (request.getSkill() == null || request.getSkill().getId() == null) {
            throw new ValidationException("Skill is required for skill request");
        }
        if (request.getUrgencyLevel() == null || request.getUrgencyLevel().trim().isEmpty()) {
            throw new ValidationException("Urgency level is required");
        }
        
        request.setActive(true);
        return skillRequestRepository.save(request);
    }
    
    @Override
    public SkillRequest getRequestById(Long id) {
        Optional<SkillRequest> request = skillRequestRepository.findById(id);
        if (request.isPresent()) {
            if (!request.get().isActive()) {
                throw new ResourceInactiveException("SkillRequest with ID " + id + " is deactivated");
            }
            return request.get();
        }
        throw new ResourceNotFoundException("SkillRequest not found with id: " + id);
    }
    
    @Override
    public List<SkillRequest> getRequestsByUser(Long userId) {
        return skillRequestRepository.findAll().stream()
            .filter(req -> req.getUser() != null && req.getUser().getId().equals(userId))
            .filter(SkillRequest::isActive)
            .toList();
    }
    
    public List<SkillRequest> getAllRequests() {
        return skillRequestRepository.findAll();
    }
    
    public List<SkillRequest> getActiveRequests() {
        return skillRequestRepository.findAll().stream()
            .filter(SkillRequest::isActive)
            .toList();
    }
    
    public SkillRequest updateRequest(Long id, SkillRequest requestDetails) {
        SkillRequest request = skillRequestRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("SkillRequest not found with id: " + id));
            
        if (!request.isActive()) {
            throw new ResourceInactiveException("Cannot update deactivated skill request with ID " + id);
        }
        
        if (requestDetails.getUrgencyLevel() != null) {
            request.setUrgencyLevel(requestDetails.getUrgencyLevel());
        }
        
        return skillRequestRepository.save(request);
    }
    
    public void deleteRequest(Long id) {
        SkillRequest request = skillRequestRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("SkillRequest not found with id: " + id));
        
        skillRequestRepository.delete(request);
    }
    
    public void deactivateRequest(Long id) {
        SkillRequest request = skillRequestRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("SkillRequest not found with id: " + id));
            
        if (!request.isActive()) {
            throw new ResourceInactiveException("SkillRequest with ID " + id + " is already deactivated");
        }
        
        request.setActive(false);
        skillRequestRepository.save(request);
    }
}