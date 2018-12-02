package pl.nbctask.model;

import pl.nbctask.model.ReportRow;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author plewandowski
 */
public class Report {

    private List<ReportRow> reportRows = new ArrayList<>();
    private int unnasignedAmount = 0;

    public Report addReportRow(ReportRow reportRow) {
        reportRows.add(reportRow);
        return this;
    }

    public void setUnnasignedAmount(int value) {
        unnasignedAmount = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        reportRows.forEach(row -> {
            sb.append(row);
            sb.append("\n");
        });

        sb.append("unnasignedAmount ");
        sb.append(unnasignedAmount);

        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.reportRows);
        hash = 61 * hash + this.unnasignedAmount;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Report other = (Report) obj;
        if (this.unnasignedAmount != other.unnasignedAmount) {
            return false;
        }
        if (!Objects.equals(this.reportRows, other.reportRows)) {
            return false;
        }
        return true;
    }

}