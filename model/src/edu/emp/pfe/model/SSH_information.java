package edu.emp.pfe.model;

public class SSH_information {
    String userName;
    String ipAddress;
    String password;

    boolean isValid = false;
    String errorMsg = "no information available";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        if (!isValid)
            return errorMsg;
        return  "user Name: " + userName + "\npassword: " + password + "\nip Address: " + ipAddress;
    }
}
