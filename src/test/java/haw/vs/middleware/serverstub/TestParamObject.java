package haw.vs.middleware.serverstub;

public class TestParamObject {
    private int bla;
    private String blupp;

    public TestParamObject() {
    }

    public TestParamObject(int bla, String blupp) {
        this.bla = bla;
        this.blupp = blupp;
    }

    public int getBla() {
        return bla;
    }

    public void setBla(int bla) {
        this.bla = bla;
    }

    public String getBlupp() {
        return blupp;
    }

    public void setBlupp(String blupp) {
        this.blupp = blupp;
    }

    @Override
    public String toString() {
        return "TestParamObject{" +
                "bla=" + bla +
                ", blupp='" + blupp + '\'' +
                '}';
    }
}
