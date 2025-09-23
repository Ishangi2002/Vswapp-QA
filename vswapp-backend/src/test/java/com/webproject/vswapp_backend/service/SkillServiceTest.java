package com.webproject.vswapp_backend.service;

import com.webproject.vswapp_backend.dto.SkillDto;
import com.webproject.vswapp_backend.entity.Category;
import com.webproject.vswapp_backend.entity.User;
import com.webproject.vswapp_backend.repository.CategoryRepository;
import com.webproject.vswapp_backend.repository.SkillRepository;
import com.webproject.vswapp_backend.repository.UserRepository;
import com.webproject.vswapp_backend.service.impl.SkillServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SkillServiceTest {



    @Mock
    private SkillRepository skillRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private SkillServiceImpl skillService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateSkill_Success() {
        SkillDto skillDto = new SkillDto();
        skillDto.setTitle("React");
        skillDto.setLevel("Intermediate");
        skillDto.setAbout("Frontend developer");
        skillDto.setCategory("Programming");

        Long userId = 28L;

        // Mock User
        User mockUser = new User();
        mockUser.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        // Mock Category
        Category mockCategory = new Category();
        mockCategory.setCategoryName("Programming");
        when(categoryRepository.findByCategoryName("Programming")).thenReturn(Optional.of(mockCategory));

        // Mock save
        when(skillRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        SkillDto result = skillService.createSkill(skillDto, userId);

        assertNotNull(result);
        assertEquals("React", result.getTitle());
        verify(skillRepository, times(1)).save(any());
        verify(userRepository, times(1)).findById(userId);
        verify(categoryRepository, times(1)).findByCategoryName("Programming");
    }

    @Test
    void testCreateSkill_InvalidTitle() {
        SkillDto skillDto = new SkillDto();
        skillDto.setTitle(""); // Invalid
        skillDto.setLevel("Beginner");
        skillDto.setAbout("Frontend dev");
        skillDto.setCategory("Programming");

        Long userId = 28L;

        User mockUser = new User();
        mockUser.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        assertThrows(IllegalArgumentException.class, () -> {
            skillService.createSkill(skillDto, userId);
        });

        verify(skillRepository, never()).save(any());
    }
}
