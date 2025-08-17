package com.webproject.vswapp_backend.mapper;

import com.webproject.vswapp_backend.dto.FeedbackDto;
import com.webproject.vswapp_backend.entity.Feedback;

public class FeedbackMapper {

    // Entity → DTO
    public static FeedbackDto mapToFeedbackDto(Feedback feedback) {
        if (feedback == null) {
            return null;
        }
        return new FeedbackDto(
                feedback.getId(),
                feedback.getComment(),
                feedback.getAddDate()
        );
    }

    // DTO → Entity (without requiring all-args constructor)
    public static Feedback mapToFeedback(FeedbackDto dto) {
        if (dto == null) {
            return null;
        }
        Feedback feedback = new Feedback(); // use no-args constructor
        feedback.setId(dto.getId());
        feedback.setComment(dto.getComment());
        feedback.setAddDate(dto.getAddDate());
        return feedback;
    }
}
