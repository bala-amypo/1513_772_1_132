package com.example.demo.service;

import com.example.demo.model.SkillRequest;
import java.util.List;

public interface SkillRequestService {
    SkillRequest createRequest(SkillRequest req);
    SkillRequest getRequestById(Long id);
    List<SkillRequest> getRequestsByUser(Long userId);
}
