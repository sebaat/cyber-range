package edu.emp.pfe.generation;

import edu.emp.pfe.model.VirtualEnvironment;
import edu.emp.pfe.utilities.Utilities;

public class VagrantEnvGenerator {
    String vagrantEnvPath;
    VirtualEnvironment virtualEnvironment;

    public VagrantEnvGenerator(VirtualEnvironment virtualEnvironment) {
        this.vagrantEnvPath = virtualEnvironment.getVagrantEnvPath();
        this.virtualEnvironment = virtualEnvironment;
    }

    public void generateVagrantEnv() {
        //delete the vagrant environment directory
        Utilities.deleteDirectory(vagrantEnvPath);

        //create the vagrant environment directory
        Utilities.createDirectory(vagrantEnvPath);

        // the calls order is important.

        //generation of the docker requirements
        new DockerRequirementGeneration(virtualEnvironment,vagrantEnvPath).generateDockerRequirement();

        //generation of the scripts files
        new ScriptsGeneration(virtualEnvironment,vagrantEnvPath).generateScripts();

        // generation of the vagrantFile
        new VagrantFileGenerator(virtualEnvironment,true).generateVagrantFile();
    }
}
