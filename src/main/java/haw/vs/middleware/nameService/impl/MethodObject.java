package haw.vs.middleware.nameService.impl;

import java.util.Objects;

public class MethodObject {
    private Long methodId;
    private Long bundleId;
    private String methodName;
    private int methodType;
    private String ip;

    public MethodObject(Long methodId, Long bundleId, String methodName, int methodType, String ip) {
        this.methodId = methodId;
        this.bundleId = bundleId;
        this.methodName = methodName;
        this.methodType = methodType;
        this.ip = ip;
    }

    public Long getMethodId() {
        return methodId;
    }

    public void setMethodId(Long methodId) {
        this.methodId = methodId;
    }

    public Long getBundleId() {
        return bundleId;
    }

    public void setBundleId(Long bundleId) {
        this.bundleId = bundleId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public int getMethodType() {
        return methodType;
    }

    public void setMethodType(int methodType) {
        this.methodType = methodType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MethodObject that = (MethodObject) o;
        return Objects.equals(methodId, that.methodId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(methodId);
    }
}
