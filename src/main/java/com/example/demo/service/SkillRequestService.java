package com.example.demo.service;

import java.util.List;
import com.example.demo.model.SkillRequest;

public interface SkillRequestService {

    SkillRequest createRequest(SkillRequest request);

    SkillRequest updateRequest(Long id, SkillRequest request);

    SkillRequest getRequestById(Long id);

    List<SkillRequest> getRequestsByUser(Long userId);

    void deactivateRequest(Long id);
}