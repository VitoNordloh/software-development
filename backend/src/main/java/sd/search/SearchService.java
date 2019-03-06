package sd.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sd.course.Course;
import sd.course.CourseRepository;

import java.util.List;

@Service
public class SearchService {
    private CourseRepository courses;

    @Autowired
    public SearchService(CourseRepository courses) {
        this.courses = courses;
    }

    public List<Course> search(SearchCriteria searchCriteria) {
        return courses.findByCriteria(searchCriteria);
    }
}
