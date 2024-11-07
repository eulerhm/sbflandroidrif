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

public class Term implements TreeElement {

    private final TopLevelParser parser;

    public enum TermType {

        TERM_MUL_FACTOR, TERM_DIV_FACTOR, FACTOR, INVALID
    }

    private TermType termType = TermType.INVALID;

    private Factor factor = null;

    private Term term = null;

    public Term(String termString, TopLevelParser parser) {
        this.parser = parser;
        if (!ListenerUtil.mutListener.listen(26649)) {
            if (!TopLevelParser.stringHasValidBrackets(termString)) {
                if (!ListenerUtil.mutListener.listen(26648)) {
                    this.termType = TermType.INVALID;
                }
                return;
            }
        }
        boolean isReady = initAsTermMulOrDivFactor(termString);
        if (!ListenerUtil.mutListener.listen(26651)) {
            if (!isReady) {
                if (!ListenerUtil.mutListener.listen(26650)) {
                    isReady = initAsFactor(termString);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26653)) {
            if (!isReady) {
                if (!ListenerUtil.mutListener.listen(26652)) {
                    this.termType = TermType.INVALID;
                }
            }
        }
    }

    private boolean initAsTermMulOrDivFactor(String termString) {
        int bracketChecker = 0;
        if (!ListenerUtil.mutListener.listen(26684)) {
            {
                long _loopCounter705 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(26683) ? (i >= termString.length()) : (ListenerUtil.mutListener.listen(26682) ? (i <= termString.length()) : (ListenerUtil.mutListener.listen(26681) ? (i > termString.length()) : (ListenerUtil.mutListener.listen(26680) ? (i != termString.length()) : (ListenerUtil.mutListener.listen(26679) ? (i == termString.length()) : (i < termString.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter705", ++_loopCounter705);
                    if (!ListenerUtil.mutListener.listen(26655)) {
                        if (termString.charAt(i) == '(') {
                            if (!ListenerUtil.mutListener.listen(26654)) {
                                bracketChecker++;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(26657)) {
                        if (termString.charAt(i) == ')') {
                            if (!ListenerUtil.mutListener.listen(26656)) {
                                bracketChecker--;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(26678)) {
                        if ((ListenerUtil.mutListener.listen(26664) ? (((ListenerUtil.mutListener.listen(26658) ? (termString.charAt(i) == '*' && termString.charAt(i) == '/') : (termString.charAt(i) == '*' || termString.charAt(i) == '/'))) || (ListenerUtil.mutListener.listen(26663) ? (bracketChecker >= 0) : (ListenerUtil.mutListener.listen(26662) ? (bracketChecker <= 0) : (ListenerUtil.mutListener.listen(26661) ? (bracketChecker > 0) : (ListenerUtil.mutListener.listen(26660) ? (bracketChecker < 0) : (ListenerUtil.mutListener.listen(26659) ? (bracketChecker != 0) : (bracketChecker == 0))))))) : (((ListenerUtil.mutListener.listen(26658) ? (termString.charAt(i) == '*' && termString.charAt(i) == '/') : (termString.charAt(i) == '*' || termString.charAt(i) == '/'))) && (ListenerUtil.mutListener.listen(26663) ? (bracketChecker >= 0) : (ListenerUtil.mutListener.listen(26662) ? (bracketChecker <= 0) : (ListenerUtil.mutListener.listen(26661) ? (bracketChecker > 0) : (ListenerUtil.mutListener.listen(26660) ? (bracketChecker < 0) : (ListenerUtil.mutListener.listen(26659) ? (bracketChecker != 0) : (bracketChecker == 0))))))))) {
                            String leftSubString = termString.substring(0, i);
                            if (!ListenerUtil.mutListener.listen(26665)) {
                                if (!TopLevelParser.stringHasValidBrackets(leftSubString)) {
                                    continue;
                                }
                            }
                            Term leftTerm = new Term(leftSubString, parser);
                            boolean isValidFirstPartTerm = leftTerm.getTermType() != TermType.INVALID;
                            if (!ListenerUtil.mutListener.listen(26666)) {
                                if (!isValidFirstPartTerm) {
                                    continue;
                                }
                            }
                            String rightSubString = termString.substring((ListenerUtil.mutListener.listen(26670) ? (i % 1) : (ListenerUtil.mutListener.listen(26669) ? (i / 1) : (ListenerUtil.mutListener.listen(26668) ? (i * 1) : (ListenerUtil.mutListener.listen(26667) ? (i - 1) : (i + 1))))));
                            if (!ListenerUtil.mutListener.listen(26671)) {
                                if (!TopLevelParser.stringHasValidBrackets(rightSubString)) {
                                    continue;
                                }
                            }
                            Factor rightFactor = new Factor(rightSubString, parser);
                            boolean isValidSecondPartFactor = rightFactor.getFactorType() != Factor.FactorType.INVALID;
                            if (!ListenerUtil.mutListener.listen(26677)) {
                                if (isValidSecondPartFactor) {
                                    if (!ListenerUtil.mutListener.listen(26674)) {
                                        if (termString.charAt(i) == '*') {
                                            if (!ListenerUtil.mutListener.listen(26673)) {
                                                this.termType = TermType.TERM_MUL_FACTOR;
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(26672)) {
                                                this.termType = TermType.TERM_DIV_FACTOR;
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(26675)) {
                                        this.term = leftTerm;
                                    }
                                    if (!ListenerUtil.mutListener.listen(26676)) {
                                        this.factor = rightFactor;
                                    }
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean initAsFactor(String termString) {
        Factor factor = new Factor(termString, parser);
        boolean isValidTerm = factor.getFactorType() != Factor.FactorType.INVALID;
        if (!ListenerUtil.mutListener.listen(26687)) {
            if (isValidTerm) {
                if (!ListenerUtil.mutListener.listen(26685)) {
                    this.termType = TermType.FACTOR;
                }
                if (!ListenerUtil.mutListener.listen(26686)) {
                    this.factor = factor;
                }
                return true;
            }
        }
        return false;
    }

    public TermType getTermType() {
        return termType;
    }

    public double getValue() throws ExpressionFormatException {
        switch(termType) {
            case TERM_MUL_FACTOR:
                return (ListenerUtil.mutListener.listen(26691) ? (term.getValue() % factor.getValue()) : (ListenerUtil.mutListener.listen(26690) ? (term.getValue() / factor.getValue()) : (ListenerUtil.mutListener.listen(26689) ? (term.getValue() - factor.getValue()) : (ListenerUtil.mutListener.listen(26688) ? (term.getValue() + factor.getValue()) : (term.getValue() * factor.getValue())))));
            case TERM_DIV_FACTOR:
                return (ListenerUtil.mutListener.listen(26695) ? (term.getValue() % factor.getValue()) : (ListenerUtil.mutListener.listen(26694) ? (term.getValue() * factor.getValue()) : (ListenerUtil.mutListener.listen(26693) ? (term.getValue() - factor.getValue()) : (ListenerUtil.mutListener.listen(26692) ? (term.getValue() + factor.getValue()) : (term.getValue() / factor.getValue())))));
            case FACTOR:
                return factor.getValue();
            case INVALID:
            default:
                throw new ExpressionFormatException("could not parse Term");
        }
    }

    @Override
    public boolean isVariable() {
        switch(termType) {
            case TERM_MUL_FACTOR:
            case TERM_DIV_FACTOR:
                return (ListenerUtil.mutListener.listen(26696) ? (term.isVariable() && factor.isVariable()) : (term.isVariable() || factor.isVariable()));
            case FACTOR:
                return factor.isVariable();
            case INVALID:
            default:
                throw new ExpressionFormatException("could not parse Term");
        }
    }
}
