package edu.emp.pfe.generation;

import edu.emp.pfe.model.VirtualEnvironment;
import edu.emp.pfe.utilities.Utilities;
import edu.emp.pfe.utilities.system.SystemCommand;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

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

        copyDirectory(virtualEnvironment.getUploadPath(),vagrantEnvPath);

        // the calls order is important.

        //generation of the docker requirements
        new DockerRequirementGeneration(virtualEnvironment,vagrantEnvPath).generateDockerRequirement();

        //generation of the scripts files
        new ScriptsGeneration(virtualEnvironment,vagrantEnvPath).generateScripts();

        // generation of the vagrantFile
        new VagrantFileGenerator(virtualEnvironment,true).generateVagrantFile();
    }

    private void copyDirectory(String srcPath, String distPath) {

        File src = new File(srcPath);
        File dest = new File(distPath);
        try {
            FileUtils.copyDirectory(src, dest);
        } catch (IOException e) {
            System.out.println("can't copy upload directory to vagrant environment directory");
            e.printStackTrace();
        }

    }
}
