/**
 * *************************************************************************************
 *  Copyright (c) 2014 Michael Goldbach <michael@wildplot.com>                           *
 *                                                                                       *
 *  This program is free software; you can redistribute it and/or modify it under        *
 *  the terms of the GNU General Public License as published by the Free Software        *
 *  Foundation; either version 3 of the License, or (at your option) any later           *
 *  version.                                                                             *
 *                                                                                       *
 *  This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 *  PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                       *
 *  You should have received a copy of the GNU General Public License along with         *
 *  this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 * **************************************************************************************
 */
package com.wildplot.android.parsing;

import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class Factor implements TreeElement {

    private final TopLevelParser parser;

    public enum FactorType {

        PLUS_FACTOR, MINUS_FACTOR, POW, INVALID
    }

    private FactorType factorType = FactorType.INVALID;

    private Factor factor;

    private Pow pow;

    public Factor(String factorString, TopLevelParser parser) {
        this.parser = parser;
        if (!ListenerUtil.mutListener.listen(26568)) {
            if (!TopLevelParser.stringHasValidBrackets(factorString)) {
                if (!ListenerUtil.mutListener.listen(26567)) {
                    this.factorType = FactorType.INVALID;
                }
                return;
            }
        }
        boolean isReady = initAsPlusFactor(factorString);
        if (!ListenerUtil.mutListener.listen(26570)) {
            if (!isReady) {
                if (!ListenerUtil.mutListener.listen(26569)) {
                    isReady = initAsMinusFactor(factorString);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26572)) {
            if (!isReady) {
                if (!ListenerUtil.mutListener.listen(26571)) {
                    isReady = initAsPow(factorString);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26574)) {
            if (!isReady) {
                if (!ListenerUtil.mutListener.listen(26573)) {
                    this.factorType = FactorType.INVALID;
                }
            }
        }
    }

    private boolean initAsPlusFactor(String factorString) {
        if (!ListenerUtil.mutListener.listen(26584)) {
            if ((ListenerUtil.mutListener.listen(26580) ? ((ListenerUtil.mutListener.listen(26579) ? (factorString.length() >= 0) : (ListenerUtil.mutListener.listen(26578) ? (factorString.length() <= 0) : (ListenerUtil.mutListener.listen(26577) ? (factorString.length() < 0) : (ListenerUtil.mutListener.listen(26576) ? (factorString.length() != 0) : (ListenerUtil.mutListener.listen(26575) ? (factorString.length() == 0) : (factorString.length() > 0)))))) || factorString.charAt(0) == '+') : ((ListenerUtil.mutListener.listen(26579) ? (factorString.length() >= 0) : (ListenerUtil.mutListener.listen(26578) ? (factorString.length() <= 0) : (ListenerUtil.mutListener.listen(26577) ? (factorString.length() < 0) : (ListenerUtil.mutListener.listen(26576) ? (factorString.length() != 0) : (ListenerUtil.mutListener.listen(26575) ? (factorString.length() == 0) : (factorString.length() > 0)))))) && factorString.charAt(0) == '+'))) {
                String leftSubString = factorString.substring(1);
                Factor leftFactor = new Factor(leftSubString, parser);
                boolean isValidFactor = leftFactor.getFactorType() != FactorType.INVALID;
                if (!ListenerUtil.mutListener.listen(26583)) {
                    if (isValidFactor) {
                        if (!ListenerUtil.mutListener.listen(26581)) {
                            this.factorType = FactorType.PLUS_FACTOR;
                        }
                        if (!ListenerUtil.mutListener.listen(26582)) {
                            this.factor = leftFactor;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean initAsMinusFactor(String factorString) {
        if (!ListenerUtil.mutListener.listen(26594)) {
            if ((ListenerUtil.mutListener.listen(26590) ? ((ListenerUtil.mutListener.listen(26589) ? (factorString.length() >= 0) : (ListenerUtil.mutListener.listen(26588) ? (factorString.length() <= 0) : (ListenerUtil.mutListener.listen(26587) ? (factorString.length() < 0) : (ListenerUtil.mutListener.listen(26586) ? (factorString.length() != 0) : (ListenerUtil.mutListener.listen(26585) ? (factorString.length() == 0) : (factorString.length() > 0)))))) || factorString.charAt(0) == '-') : ((ListenerUtil.mutListener.listen(26589) ? (factorString.length() >= 0) : (ListenerUtil.mutListener.listen(26588) ? (factorString.length() <= 0) : (ListenerUtil.mutListener.listen(26587) ? (factorString.length() < 0) : (ListenerUtil.mutListener.listen(26586) ? (factorString.length() != 0) : (ListenerUtil.mutListener.listen(26585) ? (factorString.length() == 0) : (factorString.length() > 0)))))) && factorString.charAt(0) == '-'))) {
                String leftSubString = factorString.substring(1);
                Factor leftFactor = new Factor(leftSubString, parser);
                boolean isValidFactor = leftFactor.getFactorType() != FactorType.INVALID;
                if (!ListenerUtil.mutListener.listen(26593)) {
                    if (isValidFactor) {
                        if (!ListenerUtil.mutListener.listen(26591)) {
                            this.factorType = FactorType.MINUS_FACTOR;
                        }
                        if (!ListenerUtil.mutListener.listen(26592)) {
                            this.factor = leftFactor;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean initAsPow(String factorString) {
        Pow pow = new Pow(factorString, parser);
        boolean isValidPow = pow.getPowType() != Pow.PowType.INVALID;
        if (!ListenerUtil.mutListener.listen(26597)) {
            if (isValidPow) {
                if (!ListenerUtil.mutListener.listen(26595)) {
                    this.factorType = FactorType.POW;
                }
                if (!ListenerUtil.mutListener.listen(26596)) {
                    this.pow = pow;
                }
                return true;
            }
        }
        return false;
    }

    public FactorType getFactorType() {
        return factorType;
    }

    public double getValue() throws ExpressionFormatException {
        switch(factorType) {
            case PLUS_FACTOR:
                return factor.getValue();
            case MINUS_FACTOR:
                return -factor.getValue();
            case POW:
                return pow.getValue();
            case INVALID:
            default:
                throw new ExpressionFormatException("cannot parse expression at factor level");
        }
    }

    @Override
    public boolean isVariable() throws ExpressionFormatException {
        switch(factorType) {
            case PLUS_FACTOR:
            case MINUS_FACTOR:
                return factor.isVariable();
            case POW:
                return pow.isVariable();
            case INVALID:
            default:
                throw new ExpressionFormatException("cannot parse expression at factor level");
        }
    }
}
