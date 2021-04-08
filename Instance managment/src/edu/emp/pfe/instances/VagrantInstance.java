package edu.emp.pfe.instances;
import edu.emp.pfe.generation.VagrantEnvGenerator;
import edu.emp.pfe.utilities.system.Output;
import edu.emp.pfe.utilities.system.OutputReader;
import edu.emp.pfe.utilities.system.SystemCommand;
import edu.emp.pfe.model.VirtualEnvironment;

import java.io.*;

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

    @Override
    public String connectionInfo(String vm_id) {


        return null;
    }

    public boolean validatePath(OutputReader outputReader) {
        return SystemCommand.exec("vagrant validate ", path, outputReader).getExitValue();
    }

    private String retrieveIpAddress(String vm_id) {
        String result = "";
        try {
            String command = "vagrant ssh " + vm_id;
            Process process = Runtime.getRuntime().exec(command,null,new File(path));

            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(process.getOutputStream()));
            writer.write("echo separator && ip -4 addr show eth1 | grep -oP \"(?<=inet ).*(?=/)\"");
            writer.close();

            BufferedReader errReader = new BufferedReader(new InputStreamReader(
                    process.getErrorStream()));
            StringBuilder errOutput = new StringBuilder();
            String errLine;
            while ((errLine = errReader.readLine()) != null) {
                errOutput.append(errLine);
//                System.out.println(line);
            }
            String errResult = errOutput.toString();
            System.out.println("erreur result \n" + errResult);


            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
                System.out.println(line);
            }
            result = output.toString();
            System.out.println(result.substring(result.lastIndexOf("separator")+9));
            process.waitFor();
            process.destroy();
            reader.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }


}
