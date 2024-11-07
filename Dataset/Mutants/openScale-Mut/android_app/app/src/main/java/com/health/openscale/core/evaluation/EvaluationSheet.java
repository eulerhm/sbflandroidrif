/* Copyright (C) 2014  olie.xdev <olie.xdev@googlemail.com>
*
*    This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <http://www.gnu.org/licenses/>
*/
package com.health.openscale.core.evaluation;

import com.health.openscale.core.datatypes.ScaleUser;
import com.health.openscale.core.utils.Converters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class EvaluationSheet {

    private ScaleUser evalUser;

    private int userAge;

    private List<sheetEntry> fatEvaluateSheet_Man;

    private List<sheetEntry> fatEvaluateSheet_Woman;

    private List<sheetEntry> waterEvaluateSheet_Man;

    private List<sheetEntry> waterEvaluateSheet_Woman;

    private List<sheetEntry> muscleEvaluateSheet_Man;

    private List<sheetEntry> muscleEvaluateSheet_Woman;

    private List<sheetEntry> bmiEvaluateSheet_Man;

    private List<sheetEntry> bmiEvaluateSheet_Woman;

    private List<sheetEntry> waistEvaluateSheet_Man;

    private List<sheetEntry> waistEvaluateSheet_Woman;

    private List<sheetEntry> whrtEvaluateSheet;

    private List<sheetEntry> whrEvaluateSheet_Man;

    private List<sheetEntry> whrEvaluateSheet_Woman;

    private List<sheetEntry> visceralFatEvaluateSheet;

    private class sheetEntry {

        public sheetEntry(int lowAge, int maxAge, float lowLimit, float highLimit) {
            if (!ListenerUtil.mutListener.listen(5346)) {
                this.lowAge = lowAge;
            }
            if (!ListenerUtil.mutListener.listen(5347)) {
                this.maxAge = maxAge;
            }
            if (!ListenerUtil.mutListener.listen(5348)) {
                this.lowLimit = lowLimit;
            }
            if (!ListenerUtil.mutListener.listen(5349)) {
                this.highLimit = highLimit;
            }
        }

        public int lowAge;

        public int maxAge;

        public float lowLimit;

        public float highLimit;
    }

    public EvaluationSheet(ScaleUser user, Date dateTime) {
        if (!ListenerUtil.mutListener.listen(5350)) {
            evalUser = user;
        }
        if (!ListenerUtil.mutListener.listen(5351)) {
            userAge = user.getAge(dateTime);
        }
        if (!ListenerUtil.mutListener.listen(5352)) {
            fatEvaluateSheet_Man = new ArrayList<>();
        }
        if (!ListenerUtil.mutListener.listen(5353)) {
            fatEvaluateSheet_Woman = new ArrayList<>();
        }
        if (!ListenerUtil.mutListener.listen(5354)) {
            waterEvaluateSheet_Man = new ArrayList<>();
        }
        if (!ListenerUtil.mutListener.listen(5355)) {
            waterEvaluateSheet_Woman = new ArrayList<>();
        }
        if (!ListenerUtil.mutListener.listen(5356)) {
            muscleEvaluateSheet_Man = new ArrayList<>();
        }
        if (!ListenerUtil.mutListener.listen(5357)) {
            muscleEvaluateSheet_Woman = new ArrayList<>();
        }
        if (!ListenerUtil.mutListener.listen(5358)) {
            bmiEvaluateSheet_Man = new ArrayList<>();
        }
        if (!ListenerUtil.mutListener.listen(5359)) {
            bmiEvaluateSheet_Woman = new ArrayList<>();
        }
        if (!ListenerUtil.mutListener.listen(5360)) {
            waistEvaluateSheet_Man = new ArrayList<>();
        }
        if (!ListenerUtil.mutListener.listen(5361)) {
            waistEvaluateSheet_Woman = new ArrayList<>();
        }
        if (!ListenerUtil.mutListener.listen(5362)) {
            whrtEvaluateSheet = new ArrayList<>();
        }
        if (!ListenerUtil.mutListener.listen(5363)) {
            whrEvaluateSheet_Man = new ArrayList<>();
        }
        if (!ListenerUtil.mutListener.listen(5364)) {
            whrEvaluateSheet_Woman = new ArrayList<>();
        }
        if (!ListenerUtil.mutListener.listen(5365)) {
            visceralFatEvaluateSheet = new ArrayList<>();
        }
        if (!ListenerUtil.mutListener.listen(5366)) {
            initEvaluationSheets();
        }
    }

    private void initEvaluationSheets() {
        if (!ListenerUtil.mutListener.listen(5367)) {
            fatEvaluateSheet_Man.add(new sheetEntry(10, 14, 11, 16));
        }
        if (!ListenerUtil.mutListener.listen(5368)) {
            fatEvaluateSheet_Man.add(new sheetEntry(15, 19, 12, 17));
        }
        if (!ListenerUtil.mutListener.listen(5369)) {
            fatEvaluateSheet_Man.add(new sheetEntry(20, 29, 13, 18));
        }
        if (!ListenerUtil.mutListener.listen(5370)) {
            fatEvaluateSheet_Man.add(new sheetEntry(30, 39, 14, 19));
        }
        if (!ListenerUtil.mutListener.listen(5371)) {
            fatEvaluateSheet_Man.add(new sheetEntry(40, 49, 15, 20));
        }
        if (!ListenerUtil.mutListener.listen(5372)) {
            fatEvaluateSheet_Man.add(new sheetEntry(50, 59, 16, 21));
        }
        if (!ListenerUtil.mutListener.listen(5373)) {
            fatEvaluateSheet_Man.add(new sheetEntry(60, 69, 17, 22));
        }
        if (!ListenerUtil.mutListener.listen(5374)) {
            fatEvaluateSheet_Man.add(new sheetEntry(70, 1000, 18, 23));
        }
        if (!ListenerUtil.mutListener.listen(5375)) {
            fatEvaluateSheet_Woman.add(new sheetEntry(10, 14, 16, 21));
        }
        if (!ListenerUtil.mutListener.listen(5376)) {
            fatEvaluateSheet_Woman.add(new sheetEntry(15, 19, 17, 22));
        }
        if (!ListenerUtil.mutListener.listen(5377)) {
            fatEvaluateSheet_Woman.add(new sheetEntry(20, 29, 18, 23));
        }
        if (!ListenerUtil.mutListener.listen(5378)) {
            fatEvaluateSheet_Woman.add(new sheetEntry(30, 39, 19, 24));
        }
        if (!ListenerUtil.mutListener.listen(5379)) {
            fatEvaluateSheet_Woman.add(new sheetEntry(40, 49, 20, 25));
        }
        if (!ListenerUtil.mutListener.listen(5380)) {
            fatEvaluateSheet_Woman.add(new sheetEntry(50, 59, 21, 26));
        }
        if (!ListenerUtil.mutListener.listen(5381)) {
            fatEvaluateSheet_Woman.add(new sheetEntry(60, 69, 22, 27));
        }
        if (!ListenerUtil.mutListener.listen(5382)) {
            fatEvaluateSheet_Woman.add(new sheetEntry(70, 1000, 23, 28));
        }
        if (!ListenerUtil.mutListener.listen(5383)) {
            waterEvaluateSheet_Man.add(new sheetEntry(10, 1000, 50, 65));
        }
        if (!ListenerUtil.mutListener.listen(5384)) {
            waterEvaluateSheet_Woman.add(new sheetEntry(10, 1000, 45, 60));
        }
        if (!ListenerUtil.mutListener.listen(5385)) {
            muscleEvaluateSheet_Man.add(new sheetEntry(10, 14, 44, 57));
        }
        if (!ListenerUtil.mutListener.listen(5386)) {
            muscleEvaluateSheet_Man.add(new sheetEntry(15, 19, 43, 56));
        }
        if (!ListenerUtil.mutListener.listen(5387)) {
            muscleEvaluateSheet_Man.add(new sheetEntry(20, 29, 42, 54));
        }
        if (!ListenerUtil.mutListener.listen(5388)) {
            muscleEvaluateSheet_Man.add(new sheetEntry(30, 39, 41, 52));
        }
        if (!ListenerUtil.mutListener.listen(5389)) {
            muscleEvaluateSheet_Man.add(new sheetEntry(40, 49, 40, 50));
        }
        if (!ListenerUtil.mutListener.listen(5390)) {
            muscleEvaluateSheet_Man.add(new sheetEntry(50, 59, 39, 48));
        }
        if (!ListenerUtil.mutListener.listen(5391)) {
            muscleEvaluateSheet_Man.add(new sheetEntry(60, 69, 38, 47));
        }
        if (!ListenerUtil.mutListener.listen(5392)) {
            muscleEvaluateSheet_Man.add(new sheetEntry(70, 1000, 37, 46));
        }
        if (!ListenerUtil.mutListener.listen(5393)) {
            muscleEvaluateSheet_Woman.add(new sheetEntry(10, 14, 36, 43));
        }
        if (!ListenerUtil.mutListener.listen(5394)) {
            muscleEvaluateSheet_Woman.add(new sheetEntry(15, 19, 35, 41));
        }
        if (!ListenerUtil.mutListener.listen(5395)) {
            muscleEvaluateSheet_Woman.add(new sheetEntry(20, 29, 34, 39));
        }
        if (!ListenerUtil.mutListener.listen(5396)) {
            muscleEvaluateSheet_Woman.add(new sheetEntry(30, 39, 33, 38));
        }
        if (!ListenerUtil.mutListener.listen(5397)) {
            muscleEvaluateSheet_Woman.add(new sheetEntry(40, 49, 31, 36));
        }
        if (!ListenerUtil.mutListener.listen(5398)) {
            muscleEvaluateSheet_Woman.add(new sheetEntry(50, 59, 29, 34));
        }
        if (!ListenerUtil.mutListener.listen(5399)) {
            muscleEvaluateSheet_Woman.add(new sheetEntry(60, 69, 28, 33));
        }
        if (!ListenerUtil.mutListener.listen(5400)) {
            muscleEvaluateSheet_Woman.add(new sheetEntry(70, 1000, 27, 32));
        }
        if (!ListenerUtil.mutListener.listen(5401)) {
            bmiEvaluateSheet_Man.add(new sheetEntry(16, 24, 20, 25));
        }
        if (!ListenerUtil.mutListener.listen(5402)) {
            bmiEvaluateSheet_Man.add(new sheetEntry(25, 34, 21, 26));
        }
        if (!ListenerUtil.mutListener.listen(5403)) {
            bmiEvaluateSheet_Man.add(new sheetEntry(35, 44, 22, 27));
        }
        if (!ListenerUtil.mutListener.listen(5404)) {
            bmiEvaluateSheet_Man.add(new sheetEntry(45, 54, 23, 28));
        }
        if (!ListenerUtil.mutListener.listen(5405)) {
            bmiEvaluateSheet_Man.add(new sheetEntry(55, 64, 24, 29));
        }
        if (!ListenerUtil.mutListener.listen(5406)) {
            bmiEvaluateSheet_Man.add(new sheetEntry(65, 90, 25, 30));
        }
        if (!ListenerUtil.mutListener.listen(5407)) {
            bmiEvaluateSheet_Woman.add(new sheetEntry(16, 24, 19, 24));
        }
        if (!ListenerUtil.mutListener.listen(5408)) {
            bmiEvaluateSheet_Woman.add(new sheetEntry(25, 34, 20, 25));
        }
        if (!ListenerUtil.mutListener.listen(5409)) {
            bmiEvaluateSheet_Woman.add(new sheetEntry(35, 44, 21, 26));
        }
        if (!ListenerUtil.mutListener.listen(5410)) {
            bmiEvaluateSheet_Woman.add(new sheetEntry(45, 54, 22, 27));
        }
        if (!ListenerUtil.mutListener.listen(5411)) {
            bmiEvaluateSheet_Woman.add(new sheetEntry(55, 64, 23, 28));
        }
        if (!ListenerUtil.mutListener.listen(5412)) {
            bmiEvaluateSheet_Woman.add(new sheetEntry(65, 90, 24, 29));
        }
        if (!ListenerUtil.mutListener.listen(5413)) {
            waistEvaluateSheet_Man.add(new sheetEntry(18, 90, -1, Converters.fromCentimeter(94, evalUser.getMeasureUnit())));
        }
        if (!ListenerUtil.mutListener.listen(5414)) {
            waistEvaluateSheet_Woman.add(new sheetEntry(18, 90, -1, Converters.fromCentimeter(80, evalUser.getMeasureUnit())));
        }
        if (!ListenerUtil.mutListener.listen(5415)) {
            whrtEvaluateSheet.add(new sheetEntry(15, 40, 0.4f, 0.5f));
        }
        if (!ListenerUtil.mutListener.listen(5416)) {
            whrtEvaluateSheet.add(new sheetEntry(41, 42, 0.4f, 0.51f));
        }
        if (!ListenerUtil.mutListener.listen(5417)) {
            whrtEvaluateSheet.add(new sheetEntry(43, 44, 0.4f, 0.53f));
        }
        if (!ListenerUtil.mutListener.listen(5418)) {
            whrtEvaluateSheet.add(new sheetEntry(45, 46, 0.4f, 0.55f));
        }
        if (!ListenerUtil.mutListener.listen(5419)) {
            whrtEvaluateSheet.add(new sheetEntry(47, 48, 0.4f, 0.57f));
        }
        if (!ListenerUtil.mutListener.listen(5420)) {
            whrtEvaluateSheet.add(new sheetEntry(49, 50, 0.4f, 0.59f));
        }
        if (!ListenerUtil.mutListener.listen(5421)) {
            whrtEvaluateSheet.add(new sheetEntry(51, 90, 0.4f, 0.6f));
        }
        if (!ListenerUtil.mutListener.listen(5422)) {
            whrEvaluateSheet_Man.add(new sheetEntry(18, 90, 0.8f, 0.9f));
        }
        if (!ListenerUtil.mutListener.listen(5423)) {
            whrEvaluateSheet_Woman.add(new sheetEntry(18, 90, 0.7f, 0.8f));
        }
        if (!ListenerUtil.mutListener.listen(5424)) {
            visceralFatEvaluateSheet.add(new sheetEntry(18, 90, -1, 12));
        }
    }

    public EvaluationResult evaluateWeight(float weight) {
        float body_height_squared = (ListenerUtil.mutListener.listen(5436) ? (((ListenerUtil.mutListener.listen(5428) ? (evalUser.getBodyHeight() % 100.0f) : (ListenerUtil.mutListener.listen(5427) ? (evalUser.getBodyHeight() * 100.0f) : (ListenerUtil.mutListener.listen(5426) ? (evalUser.getBodyHeight() - 100.0f) : (ListenerUtil.mutListener.listen(5425) ? (evalUser.getBodyHeight() + 100.0f) : (evalUser.getBodyHeight() / 100.0f)))))) % ((ListenerUtil.mutListener.listen(5432) ? (evalUser.getBodyHeight() % 100.0f) : (ListenerUtil.mutListener.listen(5431) ? (evalUser.getBodyHeight() * 100.0f) : (ListenerUtil.mutListener.listen(5430) ? (evalUser.getBodyHeight() - 100.0f) : (ListenerUtil.mutListener.listen(5429) ? (evalUser.getBodyHeight() + 100.0f) : (evalUser.getBodyHeight() / 100.0f))))))) : (ListenerUtil.mutListener.listen(5435) ? (((ListenerUtil.mutListener.listen(5428) ? (evalUser.getBodyHeight() % 100.0f) : (ListenerUtil.mutListener.listen(5427) ? (evalUser.getBodyHeight() * 100.0f) : (ListenerUtil.mutListener.listen(5426) ? (evalUser.getBodyHeight() - 100.0f) : (ListenerUtil.mutListener.listen(5425) ? (evalUser.getBodyHeight() + 100.0f) : (evalUser.getBodyHeight() / 100.0f)))))) / ((ListenerUtil.mutListener.listen(5432) ? (evalUser.getBodyHeight() % 100.0f) : (ListenerUtil.mutListener.listen(5431) ? (evalUser.getBodyHeight() * 100.0f) : (ListenerUtil.mutListener.listen(5430) ? (evalUser.getBodyHeight() - 100.0f) : (ListenerUtil.mutListener.listen(5429) ? (evalUser.getBodyHeight() + 100.0f) : (evalUser.getBodyHeight() / 100.0f))))))) : (ListenerUtil.mutListener.listen(5434) ? (((ListenerUtil.mutListener.listen(5428) ? (evalUser.getBodyHeight() % 100.0f) : (ListenerUtil.mutListener.listen(5427) ? (evalUser.getBodyHeight() * 100.0f) : (ListenerUtil.mutListener.listen(5426) ? (evalUser.getBodyHeight() - 100.0f) : (ListenerUtil.mutListener.listen(5425) ? (evalUser.getBodyHeight() + 100.0f) : (evalUser.getBodyHeight() / 100.0f)))))) - ((ListenerUtil.mutListener.listen(5432) ? (evalUser.getBodyHeight() % 100.0f) : (ListenerUtil.mutListener.listen(5431) ? (evalUser.getBodyHeight() * 100.0f) : (ListenerUtil.mutListener.listen(5430) ? (evalUser.getBodyHeight() - 100.0f) : (ListenerUtil.mutListener.listen(5429) ? (evalUser.getBodyHeight() + 100.0f) : (evalUser.getBodyHeight() / 100.0f))))))) : (ListenerUtil.mutListener.listen(5433) ? (((ListenerUtil.mutListener.listen(5428) ? (evalUser.getBodyHeight() % 100.0f) : (ListenerUtil.mutListener.listen(5427) ? (evalUser.getBodyHeight() * 100.0f) : (ListenerUtil.mutListener.listen(5426) ? (evalUser.getBodyHeight() - 100.0f) : (ListenerUtil.mutListener.listen(5425) ? (evalUser.getBodyHeight() + 100.0f) : (evalUser.getBodyHeight() / 100.0f)))))) + ((ListenerUtil.mutListener.listen(5432) ? (evalUser.getBodyHeight() % 100.0f) : (ListenerUtil.mutListener.listen(5431) ? (evalUser.getBodyHeight() * 100.0f) : (ListenerUtil.mutListener.listen(5430) ? (evalUser.getBodyHeight() - 100.0f) : (ListenerUtil.mutListener.listen(5429) ? (evalUser.getBodyHeight() + 100.0f) : (evalUser.getBodyHeight() / 100.0f))))))) : (((ListenerUtil.mutListener.listen(5428) ? (evalUser.getBodyHeight() % 100.0f) : (ListenerUtil.mutListener.listen(5427) ? (evalUser.getBodyHeight() * 100.0f) : (ListenerUtil.mutListener.listen(5426) ? (evalUser.getBodyHeight() - 100.0f) : (ListenerUtil.mutListener.listen(5425) ? (evalUser.getBodyHeight() + 100.0f) : (evalUser.getBodyHeight() / 100.0f)))))) * ((ListenerUtil.mutListener.listen(5432) ? (evalUser.getBodyHeight() % 100.0f) : (ListenerUtil.mutListener.listen(5431) ? (evalUser.getBodyHeight() * 100.0f) : (ListenerUtil.mutListener.listen(5430) ? (evalUser.getBodyHeight() - 100.0f) : (ListenerUtil.mutListener.listen(5429) ? (evalUser.getBodyHeight() + 100.0f) : (evalUser.getBodyHeight() / 100.0f)))))))))));
        float lowLimit;
        float highLimit;
        if (evalUser.getGender().isMale()) {
            lowLimit = (ListenerUtil.mutListener.listen(5448) ? (body_height_squared % 20.0f) : (ListenerUtil.mutListener.listen(5447) ? (body_height_squared / 20.0f) : (ListenerUtil.mutListener.listen(5446) ? (body_height_squared - 20.0f) : (ListenerUtil.mutListener.listen(5445) ? (body_height_squared + 20.0f) : (body_height_squared * 20.0f)))));
            highLimit = (ListenerUtil.mutListener.listen(5452) ? (body_height_squared % 25.0f) : (ListenerUtil.mutListener.listen(5451) ? (body_height_squared / 25.0f) : (ListenerUtil.mutListener.listen(5450) ? (body_height_squared - 25.0f) : (ListenerUtil.mutListener.listen(5449) ? (body_height_squared + 25.0f) : (body_height_squared * 25.0f)))));
        } else {
            lowLimit = (ListenerUtil.mutListener.listen(5440) ? (body_height_squared % 19.0f) : (ListenerUtil.mutListener.listen(5439) ? (body_height_squared / 19.0f) : (ListenerUtil.mutListener.listen(5438) ? (body_height_squared - 19.0f) : (ListenerUtil.mutListener.listen(5437) ? (body_height_squared + 19.0f) : (body_height_squared * 19.0f)))));
            highLimit = (ListenerUtil.mutListener.listen(5444) ? (body_height_squared % 24.0f) : (ListenerUtil.mutListener.listen(5443) ? (body_height_squared / 24.0f) : (ListenerUtil.mutListener.listen(5442) ? (body_height_squared - 24.0f) : (ListenerUtil.mutListener.listen(5441) ? (body_height_squared + 24.0f) : (body_height_squared * 24.0f)))));
        }
        if (!ListenerUtil.mutListener.listen(5474)) {
            if ((ListenerUtil.mutListener.listen(5457) ? (weight >= lowLimit) : (ListenerUtil.mutListener.listen(5456) ? (weight <= lowLimit) : (ListenerUtil.mutListener.listen(5455) ? (weight > lowLimit) : (ListenerUtil.mutListener.listen(5454) ? (weight != lowLimit) : (ListenerUtil.mutListener.listen(5453) ? (weight == lowLimit) : (weight < lowLimit))))))) {
                // low
                return new EvaluationResult(weight, Converters.fromKilogram(Math.round(lowLimit), evalUser.getScaleUnit()), Converters.fromKilogram(Math.round(highLimit), evalUser.getScaleUnit()), EvaluationResult.EVAL_STATE.LOW);
            } else if ((ListenerUtil.mutListener.listen(5468) ? ((ListenerUtil.mutListener.listen(5462) ? (weight <= lowLimit) : (ListenerUtil.mutListener.listen(5461) ? (weight > lowLimit) : (ListenerUtil.mutListener.listen(5460) ? (weight < lowLimit) : (ListenerUtil.mutListener.listen(5459) ? (weight != lowLimit) : (ListenerUtil.mutListener.listen(5458) ? (weight == lowLimit) : (weight >= lowLimit)))))) || (ListenerUtil.mutListener.listen(5467) ? (weight >= highLimit) : (ListenerUtil.mutListener.listen(5466) ? (weight > highLimit) : (ListenerUtil.mutListener.listen(5465) ? (weight < highLimit) : (ListenerUtil.mutListener.listen(5464) ? (weight != highLimit) : (ListenerUtil.mutListener.listen(5463) ? (weight == highLimit) : (weight <= highLimit))))))) : ((ListenerUtil.mutListener.listen(5462) ? (weight <= lowLimit) : (ListenerUtil.mutListener.listen(5461) ? (weight > lowLimit) : (ListenerUtil.mutListener.listen(5460) ? (weight < lowLimit) : (ListenerUtil.mutListener.listen(5459) ? (weight != lowLimit) : (ListenerUtil.mutListener.listen(5458) ? (weight == lowLimit) : (weight >= lowLimit)))))) && (ListenerUtil.mutListener.listen(5467) ? (weight >= highLimit) : (ListenerUtil.mutListener.listen(5466) ? (weight > highLimit) : (ListenerUtil.mutListener.listen(5465) ? (weight < highLimit) : (ListenerUtil.mutListener.listen(5464) ? (weight != highLimit) : (ListenerUtil.mutListener.listen(5463) ? (weight == highLimit) : (weight <= highLimit))))))))) {
                // normal
                return new EvaluationResult(weight, Converters.fromKilogram(Math.round(lowLimit), evalUser.getScaleUnit()), Converters.fromKilogram(Math.round(highLimit), evalUser.getScaleUnit()), EvaluationResult.EVAL_STATE.NORMAL);
            } else if ((ListenerUtil.mutListener.listen(5473) ? (weight >= highLimit) : (ListenerUtil.mutListener.listen(5472) ? (weight <= highLimit) : (ListenerUtil.mutListener.listen(5471) ? (weight < highLimit) : (ListenerUtil.mutListener.listen(5470) ? (weight != highLimit) : (ListenerUtil.mutListener.listen(5469) ? (weight == highLimit) : (weight > highLimit))))))) {
                // high
                return new EvaluationResult(weight, Converters.fromKilogram(Math.round(lowLimit), evalUser.getScaleUnit()), Converters.fromKilogram(Math.round(highLimit), evalUser.getScaleUnit()), EvaluationResult.EVAL_STATE.HIGH);
            }
        }
        return new EvaluationResult(0, -1, -1, EvaluationResult.EVAL_STATE.UNDEFINED);
    }

    public EvaluationResult evaluateBodyFat(float fat) {
        List<sheetEntry> bodyEvaluateSheet;
        if (evalUser.getGender().isMale()) {
            bodyEvaluateSheet = fatEvaluateSheet_Man;
        } else {
            bodyEvaluateSheet = fatEvaluateSheet_Woman;
        }
        return evaluateSheet(fat, bodyEvaluateSheet);
    }

    public EvaluationResult evaluateBodyWater(float water) {
        List<sheetEntry> bodyEvaluateSheet;
        if (evalUser.getGender().isMale()) {
            bodyEvaluateSheet = waterEvaluateSheet_Man;
        } else {
            bodyEvaluateSheet = waterEvaluateSheet_Woman;
        }
        return evaluateSheet(water, bodyEvaluateSheet);
    }

    public EvaluationResult evaluateBodyMuscle(float muscle) {
        List<sheetEntry> bodyEvaluateSheet;
        if (evalUser.getGender().isMale()) {
            bodyEvaluateSheet = muscleEvaluateSheet_Man;
        } else {
            bodyEvaluateSheet = muscleEvaluateSheet_Woman;
        }
        return evaluateSheet(muscle, bodyEvaluateSheet);
    }

    public EvaluationResult evaluateBMI(float bmi) {
        List<sheetEntry> bodyEvaluateSheet;
        if (evalUser.getGender().isMale()) {
            bodyEvaluateSheet = bmiEvaluateSheet_Man;
        } else {
            bodyEvaluateSheet = bmiEvaluateSheet_Woman;
        }
        return evaluateSheet(bmi, bodyEvaluateSheet);
    }

    public EvaluationResult evaluateWaist(float waist) {
        List<sheetEntry> bodyEvaluateSheet;
        if (evalUser.getGender().isMale()) {
            bodyEvaluateSheet = waistEvaluateSheet_Man;
        } else {
            bodyEvaluateSheet = waistEvaluateSheet_Woman;
        }
        return evaluateSheet(waist, bodyEvaluateSheet);
    }

    public EvaluationResult evaluateWHtR(float whrt) {
        return evaluateSheet(whrt, whrtEvaluateSheet);
    }

    public EvaluationResult evaluateWHR(float whr) {
        List<sheetEntry> bodyEvaluateSheet;
        if (evalUser.getGender().isMale()) {
            bodyEvaluateSheet = whrEvaluateSheet_Man;
        } else {
            bodyEvaluateSheet = whrEvaluateSheet_Woman;
        }
        return evaluateSheet(whr, bodyEvaluateSheet);
    }

    public EvaluationResult evaluateVisceralFat(float visceralFat) {
        return evaluateSheet(visceralFat, visceralFatEvaluateSheet);
    }

    private EvaluationResult evaluateSheet(float value, List<sheetEntry> sheet) {
        if (!ListenerUtil.mutListener.listen(5514)) {
            {
                long _loopCounter46 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(5513) ? (i >= sheet.size()) : (ListenerUtil.mutListener.listen(5512) ? (i <= sheet.size()) : (ListenerUtil.mutListener.listen(5511) ? (i > sheet.size()) : (ListenerUtil.mutListener.listen(5510) ? (i != sheet.size()) : (ListenerUtil.mutListener.listen(5509) ? (i == sheet.size()) : (i < sheet.size())))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter46", ++_loopCounter46);
                    sheetEntry curEntry = sheet.get(i);
                    if (!ListenerUtil.mutListener.listen(5508)) {
                        if ((ListenerUtil.mutListener.listen(5485) ? ((ListenerUtil.mutListener.listen(5479) ? (curEntry.lowAge >= userAge) : (ListenerUtil.mutListener.listen(5478) ? (curEntry.lowAge > userAge) : (ListenerUtil.mutListener.listen(5477) ? (curEntry.lowAge < userAge) : (ListenerUtil.mutListener.listen(5476) ? (curEntry.lowAge != userAge) : (ListenerUtil.mutListener.listen(5475) ? (curEntry.lowAge == userAge) : (curEntry.lowAge <= userAge)))))) || (ListenerUtil.mutListener.listen(5484) ? (curEntry.maxAge <= userAge) : (ListenerUtil.mutListener.listen(5483) ? (curEntry.maxAge > userAge) : (ListenerUtil.mutListener.listen(5482) ? (curEntry.maxAge < userAge) : (ListenerUtil.mutListener.listen(5481) ? (curEntry.maxAge != userAge) : (ListenerUtil.mutListener.listen(5480) ? (curEntry.maxAge == userAge) : (curEntry.maxAge >= userAge))))))) : ((ListenerUtil.mutListener.listen(5479) ? (curEntry.lowAge >= userAge) : (ListenerUtil.mutListener.listen(5478) ? (curEntry.lowAge > userAge) : (ListenerUtil.mutListener.listen(5477) ? (curEntry.lowAge < userAge) : (ListenerUtil.mutListener.listen(5476) ? (curEntry.lowAge != userAge) : (ListenerUtil.mutListener.listen(5475) ? (curEntry.lowAge == userAge) : (curEntry.lowAge <= userAge)))))) && (ListenerUtil.mutListener.listen(5484) ? (curEntry.maxAge <= userAge) : (ListenerUtil.mutListener.listen(5483) ? (curEntry.maxAge > userAge) : (ListenerUtil.mutListener.listen(5482) ? (curEntry.maxAge < userAge) : (ListenerUtil.mutListener.listen(5481) ? (curEntry.maxAge != userAge) : (ListenerUtil.mutListener.listen(5480) ? (curEntry.maxAge == userAge) : (curEntry.maxAge >= userAge))))))))) {
                            if (!ListenerUtil.mutListener.listen(5507)) {
                                if ((ListenerUtil.mutListener.listen(5490) ? (value >= curEntry.lowLimit) : (ListenerUtil.mutListener.listen(5489) ? (value <= curEntry.lowLimit) : (ListenerUtil.mutListener.listen(5488) ? (value > curEntry.lowLimit) : (ListenerUtil.mutListener.listen(5487) ? (value != curEntry.lowLimit) : (ListenerUtil.mutListener.listen(5486) ? (value == curEntry.lowLimit) : (value < curEntry.lowLimit))))))) {
                                    // low
                                    return new EvaluationResult(value, curEntry.lowLimit, curEntry.highLimit, EvaluationResult.EVAL_STATE.LOW);
                                } else if ((ListenerUtil.mutListener.listen(5501) ? ((ListenerUtil.mutListener.listen(5495) ? (value <= curEntry.lowLimit) : (ListenerUtil.mutListener.listen(5494) ? (value > curEntry.lowLimit) : (ListenerUtil.mutListener.listen(5493) ? (value < curEntry.lowLimit) : (ListenerUtil.mutListener.listen(5492) ? (value != curEntry.lowLimit) : (ListenerUtil.mutListener.listen(5491) ? (value == curEntry.lowLimit) : (value >= curEntry.lowLimit)))))) || (ListenerUtil.mutListener.listen(5500) ? (value >= curEntry.highLimit) : (ListenerUtil.mutListener.listen(5499) ? (value > curEntry.highLimit) : (ListenerUtil.mutListener.listen(5498) ? (value < curEntry.highLimit) : (ListenerUtil.mutListener.listen(5497) ? (value != curEntry.highLimit) : (ListenerUtil.mutListener.listen(5496) ? (value == curEntry.highLimit) : (value <= curEntry.highLimit))))))) : ((ListenerUtil.mutListener.listen(5495) ? (value <= curEntry.lowLimit) : (ListenerUtil.mutListener.listen(5494) ? (value > curEntry.lowLimit) : (ListenerUtil.mutListener.listen(5493) ? (value < curEntry.lowLimit) : (ListenerUtil.mutListener.listen(5492) ? (value != curEntry.lowLimit) : (ListenerUtil.mutListener.listen(5491) ? (value == curEntry.lowLimit) : (value >= curEntry.lowLimit)))))) && (ListenerUtil.mutListener.listen(5500) ? (value >= curEntry.highLimit) : (ListenerUtil.mutListener.listen(5499) ? (value > curEntry.highLimit) : (ListenerUtil.mutListener.listen(5498) ? (value < curEntry.highLimit) : (ListenerUtil.mutListener.listen(5497) ? (value != curEntry.highLimit) : (ListenerUtil.mutListener.listen(5496) ? (value == curEntry.highLimit) : (value <= curEntry.highLimit))))))))) {
                                    // normal
                                    return new EvaluationResult(value, curEntry.lowLimit, curEntry.highLimit, EvaluationResult.EVAL_STATE.NORMAL);
                                } else if ((ListenerUtil.mutListener.listen(5506) ? (value >= curEntry.highLimit) : (ListenerUtil.mutListener.listen(5505) ? (value <= curEntry.highLimit) : (ListenerUtil.mutListener.listen(5504) ? (value < curEntry.highLimit) : (ListenerUtil.mutListener.listen(5503) ? (value != curEntry.highLimit) : (ListenerUtil.mutListener.listen(5502) ? (value == curEntry.highLimit) : (value > curEntry.highLimit))))))) {
                                    // high
                                    return new EvaluationResult(value, curEntry.lowLimit, curEntry.highLimit, EvaluationResult.EVAL_STATE.HIGH);
                                }
                            }
                        }
                    }
                }
            }
        }
        return new EvaluationResult(0, -1, -1, EvaluationResult.EVAL_STATE.UNDEFINED);
    }
}
