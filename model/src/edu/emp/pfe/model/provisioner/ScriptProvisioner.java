package edu.emp.pfe.model.provisioner;

public class ScriptProvisioner {

    String script;
    String scriptFileName;

    //todo check that the scriptFileName if specified are unique and contain a valid file name.

    public ScriptProvisioner(String script) {
        this.script = script;
    }

    public ScriptProvisioner(String script, String scriptFileName) {
        this.script = script;
        this.scriptFileName = scriptFileName;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getScriptFileName() {
        return scriptFileName;
    }

    public void setScriptFileName(String scriptFileName) {
        this.scriptFileName = scriptFileName;
    }
}
