package practise;

import java.util.ArrayList;
import practise.Link;
import practise.Rule;

public class p01 {
    public static void main(String[] args){
        ArrayList<Rule> rules = new ArrayList<Rule>();    
        //初始化数组
        Rule rule = new Rule("E");
        Link link = new Link("E");
        Link link1 = new Link("+");
        Link link2 = new Link("T");
        Link link3 = new Link("T");
        rule.firstRule = link;
        link.nextRule = link3;
        link3.nextRule = null;
        link3.nextSymb = null;
        link.nextSymb = link1;
        link1.nextRule = null;
        link1.nextSymb = link2;
        link2.nextSymb = null;
        link2.nextRule = null;
        rules.add(rule);
        
        rule = new Rule("T");
        link = new Link("T");
        link1 = new Link("*");
        link2 = new Link("F");
        link3 = new Link("F");
        rule.firstRule = link;
        link.nextRule = link3;
        link3.nextRule = null;
        link3.nextSymb = null;
        link.nextSymb = link1;
        link1.nextRule = null;
        link1.nextSymb = link2;
        link2.nextSymb = null;
        link2.nextRule = null;
        rules.add(rule);
        
        rule = new Rule("F");
        link = new Link("(");
        link1 = new Link("E");
        link2 = new Link(")");
        link3 = new Link("i");
        rule.firstRule = link;
        link.nextRule = link3;
        link3.nextRule = null;
        link3.nextSymb = null;
        link.nextSymb = link1;
        link1.nextRule = null;
        link1.nextSymb = link2;
        link2.nextSymb = null;
        link2.nextRule = null;
        rules.add(rule);
        
        for(int i = 0; i < rules.size();i++){
            rule = rules.get(i);
            Link p = rule.firstRule;
            System.out.print(rule.lSymb+" -> ");
            do{
                Link q = p;
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
}
