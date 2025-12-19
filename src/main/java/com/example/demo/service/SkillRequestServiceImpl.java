package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.SkillRequest;
import com.example.demo.repository.SkillRequestRepository;

@Service
public class SkillRequestServiceImpl implements SkillRequestService {

    private final SkillRequestRepository repository;

    public SkillRequestServiceImpl(SkillRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public SkillRequest createRequest(SkillRequest request) {
        return repository.save(request);
    }

    @Override
    public SkillRequest updateRequest(Long id, SkillRequest request) {
        request.setId(id);
        return repository.save(request);
    }

    @Override
    public SkillRequest getRequestById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<SkillRequest> getRequestsByUser(Long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public void deactivateRequest(Long id) {
        SkillRequest request = repository.findById(id).orElse(null);
        if (request != null) {
            request.setActive(false);
            repository.save(request);
        }
    }
}
