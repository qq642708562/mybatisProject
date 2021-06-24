package test.dao;

import test.vo.GradeTable;

import java.util.ArrayList;

public interface IGradeTableDao {

    ArrayList<GradeTable> findAllGradeTable();

    GradeTable findGradeTableByName(String name);

    void insertGrade(GradeTable gradeTable);

    void updateGrade(GradeTable gradeTable);

    void deleteGradeByName(String name);
}
