package CheckData;

public class IdValidator {
    public IdValidator(){}

    public boolean checkdata(String id){
        return id.length()==16;
    }
}
