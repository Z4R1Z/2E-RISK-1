package entities;

import java.util.LinkedList;

public class ResearchTree {

    class Node {
        Research res;
        Node next, next1, next2;

        Node(Research d) { res = d; }
        Node(){}
    }

    Node head, current;
    int remaining, turnCost;
    boolean flag;

    public ResearchTree(){
        head = new Node();
        head.next = new Node(new Research("TroopResearch1",new TroopResearch(),1,1,0,0,0,new int[]{0,1,2,3}));
        head.next.res.setAvailable(true);
        head.next.next1 = new Node(new Research("TroopResearch2",new TroopResearch(),3,2,-1,0,0,new int[]{1,2}));
        head.next.next = new Node(new Research("ResearchResearch1",new ResearchTurnResearch(),2,0,0,1,0,null));
        head.next.next2 = new Node(new Research("ResourceResearch1",new ResourceResearch(),4,0,0,0,1,null));
        flag = false;
    }

    public Node getHead() {
        return head;
    }

    public void isAvailable(Node head,String name) {
        if (head.next1 != null) {
          //  System.out.println("next1 : " + head.next1.res.getName() + " " + name);
            if (head.next1.res.getName().equals(name)) {
            //    System.out.println(head.next1.res.isAvailable());
                if(head.next1.res.isAvailable())
                    flag = true;
            }
            else {
             //   System.out.println("asdasdasd");
                isAvailable(head.next1, name);
            }
        }if (head.next != null) {
          //  System.out.println("next2: " + head.next.res.getName()+ " " + name);
            if (head.next.res.getName().equals(name)) {
            //    System.out.println("asd " + head.next.res.isAvailable());
                if(head.next.res.isAvailable())
                    flag = true;
            } else
                isAvailable(head.next, name);
        }if (head.next2 != null) {
          //  System.out.println("next3: "+head.next2.res.getName()+ " " + name);
            if (head.next2.res.getName().equals(name))
                if(head.next2.res.isAvailable())
                    flag = true;
            else
                isAvailable(head.next2, name);
        }
    }

    public boolean isAvailable(String name){
        isAvailable(head, name);
        return flag;

    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void turnCounter(Player player){
        System.out.println("rema11: " + remaining);
        if(remaining > 0) {
            remaining--;
            System.out.println("rema: " + remaining);
        }
        else {
            current.res.researchDone(player);
            player.setResearching(false);
            System.out.println("ıgıgı");
//            if(current.next1 != null)
                current.next1.res.setAvailable(true);
            if(current.next != null)
                current.next.res.setAvailable(true);
            if(current.next2 != null)
                current.next2.res.setAvailable(true);
        }
    }

    public void startResearch(String name){
        if (head.next1 != null) {
             System.out.println("next1 : " + head.next1.res.getName() + " " + name);
            if (head.next1.res.getName().equals(name)) {
                  System.out.println(head.next1.res.getName());
                current = head.next1;
                turnCost = current.res.getCost();
                remaining = turnCost;
            }
            else {
                 System.out.println("asdasdasd");
                isAvailable(head.next1, name);
            }
        }if (head.next != null) {
             System.out.println("next2: " + head.next.res.getName());
            if (head.next.res.getName().equals(name)) {
                     System.out.println("asd " + head.next.res.getName());
                current = head.next;
                turnCost = current.res.getCost();
                remaining = turnCost;
                System.out.println("rema: " + remaining);
            } else
                isAvailable(head.next, name);
        }if (head.next2 != null) {
               System.out.println("next3: "+head.next2.res.getName());
            if (head.next2.res.getName().equals(name)){
                current = head.next2;
                turnCost = current.res.getCost();
                remaining = turnCost;
            }else
                isAvailable(head.next2, name);
        }
    }
}
