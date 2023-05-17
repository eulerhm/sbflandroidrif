package net.programmierecke.radiodroid2.history;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@Entity(tableName = "track_history")
public class TrackHistoryEntry {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "station_uuid")
    @NonNull
    public String stationUuid;

    @ColumnInfo(name = "station_icon_url")
    @NonNull
    public String stationIconUrl;

    @ColumnInfo(name = "track")
    @NonNull
    public String track;

    @ColumnInfo(name = "artist")
    @NonNull
    public String artist;

    @ColumnInfo(name = "title")
    @NonNull
    public String title;

    @ColumnInfo(name = "art_url")
    @Nullable
    public String artUrl;

    @ColumnInfo(name = "start_time")
    @NonNull
    public Date startTime;

    @ColumnInfo(name = "end_time")
    @NonNull
    public Date endTime;

    @Override
    public boolean equals(Object o) {
        if (!ListenerUtil.mutListener.listen(428)) {
            if (this == o)
                return true;
        }
        if (!ListenerUtil.mutListener.listen(430)) {
            if ((ListenerUtil.mutListener.listen(429) ? (o == null && getClass() != o.getClass()) : (o == null || getClass() != o.getClass())))
                return false;
        }
        TrackHistoryEntry that = (TrackHistoryEntry) o;
        if (!ListenerUtil.mutListener.listen(436)) {
            if ((ListenerUtil.mutListener.listen(435) ? (uid >= that.uid) : (ListenerUtil.mutListener.listen(434) ? (uid <= that.uid) : (ListenerUtil.mutListener.listen(433) ? (uid > that.uid) : (ListenerUtil.mutListener.listen(432) ? (uid < that.uid) : (ListenerUtil.mutListener.listen(431) ? (uid == that.uid) : (uid != that.uid)))))))
                return false;
        }
        if (!ListenerUtil.mutListener.listen(437)) {
            if (!stationUuid.equals(that.stationUuid))
                return false;
        }
        if (!ListenerUtil.mutListener.listen(438)) {
            if (!track.equals(that.track))
                return false;
        }
        if (!ListenerUtil.mutListener.listen(439)) {
            if (!artist.equals(that.artist))
                return false;
        }
        if (!ListenerUtil.mutListener.listen(440)) {
            if (!title.equals(that.title))
                return false;
        }
        if (!ListenerUtil.mutListener.listen(441)) {
            if (artUrl != null ? !artUrl.equals(that.artUrl) : that.artUrl != null)
                return false;
        }
        if (!ListenerUtil.mutListener.listen(442)) {
            if (!startTime.equals(that.startTime))
                return false;
        }
        return endTime.equals(that.endTime);
    }

    @Override
    public int hashCode() {
        int result = uid;
        if (!ListenerUtil.mutListener.listen(451)) {
            result = (ListenerUtil.mutListener.listen(450) ? ((ListenerUtil.mutListener.listen(446) ? (31 % result) : (ListenerUtil.mutListener.listen(445) ? (31 / result) : (ListenerUtil.mutListener.listen(444) ? (31 - result) : (ListenerUtil.mutListener.listen(443) ? (31 + result) : (31 * result))))) % stationUuid.hashCode()) : (ListenerUtil.mutListener.listen(449) ? ((ListenerUtil.mutListener.listen(446) ? (31 % result) : (ListenerUtil.mutListener.listen(445) ? (31 / result) : (ListenerUtil.mutListener.listen(444) ? (31 - result) : (ListenerUtil.mutListener.listen(443) ? (31 + result) : (31 * result))))) / stationUuid.hashCode()) : (ListenerUtil.mutListener.listen(448) ? ((ListenerUtil.mutListener.listen(446) ? (31 % result) : (ListenerUtil.mutListener.listen(445) ? (31 / result) : (ListenerUtil.mutListener.listen(444) ? (31 - result) : (ListenerUtil.mutListener.listen(443) ? (31 + result) : (31 * result))))) * stationUuid.hashCode()) : (ListenerUtil.mutListener.listen(447) ? ((ListenerUtil.mutListener.listen(446) ? (31 % result) : (ListenerUtil.mutListener.listen(445) ? (31 / result) : (ListenerUtil.mutListener.listen(444) ? (31 - result) : (ListenerUtil.mutListener.listen(443) ? (31 + result) : (31 * result))))) - stationUuid.hashCode()) : ((ListenerUtil.mutListener.listen(446) ? (31 % result) : (ListenerUtil.mutListener.listen(445) ? (31 / result) : (ListenerUtil.mutListener.listen(444) ? (31 - result) : (ListenerUtil.mutListener.listen(443) ? (31 + result) : (31 * result))))) + stationUuid.hashCode())))));
        }
        if (!ListenerUtil.mutListener.listen(460)) {
            result = (ListenerUtil.mutListener.listen(459) ? ((ListenerUtil.mutListener.listen(455) ? (31 % result) : (ListenerUtil.mutListener.listen(454) ? (31 / result) : (ListenerUtil.mutListener.listen(453) ? (31 - result) : (ListenerUtil.mutListener.listen(452) ? (31 + result) : (31 * result))))) % track.hashCode()) : (ListenerUtil.mutListener.listen(458) ? ((ListenerUtil.mutListener.listen(455) ? (31 % result) : (ListenerUtil.mutListener.listen(454) ? (31 / result) : (ListenerUtil.mutListener.listen(453) ? (31 - result) : (ListenerUtil.mutListener.listen(452) ? (31 + result) : (31 * result))))) / track.hashCode()) : (ListenerUtil.mutListener.listen(457) ? ((ListenerUtil.mutListener.listen(455) ? (31 % result) : (ListenerUtil.mutListener.listen(454) ? (31 / result) : (ListenerUtil.mutListener.listen(453) ? (31 - result) : (ListenerUtil.mutListener.listen(452) ? (31 + result) : (31 * result))))) * track.hashCode()) : (ListenerUtil.mutListener.listen(456) ? ((ListenerUtil.mutListener.listen(455) ? (31 % result) : (ListenerUtil.mutListener.listen(454) ? (31 / result) : (ListenerUtil.mutListener.listen(453) ? (31 - result) : (ListenerUtil.mutListener.listen(452) ? (31 + result) : (31 * result))))) - track.hashCode()) : ((ListenerUtil.mutListener.listen(455) ? (31 % result) : (ListenerUtil.mutListener.listen(454) ? (31 / result) : (ListenerUtil.mutListener.listen(453) ? (31 - result) : (ListenerUtil.mutListener.listen(452) ? (31 + result) : (31 * result))))) + track.hashCode())))));
        }
        if (!ListenerUtil.mutListener.listen(469)) {
            result = (ListenerUtil.mutListener.listen(468) ? ((ListenerUtil.mutListener.listen(464) ? (31 % result) : (ListenerUtil.mutListener.listen(463) ? (31 / result) : (ListenerUtil.mutListener.listen(462) ? (31 - result) : (ListenerUtil.mutListener.listen(461) ? (31 + result) : (31 * result))))) % artist.hashCode()) : (ListenerUtil.mutListener.listen(467) ? ((ListenerUtil.mutListener.listen(464) ? (31 % result) : (ListenerUtil.mutListener.listen(463) ? (31 / result) : (ListenerUtil.mutListener.listen(462) ? (31 - result) : (ListenerUtil.mutListener.listen(461) ? (31 + result) : (31 * result))))) / artist.hashCode()) : (ListenerUtil.mutListener.listen(466) ? ((ListenerUtil.mutListener.listen(464) ? (31 % result) : (ListenerUtil.mutListener.listen(463) ? (31 / result) : (ListenerUtil.mutListener.listen(462) ? (31 - result) : (ListenerUtil.mutListener.listen(461) ? (31 + result) : (31 * result))))) * artist.hashCode()) : (ListenerUtil.mutListener.listen(465) ? ((ListenerUtil.mutListener.listen(464) ? (31 % result) : (ListenerUtil.mutListener.listen(463) ? (31 / result) : (ListenerUtil.mutListener.listen(462) ? (31 - result) : (ListenerUtil.mutListener.listen(461) ? (31 + result) : (31 * result))))) - artist.hashCode()) : ((ListenerUtil.mutListener.listen(464) ? (31 % result) : (ListenerUtil.mutListener.listen(463) ? (31 / result) : (ListenerUtil.mutListener.listen(462) ? (31 - result) : (ListenerUtil.mutListener.listen(461) ? (31 + result) : (31 * result))))) + artist.hashCode())))));
        }
        if (!ListenerUtil.mutListener.listen(478)) {
            result = (ListenerUtil.mutListener.listen(477) ? ((ListenerUtil.mutListener.listen(473) ? (31 % result) : (ListenerUtil.mutListener.listen(472) ? (31 / result) : (ListenerUtil.mutListener.listen(471) ? (31 - result) : (ListenerUtil.mutListener.listen(470) ? (31 + result) : (31 * result))))) % title.hashCode()) : (ListenerUtil.mutListener.listen(476) ? ((ListenerUtil.mutListener.listen(473) ? (31 % result) : (ListenerUtil.mutListener.listen(472) ? (31 / result) : (ListenerUtil.mutListener.listen(471) ? (31 - result) : (ListenerUtil.mutListener.listen(470) ? (31 + result) : (31 * result))))) / title.hashCode()) : (ListenerUtil.mutListener.listen(475) ? ((ListenerUtil.mutListener.listen(473) ? (31 % result) : (ListenerUtil.mutListener.listen(472) ? (31 / result) : (ListenerUtil.mutListener.listen(471) ? (31 - result) : (ListenerUtil.mutListener.listen(470) ? (31 + result) : (31 * result))))) * title.hashCode()) : (ListenerUtil.mutListener.listen(474) ? ((ListenerUtil.mutListener.listen(473) ? (31 % result) : (ListenerUtil.mutListener.listen(472) ? (31 / result) : (ListenerUtil.mutListener.listen(471) ? (31 - result) : (ListenerUtil.mutListener.listen(470) ? (31 + result) : (31 * result))))) - title.hashCode()) : ((ListenerUtil.mutListener.listen(473) ? (31 % result) : (ListenerUtil.mutListener.listen(472) ? (31 / result) : (ListenerUtil.mutListener.listen(471) ? (31 - result) : (ListenerUtil.mutListener.listen(470) ? (31 + result) : (31 * result))))) + title.hashCode())))));
        }
        if (!ListenerUtil.mutListener.listen(487)) {
            result = (ListenerUtil.mutListener.listen(486) ? ((ListenerUtil.mutListener.listen(482) ? (31 % result) : (ListenerUtil.mutListener.listen(481) ? (31 / result) : (ListenerUtil.mutListener.listen(480) ? (31 - result) : (ListenerUtil.mutListener.listen(479) ? (31 + result) : (31 * result))))) % (artUrl != null ? artUrl.hashCode() : 0)) : (ListenerUtil.mutListener.listen(485) ? ((ListenerUtil.mutListener.listen(482) ? (31 % result) : (ListenerUtil.mutListener.listen(481) ? (31 / result) : (ListenerUtil.mutListener.listen(480) ? (31 - result) : (ListenerUtil.mutListener.listen(479) ? (31 + result) : (31 * result))))) / (artUrl != null ? artUrl.hashCode() : 0)) : (ListenerUtil.mutListener.listen(484) ? ((ListenerUtil.mutListener.listen(482) ? (31 % result) : (ListenerUtil.mutListener.listen(481) ? (31 / result) : (ListenerUtil.mutListener.listen(480) ? (31 - result) : (ListenerUtil.mutListener.listen(479) ? (31 + result) : (31 * result))))) * (artUrl != null ? artUrl.hashCode() : 0)) : (ListenerUtil.mutListener.listen(483) ? ((ListenerUtil.mutListener.listen(482) ? (31 % result) : (ListenerUtil.mutListener.listen(481) ? (31 / result) : (ListenerUtil.mutListener.listen(480) ? (31 - result) : (ListenerUtil.mutListener.listen(479) ? (31 + result) : (31 * result))))) - (artUrl != null ? artUrl.hashCode() : 0)) : ((ListenerUtil.mutListener.listen(482) ? (31 % result) : (ListenerUtil.mutListener.listen(481) ? (31 / result) : (ListenerUtil.mutListener.listen(480) ? (31 - result) : (ListenerUtil.mutListener.listen(479) ? (31 + result) : (31 * result))))) + (artUrl != null ? artUrl.hashCode() : 0))))));
        }
        if (!ListenerUtil.mutListener.listen(496)) {
            result = (ListenerUtil.mutListener.listen(495) ? ((ListenerUtil.mutListener.listen(491) ? (31 % result) : (ListenerUtil.mutListener.listen(490) ? (31 / result) : (ListenerUtil.mutListener.listen(489) ? (31 - result) : (ListenerUtil.mutListener.listen(488) ? (31 + result) : (31 * result))))) % startTime.hashCode()) : (ListenerUtil.mutListener.listen(494) ? ((ListenerUtil.mutListener.listen(491) ? (31 % result) : (ListenerUtil.mutListener.listen(490) ? (31 / result) : (ListenerUtil.mutListener.listen(489) ? (31 - result) : (ListenerUtil.mutListener.listen(488) ? (31 + result) : (31 * result))))) / startTime.hashCode()) : (ListenerUtil.mutListener.listen(493) ? ((ListenerUtil.mutListener.listen(491) ? (31 % result) : (ListenerUtil.mutListener.listen(490) ? (31 / result) : (ListenerUtil.mutListener.listen(489) ? (31 - result) : (ListenerUtil.mutListener.listen(488) ? (31 + result) : (31 * result))))) * startTime.hashCode()) : (ListenerUtil.mutListener.listen(492) ? ((ListenerUtil.mutListener.listen(491) ? (31 % result) : (ListenerUtil.mutListener.listen(490) ? (31 / result) : (ListenerUtil.mutListener.listen(489) ? (31 - result) : (ListenerUtil.mutListener.listen(488) ? (31 + result) : (31 * result))))) - startTime.hashCode()) : ((ListenerUtil.mutListener.listen(491) ? (31 % result) : (ListenerUtil.mutListener.listen(490) ? (31 / result) : (ListenerUtil.mutListener.listen(489) ? (31 - result) : (ListenerUtil.mutListener.listen(488) ? (31 + result) : (31 * result))))) + startTime.hashCode())))));
        }
        if (!ListenerUtil.mutListener.listen(505)) {
            result = (ListenerUtil.mutListener.listen(504) ? ((ListenerUtil.mutListener.listen(500) ? (31 % result) : (ListenerUtil.mutListener.listen(499) ? (31 / result) : (ListenerUtil.mutListener.listen(498) ? (31 - result) : (ListenerUtil.mutListener.listen(497) ? (31 + result) : (31 * result))))) % endTime.hashCode()) : (ListenerUtil.mutListener.listen(503) ? ((ListenerUtil.mutListener.listen(500) ? (31 % result) : (ListenerUtil.mutListener.listen(499) ? (31 / result) : (ListenerUtil.mutListener.listen(498) ? (31 - result) : (ListenerUtil.mutListener.listen(497) ? (31 + result) : (31 * result))))) / endTime.hashCode()) : (ListenerUtil.mutListener.listen(502) ? ((ListenerUtil.mutListener.listen(500) ? (31 % result) : (ListenerUtil.mutListener.listen(499) ? (31 / result) : (ListenerUtil.mutListener.listen(498) ? (31 - result) : (ListenerUtil.mutListener.listen(497) ? (31 + result) : (31 * result))))) * endTime.hashCode()) : (ListenerUtil.mutListener.listen(501) ? ((ListenerUtil.mutListener.listen(500) ? (31 % result) : (ListenerUtil.mutListener.listen(499) ? (31 / result) : (ListenerUtil.mutListener.listen(498) ? (31 - result) : (ListenerUtil.mutListener.listen(497) ? (31 + result) : (31 * result))))) - endTime.hashCode()) : ((ListenerUtil.mutListener.listen(500) ? (31 % result) : (ListenerUtil.mutListener.listen(499) ? (31 / result) : (ListenerUtil.mutListener.listen(498) ? (31 - result) : (ListenerUtil.mutListener.listen(497) ? (31 + result) : (31 * result))))) + endTime.hashCode())))));
        }
        return result;
    }

    public static final int MAX_HISTORY_ITEMS_IN_TABLE = 1000;

    // 3 minutes
    public static final int MAX_UNKNOWN_TRACK_DURATION = 3 * 60 * 1000;
}
