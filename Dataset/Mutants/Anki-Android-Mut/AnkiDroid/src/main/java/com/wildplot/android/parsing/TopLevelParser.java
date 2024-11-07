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

import com.wildplot.android.rendering.interfaces.Function2D;
import com.wildplot.android.rendering.interfaces.Function3D;
import java.util.HashMap;
import java.util.regex.Pattern;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TopLevelParser implements Function2D, Function3D, Cloneable {

    private final HashMap<String, TopLevelParser> parserRegister;

    // Number form initVarMap
    private final HashMap<String, Double> varMap = new HashMap<>(2);

    private double x = 0.0, y = 0.0;

    private final Expression expression;

    private final boolean isValid;

    private String expressionString;

    private String xName = "x", yName = "y";

    public TopLevelParser(String expressionString, HashMap<String, TopLevelParser> parserRegister) {
        if (!ListenerUtil.mutListener.listen(26697)) {
            initVarMap();
        }
        this.parserRegister = parserRegister;
        if (!ListenerUtil.mutListener.listen(26698)) {
            this.expressionString = expressionString;
        }
        boolean isValidExpressionString = initExpressionString();
        this.expression = new Expression(this.expressionString, this);
        this.isValid = (ListenerUtil.mutListener.listen(26699) ? ((expression.getExpressionType() != Expression.ExpressionType.INVALID) || isValidExpressionString) : ((expression.getExpressionType() != Expression.ExpressionType.INVALID) && isValidExpressionString));
    }

    private void initVarMap() {
        if (!ListenerUtil.mutListener.listen(26700)) {
            varMap.put("e", Math.E);
        }
        if (!ListenerUtil.mutListener.listen(26701)) {
            varMap.put("pi", Math.PI);
        }
    }

    private boolean initExpressionString() {
        if (!ListenerUtil.mutListener.listen(26702)) {
            this.expressionString = expressionString.replace(" ", "");
        }
        int equalPosition = expressionString.indexOf("=");
        if (!ListenerUtil.mutListener.listen(26757)) {
            if ((ListenerUtil.mutListener.listen(26707) ? (equalPosition <= 1) : (ListenerUtil.mutListener.listen(26706) ? (equalPosition > 1) : (ListenerUtil.mutListener.listen(26705) ? (equalPosition < 1) : (ListenerUtil.mutListener.listen(26704) ? (equalPosition != 1) : (ListenerUtil.mutListener.listen(26703) ? (equalPosition == 1) : (equalPosition >= 1))))))) {
                String leftStatement = expressionString.substring(0, equalPosition);
                if (!ListenerUtil.mutListener.listen(26712)) {
                    this.expressionString = expressionString.substring((ListenerUtil.mutListener.listen(26711) ? (equalPosition % 1) : (ListenerUtil.mutListener.listen(26710) ? (equalPosition / 1) : (ListenerUtil.mutListener.listen(26709) ? (equalPosition * 1) : (ListenerUtil.mutListener.listen(26708) ? (equalPosition - 1) : (equalPosition + 1))))));
                }
                int commaPos = leftStatement.indexOf(",");
                int leftBracketPos = leftStatement.indexOf("(");
                int rightBracketPos = leftStatement.indexOf(")");
                if (!ListenerUtil.mutListener.listen(26756)) {
                    if ((ListenerUtil.mutListener.listen(26727) ? ((ListenerUtil.mutListener.listen(26717) ? (leftBracketPos >= 0) : (ListenerUtil.mutListener.listen(26716) ? (leftBracketPos <= 0) : (ListenerUtil.mutListener.listen(26715) ? (leftBracketPos < 0) : (ListenerUtil.mutListener.listen(26714) ? (leftBracketPos != 0) : (ListenerUtil.mutListener.listen(26713) ? (leftBracketPos == 0) : (leftBracketPos > 0)))))) || (ListenerUtil.mutListener.listen(26726) ? (rightBracketPos >= (ListenerUtil.mutListener.listen(26721) ? (leftBracketPos % 1) : (ListenerUtil.mutListener.listen(26720) ? (leftBracketPos / 1) : (ListenerUtil.mutListener.listen(26719) ? (leftBracketPos * 1) : (ListenerUtil.mutListener.listen(26718) ? (leftBracketPos - 1) : (leftBracketPos + 1)))))) : (ListenerUtil.mutListener.listen(26725) ? (rightBracketPos <= (ListenerUtil.mutListener.listen(26721) ? (leftBracketPos % 1) : (ListenerUtil.mutListener.listen(26720) ? (leftBracketPos / 1) : (ListenerUtil.mutListener.listen(26719) ? (leftBracketPos * 1) : (ListenerUtil.mutListener.listen(26718) ? (leftBracketPos - 1) : (leftBracketPos + 1)))))) : (ListenerUtil.mutListener.listen(26724) ? (rightBracketPos < (ListenerUtil.mutListener.listen(26721) ? (leftBracketPos % 1) : (ListenerUtil.mutListener.listen(26720) ? (leftBracketPos / 1) : (ListenerUtil.mutListener.listen(26719) ? (leftBracketPos * 1) : (ListenerUtil.mutListener.listen(26718) ? (leftBracketPos - 1) : (leftBracketPos + 1)))))) : (ListenerUtil.mutListener.listen(26723) ? (rightBracketPos != (ListenerUtil.mutListener.listen(26721) ? (leftBracketPos % 1) : (ListenerUtil.mutListener.listen(26720) ? (leftBracketPos / 1) : (ListenerUtil.mutListener.listen(26719) ? (leftBracketPos * 1) : (ListenerUtil.mutListener.listen(26718) ? (leftBracketPos - 1) : (leftBracketPos + 1)))))) : (ListenerUtil.mutListener.listen(26722) ? (rightBracketPos == (ListenerUtil.mutListener.listen(26721) ? (leftBracketPos % 1) : (ListenerUtil.mutListener.listen(26720) ? (leftBracketPos / 1) : (ListenerUtil.mutListener.listen(26719) ? (leftBracketPos * 1) : (ListenerUtil.mutListener.listen(26718) ? (leftBracketPos - 1) : (leftBracketPos + 1)))))) : (rightBracketPos > (ListenerUtil.mutListener.listen(26721) ? (leftBracketPos % 1) : (ListenerUtil.mutListener.listen(26720) ? (leftBracketPos / 1) : (ListenerUtil.mutListener.listen(26719) ? (leftBracketPos * 1) : (ListenerUtil.mutListener.listen(26718) ? (leftBracketPos - 1) : (leftBracketPos + 1)))))))))))) : ((ListenerUtil.mutListener.listen(26717) ? (leftBracketPos >= 0) : (ListenerUtil.mutListener.listen(26716) ? (leftBracketPos <= 0) : (ListenerUtil.mutListener.listen(26715) ? (leftBracketPos < 0) : (ListenerUtil.mutListener.listen(26714) ? (leftBracketPos != 0) : (ListenerUtil.mutListener.listen(26713) ? (leftBracketPos == 0) : (leftBracketPos > 0)))))) && (ListenerUtil.mutListener.listen(26726) ? (rightBracketPos >= (ListenerUtil.mutListener.listen(26721) ? (leftBracketPos % 1) : (ListenerUtil.mutListener.listen(26720) ? (leftBracketPos / 1) : (ListenerUtil.mutListener.listen(26719) ? (leftBracketPos * 1) : (ListenerUtil.mutListener.listen(26718) ? (leftBracketPos - 1) : (leftBracketPos + 1)))))) : (ListenerUtil.mutListener.listen(26725) ? (rightBracketPos <= (ListenerUtil.mutListener.listen(26721) ? (leftBracketPos % 1) : (ListenerUtil.mutListener.listen(26720) ? (leftBracketPos / 1) : (ListenerUtil.mutListener.listen(26719) ? (leftBracketPos * 1) : (ListenerUtil.mutListener.listen(26718) ? (leftBracketPos - 1) : (leftBracketPos + 1)))))) : (ListenerUtil.mutListener.listen(26724) ? (rightBracketPos < (ListenerUtil.mutListener.listen(26721) ? (leftBracketPos % 1) : (ListenerUtil.mutListener.listen(26720) ? (leftBracketPos / 1) : (ListenerUtil.mutListener.listen(26719) ? (leftBracketPos * 1) : (ListenerUtil.mutListener.listen(26718) ? (leftBracketPos - 1) : (leftBracketPos + 1)))))) : (ListenerUtil.mutListener.listen(26723) ? (rightBracketPos != (ListenerUtil.mutListener.listen(26721) ? (leftBracketPos % 1) : (ListenerUtil.mutListener.listen(26720) ? (leftBracketPos / 1) : (ListenerUtil.mutListener.listen(26719) ? (leftBracketPos * 1) : (ListenerUtil.mutListener.listen(26718) ? (leftBracketPos - 1) : (leftBracketPos + 1)))))) : (ListenerUtil.mutListener.listen(26722) ? (rightBracketPos == (ListenerUtil.mutListener.listen(26721) ? (leftBracketPos % 1) : (ListenerUtil.mutListener.listen(26720) ? (leftBracketPos / 1) : (ListenerUtil.mutListener.listen(26719) ? (leftBracketPos * 1) : (ListenerUtil.mutListener.listen(26718) ? (leftBracketPos - 1) : (leftBracketPos + 1)))))) : (rightBracketPos > (ListenerUtil.mutListener.listen(26721) ? (leftBracketPos % 1) : (ListenerUtil.mutListener.listen(26720) ? (leftBracketPos / 1) : (ListenerUtil.mutListener.listen(26719) ? (leftBracketPos * 1) : (ListenerUtil.mutListener.listen(26718) ? (leftBracketPos - 1) : (leftBracketPos + 1)))))))))))))) {
                        String funcName = leftStatement.substring(0, leftBracketPos);
                        Pattern p = Pattern.compile("[^a-zA-Z0-9]");
                        boolean hasSpecialChar = p.matcher(funcName).find();
                        if (!ListenerUtil.mutListener.listen(26728)) {
                            if (hasSpecialChar) {
                                return false;
                            }
                        }
                        if (!ListenerUtil.mutListener.listen(26755)) {
                            if ((ListenerUtil.mutListener.listen(26733) ? (commaPos >= -1) : (ListenerUtil.mutListener.listen(26732) ? (commaPos <= -1) : (ListenerUtil.mutListener.listen(26731) ? (commaPos > -1) : (ListenerUtil.mutListener.listen(26730) ? (commaPos < -1) : (ListenerUtil.mutListener.listen(26729) ? (commaPos != -1) : (commaPos == -1))))))) {
                                String xVarName = leftStatement.substring((ListenerUtil.mutListener.listen(26751) ? (leftBracketPos % 1) : (ListenerUtil.mutListener.listen(26750) ? (leftBracketPos / 1) : (ListenerUtil.mutListener.listen(26749) ? (leftBracketPos * 1) : (ListenerUtil.mutListener.listen(26748) ? (leftBracketPos - 1) : (leftBracketPos + 1))))), rightBracketPos);
                                if (!ListenerUtil.mutListener.listen(26752)) {
                                    hasSpecialChar = p.matcher(xVarName).find();
                                }
                                if (!ListenerUtil.mutListener.listen(26753)) {
                                    if (hasSpecialChar) {
                                        return false;
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(26754)) {
                                    this.xName = xVarName;
                                }
                            } else {
                                String xVarName = leftStatement.substring((ListenerUtil.mutListener.listen(26737) ? (leftBracketPos % 1) : (ListenerUtil.mutListener.listen(26736) ? (leftBracketPos / 1) : (ListenerUtil.mutListener.listen(26735) ? (leftBracketPos * 1) : (ListenerUtil.mutListener.listen(26734) ? (leftBracketPos - 1) : (leftBracketPos + 1))))), commaPos);
                                if (!ListenerUtil.mutListener.listen(26738)) {
                                    hasSpecialChar = p.matcher(xVarName).find();
                                }
                                if (!ListenerUtil.mutListener.listen(26739)) {
                                    if (hasSpecialChar) {
                                        return false;
                                    }
                                }
                                String yVarName = leftStatement.substring((ListenerUtil.mutListener.listen(26743) ? (commaPos % 1) : (ListenerUtil.mutListener.listen(26742) ? (commaPos / 1) : (ListenerUtil.mutListener.listen(26741) ? (commaPos * 1) : (ListenerUtil.mutListener.listen(26740) ? (commaPos - 1) : (commaPos + 1))))), rightBracketPos);
                                if (!ListenerUtil.mutListener.listen(26744)) {
                                    hasSpecialChar = p.matcher(yVarName).find();
                                }
                                if (!ListenerUtil.mutListener.listen(26745)) {
                                    if (hasSpecialChar) {
                                        return false;
                                    }
                                }
                                if (!ListenerUtil.mutListener.listen(26746)) {
                                    this.xName = xVarName;
                                }
                                if (!ListenerUtil.mutListener.listen(26747)) {
                                    this.yName = yVarName;
                                }
                            }
                        }
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public double getVarVal(String varName) {
        return varMap.get(varName);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        if (!ListenerUtil.mutListener.listen(26758)) {
            this.x = x;
        }
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        if (!ListenerUtil.mutListener.listen(26759)) {
            this.y = y;
        }
    }

    @Override
    public double f(double x) {
        if (!ListenerUtil.mutListener.listen(26760)) {
            this.x = x;
        }
        if (isValid) {
            return expression.getValue();
        } else {
            throw new ExpressionFormatException("illegal Expression, cannot parse and return value");
        }
    }

    public boolean isValid() {
        return isValid;
    }

    @Override
    public double f(double x, double y) {
        if (!ListenerUtil.mutListener.listen(26761)) {
            this.x = x;
        }
        if (!ListenerUtil.mutListener.listen(26762)) {
            this.y = y;
        }
        if (isValid) {
            return expression.getValue();
        } else {
            throw new ExpressionFormatException("illegal Expression, cannot parse and return value");
        }
    }

    public double getFuncVal(String funcName, double xVal) {
        TopLevelParser funcParser = this.parserRegister.get(funcName);
        return funcParser.f(xVal);
    }

    public double getFuncVal(String funcName, double xVal, double yVal) {
        TopLevelParser funcParser = this.parserRegister.get(funcName);
        return funcParser.f(xVal, yVal);
    }

    public String getxName() {
        return xName;
    }

    public String getyName() {
        return yName;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean stringHasValidBrackets(String string) {
        int finalBracketCheck = (ListenerUtil.mutListener.listen(26766) ? (string.replaceAll("\\(", "").length() % string.replaceAll("\\)", "").length()) : (ListenerUtil.mutListener.listen(26765) ? (string.replaceAll("\\(", "").length() / string.replaceAll("\\)", "").length()) : (ListenerUtil.mutListener.listen(26764) ? (string.replaceAll("\\(", "").length() * string.replaceAll("\\)", "").length()) : (ListenerUtil.mutListener.listen(26763) ? (string.replaceAll("\\(", "").length() + string.replaceAll("\\)", "").length()) : (string.replaceAll("\\(", "").length() - string.replaceAll("\\)", "").length())))));
        if (!ListenerUtil.mutListener.listen(26772)) {
            if ((ListenerUtil.mutListener.listen(26771) ? (finalBracketCheck >= 0) : (ListenerUtil.mutListener.listen(26770) ? (finalBracketCheck <= 0) : (ListenerUtil.mutListener.listen(26769) ? (finalBracketCheck > 0) : (ListenerUtil.mutListener.listen(26768) ? (finalBracketCheck < 0) : (ListenerUtil.mutListener.listen(26767) ? (finalBracketCheck == 0) : (finalBracketCheck != 0))))))) {
                return false;
            }
        }
        int bracketOpeningCheck = 0;
        if (!ListenerUtil.mutListener.listen(26788)) {
            {
                long _loopCounter706 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(26787) ? (i >= string.length()) : (ListenerUtil.mutListener.listen(26786) ? (i <= string.length()) : (ListenerUtil.mutListener.listen(26785) ? (i > string.length()) : (ListenerUtil.mutListener.listen(26784) ? (i != string.length()) : (ListenerUtil.mutListener.listen(26783) ? (i == string.length()) : (i < string.length())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter706", ++_loopCounter706);
                    if (!ListenerUtil.mutListener.listen(26774)) {
                        if (string.charAt(i) == '(') {
                            if (!ListenerUtil.mutListener.listen(26773)) {
                                bracketOpeningCheck++;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(26776)) {
                        if (string.charAt(i) == ')') {
                            if (!ListenerUtil.mutListener.listen(26775)) {
                                bracketOpeningCheck--;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(26782)) {
                        if ((ListenerUtil.mutListener.listen(26781) ? (bracketOpeningCheck >= 0) : (ListenerUtil.mutListener.listen(26780) ? (bracketOpeningCheck <= 0) : (ListenerUtil.mutListener.listen(26779) ? (bracketOpeningCheck > 0) : (ListenerUtil.mutListener.listen(26778) ? (bracketOpeningCheck != 0) : (ListenerUtil.mutListener.listen(26777) ? (bracketOpeningCheck == 0) : (bracketOpeningCheck < 0))))))) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
