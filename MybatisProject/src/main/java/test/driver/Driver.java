package test.driver;

import test.utils.ExamSystem;
import java.io.IOException;


public class Driver {
    public static void main(String[] args) throws IOException {
        ExamSystem examSystem = new ExamSystem();
        examSystem.start();
    }
}
