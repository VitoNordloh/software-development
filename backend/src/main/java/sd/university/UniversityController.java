package sd.university;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UniversityController {
    private UniversityRepository universities;

    @Autowired
    public UniversityController(UniversityRepository universities) {
        this.universities = universities;
    }

    @GetMapping("/universities/{id}")
    public University get(@PathVariable Long id) throws Exception {
        Optional<University> uni = universities.findById(id);
        if(uni.isPresent()) {
            return uni.get();
        } else {
            throw new Exception("University not found");
        }
    }
}
