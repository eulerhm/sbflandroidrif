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

public class Pow implements TreeElement {

    private final TopLevelParser parser;

    public enum PowType {

        ATOM, ATOM_POW_FACTOR, ATOM_SQRT_FACTOR, INVALID
    }

    private PowType powType = PowType.INVALID;

    private Atom atom;

    private Factor factor;

    public Pow(String powString, TopLevelParser parser) {
        this.parser = parser;
        if (!ListenerUtil.mutListener.listen(26599)) {
            if (!TopLevelParser.stringHasValidBrackets(powString)) {
                if (!ListenerUtil.mutListener.listen(26598)) {
                    this.powType = PowType.INVALID;
                }
                return;
            }
        }
        boolean isReady = initAsAtom(powString);
        if (!ListenerUtil.mutListener.listen(26601)) {
            if (!isReady) {
                if (!ListenerUtil.mutListener.listen(26600)) {
                    isReady = initAsAtomPowFactor(powString);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26603)) {
            if (!isReady) {
                if (!ListenerUtil.mutListener.listen(26602)) {
                    isReady = initAsAtomSqrtFactor(powString);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(26605)) {
            if (!isReady) {
                if (!ListenerUtil.mutListener.listen(26604)) {
                    this.powType = PowType.INVALID;
                }
            }
        }
    }

    private boolean initAsAtom(String powString) {
        Atom atom = new Atom(powString, parser);
        boolean isValidAtom = atom.getAtomType() != Atom.AtomType.INVALID;
        if (!ListenerUtil.mutListener.listen(26608)) {
            if (isValidAtom) {
                if (!ListenerUtil.mutListener.listen(26606)) {
                    this.powType = PowType.ATOM;
                }
                if (!ListenerUtil.mutListener.listen(26607)) {
                    this.atom = atom;
                }
                return true;
            }
        }
        return false;
    }

    private boolean initAsAtomPowFactor(String powString) {
        int opPos = powString.indexOf("^");
        if (!ListenerUtil.mutListener.listen(26625)) {
            if ((ListenerUtil.mutListener.listen(26613) ? (opPos >= 0) : (ListenerUtil.mutListener.listen(26612) ? (opPos <= 0) : (ListenerUtil.mutListener.listen(26611) ? (opPos < 0) : (ListenerUtil.mutListener.listen(26610) ? (opPos != 0) : (ListenerUtil.mutListener.listen(26609) ? (opPos == 0) : (opPos > 0))))))) {
                String leftAtomString = powString.substring(0, opPos);
                String rightFactorString = powString.substring((ListenerUtil.mutListener.listen(26617) ? (opPos % 1) : (ListenerUtil.mutListener.listen(26616) ? (opPos / 1) : (ListenerUtil.mutListener.listen(26615) ? (opPos * 1) : (ListenerUtil.mutListener.listen(26614) ? (opPos - 1) : (opPos + 1))))));
                if (!ListenerUtil.mutListener.listen(26619)) {
                    if ((ListenerUtil.mutListener.listen(26618) ? (!TopLevelParser.stringHasValidBrackets(leftAtomString) && !TopLevelParser.stringHasValidBrackets(rightFactorString)) : (!TopLevelParser.stringHasValidBrackets(leftAtomString) || !TopLevelParser.stringHasValidBrackets(rightFactorString)))) {
                        return false;
                    }
                }
                Atom leftAtom = new Atom(leftAtomString, parser);
                boolean isValidAtom = leftAtom.getAtomType() != Atom.AtomType.INVALID;
                if (!ListenerUtil.mutListener.listen(26624)) {
                    if (isValidAtom) {
                        Factor rightFactor = new Factor(rightFactorString, parser);
                        boolean isValidFactor = rightFactor.getFactorType() != Factor.FactorType.INVALID;
                        if (!ListenerUtil.mutListener.listen(26623)) {
                            if (isValidFactor) {
                                if (!ListenerUtil.mutListener.listen(26620)) {
                                    this.powType = PowType.ATOM_POW_FACTOR;
                                }
                                if (!ListenerUtil.mutListener.listen(26621)) {
                                    this.atom = leftAtom;
                                }
                                if (!ListenerUtil.mutListener.listen(26622)) {
                                    this.factor = rightFactor;
                                }
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean initAsAtomSqrtFactor(String powString) {
        int opPos = powString.indexOf("**");
        if (!ListenerUtil.mutListener.listen(26642)) {
            if ((ListenerUtil.mutListener.listen(26630) ? (opPos >= 0) : (ListenerUtil.mutListener.listen(26629) ? (opPos <= 0) : (ListenerUtil.mutListener.listen(26628) ? (opPos < 0) : (ListenerUtil.mutListener.listen(26627) ? (opPos != 0) : (ListenerUtil.mutListener.listen(26626) ? (opPos == 0) : (opPos > 0))))))) {
                String leftAtomString = powString.substring(0, opPos);
                String rightFactorString = powString.substring((ListenerUtil.mutListener.listen(26634) ? (opPos % 2) : (ListenerUtil.mutListener.listen(26633) ? (opPos / 2) : (ListenerUtil.mutListener.listen(26632) ? (opPos * 2) : (ListenerUtil.mutListener.listen(26631) ? (opPos - 2) : (opPos + 2))))));
                if (!ListenerUtil.mutListener.listen(26636)) {
                    if ((ListenerUtil.mutListener.listen(26635) ? (!TopLevelParser.stringHasValidBrackets(leftAtomString) && !TopLevelParser.stringHasValidBrackets(rightFactorString)) : (!TopLevelParser.stringHasValidBrackets(leftAtomString) || !TopLevelParser.stringHasValidBrackets(rightFactorString)))) {
                        return false;
                    }
                }
                Atom leftAtom = new Atom(leftAtomString, parser);
                boolean isValidAtom = leftAtom.getAtomType() != Atom.AtomType.INVALID;
                if (!ListenerUtil.mutListener.listen(26641)) {
                    if (isValidAtom) {
                        Factor rightFactor = new Factor(rightFactorString, parser);
                        boolean isValidFactor = rightFactor.getFactorType() != Factor.FactorType.INVALID;
                        if (!ListenerUtil.mutListener.listen(26640)) {
                            if (isValidFactor) {
                                if (!ListenerUtil.mutListener.listen(26637)) {
                                    this.powType = PowType.ATOM_SQRT_FACTOR;
                                }
                                if (!ListenerUtil.mutListener.listen(26638)) {
                                    this.atom = leftAtom;
                                }
                                if (!ListenerUtil.mutListener.listen(26639)) {
                                    this.factor = rightFactor;
                                }
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public double getValue() throws ExpressionFormatException {
        switch(powType) {
            case ATOM:
                return atom.getValue();
            case ATOM_POW_FACTOR:
                return Math.pow(atom.getValue(), factor.getValue());
            case ATOM_SQRT_FACTOR:
                return Math.pow(atom.getValue(), (ListenerUtil.mutListener.listen(26646) ? (1.0 % factor.getValue()) : (ListenerUtil.mutListener.listen(26645) ? (1.0 * factor.getValue()) : (ListenerUtil.mutListener.listen(26644) ? (1.0 - factor.getValue()) : (ListenerUtil.mutListener.listen(26643) ? (1.0 + factor.getValue()) : (1.0 / factor.getValue()))))));
            case INVALID:
            default:
                throw new ExpressionFormatException("cannot parse Atom expression");
        }
    }

    @Override
    public boolean isVariable() throws ExpressionFormatException {
        switch(powType) {
            case ATOM:
                return atom.isVariable();
            case ATOM_POW_FACTOR:
            case ATOM_SQRT_FACTOR:
                return (ListenerUtil.mutListener.listen(26647) ? (atom.isVariable() && factor.isVariable()) : (atom.isVariable() || factor.isVariable()));
            case INVALID:
            default:
                throw new ExpressionFormatException("cannot parse Atom expression");
        }
    }

    public PowType getPowType() {
        return powType;
    }
}
