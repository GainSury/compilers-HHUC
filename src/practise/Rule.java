package practise;

import java.util.ArrayList;
import java.util.Stack;

public class Rule{
    public String lSymb;
    public Link firstRule;
    public Rule(String lSymb_){
        this.lSymb = lSymb_;
    }
    public Rule(String lSymb_, String rSymbs){
        this.lSymb = lSymb_;
        if(rSymbs == null || rSymbs.isEmpty() || rSymbs.equals("")){
            this.firstRule = null;
            return;
        }
        Link p = new Link(rSymbs.substring(0, 1));
        p.nextRule = null;
        this.firstRule = p;
        for(int i = 1;i < rSymbs.length();i++){
            Link t = new Link(rSymbs.substring(i, i+1));
            t.nextRule = null;
            p.nextSymb = t;
            p = t;
        }
        p.nextSymb = null;
    }
    
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
    
    public String toString(){
        StringBuilder a = new StringBuilder();
        a.append(this.lSymb +" -> ");
        if(this.firstRule == null)
            a.append("epsilon");
        Link p = this.firstRule;
        do{
            Link q = p;
            do{
                a.append(q.rSymb);
            }while((q = q.nextSymb) != null);
            if(p.nextRule != null)
                a.append(" | ");
                
        }while((p = p.nextRule) != null);
        return a.toString();
    }
}
