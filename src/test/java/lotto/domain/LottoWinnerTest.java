package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoWinnerTest {

    @Test
    @DisplayName("로또 당첨 티켓을 생성한다.")
    public void createWinnerLottoTicketTest() {
        List<LottoNumber> lottoWinnerNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(45)
        );
        LottoTicket lottoWinnerTicket = new LottoTicket(lottoWinnerNumbers);
        LottoNumber lottoWinnerBonusNumber = new LottoNumber(9);

        assertThat(new LottoWinner(lottoWinnerTicket, lottoWinnerBonusNumber)).isInstanceOf(LottoWinner.class);
    }

    @ParameterizedTest(name = "Null은 생성자의 매개변수로 허용하지 않는다.")
    @NullSource
    public void nullNotAllowedTest(LottoTicket lottoWinnerTicket) {
        LottoNumber lottoWinnerBonusNumber = new LottoNumber(1);

        assertThatThrownBy(() -> {
            new LottoWinner(lottoWinnerTicket, lottoWinnerBonusNumber);
        }).isInstanceOf(NullPointerException.class).hasMessage("null 값은 허용하지 않습니다.");
    }

    @Test
    @DisplayName("6개의 당첨 숫자 내에 중복이 있으면 예외 발생")
    public void duplicateWinnerNumberTest() {
        List<LottoNumber> lottoWinnerNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(5)
        );

        assertThatThrownBy(() -> {
            new LottoWinner(new LottoTicket(lottoWinnerNumbers), new LottoNumber(9));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보너스 숫자가 6개의 당첨 숫자 내에 중복이 있으면 예외 발생")
    public void WhenBonusNumberInWinnerNumberThrowsException() {
        List<LottoNumber> lottoWinnerNumbers = Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        );

        assertThatThrownBy(() -> {
            new LottoWinner(new LottoTicket(lottoWinnerNumbers), new LottoNumber(6));
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
