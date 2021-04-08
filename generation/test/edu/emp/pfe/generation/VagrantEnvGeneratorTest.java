package edu.emp.pfe.generation;

import edu.emp.pfe.VirtualEnvSample2;
import edu.emp.pfe.utilities.Utilities;
import org.junit.jupiter.api.Test;

class VagrantEnvGeneratorTest {

    @Test
    void generateVagrantEnv() {

        new VagrantEnvGenerator(VirtualEnvSample2.virtualEnvironment).generateVagrantEnv();

        String vagrantFilePath = VirtualEnvSample2.vagrantEvnPath + "/vagrantfile";
        System.out.println(Utilities.readFile(vagrantFilePath));
    }
}