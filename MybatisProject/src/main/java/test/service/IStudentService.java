package test.service;

import test.vo.TestPaper;
import test.vo.User;

public interface IStudentService {

    public void studentOperation(TestPaper testPaper, User user);

    public void sqlSessionClose();
}
