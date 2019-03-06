package sd.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sd.course.Course;

import java.util.List;

@RestController
public class SearchController {
    private SearchService service;

    @Autowired
    public SearchController(SearchService service) {
        this.service = service;
    }

    @PostMapping("/search")
    public List<Course> search(@RequestBody SearchCriteria searchCriteria) {
        return service.search(searchCriteria);
    }
}
