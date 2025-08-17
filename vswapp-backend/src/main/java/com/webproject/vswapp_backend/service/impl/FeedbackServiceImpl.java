package com.webproject.vswapp_backend.service.impl;

import com.webproject.vswapp_backend.dto.FeedbackDto;
import com.webproject.vswapp_backend.entity.Feedback;
import com.webproject.vswapp_backend.exception.ResourceNotFoundException;
import com.webproject.vswapp_backend.mapper.FeedbackMapper;
import com.webproject.vswapp_backend.service.FeedbackService;
import com.webproject.vswapp_backend.repository.FeedbackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private FeedbackRepository feedbackRepository;

    @Override
    public FeedbackDto createFeedback(FeedbackDto feedbackDto) {
        Feedback feedback = FeedbackMapper.mapToFeedback(feedbackDto);
        Feedback savedFeedback = feedbackRepository.save(feedback);
        return FeedbackMapper.mapToFeedbackDto(savedFeedback);
    }

    @Override
    public FeedbackDto getFeedbackById(Long feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback not found for id: " + feedbackId));
        return FeedbackMapper.mapToFeedbackDto(feedback);
    }

    @Override
    public List<FeedbackDto> getAllFeedback() {
        List<Feedback> feedbackList = feedbackRepository.findAll();
        return feedbackList.stream()
                .map(FeedbackMapper::mapToFeedbackDto)
                .collect(Collectors.toList());
    }

    @Override
    public FeedbackDto updateFeedback(Long feedbackId, FeedbackDto updatedFeedback) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback not found for id: " + feedbackId));

        feedback.setComment(updatedFeedback.getComment());
        feedback.setAddDate(updatedFeedback.getAddDate());

        Feedback updatedFeedbackObj = feedbackRepository.save(feedback);
        return FeedbackMapper.mapToFeedbackDto(updatedFeedbackObj);
    }


    @Override
    public void deleteFeedback(Long feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback not found for id: " + feedbackId));

        feedbackRepository.deleteById(feedbackId);
    }
}
