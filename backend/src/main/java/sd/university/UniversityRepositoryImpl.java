package sd.university;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import sd.search.SearchCriteria;

import java.util.ArrayList;
import java.util.List;

public class UniversityRepositoryImpl implements UniversityRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public UniversityRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<University> findByCriteria(SearchCriteria searchCriteria) {
        System.out.println(searchCriteria.location);
        List<University> results = new ArrayList<>();
        Query q = new Query();
        if(!searchCriteria.location.isEmpty()) {
            q.addCriteria(Criteria.where("location").regex(searchCriteria.location));
        }
        results.addAll(mongoTemplate.find(q, University.class));
        return results;
    }
}