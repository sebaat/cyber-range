package edu.emp.pfe.model.provisioner;


/**
 * It will create and start a container from a docker image.
 * If the docker image is not present in the system the provisioner will download it from docker hub
 */
public class DockerProvisioner {

    String imageName;

    String  containerName;

    /**
     * The command to start within the container.
     */
    String cmd = "";

    /**
     *  extra arguments for docker run on the command line.
     */
    String args = "";

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
        if (imageName == null || imageName == "")
            throw new NullPointerException("docker imageName must not be null ");
    }

    public DockerProvisioner(String imageName, String containerName, String cmd, String args) {
        this.imageName = imageName;
        this.containerName = containerName;
        this.cmd = cmd;
        this.args = args;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }
}
