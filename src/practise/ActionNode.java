package practise;

public class ActionNode{
    boolean isShift;
    boolean isError;
    boolean isReduce;
    boolean isAccept;
    int shiftState;
    int reduceProduction;
    ActionNode(){
        this.isShift = false;
        this.isAccept = false;
        this.isError = true;
        this.isReduce = false;
        shiftState = -1;
        reduceProduction = -1;
    }
    public void setShift(int shiftState_){
        this.isShift = true;
        this.shiftState = shiftState_;
        this.isError = false;
    }
    
    public void setReduce(int reduceProduction_){
        this.isReduce = true;
        this.reduceProduction = reduceProduction_;
        this.isError = false;
    }
    
    public void setAccept(){
        this.isAccept = true;
        this.isError = false;
    }
}
