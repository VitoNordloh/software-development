package sd.course;

import sd.search.SearchCriteria;

import java.util.List;

public interface CourseRepositoryCustom {
    List<Course> findByCriteria(SearchCriteria searchCriteria);
}
