 
import java.util.LinkedList;
import java.util.Queue;
 // first base code of a queue just gonna change how the item is added to queue and how the power is given
public class QueueExample {
 
    public static void main(String[] args)
    {
        Queue<Integer> q
            = new LinkedList<>();
 
        // Adds items to the queue from pickup figuring this out
        // idk how to make it so when u pickup it get s the item tbh
 
        // Display contents of the inventory queue.
        System.out.println("inventroy"
                           + q);
 
        // To use the next item.
        int removedele = q.remove();
        System.out.println("used item-"
                           + removedele);
      //gonan throw a if statemtn to when head of queue is removed we gain power or whatever the item is ill find this
 
        System.out.println(q);
 
        // To view the next item
        int head = q.peek();
        System.out.println("next item-"
                           + head);
 
        int size = q.size();
        System.out.println("total inv space-"
                           + size);
    }
}
