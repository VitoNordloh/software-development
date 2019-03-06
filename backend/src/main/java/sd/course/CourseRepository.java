package sd.course;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Long>,
        CourseRepositoryCustom {
    List<Course> findByPubukprn(int pubukprn, Sort sort);
}

