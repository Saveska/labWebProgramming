package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepositoryDB;
import mk.ukim.finki.wp.lab.service.GradeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {
    private final GradeRepositoryDB gradeRepositoryDB;

    public GradeServiceImpl(GradeRepositoryDB gradeRepositoryDB) {
        this.gradeRepositoryDB = gradeRepositoryDB;
    }

    @Override
    public List<Grade> showGradesBetween(LocalDateTime from, LocalDateTime to) {
        return gradeRepositoryDB.findAllByTimestampBetween(from,to);
    }

    @Override
    public List<Grade> showAllGrades() {
        return gradeRepositoryDB.findAll();
    }

    @Override
    public List<Grade> showAllGradesBefore(LocalDateTime to) {
        return gradeRepositoryDB.findAllByTimestampBefore(to);
    }
}
