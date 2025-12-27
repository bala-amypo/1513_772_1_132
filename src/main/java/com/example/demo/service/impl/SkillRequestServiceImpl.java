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
        request.setActive(true);
        return skillRequestRepository.save(request);
    }

    @Override
    public SkillRequest getRequestById(Long id) {
        return skillRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill request not found " ));
    }

    @Override
    public List<SkillRequest> getRequestsByUser(Long userId) {
        return skillRequestRepository.findByUserId(userId);
    }

    @Override
    public List<SkillRequest> getAllRequests() {
        return skillRequestRepository.findAll();
    }

    @Override
    public SkillRequest updateRequest(Long id, SkillRequest requestDetails) {
        SkillRequest request = getRequestById(id);
        if (requestDetails.getUrgencyLevel() != null) {
            request.setUrgencyLevel(requestDetails.getUrgencyLevel());
        }
        return skillRequestRepository.save(request);
    }

    @Override
    public void deactivateRequest(Long id) {
        SkillRequest request = getRequestById(id);
        request.setActive(false);
        skillRequestRepository.save(request);
    }
}
