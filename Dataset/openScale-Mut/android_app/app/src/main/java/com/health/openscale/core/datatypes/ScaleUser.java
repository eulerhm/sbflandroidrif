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
package com.health.openscale.core.datatypes;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.health.openscale.core.utils.Converters;
import com.health.openscale.core.utils.DateTimeHelpers;
import java.util.Calendar;
import java.util.Date;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@Entity(tableName = "scaleUsers")
public class ScaleUser {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "username")
    private String userName;

    @NonNull
    @ColumnInfo(name = "birthday")
    private Date birthday;

    @ColumnInfo(name = "bodyHeight")
    private float bodyHeight;

    @ColumnInfo(name = "scaleUnit")
    @NonNull
    private Converters.WeightUnit scaleUnit;

    @ColumnInfo(name = "gender")
    @NonNull
    private Converters.Gender gender;

    @ColumnInfo(name = "initialWeight")
    private float initialWeight;

    @ColumnInfo(name = "goalWeight")
    private float goalWeight;

    @ColumnInfo(name = "goalDate")
    private Date goalDate;

    @NonNull
    @ColumnInfo(name = "measureUnit")
    private Converters.MeasureUnit measureUnit;

    @NonNull
    @ColumnInfo(name = "activityLevel")
    private Converters.ActivityLevel activityLevel;

    @ColumnInfo(name = "assistedWeighing")
    private boolean assistedWeighing;

    @NonNull
    @ColumnInfo(name = "leftAmputationLevel")
    private Converters.AmputationLevel leftAmputationLevel;

    @NonNull
    @ColumnInfo(name = "rightAmputationLevel")
    private Converters.AmputationLevel rightAmputationLevel;

    public ScaleUser() {
        if (!ListenerUtil.mutListener.listen(5298)) {
            userName = "";
        }
        if (!ListenerUtil.mutListener.listen(5299)) {
            birthday = new Date();
        }
        if (!ListenerUtil.mutListener.listen(5300)) {
            bodyHeight = -1;
        }
        if (!ListenerUtil.mutListener.listen(5301)) {
            scaleUnit = Converters.WeightUnit.KG;
        }
        if (!ListenerUtil.mutListener.listen(5302)) {
            gender = Converters.Gender.MALE;
        }
        if (!ListenerUtil.mutListener.listen(5303)) {
            initialWeight = -1;
        }
        if (!ListenerUtil.mutListener.listen(5304)) {
            goalWeight = -1;
        }
        if (!ListenerUtil.mutListener.listen(5305)) {
            goalDate = new Date();
        }
        if (!ListenerUtil.mutListener.listen(5306)) {
            measureUnit = Converters.MeasureUnit.CM;
        }
        if (!ListenerUtil.mutListener.listen(5307)) {
            activityLevel = Converters.ActivityLevel.SEDENTARY;
        }
        if (!ListenerUtil.mutListener.listen(5308)) {
            assistedWeighing = false;
        }
        if (!ListenerUtil.mutListener.listen(5309)) {
            leftAmputationLevel = Converters.AmputationLevel.NONE;
        }
        if (!ListenerUtil.mutListener.listen(5310)) {
            rightAmputationLevel = Converters.AmputationLevel.NONE;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (!ListenerUtil.mutListener.listen(5311)) {
            this.id = id;
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (!ListenerUtil.mutListener.listen(5312)) {
            this.userName = userName;
        }
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        if (!ListenerUtil.mutListener.listen(5313)) {
            this.birthday = birthday;
        }
    }

    public float getBodyHeight() {
        return bodyHeight;
    }

    public void setBodyHeight(float bodyHeight) {
        if (!ListenerUtil.mutListener.listen(5314)) {
            this.bodyHeight = bodyHeight;
        }
    }

    public Converters.WeightUnit getScaleUnit() {
        return scaleUnit;
    }

    public void setScaleUnit(Converters.WeightUnit scaleUnit) {
        if (!ListenerUtil.mutListener.listen(5315)) {
            this.scaleUnit = scaleUnit;
        }
    }

    public Converters.Gender getGender() {
        return gender;
    }

    public void setGender(Converters.Gender gender) {
        if (!ListenerUtil.mutListener.listen(5316)) {
            this.gender = gender;
        }
    }

    public float getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(float goalWeight) {
        if (!ListenerUtil.mutListener.listen(5317)) {
            this.goalWeight = goalWeight;
        }
    }

    public Date getGoalDate() {
        return goalDate;
    }

    public void setGoalDate(Date goalDate) {
        if (!ListenerUtil.mutListener.listen(5318)) {
            this.goalDate = goalDate;
        }
    }

    public int getAge(Date todayDate) {
        Calendar calToday = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(5320)) {
            if (todayDate != null) {
                if (!ListenerUtil.mutListener.listen(5319)) {
                    calToday.setTime(todayDate);
                }
            }
        }
        Calendar calBirthday = Calendar.getInstance();
        if (!ListenerUtil.mutListener.listen(5321)) {
            calBirthday.setTime(birthday);
        }
        return DateTimeHelpers.yearsBetween(calBirthday, calToday);
    }

    public int getAge() {
        return getAge(null);
    }

    public void setInitialWeight(float weight) {
        if (!ListenerUtil.mutListener.listen(5322)) {
            this.initialWeight = weight;
        }
    }

    public float getInitialWeight() {
        return initialWeight;
    }

    public void setMeasureUnit(Converters.MeasureUnit unit) {
        if (!ListenerUtil.mutListener.listen(5323)) {
            measureUnit = unit;
        }
    }

    public Converters.MeasureUnit getMeasureUnit() {
        return measureUnit;
    }

    public void setActivityLevel(Converters.ActivityLevel level) {
        if (!ListenerUtil.mutListener.listen(5324)) {
            activityLevel = level;
        }
    }

    public Converters.ActivityLevel getActivityLevel() {
        return activityLevel;
    }

    public boolean isAssistedWeighing() {
        return assistedWeighing;
    }

    public void setAssistedWeighing(boolean assistedWeighing) {
        if (!ListenerUtil.mutListener.listen(5325)) {
            this.assistedWeighing = assistedWeighing;
        }
    }

    @NonNull
    public Converters.AmputationLevel getLeftAmputationLevel() {
        return leftAmputationLevel;
    }

    public void setLeftAmputationLevel(@NonNull Converters.AmputationLevel leftAmputationLevel) {
        if (!ListenerUtil.mutListener.listen(5326)) {
            this.leftAmputationLevel = leftAmputationLevel;
        }
    }

    @NonNull
    public Converters.AmputationLevel getRightAmputationLevel() {
        return rightAmputationLevel;
    }

    public void setRightAmputationLevel(@NonNull Converters.AmputationLevel rightAmputationLevel) {
        if (!ListenerUtil.mutListener.listen(5327)) {
            this.rightAmputationLevel = rightAmputationLevel;
        }
    }

    public float getAmputationCorrectionFactor() {
        float correctionFactor = 100.0f;
        if (!ListenerUtil.mutListener.listen(5334)) {
            switch(rightAmputationLevel) {
                case NONE:
                    break;
                case HAND:
                    if (!ListenerUtil.mutListener.listen(5328)) {
                        correctionFactor -= 0.8f;
                    }
                    break;
                case FOREARM_HAND:
                    if (!ListenerUtil.mutListener.listen(5329)) {
                        correctionFactor -= 3.0f;
                    }
                    break;
                case ARM:
                    if (!ListenerUtil.mutListener.listen(5330)) {
                        correctionFactor -= 11.5f;
                    }
                    break;
                case FOOT:
                    if (!ListenerUtil.mutListener.listen(5331)) {
                        correctionFactor -= 1.8f;
                    }
                    break;
                case LOWER_LEG_FOOT:
                    if (!ListenerUtil.mutListener.listen(5332)) {
                        correctionFactor -= 7.1f;
                    }
                    break;
                case LEG:
                    if (!ListenerUtil.mutListener.listen(5333)) {
                        correctionFactor -= 18.7f;
                    }
                    break;
            }
        }
        if (!ListenerUtil.mutListener.listen(5341)) {
            switch(leftAmputationLevel) {
                case NONE:
                    break;
                case HAND:
                    if (!ListenerUtil.mutListener.listen(5335)) {
                        correctionFactor -= 0.8f;
                    }
                    break;
                case FOREARM_HAND:
                    if (!ListenerUtil.mutListener.listen(5336)) {
                        correctionFactor -= 3.0f;
                    }
                    break;
                case ARM:
                    if (!ListenerUtil.mutListener.listen(5337)) {
                        correctionFactor -= 11.5f;
                    }
                    break;
                case FOOT:
                    if (!ListenerUtil.mutListener.listen(5338)) {
                        correctionFactor -= 1.8f;
                    }
                    break;
                case LOWER_LEG_FOOT:
                    if (!ListenerUtil.mutListener.listen(5339)) {
                        correctionFactor -= 7.1f;
                    }
                    break;
                case LEG:
                    if (!ListenerUtil.mutListener.listen(5340)) {
                        correctionFactor -= 18.7f;
                    }
                    break;
            }
        }
        return correctionFactor;
    }

    public static String getPreferenceKey(int userId, String key) {
        return String.format("user.%d.%s", userId, key);
    }

    public String getPreferenceKey(String key) {
        return getPreferenceKey(getId(), key);
    }

    @Override
    public String toString() {
        return String.format("id(%d) name(%s) birthday(%s) age(%d) body height(%.2f) scale unit(%s) " + "gender(%s) initial weight(%.2f) goal weight(%.2f) goal date(%s) " + "measure unt(%s) activity level(%d) assisted weighing(%b)", id, userName, birthday.toString(), getAge(), bodyHeight, scaleUnit.toString(), gender.toString().toLowerCase(), initialWeight, goalWeight, goalDate.toString(), measureUnit.toString(), activityLevel.toInt(), assistedWeighing);
    }
}
