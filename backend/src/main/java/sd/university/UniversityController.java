package sd.university;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sd.course.CourseRepository;

@RestController
public class UniversityController {
    private UniversityRepository universities;
    private CourseRepository courses;

    @Autowired
    public UniversityController(UniversityRepository universities, CourseRepository courses) {
        this.universities = universities;
        this.courses = courses;
    }

    @GetMapping("/universities/{ukprn}")
    public University get(@PathVariable int ukprn) throws Exception {
        University uni = universities.findByUkprn(ukprn);
        if(uni != null) {
            Sort sort = new Sort(Sort.Direction.DESC, "rating");
            uni.courses.addAll(courses.findByPubukprn(ukprn, sort));
            return uni;
        } else {
            throw new Exception("University not found");
        }
    }
}
