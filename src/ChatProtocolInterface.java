import java.util.UUID;
/**
 *
 * @author Avi
 */
public interface ChatProtocolInterface {
    public abstract void ProccessInput(String message);
    public abstract void ProccessOutput(String message);
}
