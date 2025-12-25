package com.example.demo.service.impl;

import com.example.demo.model.SkillRequest;
import com.example.demo.service.SkillRequestService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillRequestServiceImpl implements SkillRequestService {

    private final List<SkillRequest> requests = new ArrayList<>();

    @Override
    public SkillRequest createRequest(SkillRequest req) {
        requests.add(req);
        return req;
    }

    @Override
    public SkillRequest getRequestById(Long id) {
        return requests.stream().filter(r -> r.getId() != null && r.getId().equals(id))
                .findFirst().orElseThrow(() -> new RuntimeException("Request not found"));
    }

    @Override
    public List<SkillRequest> getRequestsByUser(Long userId) {
        List<SkillRequest> result = new ArrayList<>();
        for (SkillRequest r : requests) {
            if (r.getUser() != null && r.getUser().getId().equals(userId)) result.add(r);
        }
        return result;
    }
}
