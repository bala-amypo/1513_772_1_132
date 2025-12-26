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
        throw new RuntimeException("Skill request not found with id: " + id);
    }
    
    @Override
    public List<SkillRequest> getRequestsByUser(Long userId) {
        return skillRequestRepository.findByUserId(userId);
    }
    
    public List<SkillRequest> getAllRequests() {
        return skillRequestRepository.findAll();
    }
    
    public SkillRequest updateRequest(Long id, SkillRequest requestDetails) {
        SkillRequest request = getRequestById(id); // This will throw "Skill request not found" if not found
        if (requestDetails.getUrgencyLevel() != null) {
            request.setUrgencyLevel(requestDetails.getUrgencyLevel());
        }
        return skillRequestRepository.save(request);
    }
    
    public void deactivateRequest(Long id) {
        SkillRequest request = getRequestById(id); // This will throw "Skill request not found" if not found
        request.setActive(false);
        skillRequestRepository.save(request);
    }
}