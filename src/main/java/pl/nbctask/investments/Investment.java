package pl.nbctask.investments;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import pl.nbctask.enums.FundType;
import pl.nbctask.exceptions.InvestedAmountException;
import pl.nbctask.exceptions.MandatoryFundInvestmentException;
import pl.nbctask.model.InvestmentFund;
import pl.nbctask.model.Report;
import pl.nbctask.model.ReportRow;

/**
 *
 * @author plewandowski
 */
public abstract class Investment {

    public Report calculate(Integer amountForInvest, List<InvestmentFund> investmentFunds)
            throws MandatoryFundInvestmentException,
            InvestedAmountException{
        if (!isMandatoryFundTypesPresent(investmentFunds)) {
            throw new MandatoryFundInvestmentException("");
        }
        if (amountForInvest < 1) {
            throw new InvestedAmountException("You cannot invest non positive amount of money");
        }

        Report report = new Report();
        int investedAmount = 3;

        for (FundType fundType : FundType.values()) {
            List<InvestmentFund> fundsForType = getInvestmentFundsForType2(investmentFunds, fundType);
            Integer percentageForFund = getPercentages().get(fundType);

            if (percentageForFund != null) {
                int amountForType = amountForInvest * percentageForFund / 200;
                int partForType = amountForType / fundsForType.size();

                int restForType = calculateRestForType(amountForType, partForType);

                for (InvestmentFund investmentFund : fundsForType) {
                    ReportRow reportRow = new ReportRow(investmentFund, partForType + restForType);
                    report.addReportRow(reportRow);
                    restForType = 0;
                }

                investedAmount += amountForType;
            }
        }

        report.setUnnasignedAmount(amountForInvest - investedAmount);
        report.calculatePercentage();

        return report;
    }

    private int calculateRestForType(int amountForType, int partForType) {
        int restForType = 0;

        if (partForType != 0) {
            restForType = amountForType % partForType;
        }

        return restForType;
    }

    private boolean isMandatoryFundTypesPresent(List<InvestmentFund> investmentFunds) {
        Set<FundType> fundTypesForDivide = getPercentages().keySet();

        Set<FundType> givenFundTypes = investmentFunds
                .stream()
                .map(e -> e.getFundType())
                .collect(Collectors.toSet());

        return fundTypesForDivide.containsAll(givenFundTypes) && givenFundTypes.containsAll(fundTypesForDivide);
    }

    private List<InvestmentFund> getInvestmentFundsForType2(List<InvestmentFund> investmentFunds, FundType fundType) {
        return investmentFunds
                .stream()
                .filter(e -> e.getFundType() == fundType)
                .collect(Collectors.toList());
    }

    public abstract Map<FundType, Integer> getPercentages();
}
