package test.dao;

import test.vo.TestPaper;

import java.util.ArrayList;

public interface ITestPaperDao {

    ArrayList<TestPaper> getTestPaper();

    void updateTestPaperNum(int num);

    void updateTestPaperTime(String time);
}
