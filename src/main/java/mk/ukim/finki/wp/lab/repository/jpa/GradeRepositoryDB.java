package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface GradeRepositoryDB extends JpaRepository<Grade,Long> {

    List<Grade> findAllByTimestampBetween(LocalDateTime from, LocalDateTime to);

    List<Grade> getGradesByCourse(Course course);

    List<Grade> findAllByTimestampBefore(LocalDateTime to);
}
