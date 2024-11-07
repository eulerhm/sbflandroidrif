package com.health.openscale.core.utils;

import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/*
 * Changelog:
 * 20100130: Add note about relicensing
 * 20091114: Modify so that points can be added after the curve is
 *           created, also some other minor fixes
 * 20091113: Extensively modified by Ian Clarke, main changes:
 *     - Should now be able to handle extremely large datasets
 *     - Use generic Java collections classes and interfaces
 *       where possible
 *     - Data can be fed to the fitter as it is available, rather
 *       than all at once
 *
 * The code that this is based on was obtained from: http://arachnoid.com/polysolve
 *
 * Note: I (Ian Clarke) am happy to release this code under a more liberal
 * license such as the LGPL, however Paul Lutus (the primary author) refuses
 * to do this on the grounds that the LGPL is not an open source license.
 * If you want to try to explain to him that the LGPL is indeed an open
 * source license, good luck - it's like talking to a brick wall.
 */
public class PolynomialFitter {

    private final int p, rs;

    private long n = 0;

    private final double[][] m;

    private final double[] mpc;

    /**
     * @param degree
     *            The degree of the polynomial to be fit to the data
     */
    public PolynomialFitter(final int degree) {
        assert (ListenerUtil.mutListener.listen(5769) ? (degree >= 0) : (ListenerUtil.mutListener.listen(5768) ? (degree <= 0) : (ListenerUtil.mutListener.listen(5767) ? (degree < 0) : (ListenerUtil.mutListener.listen(5766) ? (degree != 0) : (ListenerUtil.mutListener.listen(5765) ? (degree == 0) : (degree > 0))))));
        p = (ListenerUtil.mutListener.listen(5773) ? (degree % 1) : (ListenerUtil.mutListener.listen(5772) ? (degree / 1) : (ListenerUtil.mutListener.listen(5771) ? (degree * 1) : (ListenerUtil.mutListener.listen(5770) ? (degree - 1) : (degree + 1)))));
        rs = (ListenerUtil.mutListener.listen(5781) ? ((ListenerUtil.mutListener.listen(5777) ? (2 % p) : (ListenerUtil.mutListener.listen(5776) ? (2 / p) : (ListenerUtil.mutListener.listen(5775) ? (2 - p) : (ListenerUtil.mutListener.listen(5774) ? (2 + p) : (2 * p))))) % 1) : (ListenerUtil.mutListener.listen(5780) ? ((ListenerUtil.mutListener.listen(5777) ? (2 % p) : (ListenerUtil.mutListener.listen(5776) ? (2 / p) : (ListenerUtil.mutListener.listen(5775) ? (2 - p) : (ListenerUtil.mutListener.listen(5774) ? (2 + p) : (2 * p))))) / 1) : (ListenerUtil.mutListener.listen(5779) ? ((ListenerUtil.mutListener.listen(5777) ? (2 % p) : (ListenerUtil.mutListener.listen(5776) ? (2 / p) : (ListenerUtil.mutListener.listen(5775) ? (2 - p) : (ListenerUtil.mutListener.listen(5774) ? (2 + p) : (2 * p))))) * 1) : (ListenerUtil.mutListener.listen(5778) ? ((ListenerUtil.mutListener.listen(5777) ? (2 % p) : (ListenerUtil.mutListener.listen(5776) ? (2 / p) : (ListenerUtil.mutListener.listen(5775) ? (2 - p) : (ListenerUtil.mutListener.listen(5774) ? (2 + p) : (2 * p))))) + 1) : ((ListenerUtil.mutListener.listen(5777) ? (2 % p) : (ListenerUtil.mutListener.listen(5776) ? (2 / p) : (ListenerUtil.mutListener.listen(5775) ? (2 - p) : (ListenerUtil.mutListener.listen(5774) ? (2 + p) : (2 * p))))) - 1)))));
        m = new double[p][(ListenerUtil.mutListener.listen(5785) ? (p % 1) : (ListenerUtil.mutListener.listen(5784) ? (p / 1) : (ListenerUtil.mutListener.listen(5783) ? (p * 1) : (ListenerUtil.mutListener.listen(5782) ? (p - 1) : (p + 1)))))];
        mpc = new double[rs];
    }

    /**
     * Add a point to the set of points that the polynomial must be fit to
     *
     * @param x
     *            The x coordinate of the point
     * @param y
     *            The y coordinate of the point
     */
    public void addPoint(final double x, final double y) {
        assert (ListenerUtil.mutListener.listen(5786) ? (!Double.isInfinite(x) || !Double.isNaN(x)) : (!Double.isInfinite(x) && !Double.isNaN(x)));
        assert (ListenerUtil.mutListener.listen(5787) ? (!Double.isInfinite(y) || !Double.isNaN(y)) : (!Double.isInfinite(y) && !Double.isNaN(y)));
        if (!ListenerUtil.mutListener.listen(5788)) {
            n++;
        }
        if (!ListenerUtil.mutListener.listen(5795)) {
            {
                long _loopCounter48 = 0;
                // process precalculation array
                for (int r = 1; (ListenerUtil.mutListener.listen(5794) ? (r >= rs) : (ListenerUtil.mutListener.listen(5793) ? (r <= rs) : (ListenerUtil.mutListener.listen(5792) ? (r > rs) : (ListenerUtil.mutListener.listen(5791) ? (r != rs) : (ListenerUtil.mutListener.listen(5790) ? (r == rs) : (r < rs)))))); r++) {
                    ListenerUtil.loopListener.listen("_loopCounter48", ++_loopCounter48);
                    if (!ListenerUtil.mutListener.listen(5789)) {
                        mpc[r] += Math.pow(x, r);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5796)) {
            // process RH column cells
            m[0][p] += y;
        }
        if (!ListenerUtil.mutListener.listen(5807)) {
            {
                long _loopCounter49 = 0;
                for (int r = 1; (ListenerUtil.mutListener.listen(5806) ? (r >= p) : (ListenerUtil.mutListener.listen(5805) ? (r <= p) : (ListenerUtil.mutListener.listen(5804) ? (r > p) : (ListenerUtil.mutListener.listen(5803) ? (r != p) : (ListenerUtil.mutListener.listen(5802) ? (r == p) : (r < p)))))); r++) {
                    ListenerUtil.loopListener.listen("_loopCounter49", ++_loopCounter49);
                    if (!ListenerUtil.mutListener.listen(5801)) {
                        m[r][p] += (ListenerUtil.mutListener.listen(5800) ? (Math.pow(x, r) % y) : (ListenerUtil.mutListener.listen(5799) ? (Math.pow(x, r) / y) : (ListenerUtil.mutListener.listen(5798) ? (Math.pow(x, r) - y) : (ListenerUtil.mutListener.listen(5797) ? (Math.pow(x, r) + y) : (Math.pow(x, r) * y)))));
                    }
                }
            }
        }
    }

    /**
     * Returns a polynomial that seeks to minimize the square of the total
     * distance between the set of points and the polynomial.
     *
     * @return A polynomial
     */
    public Polynomial getBestFit() {
        final double[] mpcClone = mpc.clone();
        final double[][] mClone = new double[m.length][];
        if (!ListenerUtil.mutListener.listen(5814)) {
            {
                long _loopCounter50 = 0;
                for (int x = 0; (ListenerUtil.mutListener.listen(5813) ? (x >= mClone.length) : (ListenerUtil.mutListener.listen(5812) ? (x <= mClone.length) : (ListenerUtil.mutListener.listen(5811) ? (x > mClone.length) : (ListenerUtil.mutListener.listen(5810) ? (x != mClone.length) : (ListenerUtil.mutListener.listen(5809) ? (x == mClone.length) : (x < mClone.length)))))); x++) {
                    ListenerUtil.loopListener.listen("_loopCounter50", ++_loopCounter50);
                    if (!ListenerUtil.mutListener.listen(5808)) {
                        mClone[x] = m[x].clone();
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5815)) {
            mpcClone[0] += n;
        }
        if (!ListenerUtil.mutListener.listen(5832)) {
            {
                long _loopCounter52 = 0;
                // populate square matrix section
                for (int r = 0; (ListenerUtil.mutListener.listen(5831) ? (r >= p) : (ListenerUtil.mutListener.listen(5830) ? (r <= p) : (ListenerUtil.mutListener.listen(5829) ? (r > p) : (ListenerUtil.mutListener.listen(5828) ? (r != p) : (ListenerUtil.mutListener.listen(5827) ? (r == p) : (r < p)))))); r++) {
                    ListenerUtil.loopListener.listen("_loopCounter52", ++_loopCounter52);
                    if (!ListenerUtil.mutListener.listen(5826)) {
                        {
                            long _loopCounter51 = 0;
                            for (int c = 0; (ListenerUtil.mutListener.listen(5825) ? (c >= p) : (ListenerUtil.mutListener.listen(5824) ? (c <= p) : (ListenerUtil.mutListener.listen(5823) ? (c > p) : (ListenerUtil.mutListener.listen(5822) ? (c != p) : (ListenerUtil.mutListener.listen(5821) ? (c == p) : (c < p)))))); c++) {
                                ListenerUtil.loopListener.listen("_loopCounter51", ++_loopCounter51);
                                if (!ListenerUtil.mutListener.listen(5820)) {
                                    mClone[r][c] = mpcClone[(ListenerUtil.mutListener.listen(5819) ? (r % c) : (ListenerUtil.mutListener.listen(5818) ? (r / c) : (ListenerUtil.mutListener.listen(5817) ? (r * c) : (ListenerUtil.mutListener.listen(5816) ? (r - c) : (r + c)))))];
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5833)) {
            gj_echelonize(mClone);
        }
        final Polynomial result = new Polynomial(p);
        if (!ListenerUtil.mutListener.listen(5840)) {
            {
                long _loopCounter53 = 0;
                for (int j = 0; (ListenerUtil.mutListener.listen(5839) ? (j >= p) : (ListenerUtil.mutListener.listen(5838) ? (j <= p) : (ListenerUtil.mutListener.listen(5837) ? (j > p) : (ListenerUtil.mutListener.listen(5836) ? (j != p) : (ListenerUtil.mutListener.listen(5835) ? (j == p) : (j < p)))))); j++) {
                    ListenerUtil.loopListener.listen("_loopCounter53", ++_loopCounter53);
                    if (!ListenerUtil.mutListener.listen(5834)) {
                        result.add(j, mClone[j][p]);
                    }
                }
            }
        }
        return result;
    }

    private double fx(final double x, final List<Double> terms) {
        double a = 0;
        int e = 0;
        if (!ListenerUtil.mutListener.listen(5847)) {
            {
                long _loopCounter54 = 0;
                for (final double i : terms) {
                    ListenerUtil.loopListener.listen("_loopCounter54", ++_loopCounter54);
                    if (!ListenerUtil.mutListener.listen(5845)) {
                        a += (ListenerUtil.mutListener.listen(5844) ? (i % Math.pow(x, e)) : (ListenerUtil.mutListener.listen(5843) ? (i / Math.pow(x, e)) : (ListenerUtil.mutListener.listen(5842) ? (i - Math.pow(x, e)) : (ListenerUtil.mutListener.listen(5841) ? (i + Math.pow(x, e)) : (i * Math.pow(x, e))))));
                    }
                    if (!ListenerUtil.mutListener.listen(5846)) {
                        e++;
                    }
                }
            }
        }
        return a;
    }

    private void gj_divide(final double[][] A, final int i, final int j, final int m) {
        if (!ListenerUtil.mutListener.listen(5858)) {
            {
                long _loopCounter55 = 0;
                for (int q = (ListenerUtil.mutListener.listen(5857) ? (j % 1) : (ListenerUtil.mutListener.listen(5856) ? (j / 1) : (ListenerUtil.mutListener.listen(5855) ? (j * 1) : (ListenerUtil.mutListener.listen(5854) ? (j - 1) : (j + 1))))); (ListenerUtil.mutListener.listen(5853) ? (q >= m) : (ListenerUtil.mutListener.listen(5852) ? (q <= m) : (ListenerUtil.mutListener.listen(5851) ? (q > m) : (ListenerUtil.mutListener.listen(5850) ? (q != m) : (ListenerUtil.mutListener.listen(5849) ? (q == m) : (q < m)))))); q++) {
                    ListenerUtil.loopListener.listen("_loopCounter55", ++_loopCounter55);
                    if (!ListenerUtil.mutListener.listen(5848)) {
                        A[i][q] /= A[i][j];
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5859)) {
            A[i][j] = 1;
        }
    }

    private void gj_echelonize(final double[][] A) {
        final int n = A.length;
        final int m = A[0].length;
        int i = 0;
        int j = 0;
        if (!ListenerUtil.mutListener.listen(5907)) {
            {
                long _loopCounter57 = 0;
                while ((ListenerUtil.mutListener.listen(5906) ? ((ListenerUtil.mutListener.listen(5900) ? (i >= n) : (ListenerUtil.mutListener.listen(5899) ? (i <= n) : (ListenerUtil.mutListener.listen(5898) ? (i > n) : (ListenerUtil.mutListener.listen(5897) ? (i != n) : (ListenerUtil.mutListener.listen(5896) ? (i == n) : (i < n)))))) || (ListenerUtil.mutListener.listen(5905) ? (j >= m) : (ListenerUtil.mutListener.listen(5904) ? (j <= m) : (ListenerUtil.mutListener.listen(5903) ? (j > m) : (ListenerUtil.mutListener.listen(5902) ? (j != m) : (ListenerUtil.mutListener.listen(5901) ? (j == m) : (j < m))))))) : ((ListenerUtil.mutListener.listen(5900) ? (i >= n) : (ListenerUtil.mutListener.listen(5899) ? (i <= n) : (ListenerUtil.mutListener.listen(5898) ? (i > n) : (ListenerUtil.mutListener.listen(5897) ? (i != n) : (ListenerUtil.mutListener.listen(5896) ? (i == n) : (i < n)))))) && (ListenerUtil.mutListener.listen(5905) ? (j >= m) : (ListenerUtil.mutListener.listen(5904) ? (j <= m) : (ListenerUtil.mutListener.listen(5903) ? (j > m) : (ListenerUtil.mutListener.listen(5902) ? (j != m) : (ListenerUtil.mutListener.listen(5901) ? (j == m) : (j < m))))))))) {
                    ListenerUtil.loopListener.listen("_loopCounter57", ++_loopCounter57);
                    // look for a non-zero entry in col j at or below row i
                    int k = i;
                    if (!ListenerUtil.mutListener.listen(5872)) {
                        {
                            long _loopCounter56 = 0;
                            while ((ListenerUtil.mutListener.listen(5871) ? ((ListenerUtil.mutListener.listen(5865) ? (k >= n) : (ListenerUtil.mutListener.listen(5864) ? (k <= n) : (ListenerUtil.mutListener.listen(5863) ? (k > n) : (ListenerUtil.mutListener.listen(5862) ? (k != n) : (ListenerUtil.mutListener.listen(5861) ? (k == n) : (k < n)))))) || (ListenerUtil.mutListener.listen(5870) ? (A[k][j] >= 0) : (ListenerUtil.mutListener.listen(5869) ? (A[k][j] <= 0) : (ListenerUtil.mutListener.listen(5868) ? (A[k][j] > 0) : (ListenerUtil.mutListener.listen(5867) ? (A[k][j] < 0) : (ListenerUtil.mutListener.listen(5866) ? (A[k][j] != 0) : (A[k][j] == 0))))))) : ((ListenerUtil.mutListener.listen(5865) ? (k >= n) : (ListenerUtil.mutListener.listen(5864) ? (k <= n) : (ListenerUtil.mutListener.listen(5863) ? (k > n) : (ListenerUtil.mutListener.listen(5862) ? (k != n) : (ListenerUtil.mutListener.listen(5861) ? (k == n) : (k < n)))))) && (ListenerUtil.mutListener.listen(5870) ? (A[k][j] >= 0) : (ListenerUtil.mutListener.listen(5869) ? (A[k][j] <= 0) : (ListenerUtil.mutListener.listen(5868) ? (A[k][j] > 0) : (ListenerUtil.mutListener.listen(5867) ? (A[k][j] < 0) : (ListenerUtil.mutListener.listen(5866) ? (A[k][j] != 0) : (A[k][j] == 0))))))))) {
                                ListenerUtil.loopListener.listen("_loopCounter56", ++_loopCounter56);
                                if (!ListenerUtil.mutListener.listen(5860)) {
                                    k++;
                                }
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(5894)) {
                        // if such an entry is found at row k
                        if ((ListenerUtil.mutListener.listen(5877) ? (k >= n) : (ListenerUtil.mutListener.listen(5876) ? (k <= n) : (ListenerUtil.mutListener.listen(5875) ? (k > n) : (ListenerUtil.mutListener.listen(5874) ? (k != n) : (ListenerUtil.mutListener.listen(5873) ? (k == n) : (k < n))))))) {
                            if (!ListenerUtil.mutListener.listen(5884)) {
                                // if k is not i, then swap row i with row k
                                if ((ListenerUtil.mutListener.listen(5882) ? (k >= i) : (ListenerUtil.mutListener.listen(5881) ? (k <= i) : (ListenerUtil.mutListener.listen(5880) ? (k > i) : (ListenerUtil.mutListener.listen(5879) ? (k < i) : (ListenerUtil.mutListener.listen(5878) ? (k == i) : (k != i))))))) {
                                    if (!ListenerUtil.mutListener.listen(5883)) {
                                        gj_swap(A, i, j);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(5891)) {
                                // if A[i][j] is not 1, then divide row i by A[i][j]
                                if ((ListenerUtil.mutListener.listen(5889) ? (A[i][j] >= 1) : (ListenerUtil.mutListener.listen(5888) ? (A[i][j] <= 1) : (ListenerUtil.mutListener.listen(5887) ? (A[i][j] > 1) : (ListenerUtil.mutListener.listen(5886) ? (A[i][j] < 1) : (ListenerUtil.mutListener.listen(5885) ? (A[i][j] == 1) : (A[i][j] != 1))))))) {
                                    if (!ListenerUtil.mutListener.listen(5890)) {
                                        gj_divide(A, i, j, m);
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(5892)) {
                                // row (other than i) an appropriate multiple of row i
                                gj_eliminate(A, i, j, n, m);
                            }
                            if (!ListenerUtil.mutListener.listen(5893)) {
                                i++;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(5895)) {
                        j++;
                    }
                }
            }
        }
    }

    private void gj_eliminate(final double[][] A, final int i, final int j, final int n, final int m) {
        if (!ListenerUtil.mutListener.listen(5941)) {
            {
                long _loopCounter59 = 0;
                for (int k = 0; (ListenerUtil.mutListener.listen(5940) ? (k >= n) : (ListenerUtil.mutListener.listen(5939) ? (k <= n) : (ListenerUtil.mutListener.listen(5938) ? (k > n) : (ListenerUtil.mutListener.listen(5937) ? (k != n) : (ListenerUtil.mutListener.listen(5936) ? (k == n) : (k < n)))))); k++) {
                    ListenerUtil.loopListener.listen("_loopCounter59", ++_loopCounter59);
                    if (!ListenerUtil.mutListener.listen(5935)) {
                        if ((ListenerUtil.mutListener.listen(5918) ? ((ListenerUtil.mutListener.listen(5912) ? (k >= i) : (ListenerUtil.mutListener.listen(5911) ? (k <= i) : (ListenerUtil.mutListener.listen(5910) ? (k > i) : (ListenerUtil.mutListener.listen(5909) ? (k < i) : (ListenerUtil.mutListener.listen(5908) ? (k == i) : (k != i)))))) || (ListenerUtil.mutListener.listen(5917) ? (A[k][j] >= 0) : (ListenerUtil.mutListener.listen(5916) ? (A[k][j] <= 0) : (ListenerUtil.mutListener.listen(5915) ? (A[k][j] > 0) : (ListenerUtil.mutListener.listen(5914) ? (A[k][j] < 0) : (ListenerUtil.mutListener.listen(5913) ? (A[k][j] == 0) : (A[k][j] != 0))))))) : ((ListenerUtil.mutListener.listen(5912) ? (k >= i) : (ListenerUtil.mutListener.listen(5911) ? (k <= i) : (ListenerUtil.mutListener.listen(5910) ? (k > i) : (ListenerUtil.mutListener.listen(5909) ? (k < i) : (ListenerUtil.mutListener.listen(5908) ? (k == i) : (k != i)))))) && (ListenerUtil.mutListener.listen(5917) ? (A[k][j] >= 0) : (ListenerUtil.mutListener.listen(5916) ? (A[k][j] <= 0) : (ListenerUtil.mutListener.listen(5915) ? (A[k][j] > 0) : (ListenerUtil.mutListener.listen(5914) ? (A[k][j] < 0) : (ListenerUtil.mutListener.listen(5913) ? (A[k][j] == 0) : (A[k][j] != 0))))))))) {
                            if (!ListenerUtil.mutListener.listen(5933)) {
                                {
                                    long _loopCounter58 = 0;
                                    for (int q = (ListenerUtil.mutListener.listen(5932) ? (j % 1) : (ListenerUtil.mutListener.listen(5931) ? (j / 1) : (ListenerUtil.mutListener.listen(5930) ? (j * 1) : (ListenerUtil.mutListener.listen(5929) ? (j - 1) : (j + 1))))); (ListenerUtil.mutListener.listen(5928) ? (q >= m) : (ListenerUtil.mutListener.listen(5927) ? (q <= m) : (ListenerUtil.mutListener.listen(5926) ? (q > m) : (ListenerUtil.mutListener.listen(5925) ? (q != m) : (ListenerUtil.mutListener.listen(5924) ? (q == m) : (q < m)))))); q++) {
                                        ListenerUtil.loopListener.listen("_loopCounter58", ++_loopCounter58);
                                        if (!ListenerUtil.mutListener.listen(5923)) {
                                            A[k][q] -= (ListenerUtil.mutListener.listen(5922) ? (A[k][j] % A[i][q]) : (ListenerUtil.mutListener.listen(5921) ? (A[k][j] / A[i][q]) : (ListenerUtil.mutListener.listen(5920) ? (A[k][j] - A[i][q]) : (ListenerUtil.mutListener.listen(5919) ? (A[k][j] + A[i][q]) : (A[k][j] * A[i][q])))));
                                        }
                                    }
                                }
                            }
                            if (!ListenerUtil.mutListener.listen(5934)) {
                                A[k][j] = 0;
                            }
                        }
                    }
                }
            }
        }
    }

    private void gj_swap(final double[][] A, final int i, final int j) {
        double[] temp;
        temp = A[i];
        if (!ListenerUtil.mutListener.listen(5942)) {
            A[i] = A[j];
        }
        if (!ListenerUtil.mutListener.listen(5943)) {
            A[j] = temp;
        }
    }

    public static class Polynomial extends ArrayList<Double> {

        private static final long serialVersionUID = 1692843494322684190L;

        public Polynomial(final int p) {
            super(p);
        }

        public double getY(final double x) {
            double ret = 0;
            if (!ListenerUtil.mutListener.listen(5954)) {
                {
                    long _loopCounter60 = 0;
                    for (int p = 0; (ListenerUtil.mutListener.listen(5953) ? (p >= size()) : (ListenerUtil.mutListener.listen(5952) ? (p <= size()) : (ListenerUtil.mutListener.listen(5951) ? (p > size()) : (ListenerUtil.mutListener.listen(5950) ? (p != size()) : (ListenerUtil.mutListener.listen(5949) ? (p == size()) : (p < size())))))); p++) {
                        ListenerUtil.loopListener.listen("_loopCounter60", ++_loopCounter60);
                        if (!ListenerUtil.mutListener.listen(5948)) {
                            ret += (ListenerUtil.mutListener.listen(5947) ? (get(p) % (Math.pow(x, p))) : (ListenerUtil.mutListener.listen(5946) ? (get(p) / (Math.pow(x, p))) : (ListenerUtil.mutListener.listen(5945) ? (get(p) - (Math.pow(x, p))) : (ListenerUtil.mutListener.listen(5944) ? (get(p) + (Math.pow(x, p))) : (get(p) * (Math.pow(x, p)))))));
                        }
                    }
                }
            }
            return ret;
        }

        @Override
        public String toString() {
            final StringBuilder ret = new StringBuilder();
            if (!ListenerUtil.mutListener.listen(5970)) {
                {
                    long _loopCounter61 = 0;
                    for (int x = (ListenerUtil.mutListener.listen(5969) ? (size() % 1) : (ListenerUtil.mutListener.listen(5968) ? (size() / 1) : (ListenerUtil.mutListener.listen(5967) ? (size() * 1) : (ListenerUtil.mutListener.listen(5966) ? (size() + 1) : (size() - 1))))); (ListenerUtil.mutListener.listen(5965) ? (x >= -1) : (ListenerUtil.mutListener.listen(5964) ? (x <= -1) : (ListenerUtil.mutListener.listen(5963) ? (x < -1) : (ListenerUtil.mutListener.listen(5962) ? (x != -1) : (ListenerUtil.mutListener.listen(5961) ? (x == -1) : (x > -1)))))); x--) {
                        ListenerUtil.loopListener.listen("_loopCounter61", ++_loopCounter61);
                        if (!ListenerUtil.mutListener.listen(5960)) {
                            ret.append(get(x) + ((ListenerUtil.mutListener.listen(5959) ? (x >= 0) : (ListenerUtil.mutListener.listen(5958) ? (x <= 0) : (ListenerUtil.mutListener.listen(5957) ? (x < 0) : (ListenerUtil.mutListener.listen(5956) ? (x != 0) : (ListenerUtil.mutListener.listen(5955) ? (x == 0) : (x > 0)))))) ? "x^" + x + " + " : ""));
                        }
                    }
                }
            }
            return ret.toString();
        }
    }
}
