package practise;

import java.util.ArrayList;
import java.util.Stack;

class Symb{
    String place;
    String symbool;
    Symb(String s){
        this.symbool = s;
    }
}
public class p16 {
    
    public static Stack<Symb> symbStack = new Stack<Symb>();
    public static void main(String[] args){
      
        ArrayList<Rule> rules = new ArrayList<Rule>();
        rules.add(new Rule("A","S"));
        rules.add(new Rule("S","i=E"));
        rules.add(new Rule("E","E+T"));
        rules.add(new Rule("E","T"));
        rules.add(new Rule("T","T*F"));
        rules.add(new Rule("T","F"));
        rules.add(new Rule("F","-P"));
        rules.add(new Rule("F","p"));
        rules.add(new Rule("P","(E)"));
        rules.add(new Rule("P","i"));
        
//        Rule.showRules(rules);
        
        ActionNode[][] action = new ActionNode[18][8];
        for(int i = 0;i < 18;i++){
            for(int j = 0; j < 8;j++){
                action[i][j] = new ActionNode();
            }
        }
        
        action[0][0].setShift(2);
        action[2][1].setShift(3);
        action[3][0].setShift(9);
        action[7][0].setShift(9);
        action[8][0].setShift(9);
        action[10][0].setShift(9);
        action[11][0].setShift(9);
        action[3][4].setShift(7);
        action[8][4].setShift(7);
        action[10][4].setShift(7);
        action[11][4].setShift(7);
        action[3][5].setShift(8);
        action[7][5].setShift(8);
        action[8][5].setShift(8);
        action[10][5].setShift(8);
        action[11][5].setShift(8);
        action[4][2].setShift(10);
        action[5][3].setShift(11);
        action[13][2].setShift(10);
        action[15][3].setShift(11);
        action[13][7].setShift(17);
      
        
        action[4][7].setReduce(1);
        
        action[5][2].setReduce(3);
        action[5][6].setReduce(3);
        action[5][7].setReduce(3);
        
        action[6][2].setReduce(5);
        action[6][3].setReduce(5);
        action[6][6].setReduce(5);
        action[6][7].setReduce(5);
    
        action[9][2].setReduce(9);
        action[9][3].setReduce(9);
        action[9][6].setReduce(9);
        action[9][7].setReduce(9);
        
        action[12][2].setReduce(6);
        action[12][3].setReduce(6);
        action[12][6].setReduce(6);
        action[12][7].setReduce(6);
        
        action[14][2].setReduce(7);
        action[14][3].setReduce(7);
        action[14][6].setReduce(7);
        action[14][7].setReduce(7);
        
        action[15][2].setReduce(2);
        action[15][6].setReduce(2);
        action[15][7].setReduce(2);
        
        action[16][2].setReduce(4);
        action[16][3].setReduce(4);
        action[16][6].setReduce(4);
        action[16][7].setReduce(4);
        
        action[17][2].setReduce(8);
        action[17][3].setReduce(8);
        action[17][6].setReduce(8);
        
        action[17][7].setReduce(4);
        
        action[1][7].setAccept();
        
        int[][] Goto = new int[18][5];
        for(int i = 0; i < 18; i++){
            for(int j = 0;j < 5;j++){
                Goto[i][j] = -1;
            }
        }
        
        Goto[0][0] = 1;
        Goto[3][1] = 4;
        Goto[3][2] = 5;
        Goto[3][3] = 6;
        Goto[3][4] = 14;
        
        Goto[7][4] = 12;
        
        Goto[8][1] = 13;
        Goto[8][2] = 5;
        Goto[8][3] = 6;
        Goto[8][4] = 14;
        
        Goto[10][2] = 15;
        Goto[10][3] = 6;
        Goto[10][4] = 14;
        
        Goto[11][3] = 16;
        Goto[11][4] = 14;
        
        String input = "i=-i+i*i*i$";
        
        Stack<Integer> stateStack = new Stack<Integer>();
        
        int curLoc = 0;
        String a = input.substring(curLoc, curLoc+1);
        stateStack.push(0);
        symbStack.push(new Symb("$"));
        boolean showOutput =true;
        while(true){
            int s = stateStack.peek();
            if(action[s][t2int(a)].isShift == true){
                stateStack.push(action[s][t2int(a)].shiftState);
                symbStack.push(new Symb(a));
                
                //debug
                if(showOutput == true)
                    System.out.println("shift "+ action[s][t2int(a)].shiftState);
                curLoc++;
                a = input.substring(curLoc, curLoc+1);
            }
            else if(action[s][t2int(a)].isReduce == true){
                int ruleNo = action[s][t2int(a)].reduceProduction;
                Rule rule = rules.get(ruleNo);
                Link p = rule.firstRule;
                Stack<Symb> symbStackTmp = new Stack<Symb>();
                
                int count = 0;
                do{
                    count++;
                }while((p = p.nextSymb) != null);
                
                for(int i = 0; i < count; i++){
                    stateStack.pop();
                    symbStackTmp.push(symbStack.pop());
                }
                //把reduce j 对应的rule的左侧head压进符号栈
                symbStack.push(new Symb(rule.lSymb));
                int t = stateStack.peek();
                stateStack.push(Goto[t][n2int(rule.lSymb)]);
                if(showOutput == true)
                    System.out.println("output "+ rule.toString());
                boot(ruleNo,symbStackTmp);
            }
            else if(action[s][t2int(a)].isAccept == true){
                System.out.println("succeed");
                break;
            }
            else if(action[s][t2int(a)].isError == true){
                System.out.println("error");
                break;
            }    
        }
    }
    private static void boot(int ruleNo, Stack<Symb> symbStackTmp) {
        // TODO Auto-generated method stub
        switch(ruleNo){
            case 1:rule1(symbStackTmp);break;
            case 2:rule2(symbStackTmp);break;
            case 3:rule3(symbStackTmp);break;
            case 4:rule4(symbStackTmp);break;
            case 5:rule5(symbStackTmp);break;
            case 6:rule6(symbStackTmp);break;
            case 7:rule7(symbStackTmp);break;
            case 8:rule8(symbStackTmp);break;
            case 9:rule9(symbStackTmp);break;
        }
        
    }
    private static void rule9(Stack<Symb> symbStackTmp) {
        // TODO Auto-generated method stub
        //P->id
        Symb P = symbStack.pop();
        P.place = "id";
        symbStack.push(P);
    }
    private static void rule8(Stack<Symb> symbStackTmp) {
        // TODO Auto-generated method stub
        //P->(E)
        Symb P = symbStack.pop();
        //pop (
        symbStackTmp.pop();
        Symb E = symbStackTmp.pop();
        P.place = E.place;
        symbStack.push(P);
    }
    private static void rule7(Stack<Symb> symbStackTmp) {
        // TODO Auto-generated method stub
        //F->P
        Symb F = symbStack.pop();
        Symb P = symbStackTmp.pop();
        F.place = P.place;
        symbStack.push(F);
    }
    private static void rule6(Stack<Symb> symbStackTmp) {
        // TODO Auto-generated method stub
        //F->-P
        Symb F = symbStack.pop();
        //pop "-"
        symbStackTmp.pop();
        Symb P = symbStackTmp.pop();
        String t = getTmp();
        System.out.println("-, "+P.place+", "+t);
        F.place = t;
        symbStack.push(F);
        
    }
    private static void rule5(Stack<Symb> symbStackTmp) {
        // TODO Auto-generated method stub
        //T->F
        Symb T = symbStack.pop();
        Symb F = symbStackTmp.pop();
        T.place = F.place;
        symbStack.push(T);
        
    }
    private static void rule4(Stack<Symb> symbStackTmp) {
        // TODO Auto-generated method stub
        //T->T*F
        Symb T = symbStack.pop();
        Symb T1 = symbStackTmp.pop();
        symbStackTmp.pop();
        Symb F = symbStackTmp.pop();
        String t = getTmp();
        System.out.println("*, "+T1.place+", "+F.place+ ", "+t);
        T.place = t;
        symbStack.push(T);
        
    }
    private static void rule3(Stack<Symb> symbStackTmp) {
        // TODO Auto-generated method stub
        //E->T
        Symb E = symbStack.pop();
        Symb T = symbStackTmp.pop();
        E.place = T.place;
        symbStack.push(E);
    }
    private static void rule2(Stack<Symb> symbStackTmp) {
        // TODO Auto-generated method stub
        //E->E1+T
        Symb E = symbStack.pop();
        Symb E1 = symbStackTmp.pop();
        //pop "+"
        symbStackTmp.pop();
        Symb T = symbStackTmp.pop();
        String t = getTmp();
        System.out.println("+, "+E1.place+", "+T.place+ ", "+t);
        E.place = t;
        symbStack.push(E);
    }
    private static void rule1(Stack<Symb> symbStackTmp) {
        // TODO Auto-generated method stub
        //S->id=E
        Symb S = symbStack.pop();
        symbStackTmp.pop();
        symbStackTmp.pop();
        Symb E = symbStackTmp.pop();
        System.out.println(":=, "+E.place +", , id");
        symbStack.push(S);
        
    }
    public static int count=0;
    public static int getAndInc(){
        return count++;
    }
    public static String getTmp(){
        return "t" + getAndInc();
    }
     
    public static int n2int(String aString){
        if(aString.equals("S"))
            return 0;
        if(aString.equals("E"))
            return 1;
        if(aString.equals("T"))
            return 2;
        if(aString.equals("F"))
            return 3;
        if(aString.equals("P"))
            return 4;
        return -1;  
    }
    
    public static int t2int(String aString){
        if(aString.equals("i"))
            return 0;
        if(aString.equals("="))
            return 1;
        if(aString.equals("+"))
            return 2;
        if(aString.equals("*"))
            return 3;
        if(aString.equals("-"))
            return 4;
        if(aString.equals("("))
            return 5;
        if(aString.equals(")"))
            return 6;
        if(aString.equals("$"))
            return 7;
        return -1;
    }
}