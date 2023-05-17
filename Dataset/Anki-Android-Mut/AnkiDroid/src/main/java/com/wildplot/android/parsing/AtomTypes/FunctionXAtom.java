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

import com.wildplot.android.parsing.*;
import java.util.regex.Pattern;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class FunctionXAtom implements TreeElement {

    private Atom.AtomType atomType = Atom.AtomType.FUNCTION_X;

    private final TopLevelParser parser;

    private Expression expression;

    private String funcName;

    public FunctionXAtom(String funcString, TopLevelParser parser) {
        this.parser = parser;
        boolean isValid = init(funcString);
        if (!ListenerUtil.mutListener.listen(26310)) {
            if (!isValid) {
                if (!ListenerUtil.mutListener.listen(26309)) {
                    this.atomType = Atom.AtomType.INVALID;
                }
            }
        }
    }

    private boolean init(String funcString) {
        int leftBracket = funcString.indexOf("(");
        int rightBracket = funcString.lastIndexOf(")");
        if (!ListenerUtil.mutListener.listen(26342)) {
            if ((ListenerUtil.mutListener.listen(26325) ? ((ListenerUtil.mutListener.listen(26315) ? (leftBracket >= 1) : (ListenerUtil.mutListener.listen(26314) ? (leftBracket <= 1) : (ListenerUtil.mutListener.listen(26313) ? (leftBracket < 1) : (ListenerUtil.mutListener.listen(26312) ? (leftBracket != 1) : (ListenerUtil.mutListener.listen(26311) ? (leftBracket == 1) : (leftBracket > 1)))))) || (ListenerUtil.mutListener.listen(26324) ? (rightBracket >= (ListenerUtil.mutListener.listen(26319) ? (leftBracket % 1) : (ListenerUtil.mutListener.listen(26318) ? (leftBracket / 1) : (ListenerUtil.mutListener.listen(26317) ? (leftBracket * 1) : (ListenerUtil.mutListener.listen(26316) ? (leftBracket - 1) : (leftBracket + 1)))))) : (ListenerUtil.mutListener.listen(26323) ? (rightBracket <= (ListenerUtil.mutListener.listen(26319) ? (leftBracket % 1) : (ListenerUtil.mutListener.listen(26318) ? (leftBracket / 1) : (ListenerUtil.mutListener.listen(26317) ? (leftBracket * 1) : (ListenerUtil.mutListener.listen(26316) ? (leftBracket - 1) : (leftBracket + 1)))))) : (ListenerUtil.mutListener.listen(26322) ? (rightBracket < (ListenerUtil.mutListener.listen(26319) ? (leftBracket % 1) : (ListenerUtil.mutListener.listen(26318) ? (leftBracket / 1) : (ListenerUtil.mutListener.listen(26317) ? (leftBracket * 1) : (ListenerUtil.mutListener.listen(26316) ? (leftBracket - 1) : (leftBracket + 1)))))) : (ListenerUtil.mutListener.listen(26321) ? (rightBracket != (ListenerUtil.mutListener.listen(26319) ? (leftBracket % 1) : (ListenerUtil.mutListener.listen(26318) ? (leftBracket / 1) : (ListenerUtil.mutListener.listen(26317) ? (leftBracket * 1) : (ListenerUtil.mutListener.listen(26316) ? (leftBracket - 1) : (leftBracket + 1)))))) : (ListenerUtil.mutListener.listen(26320) ? (rightBracket == (ListenerUtil.mutListener.listen(26319) ? (leftBracket % 1) : (ListenerUtil.mutListener.listen(26318) ? (leftBracket / 1) : (ListenerUtil.mutListener.listen(26317) ? (leftBracket * 1) : (ListenerUtil.mutListener.listen(26316) ? (leftBracket - 1) : (leftBracket + 1)))))) : (rightBracket > (ListenerUtil.mutListener.listen(26319) ? (leftBracket % 1) : (ListenerUtil.mutListener.listen(26318) ? (leftBracket / 1) : (ListenerUtil.mutListener.listen(26317) ? (leftBracket * 1) : (ListenerUtil.mutListener.listen(26316) ? (leftBracket - 1) : (leftBracket + 1)))))))))))) : ((ListenerUtil.mutListener.listen(26315) ? (leftBracket >= 1) : (ListenerUtil.mutListener.listen(26314) ? (leftBracket <= 1) : (ListenerUtil.mutListener.listen(26313) ? (leftBracket < 1) : (ListenerUtil.mutListener.listen(26312) ? (leftBracket != 1) : (ListenerUtil.mutListener.listen(26311) ? (leftBracket == 1) : (leftBracket > 1)))))) && (ListenerUtil.mutListener.listen(26324) ? (rightBracket >= (ListenerUtil.mutListener.listen(26319) ? (leftBracket % 1) : (ListenerUtil.mutListener.listen(26318) ? (leftBracket / 1) : (ListenerUtil.mutListener.listen(26317) ? (leftBracket * 1) : (ListenerUtil.mutListener.listen(26316) ? (leftBracket - 1) : (leftBracket + 1)))))) : (ListenerUtil.mutListener.listen(26323) ? (rightBracket <= (ListenerUtil.mutListener.listen(26319) ? (leftBracket % 1) : (ListenerUtil.mutListener.listen(26318) ? (leftBracket / 1) : (ListenerUtil.mutListener.listen(26317) ? (leftBracket * 1) : (ListenerUtil.mutListener.listen(26316) ? (leftBracket - 1) : (leftBracket + 1)))))) : (ListenerUtil.mutListener.listen(26322) ? (rightBracket < (ListenerUtil.mutListener.listen(26319) ? (leftBracket % 1) : (ListenerUtil.mutListener.listen(26318) ? (leftBracket / 1) : (ListenerUtil.mutListener.listen(26317) ? (leftBracket * 1) : (ListenerUtil.mutListener.listen(26316) ? (leftBracket - 1) : (leftBracket + 1)))))) : (ListenerUtil.mutListener.listen(26321) ? (rightBracket != (ListenerUtil.mutListener.listen(26319) ? (leftBracket % 1) : (ListenerUtil.mutListener.listen(26318) ? (leftBracket / 1) : (ListenerUtil.mutListener.listen(26317) ? (leftBracket * 1) : (ListenerUtil.mutListener.listen(26316) ? (leftBracket - 1) : (leftBracket + 1)))))) : (ListenerUtil.mutListener.listen(26320) ? (rightBracket == (ListenerUtil.mutListener.listen(26319) ? (leftBracket % 1) : (ListenerUtil.mutListener.listen(26318) ? (leftBracket / 1) : (ListenerUtil.mutListener.listen(26317) ? (leftBracket * 1) : (ListenerUtil.mutListener.listen(26316) ? (leftBracket - 1) : (leftBracket + 1)))))) : (rightBracket > (ListenerUtil.mutListener.listen(26319) ? (leftBracket % 1) : (ListenerUtil.mutListener.listen(26318) ? (leftBracket / 1) : (ListenerUtil.mutListener.listen(26317) ? (leftBracket * 1) : (ListenerUtil.mutListener.listen(26316) ? (leftBracket - 1) : (leftBracket + 1)))))))))))))) {
                String funcName = funcString.substring(0, leftBracket);
                Pattern p = Pattern.compile("[^a-zA-Z0-9]");
                boolean hasSpecialChar = p.matcher(funcName).find();
                if (!ListenerUtil.mutListener.listen(26341)) {
                    if ((ListenerUtil.mutListener.listen(26331) ? (!hasSpecialChar || ((ListenerUtil.mutListener.listen(26330) ? (funcName.length() >= 0) : (ListenerUtil.mutListener.listen(26329) ? (funcName.length() <= 0) : (ListenerUtil.mutListener.listen(26328) ? (funcName.length() < 0) : (ListenerUtil.mutListener.listen(26327) ? (funcName.length() != 0) : (ListenerUtil.mutListener.listen(26326) ? (funcName.length() == 0) : (funcName.length() > 0)))))))) : (!hasSpecialChar && ((ListenerUtil.mutListener.listen(26330) ? (funcName.length() >= 0) : (ListenerUtil.mutListener.listen(26329) ? (funcName.length() <= 0) : (ListenerUtil.mutListener.listen(26328) ? (funcName.length() < 0) : (ListenerUtil.mutListener.listen(26327) ? (funcName.length() != 0) : (ListenerUtil.mutListener.listen(26326) ? (funcName.length() == 0) : (funcName.length() > 0)))))))))) {
                        String expressionString = funcString.substring((ListenerUtil.mutListener.listen(26336) ? (leftBracket % 1) : (ListenerUtil.mutListener.listen(26335) ? (leftBracket / 1) : (ListenerUtil.mutListener.listen(26334) ? (leftBracket * 1) : (ListenerUtil.mutListener.listen(26333) ? (leftBracket - 1) : (leftBracket + 1))))), rightBracket);
                        Expression expressionInBrackets = new Expression(expressionString, parser);
                        boolean isValidExpression = expressionInBrackets.getExpressionType() != Expression.ExpressionType.INVALID;
                        if (!ListenerUtil.mutListener.listen(26340)) {
                            if (isValidExpression) {
                                if (!ListenerUtil.mutListener.listen(26337)) {
                                    this.atomType = Atom.AtomType.FUNCTION_X;
                                }
                                if (!ListenerUtil.mutListener.listen(26338)) {
                                    this.funcName = funcName;
                                }
                                if (!ListenerUtil.mutListener.listen(26339)) {
                                    this.expression = expressionInBrackets;
                                }
                                return true;
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(26332)) {
                            this.atomType = Atom.AtomType.INVALID;
                        }
                        return false;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public double getValue() throws ExpressionFormatException {
        if (atomType != Atom.AtomType.INVALID) {
            return parser.getFuncVal(funcName, expression.getValue());
        } else {
            throw new ExpressionFormatException("Number is Invalid, cannot parse");
        }
    }

    @Override
    public boolean isVariable() throws ExpressionFormatException {
        // TODO check how changed related function definitions are handled
        if (atomType != Atom.AtomType.INVALID) {
            return expression.isVariable();
        } else {
            throw new ExpressionFormatException("Number is Invalid, cannot parse");
        }
    }

    public Atom.AtomType getAtomType() {
        return atomType;
    }
}
