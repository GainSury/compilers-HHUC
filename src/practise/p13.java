package practise;
import java.util.ArrayList;




public class p13 {
    //3是大于,2是等于,1是小于,0是没有 有疑问的地方是1,1,1,1,2,0还是1,1,1,1,0,0
    public static int[][] priorTab = {{3,1,1,1,3,3},{3,3,1,1,3,3},{3,3,0,0,3,3},{1,1,1,1,0,0},{3,3,0,0,3,3},{1,1,1,1,0,2}};
    public static ArrayList<Rule> rules = new ArrayList<Rule>();
    
    public static void main(String[] args){
        ArrayList<Rule> rules = new ArrayList<Rule>();   
        //初始化文法
        rules.add(new Rule("E","E+T"));
        rules.add(new Rule("E","T"));
        rules.add(new Rule("T","T*F"));
        rules.add(new Rule("T","F"));
        rules.add(new Rule("F","(E)"));
        rules.add(new Rule("F","i"));
        p09.showRules(rules);
        //具体分析过程请见ppt4-2第41张
        String input = "i+i*i#";
        
        int k = 1;
        String[] S = new String[20];
        S[k] = "#";
        int curCount = 0;
        int j=0;
        //Q详细用法见算法
        String Q;
        String curSymb = input.substring(curCount, curCount+1);
        while(true){
            if(isTerminal(S[k]))
                j = k;
            else
                j = k-1;
            while(symComp(S[j],curSymb) == 3){
                do{
                    Q = S[j];
                    if(isTerminal(S[j-1]))
                        j=j-1;
                    else
                        j=j-2;
                    if(j < 0){
                        System.out.println("error");
                        return;
                    }
                }while(symComp(S[j],Q) == 3 && symComp(S[j],Q) == 2);
                String N = reduceSymb(j+1,k,S);
                k = j+1;
                S[k]=N;
            }
            
            if(symComp(S[j],curSymb) == 1){
                k = k + 1;
                S[k] = curSymb;
            }
            else if(symComp(S[j],curSymb) == 2){
                if(symComp(S[j],"#") == 2){
                    System.out.println("succeed");
                    break;
                }
                else{
                    k = k + 1;
                    S[k] = curSymb;
                }
            }
            else{
                System.out.println("error");
                break;
            }
           
            if(curCount == input.length() - 1)
                break;
            System.out.println("shift "+curSymb);
            curCount ++;
            curSymb = input.substring(curCount, curCount+1);
        }
        
       
    }
    public static String reduceSymb(int start,int end,String[] S){
        if(start == end){ 
            System.out.println("reduce F -> i");
            return "F";
        }
        else if(S[start+1].equals("+")){
            System.out.println("reduce E -> E + T");
            return "E";
        }
        else if(S[start+1].equals("*")){
            System.out.println("reduce T -> T * F");
            return "T";
        }
        return null;
    }
    
    
    public static int symComp(String a,String b){
        return priorTab[symb2int(a)][symb2int(b)];
    }
    public static int symb2int(String aString){
        if(aString.equals("+"))
            return 0;
        if(aString.equals("*"))
            return 1;
        if(aString.equals("i"))
            return 2;
        if(aString.equals("("))
            return 3;
        if(aString.equals(")"))
            return 4;
        if(aString.equals("#"))
            return 5;
        return -1;
    }
    
    //在这个实验中 #算终结符
    public static boolean isTerminal(String aString){
        if(aString.equals("E") || aString.equals("T") || aString.equals("F"))
            return false;
        return true;
    }
}
