package com.example.demo.service.impl;

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
        request.setActive(true);
        return skillRequestRepository.save(request);
    }
    
    @Override
    public SkillRequest getRequestById(Long id) {
        Optional<SkillRequest> request = skillRequestRepository.findById(id);
        if (request.isPresent()) {
            return request.get();
        }
        throw new RuntimeException("SkillRequest not found with id: " + id);
    }
    
    @Override
    public List<SkillRequest> getRequestsByUser(Long userId) {
        return skillRequestRepository.findAll().stream()
            .filter(request -> request.getUser() != null && request.getUser().getId().equals(userId))
            .toList();
    }
    
    public List<SkillRequest> getAllRequests() {
        return skillRequestRepository.findAll();
    }
    
    public SkillRequest updateRequest(Long id, SkillRequest requestDetails) {
        SkillRequest request = getRequestById(id);
        if (requestDetails.getUrgencyLevel() != null) {
            request.setUrgencyLevel(requestDetails.getUrgencyLevel());
        }
        if (requestDetails.getSkill() != null) {
            request.setSkill(requestDetails.getSkill());
        }
        if (requestDetails.getUser() != null) {
            request.setUser(requestDetails.getUser());
        }
        return skillRequestRepository.save(request);
    }
    
    public void deleteRequest(Long id) {
        SkillRequest request = getRequestById(id);
        skillRequestRepository.delete(request);
    }
    
    public void softDeleteRequest(Long id) {
        SkillRequest request = getRequestById(id);
        request.setActive(false);
        skillRequestRepository.save(request);
    }
    
    public List<SkillRequest> getActiveRequests() {
        return skillRequestRepository.findAll().stream()
            .filter(SkillRequest::isActive)
            .toList();
    }
}