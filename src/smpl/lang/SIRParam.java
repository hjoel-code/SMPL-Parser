package smpl.lang;

public class SIRParam {
    


    private String param;
    private String type;

    public SIRParam(String id, String type) {
        this.param = id;
        this.type = type;
    }


    public String getParam() {
        return param;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return getType() +  " " + getParam();
    }
    
}
