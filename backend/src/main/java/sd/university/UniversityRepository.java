package sd.university;

import org.springframework.data.repository.CrudRepository;

public interface UniversityRepository extends CrudRepository<University, Long>,
        UniversityRepositoryCustom {

}
