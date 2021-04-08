package edu.emp.pfe.instances;

import edu.emp.pfe.utilities.system.Output;
import edu.emp.pfe.model.VirtualEnvironment;
import edu.emp.pfe.utilities.system.OutputReader;

/**
 * for all methods with the vm_id parameter, if the vm_id is null
 * then the method will be applied on all virtual machines.
 */
public interface Instance {
    VirtualEnvironment virtualEnvironment = null;

    void generate(OutputReader outputReader);

    Output start(String vm_id, OutputReader outputReader);

    Output status(String vm_id, OutputReader outputReader);

    Output destroy(String vm_id, OutputReader outputReader);

    Output pause(String vm_id, OutputReader outputReader);

    Output halt(String vm_id, OutputReader outputReader);

    String connectionInfo(String vm_id);
}
