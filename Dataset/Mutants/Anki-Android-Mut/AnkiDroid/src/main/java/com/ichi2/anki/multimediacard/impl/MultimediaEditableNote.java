/**
 * *************************************************************************************
 *  Copyright (c) 2013 Bibek Shrestha <bibekshrestha@gmail.com>                          *
 *  Copyright (c) 2013 Zaur Molotnikov <qutorial@gmail.com>                              *
 *  Copyright (c) 2013 Nicolas Raoul <nicolas.raoul@gmail.com>                           *
 *  Copyright (c) 2013 Flavio Lerda <flerda@gmail.com>                                   *
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
package com.ichi2.anki.multimediacard.impl;

import com.ichi2.anki.multimediacard.IMultimediaEditableNote;
import com.ichi2.anki.multimediacard.fields.IField;
import java.util.ArrayList;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MultimediaEditableNote implements IMultimediaEditableNote {

    private static final long serialVersionUID = -6161821367135636659L;

    private boolean mIsModified = false;

    private ArrayList<IField> mFields;

    private long mModelId;

    private void setThisModified() {
        if (!ListenerUtil.mutListener.listen(2007)) {
            mIsModified = true;
        }
    }

    @Override
    public boolean isModified() {
        return mIsModified;
    }

    // package
    public void setNumFields(int numberOfFields) {
        if (!ListenerUtil.mutListener.listen(2008)) {
            getFieldsPrivate().clear();
        }
        if (!ListenerUtil.mutListener.listen(2015)) {
            {
                long _loopCounter26 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(2014) ? (i >= numberOfFields) : (ListenerUtil.mutListener.listen(2013) ? (i <= numberOfFields) : (ListenerUtil.mutListener.listen(2012) ? (i > numberOfFields) : (ListenerUtil.mutListener.listen(2011) ? (i != numberOfFields) : (ListenerUtil.mutListener.listen(2010) ? (i == numberOfFields) : (i < numberOfFields)))))); ++i) {
                    ListenerUtil.loopListener.listen("_loopCounter26", ++_loopCounter26);
                    if (!ListenerUtil.mutListener.listen(2009)) {
                        getFieldsPrivate().add(null);
                    }
                }
            }
        }
    }

    private ArrayList<IField> getFieldsPrivate() {
        if (!ListenerUtil.mutListener.listen(2017)) {
            if (mFields == null) {
                if (!ListenerUtil.mutListener.listen(2016)) {
                    mFields = new ArrayList<>(0);
                }
            }
        }
        return mFields;
    }

    @Override
    public int getNumberOfFields() {
        return getFieldsPrivate().size();
    }

    @Override
    public IField getField(int index) {
        if (!ListenerUtil.mutListener.listen(2029)) {
            if ((ListenerUtil.mutListener.listen(2028) ? ((ListenerUtil.mutListener.listen(2022) ? (index <= 0) : (ListenerUtil.mutListener.listen(2021) ? (index > 0) : (ListenerUtil.mutListener.listen(2020) ? (index < 0) : (ListenerUtil.mutListener.listen(2019) ? (index != 0) : (ListenerUtil.mutListener.listen(2018) ? (index == 0) : (index >= 0)))))) || (ListenerUtil.mutListener.listen(2027) ? (index >= getNumberOfFields()) : (ListenerUtil.mutListener.listen(2026) ? (index <= getNumberOfFields()) : (ListenerUtil.mutListener.listen(2025) ? (index > getNumberOfFields()) : (ListenerUtil.mutListener.listen(2024) ? (index != getNumberOfFields()) : (ListenerUtil.mutListener.listen(2023) ? (index == getNumberOfFields()) : (index < getNumberOfFields()))))))) : ((ListenerUtil.mutListener.listen(2022) ? (index <= 0) : (ListenerUtil.mutListener.listen(2021) ? (index > 0) : (ListenerUtil.mutListener.listen(2020) ? (index < 0) : (ListenerUtil.mutListener.listen(2019) ? (index != 0) : (ListenerUtil.mutListener.listen(2018) ? (index == 0) : (index >= 0)))))) && (ListenerUtil.mutListener.listen(2027) ? (index >= getNumberOfFields()) : (ListenerUtil.mutListener.listen(2026) ? (index <= getNumberOfFields()) : (ListenerUtil.mutListener.listen(2025) ? (index > getNumberOfFields()) : (ListenerUtil.mutListener.listen(2024) ? (index != getNumberOfFields()) : (ListenerUtil.mutListener.listen(2023) ? (index == getNumberOfFields()) : (index < getNumberOfFields()))))))))) {
                return getFieldsPrivate().get(index);
            }
        }
        return null;
    }

    @Override
    public boolean setField(int index, IField field) {
        if (!ListenerUtil.mutListener.listen(2046)) {
            if ((ListenerUtil.mutListener.listen(2040) ? ((ListenerUtil.mutListener.listen(2034) ? (index <= 0) : (ListenerUtil.mutListener.listen(2033) ? (index > 0) : (ListenerUtil.mutListener.listen(2032) ? (index < 0) : (ListenerUtil.mutListener.listen(2031) ? (index != 0) : (ListenerUtil.mutListener.listen(2030) ? (index == 0) : (index >= 0)))))) || (ListenerUtil.mutListener.listen(2039) ? (index >= getNumberOfFields()) : (ListenerUtil.mutListener.listen(2038) ? (index <= getNumberOfFields()) : (ListenerUtil.mutListener.listen(2037) ? (index > getNumberOfFields()) : (ListenerUtil.mutListener.listen(2036) ? (index != getNumberOfFields()) : (ListenerUtil.mutListener.listen(2035) ? (index == getNumberOfFields()) : (index < getNumberOfFields()))))))) : ((ListenerUtil.mutListener.listen(2034) ? (index <= 0) : (ListenerUtil.mutListener.listen(2033) ? (index > 0) : (ListenerUtil.mutListener.listen(2032) ? (index < 0) : (ListenerUtil.mutListener.listen(2031) ? (index != 0) : (ListenerUtil.mutListener.listen(2030) ? (index == 0) : (index >= 0)))))) && (ListenerUtil.mutListener.listen(2039) ? (index >= getNumberOfFields()) : (ListenerUtil.mutListener.listen(2038) ? (index <= getNumberOfFields()) : (ListenerUtil.mutListener.listen(2037) ? (index > getNumberOfFields()) : (ListenerUtil.mutListener.listen(2036) ? (index != getNumberOfFields()) : (ListenerUtil.mutListener.listen(2035) ? (index == getNumberOfFields()) : (index < getNumberOfFields()))))))))) {
                if (!ListenerUtil.mutListener.listen(2044)) {
                    // If the same unchanged field is set.
                    if (getField(index) == field) {
                        if (!ListenerUtil.mutListener.listen(2043)) {
                            if (field.isModified()) {
                                if (!ListenerUtil.mutListener.listen(2042)) {
                                    setThisModified();
                                }
                            }
                        }
                    } else {
                        if (!ListenerUtil.mutListener.listen(2041)) {
                            setThisModified();
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(2045)) {
                    getFieldsPrivate().set(index, field);
                }
                return true;
            }
        }
        return false;
    }

    public void setModelId(long modelId) {
        if (!ListenerUtil.mutListener.listen(2047)) {
            mModelId = modelId;
        }
    }

    public long getModelId() {
        return mModelId;
    }
}
