package practise;

import java.util.ArrayList;
import java.util.Stack;


public class p15 {
    public static void main(String[] args){
        ActionNode[][] action = new ActionNode[7][3];
        for(int i = 0;i < 7;i++){
            for(int j = 0; j < 3;j++){
                action[i][j] = new ActionNode();
            }
        }
       
        action[0][0].setShift(3);
        action[0][1].setShift(4);
        action[2][0].setShift(3);
        action[2][1].setShift(4);
        action[3][0].setShift(3);
        action[3][1].setShift(4);
        
        action[4][0].setReduce(3);
        action[4][1].setReduce(3);
        action[4][2].setReduce(3);
        action[5][0].setReduce(1);
        action[5][1].setReduce(1);
        action[5][2].setReduce(1);
        action[6][0].setReduce(2);
        action[6][1].setReduce(2);
        action[6][2].setReduce(2);
        
        action[1][2].setAccept();
        
        int[][] Goto = new int[7][2];
        for(int i = 0;i<7;i++){
            for(int j = 0;j < 2;j++){
                Goto[i][j] = -1;
            }
        }
        
        Goto[0][0] = 1;
        Goto[0][1] = 2;
        Goto[2][1] = 5;
        Goto[3][1] = 6;
        
        ArrayList<Rule> rules = new ArrayList<Rule>();
        rules.add(new Rule("A","S"));
        rules.add(new Rule("S","BB"));
        rules.add(new Rule("B","aB"));
        rules.add(new Rule("B","b"));
        
        String input ="baab$";
        
        Stack<Integer> stack = new Stack<Integer>();
        int curLoc = 0;
        String a = input.substring(curLoc, curLoc+1);
        stack.push(0);
        while(true){
            int s = stack.peek();
            if(action[s][s2int(a)].isShift == true){
                stack.push(action[s][s2int(a)].shiftState);
                System.out.println("shift "+ action[s][s2int(a)].shiftState);
                curLoc++;
                a = input.substring(curLoc, curLoc+1);
            }
            else if(action[s][s2int(a)].isReduce == true){
                Rule rule = rules.get(action[s][s2int(a)].reduceProduction);
                Link p = rule.firstRule;
                int count = 0;
                do{
                    count++;
                }while((p = p.nextSymb) != null);
                
                for(int i = 0; i < count; i++){
                    stack.pop();
                }
                
                int t = stack.peek();
                stack.push(Goto[t][n2int(rule.lSymb)]);
                System.out.println("output"+ rule.toString());
            }
            else if(action[s][s2int(a)].isAccept == true){
                System.out.println("succeed");
                break;
            }
            else if(action[s][s2int(a)].isAccept == true){
                System.out.println("error");
                break;
            }    
        }
    }
    
    public static int n2int(String aString){
        if(aString.equals("S"))
            return 0;
        if(aString.equals("B"))
            return 1;
        return -1;  
    }
    
    public static int s2int(String aString){
        if(aString.equals("a"))
            return 0;
        if(aString.equals("b"))
            return 1;
        if(aString.equals("$"))
            return 2;
        return -1;
    }
}
