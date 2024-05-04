import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {
    @Test
    public void testGetLocalNumber() {
        int number = 14;
        if (getLocalNumber() != number)
            Assert.fail("Метод не вернул число 14" );
    }
    @Test
    public void testGetClassNumber() {
        int number = 45;
        if (getClassNumber() < number)
            Assert.fail("Метод вернул число меньше 45");
    }

    @Test
    public void testGetClassString() {
        String text = "Hello";
        if (!getClassString().contains(text))
            Assert.fail("Не содержит текст " + text);
    }
}
