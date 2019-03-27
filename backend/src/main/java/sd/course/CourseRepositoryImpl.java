package sd.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import sd.search.SearchCriteria;
import sd.university.University;

import java.util.ArrayList;
import java.util.List;

public class CourseRepositoryImpl implements CourseRepositoryCustom {
    private final transient MongoTemplate mongoTemplate;

    @Autowired
    public CourseRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Course> findByCriteria(SearchCriteria searchCriteria) {
        Query q = new Query();

        if(!searchCriteria.title.isEmpty()) {
            q.addCriteria(Criteria.where("title").regex(".*"+searchCriteria.title+".*"));
        }

        if(!searchCriteria.university.isEmpty()) {
            q.addCriteria(Criteria.where("university.name").regex(".*"+searchCriteria.university+".*"));
        }

        q.addCriteria(Criteria.where("employmentChances").gte(searchCriteria.employmentChances));
        q.addCriteria(Criteria.where("qualityOfLife").gte(searchCriteria.qualityOfLife));
        q.addCriteria(Criteria.where("firstClassRate").gte(searchCriteria.firstClassRate));
        q.addCriteria(Criteria.where("university.teachingExcellence").gte(searchCriteria.teachingExcellence));

        q.limit(50);
        q.with(new Sort(Sort.Direction.DESC, "rating"));
        return new ArrayList<>(mongoTemplate.find(q, Course.class));
    }
}