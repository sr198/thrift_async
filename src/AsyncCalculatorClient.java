import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TNonblockingSocket;
import tutorial.calculator.gen.CalculatorService;

import java.io.IOException;

public class AsyncCalculatorClient {

    public void callServer()
    {
        try {
            //Connect to server and call appropriate service
            CalculatorService.AsyncClient client = new CalculatorService.AsyncClient(new TBinaryProtocol.Factory(),
                    new TAsyncClientManager(), new TNonblockingSocket("localhost", 7911));

            client.add(200, 400, new AddMethodCallback());

        }catch(IOException e){
            e.printStackTrace();

        }catch(TException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args){
        AsyncCalculatorClient calculatorClient = new AsyncCalculatorClient();
        calculatorClient.callServer();
    }

    //callback for add method
    public class AddMethodCallback
            implements AsyncMethodCallback<CalculatorService.AsyncClient.add_call> {

        @Override
        public void onComplete(CalculatorService.AsyncClient.add_call add_call) {
            System.out.println("Add method done");
            try {
                int result = add_call.getResult();
            } catch (TException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(Exception e) {
            System.out.println("Add method failed");
        }
    }

    //callback for multiply service
    public class MultiplyCallback implements AsyncMethodCallback<CalculatorService.AsyncClient.multiply_call> {

        @Override
        public void onComplete(CalculatorService.AsyncClient.multiply_call multiply_call) {
            System.out.println("Multiply method done");
            try{
                int result = multiply_call.getResult();
            }catch(TException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onError(Exception e) {
            System.out.println("Multiply method done");
        }
    }
}
