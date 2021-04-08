package edu.emp.pfe.instances;

import edu.emp.pfe.VirtualEnvSample2;
import org.junit.jupiter.api.Test;

import java.io.*;

class VagrantInstanceTest {

    VagrantInstance vagrantInstance = new VagrantInstance(VirtualEnvSample2.virtualEnvironment);
    @Test
    void generate() {
        vagrantInstance.generate( null);
    }

    @Test
    void start() {
        vagrantInstance.start(null, line -> System.out.println(line));
    }

    @Test
    void status() {
        vagrantInstance.status(null, line -> System.out.println(line));
    }

    @Test
    void destroy() {
        vagrantInstance.destroy(null, line -> System.out.println(line));
    }

    @Test
    void pause() {
        vagrantInstance.pause(null, line -> System.out.println(line));
    }

    @Test
    void halt() {
        vagrantInstance.halt(null, line -> System.out.println(line));
    }

    @Test
    void connectionInfo() {
        System.out.println(vagrantInstance.connectionInfo(null));
    }

    @Test
    void validatePath() {
        System.out.println(vagrantInstance.validatePath(line -> System.out.println(line)));
    }

    @Test
    void checkIfWeCanGetTheIpAddressOfAMachine() {
/*        System.out.println("-------------creating the vm-------------");
        vagrantInstance.generate(line -> System.out.println(line));
        vagrantInstance.start(vagrantInstance.getVirtualEnvironment().getVirtualMachines().get(1).getVm_id(),
                line -> System.out.println(line));
        System.out.println("-------------connection infos-------------");
*/
        String command = "vagrant ssh student1";
        String path = "/home/pfe21/Documents/cyber_range/vagrant_environments/prof_1/exo1/instance-1";

        try {
            Process process = Runtime.getRuntime().exec(command,null,new File(path));
//            PrintStream out = new PrintStream(process.getOutputStream());
//            out.println("ls -l /home");
//            out.close();
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
//                System.out.println(line);
            }
            String result = output.toString();
            System.out.println(result.substring(result.lastIndexOf("separator")+9));
            process.waitFor();
            process.destroy();
            reader.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}