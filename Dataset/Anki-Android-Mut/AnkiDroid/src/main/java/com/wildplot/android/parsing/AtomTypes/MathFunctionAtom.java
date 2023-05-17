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
package com.wildplot.android.parsing.AtomTypes;

import com.wildplot.android.parsing.Expression;
import com.wildplot.android.parsing.ExpressionFormatException;
import com.wildplot.android.parsing.TopLevelParser;
import com.wildplot.android.parsing.TreeElement;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MathFunctionAtom implements TreeElement {

    private final TopLevelParser parser;

    public enum MathType {

        SIN,
        COS,
        TAN,
        SQRT,
        ACOS,
        ASIN,
        ATAN,
        SINH,
        COSH,
        LOG,
        LN,
        INVALID
    }

    private MathType mathType = MathType.INVALID;

    private Expression expression;

    private boolean hasSavedValue = false;

    private double savedValue = 0;

    public MathFunctionAtom(String funcString, TopLevelParser parser) {
        this.parser = parser;
        boolean isValid = init(funcString);
        if (!ListenerUtil.mutListener.listen(26409)) {
            if (!isValid) {
                if (!ListenerUtil.mutListener.listen(26408)) {
                    this.mathType = MathType.INVALID;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26413)) {
            if ((ListenerUtil.mutListener.listen(26410) ? (isValid || !isVariable()) : (isValid && !isVariable()))) {
                if (!ListenerUtil.mutListener.listen(26411)) {
                    savedValue = getValue();
                }
                if (!ListenerUtil.mutListener.listen(26412)) {
                    hasSavedValue = true;
                }
            }
        }
    }

    private boolean init(String funcString) {
        int leftBracket = funcString.indexOf("(");
        int rightBracket = funcString.lastIndexOf(")");
        if (!ListenerUtil.mutListener.listen(26448)) {
            if ((ListenerUtil.mutListener.listen(26428) ? ((ListenerUtil.mutListener.listen(26418) ? (leftBracket >= 1) : (ListenerUtil.mutListener.listen(26417) ? (leftBracket <= 1) : (ListenerUtil.mutListener.listen(26416) ? (leftBracket < 1) : (ListenerUtil.mutListener.listen(26415) ? (leftBracket != 1) : (ListenerUtil.mutListener.listen(26414) ? (leftBracket == 1) : (leftBracket > 1)))))) || (ListenerUtil.mutListener.listen(26427) ? (rightBracket >= (ListenerUtil.mutListener.listen(26422) ? (leftBracket % 1) : (ListenerUtil.mutListener.listen(26421) ? (leftBracket / 1) : (ListenerUtil.mutListener.listen(26420) ? (leftBracket * 1) : (ListenerUtil.mutListener.listen(26419) ? (leftBracket - 1) : (leftBracket + 1)))))) : (ListenerUtil.mutListener.listen(26426) ? (rightBracket <= (ListenerUtil.mutListener.listen(26422) ? (leftBracket % 1) : (ListenerUtil.mutListener.listen(26421) ? (leftBracket / 1) : (ListenerUtil.mutListener.listen(26420) ? (leftBracket * 1) : (ListenerUtil.mutListener.listen(26419) ? (leftBracket - 1) : (leftBracket + 1)))))) : (ListenerUtil.mutListener.listen(26425) ? (rightBracket < (ListenerUtil.mutListener.listen(26422) ? (leftBracket % 1) : (ListenerUtil.mutListener.listen(26421) ? (leftBracket / 1) : (ListenerUtil.mutListener.listen(26420) ? (leftBracket * 1) : (ListenerUtil.mutListener.listen(26419) ? (leftBracket - 1) : (leftBracket + 1)))))) : (ListenerUtil.mutListener.listen(26424) ? (rightBracket != (ListenerUtil.mutListener.listen(26422) ? (leftBracket % 1) : (ListenerUtil.mutListener.listen(26421) ? (leftBracket / 1) : (ListenerUtil.mutListener.listen(26420) ? (leftBracket * 1) : (ListenerUtil.mutListener.listen(26419) ? (leftBracket - 1) : (leftBracket + 1)))))) : (ListenerUtil.mutListener.listen(26423) ? (rightBracket == (ListenerUtil.mutListener.listen(26422) ? (leftBracket % 1) : (ListenerUtil.mutListener.listen(26421) ? (leftBracket / 1) : (ListenerUtil.mutListener.listen(26420) ? (leftBracket * 1) : (ListenerUtil.mutListener.listen(26419) ? (leftBracket - 1) : (leftBracket + 1)))))) : (rightBracket > (ListenerUtil.mutListener.listen(26422) ? (leftBracket % 1) : (ListenerUtil.mutListener.listen(26421) ? (leftBracket / 1) : (ListenerUtil.mutListener.listen(26420) ? (leftBracket * 1) : (ListenerUtil.mutListener.listen(26419) ? (leftBracket - 1) : (leftBracket + 1)))))))))))) : ((ListenerUtil.mutListener.listen(26418) ? (leftBracket >= 1) : (ListenerUtil.mutListener.listen(26417) ? (leftBracket <= 1) : (ListenerUtil.mutListener.listen(26416) ? (leftBracket < 1) : (ListenerUtil.mutListener.listen(26415) ? (leftBracket != 1) : (ListenerUtil.mutListener.listen(26414) ? (leftBracket == 1) : (leftBracket > 1)))))) && (ListenerUtil.mutListener.listen(26427) ? (rightBracket >= (ListenerUtil.mutListener.listen(26422) ? (leftBracket % 1) : (ListenerUtil.mutListener.listen(26421) ? (leftBracket / 1) : (ListenerUtil.mutListener.listen(26420) ? (leftBracket * 1) : (ListenerUtil.mutListener.listen(26419) ? (leftBracket - 1) : (leftBracket + 1)))))) : (ListenerUtil.mutListener.listen(26426) ? (rightBracket <= (ListenerUtil.mutListener.listen(26422) ? (leftBracket % 1) : (ListenerUtil.mutListener.listen(26421) ? (leftBracket / 1) : (ListenerUtil.mutListener.listen(26420) ? (leftBracket * 1) : (ListenerUtil.mutListener.listen(26419) ? (leftBracket - 1) : (leftBracket + 1)))))) : (ListenerUtil.mutListener.listen(26425) ? (rightBracket < (ListenerUtil.mutListener.listen(26422) ? (leftBracket % 1) : (ListenerUtil.mutListener.listen(26421) ? (leftBracket / 1) : (ListenerUtil.mutListener.listen(26420) ? (leftBracket * 1) : (ListenerUtil.mutListener.listen(26419) ? (leftBracket - 1) : (leftBracket + 1)))))) : (ListenerUtil.mutListener.listen(26424) ? (rightBracket != (ListenerUtil.mutListener.listen(26422) ? (leftBracket % 1) : (ListenerUtil.mutListener.listen(26421) ? (leftBracket / 1) : (ListenerUtil.mutListener.listen(26420) ? (leftBracket * 1) : (ListenerUtil.mutListener.listen(26419) ? (leftBracket - 1) : (leftBracket + 1)))))) : (ListenerUtil.mutListener.listen(26423) ? (rightBracket == (ListenerUtil.mutListener.listen(26422) ? (leftBracket % 1) : (ListenerUtil.mutListener.listen(26421) ? (leftBracket / 1) : (ListenerUtil.mutListener.listen(26420) ? (leftBracket * 1) : (ListenerUtil.mutListener.listen(26419) ? (leftBracket - 1) : (leftBracket + 1)))))) : (rightBracket > (ListenerUtil.mutListener.listen(26422) ? (leftBracket % 1) : (ListenerUtil.mutListener.listen(26421) ? (leftBracket / 1) : (ListenerUtil.mutListener.listen(26420) ? (leftBracket * 1) : (ListenerUtil.mutListener.listen(26419) ? (leftBracket - 1) : (leftBracket + 1)))))))))))))) {
                String funcName = funcString.substring(0, leftBracket);
                String expressionString = funcString.substring((ListenerUtil.mutListener.listen(26432) ? (leftBracket % 1) : (ListenerUtil.mutListener.listen(26431) ? (leftBracket / 1) : (ListenerUtil.mutListener.listen(26430) ? (leftBracket * 1) : (ListenerUtil.mutListener.listen(26429) ? (leftBracket - 1) : (leftBracket + 1))))), rightBracket);
                Expression expressionInBrackets = new Expression(expressionString, parser);
                boolean isValidExpression = expressionInBrackets.getExpressionType() != Expression.ExpressionType.INVALID;
                if (!ListenerUtil.mutListener.listen(26447)) {
                    if (isValidExpression) {
                        if (!ListenerUtil.mutListener.listen(26445)) {
                            switch(funcName) {
                                case "sin":
                                    if (!ListenerUtil.mutListener.listen(26433)) {
                                        this.mathType = MathType.SIN;
                                    }
                                    break;
                                case "cos":
                                    if (!ListenerUtil.mutListener.listen(26434)) {
                                        this.mathType = MathType.COS;
                                    }
                                    break;
                                case "tan":
                                    if (!ListenerUtil.mutListener.listen(26435)) {
                                        this.mathType = MathType.TAN;
                                    }
                                    break;
                                case "sqrt":
                                    if (!ListenerUtil.mutListener.listen(26436)) {
                                        this.mathType = MathType.SQRT;
                                    }
                                    break;
                                case "acos":
                                    if (!ListenerUtil.mutListener.listen(26437)) {
                                        this.mathType = MathType.ACOS;
                                    }
                                    break;
                                case "asin":
                                    if (!ListenerUtil.mutListener.listen(26438)) {
                                        this.mathType = MathType.ASIN;
                                    }
                                    break;
                                case "atan":
                                    if (!ListenerUtil.mutListener.listen(26439)) {
                                        this.mathType = MathType.ATAN;
                                    }
                                    break;
                                case "sinh":
                                    if (!ListenerUtil.mutListener.listen(26440)) {
                                        this.mathType = MathType.SINH;
                                    }
                                    break;
                                case "cosh":
                                    if (!ListenerUtil.mutListener.listen(26441)) {
                                        this.mathType = MathType.COSH;
                                    }
                                    break;
                                case "log":
                                case "lg":
                                    if (!ListenerUtil.mutListener.listen(26442)) {
                                        this.mathType = MathType.LOG;
                                    }
                                    break;
                                case "ln":
                                    if (!ListenerUtil.mutListener.listen(26443)) {
                                        this.mathType = MathType.LN;
                                    }
                                    break;
                                default:
                                    if (!ListenerUtil.mutListener.listen(26444)) {
                                        this.mathType = MathType.INVALID;
                                    }
                                    return false;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(26446)) {
                            this.expression = expressionInBrackets;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public double getValue() throws ExpressionFormatException {
        if (hasSavedValue) {
            return savedValue;
        }
        switch(mathType) {
            case SIN:
                return Math.sin(expression.getValue());
            case COS:
                return Math.cos(expression.getValue());
            case TAN:
                return Math.tan(expression.getValue());
            case SQRT:
                return Math.sqrt(expression.getValue());
            case ACOS:
                return Math.acos(expression.getValue());
            case ASIN:
                return Math.asin(expression.getValue());
            case ATAN:
                return Math.atan(expression.getValue());
            case SINH:
                return Math.sinh(expression.getValue());
            case COSH:
                return Math.cosh(expression.getValue());
            case LOG:
                return Math.log10(expression.getValue());
            case LN:
                return Math.log(expression.getValue());
            case INVALID:
            default:
                throw new ExpressionFormatException("Number is Invalid, cannot parse");
        }
    }

    @Override
    public boolean isVariable() {
        if (mathType != MathType.INVALID) {
            return expression.isVariable();
        } else {
            throw new ExpressionFormatException("Number is Invalid, cannot parse");
        }
    }

    public MathType getMathType() {
        return mathType;
    }
}
