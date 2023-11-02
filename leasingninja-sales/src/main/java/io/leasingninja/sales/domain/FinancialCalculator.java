package io.leasingninja.sales.domain;

import org.jmolecules.ddd.annotation.Service;

/**
 * Simulates the infamous HP12c calculator that is widely used in the leasing industry.
 */
@Service
public class FinancialCalculator {

    /**
     * @param n number of periods
     * @param iInPercent percentage interest rate per period
     * @param pv present value
     * @param fv future value
     * @param s
     * @return payment per period
     */
	public static double pmt(double n, double iInPercent, double pv, double fv, double s) {
		double i = iInPercent / 100.0;

        return pmtWithDecimalInterestRate(n, i, pv, fv, s);
	}

    /**
     * @param n number of periods
     * @param i decimal interest rate per period
     * @param pv present value
     * @param fv future value
     * @param s
     * @return payment per period
     */
    private static double pmtWithDecimalInterestRate(double n, double i, double pv, double fv, double s) {
        if (i == 0.0) {
            return (-1.0 * pv - fv) / n;
        }

		return (i * (fv + pv * Math.pow(1.0 + i, n))) / ((1.0 + i * s) * (1.0 - Math.pow(1.0 + i, n)));
    }

}

// Alternative implementation:

        /*
		return
			(-1 * pv * Math.pow(1 + i, n) - fv)
			/
			(
				(1 + i * s)
				*
				(
					(Math.pow(1 + i, n) - 1)
					/
					i
				)
			);
		*/
