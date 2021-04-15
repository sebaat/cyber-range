package edu.emp.pfe.api;

import com.google.gson.Gson;
import edu.emp.pfe.VirtualEnvSample2;
import edu.emp.pfe.model.VirtualEnvironment;
import org.junit.jupiter.api.Test;

class EnvironmentTest {

    @Test
    void hello() {
        Gson gson = new Gson();
        String s = gson.toJson(VirtualEnvSample2.virtualEnvironment);
        System.out.printf(s);

        VirtualEnvironment virtualEnvironment = gson.fromJson(s, VirtualEnvironment.class);
    }

}