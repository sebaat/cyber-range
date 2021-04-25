package edu.emp.pfe.utilities.system;

class SystemCommandTest {

    @org.junit.jupiter.api.Test
    void exec() {
        Output output = SystemCommand.exec("ls",null);
        System.out.println(output.toString());
    }
}