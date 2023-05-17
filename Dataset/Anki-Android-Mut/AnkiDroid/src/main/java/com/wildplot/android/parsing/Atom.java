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

import com.wildplot.android.parsing.AtomTypes.*;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class Atom implements TreeElement {

    private final TopLevelParser parser;

    public enum AtomType {

        VARIABLE,
        NUMBER,
        EXP_IN_BRACKETS,
        FUNCTION_MATH,
        FUNCTION_X,
        FUNCTION_X_Y,
        INVALID
    }

    private AtomType atomType = AtomType.INVALID;

    private TreeElement atomObject;

    private Expression expression;

    public Atom(String atomString, TopLevelParser parser) {
        this.parser = parser;
        if (!ListenerUtil.mutListener.listen(26460)) {
            if (!TopLevelParser.stringHasValidBrackets(atomString)) {
                if (!ListenerUtil.mutListener.listen(26459)) {
                    this.atomType = AtomType.INVALID;
                }
                return;
            }
        }
        boolean isValid = initAsExpInBrackets(atomString);
        if (!ListenerUtil.mutListener.listen(26462)) {
            if (!isValid) {
                if (!ListenerUtil.mutListener.listen(26461)) {
                    isValid = initAsFunctionMath(atomString);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26464)) {
            if (!isValid) {
                if (!ListenerUtil.mutListener.listen(26463)) {
                    isValid = initAsFunctionX(atomString);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26466)) {
            if (!isValid) {
                if (!ListenerUtil.mutListener.listen(26465)) {
                    isValid = initAsFunctionXY(atomString);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26468)) {
            if (!isValid) {
                if (!ListenerUtil.mutListener.listen(26467)) {
                    isValid = initAsNumber(atomString);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26470)) {
            if (!isValid) {
                if (!ListenerUtil.mutListener.listen(26469)) {
                    isValid = initAsXVariable(atomString);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26472)) {
            if (!isValid) {
                if (!ListenerUtil.mutListener.listen(26471)) {
                    isValid = initAsYVariable(atomString);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26474)) {
            if (!isValid) {
                if (!ListenerUtil.mutListener.listen(26473)) {
                    isValid = initAsVariable(atomString);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26476)) {
            if (!isValid) {
                if (!ListenerUtil.mutListener.listen(26475)) {
                    this.atomType = AtomType.INVALID;
                }
            }
        }
    }

    private boolean initAsExpInBrackets(String atomString) {
        if (!ListenerUtil.mutListener.listen(26495)) {
            if ((ListenerUtil.mutListener.listen(26487) ? ((ListenerUtil.mutListener.listen(26482) ? ((ListenerUtil.mutListener.listen(26481) ? (atomString.length() >= 0) : (ListenerUtil.mutListener.listen(26480) ? (atomString.length() <= 0) : (ListenerUtil.mutListener.listen(26479) ? (atomString.length() < 0) : (ListenerUtil.mutListener.listen(26478) ? (atomString.length() != 0) : (ListenerUtil.mutListener.listen(26477) ? (atomString.length() == 0) : (atomString.length() > 0)))))) || atomString.charAt(0) == '(') : ((ListenerUtil.mutListener.listen(26481) ? (atomString.length() >= 0) : (ListenerUtil.mutListener.listen(26480) ? (atomString.length() <= 0) : (ListenerUtil.mutListener.listen(26479) ? (atomString.length() < 0) : (ListenerUtil.mutListener.listen(26478) ? (atomString.length() != 0) : (ListenerUtil.mutListener.listen(26477) ? (atomString.length() == 0) : (atomString.length() > 0)))))) && atomString.charAt(0) == '(')) || atomString.charAt((ListenerUtil.mutListener.listen(26486) ? (atomString.length() % 1) : (ListenerUtil.mutListener.listen(26485) ? (atomString.length() / 1) : (ListenerUtil.mutListener.listen(26484) ? (atomString.length() * 1) : (ListenerUtil.mutListener.listen(26483) ? (atomString.length() + 1) : (atomString.length() - 1)))))) == ')') : ((ListenerUtil.mutListener.listen(26482) ? ((ListenerUtil.mutListener.listen(26481) ? (atomString.length() >= 0) : (ListenerUtil.mutListener.listen(26480) ? (atomString.length() <= 0) : (ListenerUtil.mutListener.listen(26479) ? (atomString.length() < 0) : (ListenerUtil.mutListener.listen(26478) ? (atomString.length() != 0) : (ListenerUtil.mutListener.listen(26477) ? (atomString.length() == 0) : (atomString.length() > 0)))))) || atomString.charAt(0) == '(') : ((ListenerUtil.mutListener.listen(26481) ? (atomString.length() >= 0) : (ListenerUtil.mutListener.listen(26480) ? (atomString.length() <= 0) : (ListenerUtil.mutListener.listen(26479) ? (atomString.length() < 0) : (ListenerUtil.mutListener.listen(26478) ? (atomString.length() != 0) : (ListenerUtil.mutListener.listen(26477) ? (atomString.length() == 0) : (atomString.length() > 0)))))) && atomString.charAt(0) == '(')) && atomString.charAt((ListenerUtil.mutListener.listen(26486) ? (atomString.length() % 1) : (ListenerUtil.mutListener.listen(26485) ? (atomString.length() / 1) : (ListenerUtil.mutListener.listen(26484) ? (atomString.length() * 1) : (ListenerUtil.mutListener.listen(26483) ? (atomString.length() + 1) : (atomString.length() - 1)))))) == ')'))) {
                String expressionString = atomString.substring(1, (ListenerUtil.mutListener.listen(26491) ? (atomString.length() % 1) : (ListenerUtil.mutListener.listen(26490) ? (atomString.length() / 1) : (ListenerUtil.mutListener.listen(26489) ? (atomString.length() * 1) : (ListenerUtil.mutListener.listen(26488) ? (atomString.length() + 1) : (atomString.length() - 1))))));
                Expression expressionInBrackets = new Expression(expressionString, parser);
                boolean isValidExpressionInBrackets = expressionInBrackets.getExpressionType() != Expression.ExpressionType.INVALID;
                if (!ListenerUtil.mutListener.listen(26494)) {
                    if (isValidExpressionInBrackets) {
                        if (!ListenerUtil.mutListener.listen(26492)) {
                            this.expression = expressionInBrackets;
                        }
                        if (!ListenerUtil.mutListener.listen(26493)) {
                            this.atomType = AtomType.EXP_IN_BRACKETS;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean initAsFunctionMath(String atomString) {
        MathFunctionAtom mathFunctionAtom = new MathFunctionAtom(atomString, parser);
        boolean isValidMathFunction = mathFunctionAtom.getMathType() != MathFunctionAtom.MathType.INVALID;
        if (!ListenerUtil.mutListener.listen(26498)) {
            if (isValidMathFunction) {
                if (!ListenerUtil.mutListener.listen(26496)) {
                    this.atomType = AtomType.FUNCTION_MATH;
                }
                if (!ListenerUtil.mutListener.listen(26497)) {
                    this.atomObject = mathFunctionAtom;
                }
                return true;
            }
        }
        return false;
    }

    private boolean initAsFunctionX(String atomString) {
        FunctionXAtom functionXAtom = new FunctionXAtom(atomString, parser);
        boolean isValidFunctionXAtom = functionXAtom.getAtomType() != AtomType.INVALID;
        if (!ListenerUtil.mutListener.listen(26501)) {
            if (isValidFunctionXAtom) {
                if (!ListenerUtil.mutListener.listen(26499)) {
                    this.atomType = AtomType.FUNCTION_X;
                }
                if (!ListenerUtil.mutListener.listen(26500)) {
                    this.atomObject = functionXAtom;
                }
                return true;
            }
        }
        return false;
    }

    private boolean initAsFunctionXY(String atomString) {
        FunctionXYAtom functionXYAtom = new FunctionXYAtom(atomString, parser);
        boolean isValidFunctionXYAtom = functionXYAtom.getAtomType() != AtomType.INVALID;
        if (!ListenerUtil.mutListener.listen(26504)) {
            if (isValidFunctionXYAtom) {
                if (!ListenerUtil.mutListener.listen(26502)) {
                    this.atomType = AtomType.FUNCTION_X_Y;
                }
                if (!ListenerUtil.mutListener.listen(26503)) {
                    this.atomObject = functionXYAtom;
                }
                return true;
            }
        }
        return false;
    }

    private boolean initAsNumber(String atomString) {
        NumberAtom numberAtom = new NumberAtom(atomString);
        boolean isValidNumberAtom = numberAtom.getAtomType() != AtomType.INVALID;
        if (!ListenerUtil.mutListener.listen(26507)) {
            if (isValidNumberAtom) {
                if (!ListenerUtil.mutListener.listen(26505)) {
                    this.atomType = numberAtom.getAtomType();
                }
                if (!ListenerUtil.mutListener.listen(26506)) {
                    this.atomObject = numberAtom;
                }
                return true;
            }
        }
        return false;
    }

    private boolean initAsXVariable(String atomString) {
        if (!ListenerUtil.mutListener.listen(26510)) {
            if (atomString.equals(parser.getxName())) {
                if (!ListenerUtil.mutListener.listen(26508)) {
                    this.atomType = AtomType.VARIABLE;
                }
                if (!ListenerUtil.mutListener.listen(26509)) {
                    this.atomObject = new XVariableAtom(parser);
                }
                return true;
            }
        }
        return false;
    }

    private boolean initAsYVariable(String atomString) {
        if (!ListenerUtil.mutListener.listen(26513)) {
            if (atomString.equals(parser.getyName())) {
                if (!ListenerUtil.mutListener.listen(26511)) {
                    this.atomType = AtomType.VARIABLE;
                }
                if (!ListenerUtil.mutListener.listen(26512)) {
                    this.atomObject = new YVariableAtom(parser);
                }
                return true;
            }
        }
        return false;
    }

    private boolean initAsVariable(String atomString) {
        VariableAtom variableAtom = new VariableAtom(atomString, parser);
        boolean isValidVariableAtom = variableAtom.getAtomType() != AtomType.INVALID;
        if (!ListenerUtil.mutListener.listen(26516)) {
            if (isValidVariableAtom) {
                if (!ListenerUtil.mutListener.listen(26514)) {
                    this.atomType = variableAtom.getAtomType();
                }
                if (!ListenerUtil.mutListener.listen(26515)) {
                    this.atomObject = variableAtom;
                }
                return true;
            }
        }
        return false;
    }

    public AtomType getAtomType() {
        return atomType;
    }

    public double getValue() throws ExpressionFormatException {
        switch(atomType) {
            case EXP_IN_BRACKETS:
                return expression.getValue();
            case VARIABLE:
            case NUMBER:
            case FUNCTION_MATH:
            case FUNCTION_X:
            case FUNCTION_X_Y:
                return atomObject.getValue();
            case INVALID:
            default:
                throw new ExpressionFormatException("cannot parse Atom object");
        }
    }

    @Override
    public boolean isVariable() throws ExpressionFormatException {
        switch(atomType) {
            case EXP_IN_BRACKETS:
                return expression.isVariable();
            case VARIABLE:
            case NUMBER:
            case FUNCTION_MATH:
            case FUNCTION_X:
            case FUNCTION_X_Y:
                return atomObject.isVariable();
            case INVALID:
            default:
                throw new ExpressionFormatException("cannot parse Atom object");
        }
    }
}
