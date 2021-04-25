package edu.emp.pfe.utilities.system;

/**
 * receive the output from the commandLine (line by line)
 */
@FunctionalInterface
public interface OutputReader {
    void read(String line);
}
