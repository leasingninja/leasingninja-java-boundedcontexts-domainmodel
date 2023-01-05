package io.leasingninja.sales.domain;

import org.jmolecules.ddd.annotation.Service;

/**
 * Simulates the infamous HP12c calculator that is widely used in the leasing industry.
 */
@Service
public class FinancialCalculator {

	public static double pmt(double n, double iInPercent, double pv, double fv, double s) {
		double i = iInPercent / 100;

        if (i == 0) {
            return (-1 * pv - fv) / n;
        }

		return (i * (fv + pv * Math.pow(1 + i, n))) / ((1 + i * s) * (1 - Math.pow(1 + i, n)));
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
