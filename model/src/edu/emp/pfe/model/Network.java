package edu.emp.pfe.model;

public class Network {
    NetworkType networkType;
    IpAssignment ipAssignment;
    String ipAddress;

    public Network(NetworkType networkType, IpAssignment ipAssignment, String ipAddress) {
        this.networkType = networkType;
        this.ipAssignment = ipAssignment;
        this.ipAddress = ipAddress;
    }

    public Network(NetworkType networkType, IpAssignment ipAssignment) {
        this.networkType = networkType;
        this.ipAssignment = ipAssignment;
    }

    public NetworkType getNetworkType() {
        return networkType;
    }

    public void setNetworkType(NetworkType networkType) {
        this.networkType = networkType;
    }

    public IpAssignment getIpAssignment() {
        return ipAssignment;
    }

    public void setIpAssignment(IpAssignment ipAssignment) {
        this.ipAssignment = ipAssignment;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}


