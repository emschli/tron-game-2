package haw.vs.middleware.common;

public class JsonRequest {

    private String methodname;
    private Object[] params;

    public JsonRequest() {
    }

    public String getMethodname() {
        return methodname;
    }

    public void setMethodname(String methodname) {
        this.methodname = methodname;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

}
