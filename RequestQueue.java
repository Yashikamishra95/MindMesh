import java.util.*;

public class RequestQueue {

    private Queue<Request> queue;

    public RequestQueue() {

        queue = new LinkedList<>();

    }

    // Send Request
    public void sendRequest(Request request){

        queue.offer(request);

    }

    // Accept Request
    public Request acceptRequest() {

    Request request = queue.poll();

    if (request != null) {

        request.setStatus("Accepted");
        request.setCompleted(true);

        User sender = request.getSender();
        User receiver = request.getReceiver();

        sender.addPoints(10);
        receiver.addPoints(10);

        sender.incrementCompletedExchanges();

        receiver.incrementCompletedExchanges();
    }

    return request;

}

    // Reject Request
    public Request rejectRequest(){

        Request request = queue.poll();

        if(request != null){

            request.setStatus("Rejected");

        }

        return request;

    }

    public ArrayList<Request> getAllRequests(){

        return new ArrayList<>(queue);

    }

}