package edu.emp.pfe.utilities.system;

public class Output {
    String command;
    String stdOut;
    String stdErr;
    int exitValue;

    public Output(String stdOut, String stdErr, int exitValue,  String command) {
        this.command = command;
        this.stdOut = stdOut;
        this.stdErr = stdErr;
        this.exitValue = exitValue;
    }

    public String getCommand() {
        return command;
    }

    public String getStdOut() {
        return stdOut;
    }

    public String getStdErr() {
        return stdErr;
    }

    public boolean getExitValue() {
        return exitValue == 0;
    }

    @Override
    public String toString() {
        String s ="command: " + command + "\n" +
                "------------------------- stdOut -------------------------\n"
                + stdOut + "\n" +
                "------------------------- stdErr -------------------------\n"
                + stdErr + "\n" +
                "------------------------- exitValue ----------------------\n"
                + exitValue;
        return s;

    }
}
