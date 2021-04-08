package edu.emp.pfe.model;

import edu.emp.pfe.model.provisioner.AnsibleProvisioner;
import edu.emp.pfe.model.provisioner.DockerProvisioner;
import edu.emp.pfe.model.provisioner.ScriptProvisioner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class VirtualMachine {
    String vm_id;
    String boxName;
    String password;

    //these attributes are not null and don't contain any null value
    List<Network> networks;
    List<DockerProvisioner> dockerProvisioners;
    List<ScriptProvisioner> scriptProvisioners;
    List<AnsibleProvisioner> ansibleProvisioners;


    //todo check that the virtual machines ID's are unique

    public VirtualMachine(String vm_id, String boxName, String password) {
        this.vm_id = vm_id;
        this.boxName = boxName;
        this.password = password;
    }

    public VirtualMachine(String vm_id, String boxName, String password, String userName, List<Network> networks, List<DockerProvisioner> dockerProvisioners, List<ScriptProvisioner> scriptProvisioners, List<AnsibleProvisioner> ansibleProvisioners) {
        this.vm_id = vm_id;
        this.boxName = boxName;
        this.password = password;
        this.networks = networks;
        this.dockerProvisioners = dockerProvisioners;
        this.scriptProvisioners = scriptProvisioners;
        this.ansibleProvisioners = ansibleProvisioners;
        if (networks == null)
            this.networks = new ArrayList<>();
        if (dockerProvisioners == null)
            this.dockerProvisioners = new ArrayList<>();
        if (scriptProvisioners == null)
            this.scriptProvisioners = new ArrayList<>();
        if (ansibleProvisioners == null)
            this.ansibleProvisioners = new ArrayList<>();

        this.networks.removeAll(Collections.singletonList(null));
        this.dockerProvisioners.removeAll(Collections.singletonList(null));
        this.scriptProvisioners.removeAll(Collections.singletonList(null));
        this.ansibleProvisioners.removeAll(Collections.singletonList(null));
    }

    public List<Network> getNetworks() {
        return networks;
    }

    public void setNetworks(List<Network> networks) {
        this.networks = networks;
        if (networks == null)
            this.networks = new ArrayList<>();
        this.networks.removeAll(Collections.singletonList(null));
    }

    public String getVm_id() {
        return vm_id;
    }

    public void setVm_id(String vm_id) {
        this.vm_id = vm_id;
    }

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<DockerProvisioner> getDockerProvisioners() {
        return dockerProvisioners;
    }

    public void setDockerProvisioners(List<DockerProvisioner> dockerProvisioners) {
        this.dockerProvisioners = dockerProvisioners;
        if (dockerProvisioners == null)
            this.dockerProvisioners = new ArrayList<>();
        this.dockerProvisioners.removeAll(Collections.singletonList(null));

    }

    public List<ScriptProvisioner> getScriptProvisioners() {
        return scriptProvisioners;
    }

    public void setScriptProvisioners(List<ScriptProvisioner> scriptProvisioners) {
        this.scriptProvisioners = scriptProvisioners;
        if (scriptProvisioners == null)
            this.scriptProvisioners = new ArrayList<>();
        this.scriptProvisioners.removeAll(Collections.singletonList(null));
    }

    public void addScriptProvisioner(ScriptProvisioner scriptProvisioner) {
        if (scriptProvisioner != null) {
            this.scriptProvisioners.add(scriptProvisioner);
        }
    }

    public List<AnsibleProvisioner> getAnsibleProvisioners() {
        return ansibleProvisioners;
    }

    public void setAnsibleProvisioners(List<AnsibleProvisioner> ansibleProvisioners) {
        this.ansibleProvisioners = ansibleProvisioners;
        if (ansibleProvisioners == null)
            this.ansibleProvisioners = new ArrayList<>();
        this.ansibleProvisioners.removeAll(Collections.singletonList(null));

    }
}
