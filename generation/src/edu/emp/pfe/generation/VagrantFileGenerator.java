package edu.emp.pfe.generation;

import edu.emp.pfe.model.*;
import edu.emp.pfe.model.provisioner.DockerProvisioner;
import edu.emp.pfe.model.provisioner.ScriptProvisioner;
import edu.emp.pfe.utilities.Utilities;

@SuppressWarnings({"StringConcatenationInLoop", "unused"})
public class VagrantFileGenerator {

    VirtualEnvironment virtualEnvironment;
    String vagrantEnvPath;

    String vagrantFileContent = "";
    boolean fileCreation;

    public VagrantFileGenerator(VirtualEnvironment virtualEnvironment, boolean fileCreation) {
        this.virtualEnvironment = virtualEnvironment;
        this.vagrantEnvPath = virtualEnvironment.getVagrantEnvPath();
        this.fileCreation = fileCreation;
    }

    public String generateVagrantFile() {
        if (virtualEnvironment.getVirtualMachines().size() == 0)
            return "";
        vagrantFileContent = "Vagrant.configure(\"2\") do |config|\n\n";
        generatePerVM();
        vagrantFileContent += "end";
        if (fileCreation) {
            Utilities.writeToFile(vagrantEnvPath + "/vagrantfile", vagrantFileContent);
        }
        return vagrantFileContent;
    }

    private void generatePerVM() {
        for (VirtualMachine virtualMachine : virtualEnvironment.getVirtualMachines()) {
            String boxName = virtualMachine.getBoxName();
            String vm_id = virtualMachine.getVm_id();

            vagrantFileContent += "\tconfig.vm.define \"" + vm_id + "\" do |" + vm_id + "|\n";
            vagrantFileContent += "\t\t" + vm_id + ".vm.box = " + "\"" + boxName + "\"\n";
            vagrantFileContent += "\t\t" + vm_id + ".vm.hostname = " + "\"" + vm_id + "\"\n";
            scriptProvisioning(virtualMachine);
            dockerProvisioning(virtualMachine);
            ansibleProvisioning(virtualMachine);
            networkConfiguration(virtualMachine);
            vagrantFileContent += "\tend\n\n";
        }
    }

    private void networkConfiguration(VirtualMachine virtualMachine) {
        if (virtualMachine.getNetworks().size() != 0) {
            String vm_id = virtualMachine.getVm_id();
            for (Network network : virtualMachine.getNetworks()) {
                String networkType;
                if (network.getNetworkType() == NetworkType.public_network)
                    networkType = "public_network";
                else
                    networkType = "private_network";
                if (network.getIpAssignment() == IpAssignment.dhcp)
                    vagrantFileContent += "\t\t" + vm_id + ".vm.network \"" + networkType + "\", type: \"dhcp\", bridge: \"usb0\"\n";
                if (network.getIpAssignment() == IpAssignment.static_ip)
                    vagrantFileContent += "\t\t" + vm_id + ".vm.network \"" + networkType + "\", ip:  \"" + network.getIpAddress() + "\", bridge: \"usb0\"\n";
            }
        }
    }

    private void scriptProvisioning(VirtualMachine virtualMachine) {
        String vm_id = virtualMachine.getVm_id();

        if (virtualMachine.getScriptProvisioners().size() != 0) {
            for (int i = 0; i < virtualMachine.getScriptProvisioners().size(); i++) {
                ScriptProvisioner scriptProvisioner = virtualMachine.getScriptProvisioners().get(i);
                if (scriptProvisioner.getScriptFileName() == null)
                    vagrantFileContent += "\t\t" + vm_id + ".vm.provision \"shell\", path: \"" + vm_id + "_" + i + ".sh\"\n";
                else
                    vagrantFileContent += "\t\t" + vm_id + ".vm.provision \"shell\", path: \"" + scriptProvisioner.getScriptFileName() + "\"\n";

            }
        }
    }

    private void dockerProvisioning(VirtualMachine virtualMachine) {
        String vm_id = virtualMachine.getVm_id();
        if (virtualMachine.getDockerProvisioners().size() != 0) {

            vagrantFileContent += "\t\t" + vm_id + ".vm.provision \"docker\" do |d|\n";
            for (DockerProvisioner dockerProvisioner : virtualMachine.getDockerProvisioners()) {

                String containerName = dockerProvisioner.getContainerName();
                if (containerName != null && !containerName.equals("")) {
                    vagrantFileContent += "\t\t\td.run \"" + containerName + "\",\n";
                    //image parameter
                    vagrantFileContent += "\t\t\t  image: \"" + dockerProvisioner.getImageName() + "\",\n";

                } else {
                    //run command
                    vagrantFileContent += "\t\t\td.run \"" + dockerProvisioner.getImageName() + "\",\n";
                }

                //arg argument
                vagrantFileContent += "\t\t\t  args: \"" + dockerProvisioner.getArgs() + "\",\n";
                //cmd argument
                vagrantFileContent += "\t\t\t  cmd: \"" + dockerProvisioner.getCmd() + "\",\n";
                // restart argument : by default is no
                vagrantFileContent += "\t\t\t  restart: \"no\"\n";
            }
            vagrantFileContent += "\t\tend\n";
        }

    }

    private void ansibleProvisioning(VirtualMachine virtualMachine) {
        String vm_id = virtualMachine.getVm_id();
        if (virtualMachine.getAnsibleProvisioners().size() != 0) {
        }
    }
}
