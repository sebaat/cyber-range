package edu.emp.pfe.model;

import java.io.File;
import java.util.*;

import static edu.emp.pfe.model.Provider.vagrant_virtualBox;

@SuppressWarnings("unused")
public class VirtualEnvironment {
    Provider provider;
    List<VirtualMachine> virtualMachines;
    String vagrantEnvPath;
    Map<String, String> dockerImagesPaths;
    Map<String, String> vagrantBoxesPaths;

    public VirtualEnvironment(List<VirtualMachine> virtualMachines, String vagrantEnvPath) {
        this.virtualMachines = virtualMachines;
        this.vagrantEnvPath = vagrantEnvPath;
        if (virtualMachines == null)
            this.virtualMachines = new ArrayList<>();
        this.virtualMachines.removeAll(Collections.singletonList(null));

        this.dockerImagesPaths = new HashMap<>();
        this.vagrantBoxesPaths = new HashMap<>();
    }

    public VirtualEnvironment(List<VirtualMachine> virtualMachines, String vagrantEnvPath, Map<String, String> dockerImagesPaths, Map<String, String> vagrantBoxesPaths) {
        this.virtualMachines = virtualMachines;
        this.provider = vagrant_virtualBox;
        this.vagrantEnvPath = vagrantEnvPath;
        this.dockerImagesPaths = dockerImagesPaths;
        this.vagrantBoxesPaths = vagrantBoxesPaths;

        if (virtualMachines == null)
            this.virtualMachines = new ArrayList<>();
        this.virtualMachines.removeAll(Collections.singletonList(null));
        if (dockerImagesPaths == null)
            this.dockerImagesPaths = new HashMap<>();
        this.dockerImagesPaths.values().removeAll(Collections.singleton(null));
        if (vagrantBoxesPaths == null)
            this.vagrantBoxesPaths = new HashMap<>();
        this.vagrantBoxesPaths.values().removeAll(Collections.singleton(null));

    }

    public String getVagrantEnvPath() {
        return vagrantEnvPath;
    }

    public void setVagrantEnvPath(String vagrantEnvPath) {
        this.vagrantEnvPath = vagrantEnvPath;
    }

    public Map<String, String> getDockerImagesPaths() {
        return dockerImagesPaths;
    }

    public void setDockerImagesPaths(Map<String, String> dockerImagesPaths) {
        this.dockerImagesPaths = dockerImagesPaths;
        if (dockerImagesPaths == null)
            this.dockerImagesPaths = new HashMap<>();
        this.dockerImagesPaths.values().removeAll(Collections.singleton(null));
    }

    public Map<String, String> getVagrantBoxesPaths() {
        return vagrantBoxesPaths;
    }

    public void setVagrantBoxesPaths(Map<String, String> vagrantBoxesPaths) {
        this.vagrantBoxesPaths = vagrantBoxesPaths;
        if (vagrantBoxesPaths == null)
            this.vagrantBoxesPaths = new HashMap<>();
        this.vagrantBoxesPaths.values().removeAll(Collections.singleton(null));
    }

    public List<VirtualMachine> getVirtualMachines() {
        return virtualMachines;
    }

    public void setVirtualMachines(List<VirtualMachine> virtualMachines) {
        this.virtualMachines = virtualMachines;
        if (virtualMachines == null)
            this.virtualMachines = new ArrayList<>();
        this.virtualMachines.removeAll(Collections.singletonList(null));
    }

}
