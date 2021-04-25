package edu.emp.pfe;

import edu.emp.pfe.model.*;
import edu.emp.pfe.model.provisioner.DockerProvisioner;

import java.util.*;

public class VirtualEnvSample2 {

    static public String vagrantEvnPath = VirtualEnvSample.vagrantEvnPath;
    static public VirtualEnvironment virtualEnvironment = VirtualEnvSample.virtualEnvironment;

    static {
        Map<String,String> DockerImagesPaths = new HashMap<>();
        Map<String,String> vagrantBoxPaths = new HashMap<>();
//        DockerImagesPaths.put("vulnerables/web-dvwa","/home/pfe21/Desktop/TP01/dockerimage");
        DockerImagesPaths.put("vulnerables/web-dvwa","/home/chakib-user/Desktop/exo01/dockerimage");
        vagrantBoxPaths.put("vagrantBox", "/home/chakib-user/Desktop/chakib/boxWithDocker");
        virtualEnvironment.setDockerImagesPaths(DockerImagesPaths);
        virtualEnvironment.setVagrantBoxesPaths(vagrantBoxPaths);
    }

}

