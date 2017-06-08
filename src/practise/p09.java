package practise;

import java.util.ArrayList;
import java.util.Stack;

public class p09 {
    static void showRules(ArrayList<Rule> rules){
        Rule rule;
        for(int i = 0; i < rules.size();i++){
            rule = rules.get(i);
            Link p = rule.firstRule;
            System.out.print("<"+i+"> "+rule.lSymb+" -> ");
            do{
                Link q = p;
                if(p == null)
                    break;
                do{
                    System.out.print(q.rSymb);
                }while((q = q.nextSymb) != null);
                if(p.nextRule != null)
                    System.out.print(" | ");
            }while( (p = p.nextRule) != null);
            //每个nonterminal之间相隔一行，输出换行。
            System.out.println("");    
        }
    }
    
    public static void main(String[] args){
        ArrayList<Rule> rules = new ArrayList<Rule>();   
        //初始化文法
        rules.add(new Rule("E","TA"));
        rules.add(new Rule("A","+TA"));
        rules.add(new Rule("A",""));
        rules.add(new Rule("T","FB"));
        rules.add(new Rule("B","*FB"));
        rules.add(new Rule("B",""));
        rules.add(new Rule("F","(E)"));
        rules.add(new Rule("F","i"));
        
        
        showRules(rules);
        //分析表
        int[][] pTab = new int[5][6];
        for(int i = 0; i < 5 ;i ++){
            for(int j = 0;j < 6;j++){
                pTab[i][j] = -1;
            }
        }
        pTab[0][0] = 0;
        pTab[0][3] = 0;
        pTab[1][1] = 1;
        pTab[1][4] = 2;
        pTab[1][5] = 2;
        pTab[2][0] = 3;
        pTab[2][3] = 3;
        pTab[3][1] = 5;
        pTab[3][2] = 4;
        pTab[3][4] = 5;
        pTab[3][5] = 5;
        pTab[4][0] = 7;
        pTab[4][3] = 6;
        
        Stack<String> stack = new Stack<String>();
        String strTodo = "(i+i)+i#";
        int curLoc = 0;
        String b = strTodo.substring(curLoc, curLoc+1);
        stack.push("#");
        stack.push("E");
        boolean flag = true;
        int ruleTmp;
        while(flag){
           String x = stack.pop();
           if(isTerminal(x) == true){
               if(x.equals(b)){
                   System.out.println("match " + b);
                   curLoc++;
                   b = strTodo.substring(curLoc, curLoc+1);
               }
               else{
                   System.out.println("error");
                   break;
               }
           }
           else if(x.equals("#")){
               if(x.equals(b)){
                   flag = false;
                   System.out.println("succeed");
               }
               else{
                   System.out.println("error");
                   break;
               }
           }
           else if((ruleTmp = pTab[n2Int(x)][t2Int(b)]) != -1){
               Rule rule = rules.get(ruleTmp);
               System.out.print("output "+rule.lSymb+" -> ");
               Link p = rule.firstRule;
               if(p == null){
                   System.out.print("epsilon");
                   System.out.println();
                   continue;
               }
               Link q = p;
               //输出
               do{
                   System.out.print(q.rSymb);
               }while((q = q.nextSymb) != null);
               System.out.println();
               //压进栈
               Stack<String> stacTmp = new Stack<String>();
               do{
                   stacTmp.push(p.rSymb);
               }while((p = p.nextSymb) != null);
               while(stacTmp.isEmpty() == false){
                   stack.push(stacTmp.pop());
               }
           }
           else{
               System.out.println("error");
               break;
           }
        }
        
        
        
    }
    
    public static int t2Int(String aString){
        if(aString.equals("i"))
            return 0;
        if(aString.equals("+"))
            return 1;
        if(aString.equals("*"))
            return 2;
        if(aString.equals("("))
            return 3;
        if(aString.equals(")"))
            return 4;
        if(aString.equals("#"))
            return 5;
        return -1;
    }
    
    public static int n2Int(String aString){
        if(aString.equals("E"))
            return 0;
        if(aString.equals("A"))
            return 1;
        if(aString.equals("T"))
            return 2;
        if(aString.equals("B"))
            return 3;
        if(aString.equals("F"))
            return 4;
        return -1;
    }
    
    public static boolean isTerminal(String aString){
        if(aString.equals("E") || aString.equals("A") || aString.equals("T") || aString.equals("B") || aString.equals("F") ||  aString.equals("#"))
            return false;
        return true;
    }
}
