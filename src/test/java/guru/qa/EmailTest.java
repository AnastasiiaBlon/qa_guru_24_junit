package guru.qa;

import org.junit.jupiter.api.*;

@DisplayName("Tests email distribution")
public class EmailTest {

    @Test
    @DisplayName("Email should be sent to new user")
    void emailShouldBeSentForNewUser() {
        System.out.println("Hello, world!");
    }

    @Test
    @Tag("SMOKE")
    @DisplayName("Email should be sent to banned user")
    void emailShouldBeSentForBannedUser() {
        System.out.println("Hello, world!");
    }

    @Disabled("BUG-1233455")
    @Test
    @Tags({
            @Tag("SMOKE"),
            @Tag("WEB"),
    })
    @DisplayName("Email should be sent after payment method change")
    void emailShouldBeSentAfterPaymentMethod() {
        throw new AssertionError("Test fails");
    }
}
