package edu.emp.pfe.generation;

import edu.emp.pfe.utilities.system.SystemCommand;
import edu.emp.pfe.model.VirtualEnvironment;
import edu.emp.pfe.model.VirtualMachine;
import edu.emp.pfe.model.provisioner.DockerProvisioner;
import edu.emp.pfe.model.provisioner.ScriptProvisioner;

import java.io.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DockerRequirementGeneration {

    VirtualEnvironment virtualEnvironment;

    String vagrantEnvPath;
    Map<String, String> dockerImagesPaths;
    Map<String, String> vagrantBoxesPaths;

    public DockerRequirementGeneration(VirtualEnvironment virtualEnvironment, String vagrantEnvPath) {
        this.virtualEnvironment = virtualEnvironment;
        this.vagrantEnvPath = vagrantEnvPath;
        this.dockerImagesPaths = virtualEnvironment.getDockerImagesPaths();
        this.vagrantBoxesPaths = virtualEnvironment.getVagrantBoxesPaths();

    }

    public void generateDockerRequirement() {
        String[] allDockerImageName = allDockerImageName();

    }


    //return a set that contain all Docker image name require by the virtual environment.
    private String[] allDockerImageName() {
        Set<String> allDockerImageNames = new HashSet<>(); // this set will contain the DockerImageNames required by all virtual machines.
        int i = 0;
        for (VirtualMachine virtualMachine : virtualEnvironment.getVirtualMachines()) {
            String vm_id = virtualMachine.getVm_id();
            for (DockerProvisioner dockerProvisioner : virtualMachine.getDockerProvisioners()) {
                String imageName = dockerProvisioner.getImageName();
                if (!allDockerImageNames.contains(imageName)) {
                    allDockerImageNames.add(imageName);
                    String imagePath = dockerImagesPaths.get(imageName);
                    if (imagePath == null)
                        continue;
                    if (new File(imagePath).canRead()) {
                        SystemCommand.exec("ln " + imagePath + " " + vagrantEnvPath + imagePath.substring(imagePath.lastIndexOf("/")),null).toString();
                        String loadScript = "#!/usr/bin/env bash\n" +
                                "docker load -i /vagrant/" + imagePath.substring(imagePath.lastIndexOf("/")+1);
                        virtualMachine.addScriptProvisioner( new ScriptProvisioner(loadScript, vm_id + "_" + "load_docker_image.sh"));
                    } else {
                        throw new RuntimeException("the docker image at path " + imagePath + "doesn't exist");
                    }
                }
            }
        }
        return allDockerImageNames.toArray(new String[allDockerImageNames.size()]);
    }
}
