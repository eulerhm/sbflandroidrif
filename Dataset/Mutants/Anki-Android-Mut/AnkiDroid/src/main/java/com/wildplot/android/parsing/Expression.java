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

public class Expression implements TreeElement {

    private final TopLevelParser parser;

    public enum ExpressionType {

        EXP_PLUS_TERM, EXP_MINUS_TERM, TERM, INVALID
    }

    private ExpressionType expressionType = ExpressionType.INVALID;

    private Expression expression = null;

    private Term term = null;

    public Expression(String expressionString, TopLevelParser parser) {
        this.parser = parser;
        if (!ListenerUtil.mutListener.listen(26518)) {
            if (!TopLevelParser.stringHasValidBrackets(expressionString)) {
                if (!ListenerUtil.mutListener.listen(26517)) {
                    this.expressionType = ExpressionType.INVALID;
                }
                return;
            }
        }
        boolean isReady = initAsExpPlusOrMinusTerm(expressionString);
        if (!ListenerUtil.mutListener.listen(26520)) {
            if (!isReady) {
                if (!ListenerUtil.mutListener.listen(26519)) {
                    isReady = initAsTerm(expressionString);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26522)) {
            if (!isReady) {
                if (!ListenerUtil.mutListener.listen(26521)) {
                    this.expressionType = ExpressionType.INVALID;
                }
            }
        }
    }

    private boolean initAsExpPlusOrMinusTerm(String expressionString) {
        int bracketChecker = 0;
        if (!ListenerUtil.mutListener.listen(26553)) {
            {
                long _loopCounter704 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(26552) ? (i >= expressionString.length()) : (ListenerUtil.mutListener.listen(26551) ? (i <= expressionString.length()) : (ListenerUtil.mutListener.listen(26550) ? (i > expressionString.length()) : (ListenerUtil.mutListener.listen(26549) ? (i != expressionString.length()) : (ListenerUtil.mutListener.listen(26548) ? (i == expressionString.length()) : (i < expressionString.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter704", ++_loopCounter704);
                    if (!ListenerUtil.mutListener.listen(26524)) {
                        if (expressionString.charAt(i) == '(') {
                            if (!ListenerUtil.mutListener.listen(26523)) {
                                bracketChecker++;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(26526)) {
                        if (expressionString.charAt(i) == ')') {
                            if (!ListenerUtil.mutListener.listen(26525)) {
                                bracketChecker--;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(26547)) {
                        if ((ListenerUtil.mutListener.listen(26533) ? (((ListenerUtil.mutListener.listen(26527) ? (expressionString.charAt(i) == '+' && expressionString.charAt(i) == '-') : (expressionString.charAt(i) == '+' || expressionString.charAt(i) == '-'))) || (ListenerUtil.mutListener.listen(26532) ? (bracketChecker >= 0) : (ListenerUtil.mutListener.listen(26531) ? (bracketChecker <= 0) : (ListenerUtil.mutListener.listen(26530) ? (bracketChecker > 0) : (ListenerUtil.mutListener.listen(26529) ? (bracketChecker < 0) : (ListenerUtil.mutListener.listen(26528) ? (bracketChecker != 0) : (bracketChecker == 0))))))) : (((ListenerUtil.mutListener.listen(26527) ? (expressionString.charAt(i) == '+' && expressionString.charAt(i) == '-') : (expressionString.charAt(i) == '+' || expressionString.charAt(i) == '-'))) && (ListenerUtil.mutListener.listen(26532) ? (bracketChecker >= 0) : (ListenerUtil.mutListener.listen(26531) ? (bracketChecker <= 0) : (ListenerUtil.mutListener.listen(26530) ? (bracketChecker > 0) : (ListenerUtil.mutListener.listen(26529) ? (bracketChecker < 0) : (ListenerUtil.mutListener.listen(26528) ? (bracketChecker != 0) : (bracketChecker == 0))))))))) {
                            String leftSubString = expressionString.substring(0, i);
                            if (!ListenerUtil.mutListener.listen(26534)) {
                                if (!TopLevelParser.stringHasValidBrackets(leftSubString)) {
                                    continue;
                                }
                            }
                            Expression leftExpression = new Expression(leftSubString, parser);
                            boolean isValidFirstPartExpression = leftExpression.getExpressionType() != ExpressionType.INVALID;
                            if (!ListenerUtil.mutListener.listen(26535)) {
                                if (!isValidFirstPartExpression) {
                                    continue;
                                }
                            }
                            String rightSubString = expressionString.substring((ListenerUtil.mutListener.listen(26539) ? (i % 1) : (ListenerUtil.mutListener.listen(26538) ? (i / 1) : (ListenerUtil.mutListener.listen(26537) ? (i * 1) : (ListenerUtil.mutListener.listen(26536) ? (i - 1) : (i + 1))))));
                            if (!ListenerUtil.mutListener.listen(26540)) {
                                if (!TopLevelParser.stringHasValidBrackets(rightSubString)) {
                                    continue;
                                }
                            }
                            Term rightTerm = new Term(rightSubString, parser);
                            boolean isValidSecondPartTerm = rightTerm.getTermType() != Term.TermType.INVALID;
                            if (!ListenerUtil.mutListener.listen(26546)) {
                                if (isValidSecondPartTerm) {
                                    if (!ListenerUtil.mutListener.listen(26543)) {
                                        if (expressionString.charAt(i) == '+') {
                                            if (!ListenerUtil.mutListener.listen(26542)) {
                                                this.expressionType = ExpressionType.EXP_PLUS_TERM;
                                            }
                                        } else {
                                            if (!ListenerUtil.mutListener.listen(26541)) {
                                                this.expressionType = ExpressionType.EXP_MINUS_TERM;
                                            }
                                        }
                                    }
                                    if (!ListenerUtil.mutListener.listen(26544)) {
                                        this.expression = leftExpression;
                                    }
                                    if (!ListenerUtil.mutListener.listen(26545)) {
                                        this.term = rightTerm;
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

    private boolean initAsTerm(String expressionString) {
        if (!ListenerUtil.mutListener.listen(26554)) {
            if (!TopLevelParser.stringHasValidBrackets(expressionString)) {
                return false;
            }
        }
        Term term = new Term(expressionString, parser);
        boolean isValidTerm = term.getTermType() != Term.TermType.INVALID;
        if (!ListenerUtil.mutListener.listen(26557)) {
            if (isValidTerm) {
                if (!ListenerUtil.mutListener.listen(26555)) {
                    this.expressionType = ExpressionType.TERM;
                }
                if (!ListenerUtil.mutListener.listen(26556)) {
                    this.term = term;
                }
                return true;
            }
        }
        return false;
    }

    public ExpressionType getExpressionType() {
        return expressionType;
    }

    public double getValue() throws ExpressionFormatException {
        switch(expressionType) {
            case EXP_PLUS_TERM:
                return (ListenerUtil.mutListener.listen(26561) ? (expression.getValue() % term.getValue()) : (ListenerUtil.mutListener.listen(26560) ? (expression.getValue() / term.getValue()) : (ListenerUtil.mutListener.listen(26559) ? (expression.getValue() * term.getValue()) : (ListenerUtil.mutListener.listen(26558) ? (expression.getValue() - term.getValue()) : (expression.getValue() + term.getValue())))));
            case EXP_MINUS_TERM:
                return (ListenerUtil.mutListener.listen(26565) ? (expression.getValue() % term.getValue()) : (ListenerUtil.mutListener.listen(26564) ? (expression.getValue() / term.getValue()) : (ListenerUtil.mutListener.listen(26563) ? (expression.getValue() * term.getValue()) : (ListenerUtil.mutListener.listen(26562) ? (expression.getValue() + term.getValue()) : (expression.getValue() - term.getValue())))));
            case TERM:
                return term.getValue();
            default:
            case INVALID:
                throw new ExpressionFormatException("could not parse Expression");
        }
    }

    @Override
    public boolean isVariable() {
        switch(expressionType) {
            case EXP_PLUS_TERM:
            case EXP_MINUS_TERM:
                return (ListenerUtil.mutListener.listen(26566) ? (expression.isVariable() && term.isVariable()) : (expression.isVariable() || term.isVariable()));
            case TERM:
                return term.isVariable();
            default:
            case INVALID:
                throw new ExpressionFormatException("could not parse Expression");
        }
    }
}
