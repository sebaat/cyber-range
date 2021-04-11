package edu.emp.pfe.instances;

import edu.emp.pfe.model.SSH_information;
import edu.emp.pfe.model.VirtualEnvironment;
import edu.emp.pfe.model.VirtualMachine;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IpAddressesRetriever {
    VirtualEnvironment virtualEnvironment;

    private String path;

    public IpAddressesRetriever(VirtualEnvironment virtualEnvironment) {
        this.virtualEnvironment = virtualEnvironment;
        this.path = virtualEnvironment.getVagrantEnvPath();
    }

    public List<SSH_information> retrieveAll() {
        List<SSH_information> ssh_information = new ArrayList<>();
        for (VirtualMachine virtualMachine : virtualEnvironment.getVirtualMachines()) {
            SSH_information information = retrieve(virtualMachine);
            if (information != null) {
                ssh_information.add(information);
            }
        }
        return ssh_information;
    }

    public SSH_information retrieve(String vm_id) {
        for (VirtualMachine virtualMachine : virtualEnvironment.getVirtualMachines()) {
            if (virtualMachine.getVm_id().equals(vm_id)) {
                return retrieve(virtualMachine);
            }
        }
        return null;
    }

    public SSH_information retrieve(VirtualMachine virtualMachine) {
        SSH_information information = new SSH_information();

        try {
            // ssh to the virtual machine
            String command = "vagrant ssh " + virtualMachine.getVm_id();
            Process process = Runtime.getRuntime().exec(command,null,new File(path));

            // retrieve the ip address
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(process.getOutputStream()));
            //todo this command is os dependent
            writer.write("echo separator && ip -4 addr show eth1 | grep -oP \"(?<=inet ).*(?=/)\"");
            writer.close();

            // retrieve the standard error
            BufferedReader errReader = new BufferedReader(new InputStreamReader(
                    process.getErrorStream()));
            StringBuilder errOutput = new StringBuilder();
            String errLine;
            while ((errLine = errReader.readLine()) != null) {
                errOutput.append(errLine);
                System.out.println(errLine);
            }
            String errResult = errOutput.toString();
            System.out.println("erreur result \n" + errResult);

            if (!errResult.equals("")) {
                information.setValid(false);
                information.setErrorMsg(errResult);
                return information;
            }

            // retrieve the standard output
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
                System.out.println(line);
            }
            String result = output.toString();
            String ipAddress = result.substring(result.lastIndexOf("separator") + 9);

            information.setValid(true);
            information.setIpAddress(ipAddress);
            information.setPassword(virtualMachine.getPassword());
            information.setUserName(virtualMachine.getVm_id());

            process.waitFor();
            process.destroy();
            reader.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return information;
    }

}
