package edu.emp.pfe.utilities.system;

import java.io.*;

public class SystemCommand {


    static public Output exec(String command, OutputReader reader) {
        return exec(command, System.getProperty("user.dir"), reader);
    }


    static public Output exec(String command, String path, OutputReader reader) {
        int exitValue = 1;
        String stdOut = "";
        String stdErr = "";

        try {
            Process process = Runtime.getRuntime().exec(command, null, new File(path));
            outputRecuperation stdOutRecuperationThread = new outputRecuperation(process.getInputStream(), reader);
            stdOutRecuperationThread.start();
            outputRecuperation stdErrRecuperationThread = new outputRecuperation(process.getErrorStream(), reader);
            stdErrRecuperationThread.start();

            exitValue = process.waitFor();
            process.destroy();
            stdOut = stdOutRecuperationThread.getOutput();
            stdErr = stdErrRecuperationThread.getOutput();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return new Output(stdOut, stdErr, exitValue, command);
    }
}

class outputRecuperation extends Thread {

    private InputStream flux;
    private OutputReader reader;
    private String output;

    public outputRecuperation(InputStream flux, OutputReader reader) {
        this.flux = flux;
        this.reader = reader;
        this.output = "";
    }

    @Override
    public void run() {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(flux);
            StringBuilder output = new StringBuilder();
            BufferedReader br = new BufferedReader(inputStreamReader);
            String line = null;
            while ((line = br.readLine()) != null) {
                if (reader != null) {
                    reader.read(line);
                }
                output.append(line + "\n");
            }
            this.output = output.toString();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public String getOutput() {
        return output;
    }
}
