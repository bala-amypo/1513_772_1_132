package com.example.demo.service.impl;

import com.example.demo.model.SkillRequest;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SkillRequestServiceImpl implements SkillRequestService {
    @Override
    public SkillRequest createRequest(SkillRequest request) {
        return request;
    }
    
    @Override
    public SkillRequest getRequestById(Long id) {
        SkillRequest request = new SkillRequest();
        request.setId(id);
        return request;
    }
    
    @Override
    public List<SkillRequest> getRequestsByUser(Long userId) {
        return new ArrayList<>();
    }
}