package edu.emp.pfe;

import edu.emp.pfe.model.*;
import edu.emp.pfe.model.provisioner.DockerProvisioner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VirtualEnvSample {

    static public List<Network> networks = new ArrayList<>(Arrays.asList(new Network(
            NetworkType.public_network,
            IpAssignment.dhcp
    )));

    static public List<DockerProvisioner> dockerProvisioners = new ArrayList<>(Arrays.asList(new DockerProvisioner(
            "vulnerables/web-dvwa",
            "mycontainer",
            "",
            "--rm -p 80:80"
    ), null, null));

    static public VirtualMachine cibleMachine = new VirtualMachine("cible",
            "boxWithDocker",
            "cible",
            "cible",
            networks,
            dockerProvisioners,
            null,
            null
    );

    static public VirtualMachine student1Machine = new VirtualMachine("student1",
            "hashicorp/bionic64",
            "student1",
            "student1",
            networks,
            null,
            null,
            null
    );

    static public List<VirtualMachine> virtualMachines = new ArrayList<>(Arrays.asList(cibleMachine, null, student1Machine));

    static public String vagrantEvnPath = "/home/chakib-user/Documents/cyber_range/vagrant_environments/prof_1/exo1/instance-1";
//    static public String vagrantEvnPath = "/home/pfe21/Documents/cyber_range/vagrant_environments/prof_1/exo1/instance-1";
    static public VirtualEnvironment virtualEnvironment = new VirtualEnvironment(virtualMachines,vagrantEvnPath);
}

