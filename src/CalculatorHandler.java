import org.apache.thrift.TException;
import tutorial.calculator.gen.CalculatorService;
public class CalculatorHandler implements CalculatorService.Iface {

    @Override
    public int add(int n1, int n2) throws TException {
        System.out.println("Serving service add... for values "+n1+" and "+n2);
        return n1 + n2;
    }

    @Override
    public int multiply(int n1, int n2) throws TException {
        System.out.println("Serving service multiply... for values "+n1+" and "+n2);
        return n1 * n2;
    }
}
