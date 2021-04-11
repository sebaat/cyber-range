package edu.emp.pfe.instances;

import edu.emp.pfe.generation.VagrantEnvGenerator;
import edu.emp.pfe.model.SSH_information;
import edu.emp.pfe.utilities.system.Output;
import edu.emp.pfe.utilities.system.OutputReader;
import edu.emp.pfe.utilities.system.SystemCommand;
import edu.emp.pfe.model.VirtualEnvironment;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VagrantInstance implements Instance {
    VirtualEnvironment virtualEnvironment;

    private String path;

    public VagrantInstance(VirtualEnvironment virtualEnvironment) {
        this.virtualEnvironment = virtualEnvironment;
        this.path = virtualEnvironment.getVagrantEnvPath();
    }

    public VirtualEnvironment getVirtualEnvironment() {
        return virtualEnvironment;
    }

    @Override
    public void generate(OutputReader outputReader) {
        destroy(null, null); // we need to destroy the previous VM before generating a new vagrant environment
        new VagrantEnvGenerator(virtualEnvironment).generateVagrantEnv();
    }

    @Override
    public Output start(String vm_id, OutputReader outputReader) {
        if (vm_id == null) {
            vm_id = "";
        }
        return SystemCommand.exec("vagrant up " + vm_id, path, outputReader);
    }

    @Override

    public Output status(String vm_id, OutputReader outputReader) {
        if (vm_id == null) {
            vm_id = "";
        }
        return SystemCommand.exec("vagrant status " + vm_id, path, outputReader);
    }

    @Override
    public Output destroy(String vm_id, OutputReader outputReader) {
        if (vm_id == null) {
            vm_id = "";
        }
        return SystemCommand.exec("vagrant destroy -f " + vm_id, path, outputReader);
    }

    @Override
    public Output pause(String vm_id, OutputReader outputReader) {
        if (vm_id == null) {
            vm_id = "";
        }
        return SystemCommand.exec("vagrant suspend " + vm_id, path, outputReader);
    }

    @Override
    public Output halt(String vm_id, OutputReader outputReader) {
        if (vm_id == null) {
            vm_id = "";
        }
        return SystemCommand.exec("vagrant halt " + vm_id, path, outputReader);
    }


    public boolean validatePath(OutputReader outputReader) {
        return SystemCommand.exec("vagrant validate ", path, outputReader).getExitValue();
    }

    @Override
    public List<SSH_information> retrieveIpAddress(String vm_id) {
        IpAddressesRetriever ipAddressesRetriever = new IpAddressesRetriever(virtualEnvironment);
        if (vm_id == null)
            return ipAddressesRetriever.retrieveAll();

        List<SSH_information> ssh_information = new ArrayList<>();
        ssh_information.add(ipAddressesRetriever.retrieve(vm_id));
        return ssh_information;

    }


}
