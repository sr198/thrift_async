import org.apache.thrift.TException;

import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.transport.TTransportException;
import tutorial.calculator.gen.CalculatorService;

public class NonBlockingServer {

    private static class AsyncCalculatorHandler implements CalculatorService.AsyncIface {

        @Override
        public void add(int n1, int n2, AsyncMethodCallback<Integer> resultHandler) throws TException {
            System.out.println("Point ADD");
            int sum = n1 + n2;
            resultHandler.onComplete(sum);
        }

        @Override
        public void multiply(int n1, int n2, AsyncMethodCallback<Integer> resultHandler) throws TException {
            System.out.println("Point B");
            int product = n1 * n2;
            resultHandler.onComplete(product);
        }
    }
    private void start() {
        try {

            TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(7911);
            //ArithmeticService.Processor processor = new ArithmeticService.Processor(new ArithmeticServiceImpl());
            //CalculatorService.Processor processor = new CalculatorService.Processor(new CalculatorHandler());
            CalculatorService.AsyncProcessor processor = new CalculatorService.AsyncProcessor(new AsyncCalculatorHandler());


            TServer server =
                    new THsHaServer(
                            new THsHaServer.Args(serverTransport)
                                    .processor(processor)
                                    .protocolFactory(new TBinaryProtocol.Factory())
                                    .workerThreads(4));
            //TServer server = new TNonblockingServer(new TNonblockingServer.Args(serverTransport).
                    //processor(processor));
            System.out.println("Starting server on port 7911 ...");
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        NonBlockingServer srv = new NonBlockingServer();
        srv.start();
    }
}
