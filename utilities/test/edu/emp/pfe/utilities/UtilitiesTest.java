package edu.emp.pfe.utilities;

import org.junit.jupiter.api.Test;

class UtilitiesTest {

    @Test
    void deleteDirectory() {
        String path = "/home/pfe21/Documents/cyber_range/vagrant_environments/prof_1/exo1/instance-1";
        System.out.println(Utilities.deleteDirectory(path));
    }


}