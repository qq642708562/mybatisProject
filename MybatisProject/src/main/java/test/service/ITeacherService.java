package test.service;

import test.vo.User;


public interface ITeacherService {

    public void teacherOperation(User user);

    public void sqlSessionClose();

    public void reorderId();
}
