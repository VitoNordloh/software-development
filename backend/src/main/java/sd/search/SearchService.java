package sd.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sd.university.University;
import sd.university.UniversityRepository;

import java.util.List;

@Service
public class SearchService {
    private UniversityRepository universities;

    @Autowired
    public SearchService(UniversityRepository universities) {
        this.universities = universities;
    }

    public List<University> search(SearchCriteria searchCriteria) {
        return universities.findByCriteria(searchCriteria);
    }
}
