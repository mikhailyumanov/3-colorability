import com.mikhailyumanov.three_colorability.csp_instance.CSPInstance;
import com.mikhailyumanov.three_colorability.parser.InputParser;

import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    CSPInstance instance = InputParser.parse("src/main/resources/csp_instance/Lemma2TestWithCrossConstraints");
  }
}
