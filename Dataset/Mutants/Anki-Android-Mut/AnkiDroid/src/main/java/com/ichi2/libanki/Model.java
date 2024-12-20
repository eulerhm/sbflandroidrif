/**
 * *************************************************************************************
 *  Copyright (c) 2020 Arthur Milchior <arthur@milchior.fr>                             *
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
package com.ichi2.libanki;

import android.text.TextUtils;
import android.util.Pair;
import com.ichi2.libanki.template.ParsedNode;
import com.ichi2.libanki.template.TemplateError;
import com.ichi2.utils.JSONArray;
import com.ichi2.utils.JSONObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import androidx.annotation.CheckResult;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Represents a note type, a.k.a. Model.
 * The content of an object is described in https://github.com/ankidroid/Anki-Android/wiki/Database-Structure
 * Each time the object is modified, `Models.save(this)` should be called, otherwise the change will not be synchronized
 * If a change affect card generation, (i.e. any change on the list of field, or the question side of a card type), `Models.save(this, true)` should be called. However, you should do the change in batch and change only when aall are done, because recomputing the list of card is an expensive operation.
 */
public class Model extends JSONObject {

    public Model() {
        super();
    }

    public Model(JSONObject json) {
        super(json);
    }

    public Model(String json) {
        super(json);
    }

    @Override
    @CheckResult
    public Model deepClone() {
        Model clone = new Model();
        return deepClonedInto(clone);
    }

    public List<String> getFieldsNames() {
        return getJSONArray("flds").toStringList("name");
    }

    public List<String> getTemplatesNames() {
        return getJSONArray("tmpls").toStringList("name");
    }

    public boolean isStd() {
        return getInt("type") == Consts.MODEL_STD;
    }

    public boolean isCloze() {
        return getInt("type") == Consts.MODEL_CLOZE;
    }

    /**
     * @param sfld Fields of a note of this note type
     * @return The set of name of non-empty fields.
     */
    public Set<String> nonEmptyFields(String[] sfld) {
        List<String> fieldNames = getFieldsNames();
        Set<String> nonemptyFields = new HashSet<>(sfld.length);
        if (!ListenerUtil.mutListener.listen(23167)) {
            {
                long _loopCounter567 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(23166) ? (i >= sfld.length) : (ListenerUtil.mutListener.listen(23165) ? (i <= sfld.length) : (ListenerUtil.mutListener.listen(23164) ? (i > sfld.length) : (ListenerUtil.mutListener.listen(23163) ? (i != sfld.length) : (ListenerUtil.mutListener.listen(23162) ? (i == sfld.length) : (i < sfld.length)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter567", ++_loopCounter567);
                    if (!ListenerUtil.mutListener.listen(23161)) {
                        if (!TextUtils.isEmpty(sfld[i].trim())) {
                            if (!ListenerUtil.mutListener.listen(23160)) {
                                nonemptyFields.add(fieldNames.get(i));
                            }
                        }
                    }
                }
            }
        }
        return nonemptyFields;
    }

    /**
     * @return A list of parsed nodes for each template's question. null in case of exception
     */
    public List<ParsedNode> parsedNodes() {
        JSONArray tmpls = getJSONArray("tmpls");
        List<ParsedNode> nodes = new ArrayList<>(tmpls.length());
        if (!ListenerUtil.mutListener.listen(23170)) {
            {
                long _loopCounter568 = 0;
                for (JSONObject tmpl : tmpls.jsonObjectIterable()) {
                    ListenerUtil.loopListener.listen("_loopCounter568", ++_loopCounter568);
                    String format_question = tmpl.getString("qfmt");
                    ParsedNode node = null;
                    try {
                        if (!ListenerUtil.mutListener.listen(23168)) {
                            node = ParsedNode.parse_inner(format_question);
                        }
                    } catch (TemplateError er) {
                    }
                    if (!ListenerUtil.mutListener.listen(23169)) {
                        nodes.add(node);
                    }
                }
            }
        }
        return nodes;
    }
}
