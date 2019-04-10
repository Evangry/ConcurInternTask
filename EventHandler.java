import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

public class EventHandler{
  static HashSet<String> mySet = new HashSet<String>();

  //time until eviction.
  static long delay = 1000;

  static Timer timer = new Timer();


  public static void processEvent(String EventID, String EventBody){
     
     // 
     //
     // HashTable 
     // void -> put(key, value)
     // boolean -> contains(key)

     //here I'm assuming that I don't renew duplicate eventIDs (I won't set a new timer if EventID was recently used.)
     //removes eventIDs from the set after the delay.
     if (!mySet.contains(EventID)){
         
         mySet.add(EventID);
         processEventWithoutDuplicates(EventID, EventBody);
         timer.schedule(new TimerTask() {
                          @Override
                          public void run(){
                            mySet.remove(EventID);
                          }
                        }, delay);
         System.out.println(EventID + " " + EventBody + " processed.");

     } else {
        System.out.println(EventID + " " + EventBody + " dropped.");
     }
  }


  public static void processEventWithoutDuplicates(String EventID, String EventBody){
     // this does something very complicated that we do not what do touch
  }

  //used to test
  public static void main(String[] args){
    processEvent("1", "Howdy");
    processEvent("2", "Pardner.");
    processEvent("1", "Hows the");
    processEvent("3", "Chili?");
    timer.schedule(new TimerTask() {
                      @Override
                      public void run(){
                        processEvent("2", "Yes.");
                      }
                    }, delay);
    
  }
}
