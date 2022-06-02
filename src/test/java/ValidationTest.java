
import com.example.demos.model.entity.User;
import com.example.demos.model.utils.Validation;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidationTest {


    @Test
    public void Validation() {
        User user = new User();
        user.setLogin("user");
        user.setFirstName("Костя");
        user.setLastName("Бороздих");
        user.setPassword("qazwsxedcA7");
        user.setSecondPassword("qazwsxedcA7");
        user.setEmail("kostiaborozdyh@gmail.com");
        user.setPhoneNumber("+380938988645");
        assertTrue(Validation.count(Validation.valid(user, false,false)));
        User user2 = user.cloneUser();
        user2.setPassword("qazwsxedcA7");
        user2.setSecondPassword("qazwsxedcA7");
        user2.setLogin("user256");
        assertFalse(Validation.count(Validation.valid(user2, true,true)));
        user2.setEmail("kotikm@gmail.com");
        assertTrue(Validation.count(Validation.valid(user2, true,true)));
    }
}
