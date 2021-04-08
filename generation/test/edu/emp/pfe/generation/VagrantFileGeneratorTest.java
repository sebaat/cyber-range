package edu.emp.pfe.generation;

import edu.emp.pfe.VirtualEnvSample;
import org.junit.jupiter.api.Test;

class VagrantFileGeneratorTest {

    @Test
    void generateVagrantFile() {
        System.out.println(new VagrantFileGenerator(VirtualEnvSample.virtualEnvironment,false).generateVagrantFile());
    }
}