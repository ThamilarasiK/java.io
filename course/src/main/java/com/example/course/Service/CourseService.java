package com.example.course.Service;

import com.example.course.Entity.Course;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private List<Course> courses = new ArrayList<>();
    private Long nextId = 1L;

    public List<Course> getAllCourses() {
        return courses;
    }

    public Optional<Course> getCourseById(Long id) {
        return courses.stream().filter(course -> course.getId().equals(id)).findFirst();
    }

    public Course addCourse(Course course) {
        course.setId(nextId++);
        courses.add(course);
        return course;
    }

    public Optional<Course> updateCourse(Long id, Course updatedCourse) {
        Optional<Course> existingCourse = getCourseById(id);
        existingCourse.ifPresent(course -> {
            course.setTitle(updatedCourse.getTitle());
            course.setDescription(updatedCourse.getDescription());
            course.setDuration(updatedCourse.getDuration());
        });
        return existingCourse;
    }

    public boolean deleteCourse(Long id) {
        return courses.removeIf(course -> course.getId().equals(id));
    }
}
