package edu.emp.pfe.instances;

import edu.emp.pfe.model.SSH_information;
import edu.emp.pfe.model.VirtualEnvironment;
import edu.emp.pfe.model.VirtualMachine;

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


        return null;
    }

}
