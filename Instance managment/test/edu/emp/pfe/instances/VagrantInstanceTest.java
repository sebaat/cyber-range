package edu.emp.pfe.instances;

import edu.emp.pfe.VirtualEnvSample2;
import edu.emp.pfe.VirtualEnvTestSenario;
import edu.emp.pfe.model.SSH_information;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Scanner;

class VagrantInstanceTest {

//    VagrantInstance vagrantInstance = new VagrantInstance(VirtualEnvSample2.virtualEnvironment);
    VagrantInstance vagrantInstance = new VagrantInstance(VirtualEnvTestSenario.virtualEnvironment);

    @Test
    void generate() {
        vagrantInstance.generate(line -> System.out.println(line));
    }

    @Test
    void start() {
        vagrantInstance.start(null, line -> System.out.println(line));
    }

    @Test
    void status() {
        vagrantInstance.status(null, line -> System.out.println(line));
    }

    @Test
    void destroy() {
        vagrantInstance.destroy(null, line -> System.out.println(line));
    }

    @Test
    void pause() {
        vagrantInstance.pause(null, line -> System.out.println(line));
    }

    @Test
    void halt() {
        vagrantInstance.halt(null, line -> System.out.println(line));
    }

    @Test
    void validatePath() {
        System.out.println(vagrantInstance.validatePath(line -> System.out.println(line)));
    }

    @Test
    void retrieveIpAddress() {
//        System.out.println("-------------creating the vm-------------");
//        vagrantInstance.status(null, line -> System.out.println(line));
//        vagrantInstance.generate(line -> System.out.println(line));
//        vagrantInstance.start(null, line -> System.out.println(line));
        System.out.println("-------------connection infos-------------\n");
        List<SSH_information> ssh_information = vagrantInstance.retrieveIpAddress(null);
        for (SSH_information information : ssh_information)
            System.out.println(information.toString() + "\n");
    }

    private boolean getConfirmation() {
        boolean valid = false;
        while (true) {
            System.out.println("do you want to create the virtual environment [y/n]: ");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            switch (answer) {
                case "YES":
                case "Yes":
                case "yes":
                case "y":
                    return true;

                case "NO":
                case "No":
                case "no":
                case "n":
                    return false;
            }
        }
    }
}