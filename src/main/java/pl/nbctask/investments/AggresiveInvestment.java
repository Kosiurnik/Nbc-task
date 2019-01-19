package pl.nbctask.investments;

import java.util.HashMap;
import java.util.Map;
import pl.nbctask.enums.FundType;

/**
 *
 * @author plewandowski
 */
public class AggresiveInvestment extends Investment {

    @Override
    public Map<FundType, Integer> getPercentages() {
        Map<FundType, Integer> percentages = new HashMap<>();

        percentages.put(FundType.POLISH, 20);
        percentages.put(FundType.FOREIGN, 40);
        percentages.put(FundType.MONETARY, 40);

        return percentages;
    }
}
