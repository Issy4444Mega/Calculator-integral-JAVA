package Window;
import java.io.Serializable;

public class Rect_integral implements Serializable  {
    private static final long serialVersionUID = 1L;
    private double step;
    private double high_value;
    private double low_value;
    private double result;
    
    
    public Rect_integral(double high_value, double low_value, double step, double result){
        
        this.high_value = high_value;
        this.low_value = low_value;
        this.step = step;
        this.result = result;
    }
    
    public double getHigh_value()
    {
        return high_value;
    }
    
    public double getLow_value()
    {
        return low_value;
    }
    
    public double getStep()
    {
        return step;
    }
    
    public double getResult()
    {
        return result;
    }
    
    public void setResult(double result)
    {
        this.result = result;
    }
}
