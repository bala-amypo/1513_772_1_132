package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SkillRequest;
import com.example.demo.repository.SkillRequestRepository;
import com.example.demo.service.SkillRequestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillRequestServiceImpl implements SkillRequestService {

    private final SkillRequestRepository repo;

    public SkillRequestServiceImpl(SkillRequestRepository repo) {
        this.repo = repo;
    }

    @Override
    public SkillRequest createRequest(SkillRequest request) {
        return repo.save(request);
    }

    @Override
    public SkillRequest getRequestById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found"));
    }

    @Override
    public List<SkillRequest> getAllRequests() {
        return repo.findAll();
    }

    @Override
    public List<SkillRequest> getRequestsByUser(Long userId) {
        return repo.findByUserId(userId);
    }

    @Override
    public List<SkillRequest> getRequestsByCategory(Long categoryId) {
        return repo.findByCategoryId(categoryId);
    }

    @Override
    public List<SkillRequest> getOpenRequests() {
        return repo.findByOpenTrue();
    }
}
