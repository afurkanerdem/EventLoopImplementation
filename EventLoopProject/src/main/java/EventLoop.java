import java.time.Instant;

public class EventLoop{

    public static final int SECOND_TO_MILLI = 1000;
    private CallQueue callQueue;
    private Long lastExecutionTime;


    public EventLoop() {
        this.callQueue = new CallQueue();
        //this.time = Instant.EPOCH.getEpochSecond()* SECOND_TO_MILLI;
    }

    public void run(Runnable initialCall){

        execute(initialCall);

        while (!callQueue.isEmpty()){
            Runnable call = callQueue.pop(lastExecutionTime);
            execute(call);
        }

        callQueue.close();
    }

    private void execute(Runnable call) {
         try {
             call.run();
         }catch (Exception e){
            System.out.println("Unhandled exception");
         }finally {
             lastExecutionTime = getCurrentTime();
         }
    }

    private Long getCurrentTime(){
        return Instant.EPOCH.getEpochSecond()* SECOND_TO_MILLI;
    }


    public boolean registerSocket(AsyncSocket asyncSocket){return ;}

    public boolean unregisterSocket(AsyncSocket asyncSocket){return true;}

}
