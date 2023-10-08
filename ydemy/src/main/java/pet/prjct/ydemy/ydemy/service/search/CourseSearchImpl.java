package pet.prjct.ydemy.ydemy.service.search;

import java.util.ArrayList;
import java.util.List;

public class CourseSearchImpl implements CourseSearch {

    @Override
    public List<String> searchCourseByKeywords(String keywords) {
        String[] keywordsArray = keywords.toLowerCase().split(" ");

        return new ArrayList<>(List.of(keywordsArray));
    }
}
