package edu.emp.pfe.generation;

import edu.emp.pfe.model.VirtualEnvironment;
import edu.emp.pfe.model.VirtualMachine;
import edu.emp.pfe.model.provisioner.ScriptProvisioner;
import edu.emp.pfe.utilities.Utilities;

import java.util.List;

public class ScriptsGeneration {

    VirtualEnvironment virtualEnvironment;
    String vagrantEnvPath;

    public ScriptsGeneration(VirtualEnvironment virtualEnvironment, String vagrantEnvPath) {
        this.virtualEnvironment = virtualEnvironment;
        this.vagrantEnvPath = vagrantEnvPath;
    }

    @SuppressWarnings("SpellCheckingInspection")
    public void generateScripts() {

        for (VirtualMachine virtualMachine : virtualEnvironment.getVirtualMachines()) {
            String vm_id = virtualMachine.getVm_id();

            // don't forget the default script per eatch virtualMachine  ( user account and password configuration)
            String defaultScriptContent = "#!/usr/bin/env bash\n" +
                    "\n" +
//                "whoami\n" +
//                "echo -e \"vagrant4\\nvagrant4\" | passwd vagrant\n" +
//                "\n" +
                    "useradd -s /bin/bash -d /home/" + vm_id + "/ -m -G sudo " + vm_id + "\n" +
                    "echo -e \"" + vm_id + "\\n" + vm_id + "\" | passwd " + vm_id + "\n" +
//                "\n" +
//                "userdel -rf vagrant"
                    "";
            virtualMachine.addScriptProvisioner(new ScriptProvisioner(defaultScriptContent, vm_id + "_default.sh"));


            List<ScriptProvisioner> scriptProvisioners = virtualMachine.getScriptProvisioners();
            if (scriptProvisioners != null) {
                for (int i = 0; i < scriptProvisioners.size(); i++) {
                    String scriptPath;
                    if (scriptProvisioners.get(i) != null) {
                        scriptPath = vagrantEnvPath + "/" + scriptProvisioners.get(i).getScriptFileName();
                    } else
                        scriptPath = vagrantEnvPath + "/" + vm_id + "_" + i + ".sh";
                    Utilities.writeToFile(scriptPath, scriptProvisioners.get(i).getScript());
                }
            }
        }
    }
}
