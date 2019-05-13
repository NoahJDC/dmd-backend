package com.dm_daddy.first_edition;

import com.dm_daddy.first_edition.Model.Check;
import com.dm_daddy.first_edition.Service.UnitTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.InvalidParameterException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {FirstEditionTestsConfiguration.class})
public class UnitTests {

    @Test()
    public void given_party_less_than_six_when_calculating_total_return_total_without_gratuity() {

        double checkTotal = 42;
        int partySize = 5;

        Check ck = new Check(partySize, checkTotal);

        double finalTotal = ck.calculateTotal();
        assertThat(finalTotal).isEqualTo(42);
    }

    @Test()
    public void given_party_greater_than_or_equal_to_six_when_calculating_total_return_total_with_eighteen_percent_gratuity() {

        double checkTotal = 42;
        int partySize = 7;

        Check ck = new Check(partySize, checkTotal);

        double finalTotal = ck.calculateTotal();
        assertThat(finalTotal).isEqualTo(49.56);
    }

    @Test()
    public void given_party_equal_to_six_when_calculating_total_return_total_with_eighteen_percent_gratuity() {

        double checkTotal = 42;
        int partySize = 6;

        Check ck = new Check(partySize, checkTotal);

        double finalTotal = ck.calculateTotal();
        assertThat(finalTotal).isEqualTo(49.56);
    }

    @Test(expected = InvalidParameterException.class)
    public void given_negative_party_size_when_calculating_total_return_total_with_eighteen_percent_gratuity() {

        double checkTotal = 42;
        int partySize = -1;

        Check ck = new Check(partySize, checkTotal);
    }

    @Test()
    public void given_any_check_with_any_set_of_parameters_when_calculating_total_return_total_with_fifteen_percent_gratuity() {

        double checkTotal = 42;
        int partySize = 5;

        Check ck = new Check(partySize, checkTotal);

        double fifteenPercent = ck.calculateFifteenPercentTip();
        assertThat(fifteenPercent).isEqualTo(6.30);
    }

    @Test()
    public void given_any_check_with_any_set_of_parameters_when_calculating_total_return_total_with_eighteen_percent_gratuity() {

        double checkTotal = 42;
        int partySize = 5;

        Check ck = new Check(partySize, checkTotal);

        double fifteenPercent = ck.calculateEighteenPercentTip();
        assertThat(fifteenPercent).isEqualTo(7.56);
    }

    @Test()
    public void given_any_check_with_any_set_of_parameters_when_calculating_total_return_total_with_twenty_percent_gratuity() {

        double checkTotal = 42;
        int partySize = 5;

        Check ck = new Check(partySize, checkTotal);

        double fifteenPercent = ck.calculateTwentyPercentTip();
        assertThat(fifteenPercent).isEqualTo(8.40);
    }

}
