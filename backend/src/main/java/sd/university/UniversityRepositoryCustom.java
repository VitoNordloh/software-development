package sd.university;

import sd.search.SearchCriteria;

import java.util.List;

public interface UniversityRepositoryCustom {
    public List<University> findByCriteria(SearchCriteria searchCriteria);
}
