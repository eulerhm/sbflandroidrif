package org.owntracks.android.data;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import android.location.Location;
import androidx.annotation.NonNull;
import java.util.concurrent.TimeUnit;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Index;
import io.objectbox.annotation.Unique;
import org.owntracks.android.BR;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

@Entity
public class WaypointModel extends BaseObservable {

    @Id
    private long id;

    private String description = "";

    private double geofenceLatitude = 0.0;

    private double geofenceLongitude = 0.0;

    private int geofenceRadius = 0;

    private long lastTriggered = 0;

    private int lastTransition = 0;

    @Unique
    @Index
    private long tst = 0;

    public WaypointModel() {
        if (!ListenerUtil.mutListener.listen(38)) {
            setTst(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        }
    }

    public WaypointModel(long id, long tst, @NonNull String description, double geofenceLatitude, double geofenceLongitude, int geofenceRadius, int lastTransition, long lastTriggered) {
        if (!ListenerUtil.mutListener.listen(39)) {
            this.id = id;
        }
        if (!ListenerUtil.mutListener.listen(40)) {
            this.tst = tst;
        }
        if (!ListenerUtil.mutListener.listen(41)) {
            this.description = description;
        }
        if (!ListenerUtil.mutListener.listen(42)) {
            setGeofenceLongitude(geofenceLongitude);
        }
        if (!ListenerUtil.mutListener.listen(43)) {
            setGeofenceLatitude(geofenceLatitude);
        }
        if (!ListenerUtil.mutListener.listen(44)) {
            this.geofenceLongitude = geofenceLongitude;
        }
        if (!ListenerUtil.mutListener.listen(45)) {
            this.geofenceRadius = geofenceRadius;
        }
        if (!ListenerUtil.mutListener.listen(46)) {
            this.lastTransition = lastTransition;
        }
        if (!ListenerUtil.mutListener.listen(47)) {
            this.lastTriggered = lastTriggered;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if (!ListenerUtil.mutListener.listen(48)) {
            this.id = id;
        }
    }

    public long getTst() {
        // unit is seconds
        return tst;
    }

    private void setTst(long tst) {
        if (!ListenerUtil.mutListener.listen(49)) {
            this.tst = tst;
        }
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        if (!ListenerUtil.mutListener.listen(50)) {
            this.description = description;
        }
    }

    @Bindable
    public String getGeofenceLatitudeAsStr() {
        return String.valueOf(geofenceLatitude);
    }

    @Bindable
    public double getGeofenceLatitude() {
        return geofenceLatitude;
    }

    public void setGeofenceLatitudeAsStr(String geofenceLatitudeAsStr) {
        try {
            double geofenceLatitude = Double.parseDouble(geofenceLatitudeAsStr);
            if (!ListenerUtil.mutListener.listen(51)) {
                setGeofenceLatitude(geofenceLatitude);
            }
        } catch (NumberFormatException e) {
        }
    }

    public void setGeofenceLatitude(double geofenceLatitude) {
        if (!ListenerUtil.mutListener.listen(65)) {
            if ((ListenerUtil.mutListener.listen(56) ? (geofenceLatitude >= 90) : (ListenerUtil.mutListener.listen(55) ? (geofenceLatitude <= 90) : (ListenerUtil.mutListener.listen(54) ? (geofenceLatitude < 90) : (ListenerUtil.mutListener.listen(53) ? (geofenceLatitude != 90) : (ListenerUtil.mutListener.listen(52) ? (geofenceLatitude == 90) : (geofenceLatitude > 90))))))) {
                if (!ListenerUtil.mutListener.listen(64)) {
                    this.geofenceLatitude = 90;
                }
            } else if ((ListenerUtil.mutListener.listen(61) ? (geofenceLatitude >= -90) : (ListenerUtil.mutListener.listen(60) ? (geofenceLatitude <= -90) : (ListenerUtil.mutListener.listen(59) ? (geofenceLatitude > -90) : (ListenerUtil.mutListener.listen(58) ? (geofenceLatitude != -90) : (ListenerUtil.mutListener.listen(57) ? (geofenceLatitude == -90) : (geofenceLatitude < -90))))))) {
                if (!ListenerUtil.mutListener.listen(63)) {
                    this.geofenceLatitude = -90;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(62)) {
                    this.geofenceLatitude = geofenceLatitude;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(66)) {
            notifyPropertyChanged(BR.geofenceLatitude);
        }
    }

    @Bindable
    public String getGeofenceLongitudeAsStr() {
        return String.valueOf(geofenceLongitude);
    }

    public void setGeofenceLongitudeAsStr(String geofenceLongitudeAsStr) {
        try {
            double geofenceLatitude = Double.parseDouble(geofenceLongitudeAsStr);
            if (!ListenerUtil.mutListener.listen(67)) {
                setGeofenceLongitude(geofenceLatitude);
            }
        } catch (NumberFormatException e) {
        }
    }

    @Bindable
    public double getGeofenceLongitude() {
        return geofenceLongitude;
    }

    public void setGeofenceLongitude(double geofenceLongitude) {
        if (!ListenerUtil.mutListener.listen(81)) {
            if ((ListenerUtil.mutListener.listen(72) ? (geofenceLongitude >= 180) : (ListenerUtil.mutListener.listen(71) ? (geofenceLongitude <= 180) : (ListenerUtil.mutListener.listen(70) ? (geofenceLongitude < 180) : (ListenerUtil.mutListener.listen(69) ? (geofenceLongitude != 180) : (ListenerUtil.mutListener.listen(68) ? (geofenceLongitude == 180) : (geofenceLongitude > 180))))))) {
                if (!ListenerUtil.mutListener.listen(80)) {
                    this.geofenceLongitude = 180;
                }
            } else if ((ListenerUtil.mutListener.listen(77) ? (geofenceLongitude >= -180) : (ListenerUtil.mutListener.listen(76) ? (geofenceLongitude <= -180) : (ListenerUtil.mutListener.listen(75) ? (geofenceLongitude > -180) : (ListenerUtil.mutListener.listen(74) ? (geofenceLongitude != -180) : (ListenerUtil.mutListener.listen(73) ? (geofenceLongitude == -180) : (geofenceLongitude < -180))))))) {
                if (!ListenerUtil.mutListener.listen(79)) {
                    this.geofenceLongitude = -180;
                }
            } else {
                if (!ListenerUtil.mutListener.listen(78)) {
                    this.geofenceLongitude = geofenceLongitude;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(82)) {
            notifyPropertyChanged(BR.geofenceLongitude);
        }
    }

    public int getGeofenceRadius() {
        return geofenceRadius;
    }

    public void setGeofenceRadius(int geofenceRadius) {
        if (!ListenerUtil.mutListener.listen(83)) {
            this.geofenceRadius = geofenceRadius;
        }
    }

    @NonNull
    public Location getLocation() {
        Location l = new Location("waypoint");
        if (!ListenerUtil.mutListener.listen(84)) {
            l.setLatitude(getGeofenceLatitude());
        }
        if (!ListenerUtil.mutListener.listen(85)) {
            l.setLongitude(getGeofenceLongitude());
        }
        if (!ListenerUtil.mutListener.listen(86)) {
            l.setAccuracy(getGeofenceRadius());
        }
        return l;
    }

    public long getLastTriggered() {
        // unit is seconds
        return lastTriggered;
    }

    private void setLastTriggered(long lastTriggered) {
        if (!ListenerUtil.mutListener.listen(87)) {
            // unit is seconds
            this.lastTriggered = lastTriggered;
        }
    }

    public void setLastTriggeredNow() {
        if (!ListenerUtil.mutListener.listen(88)) {
            setLastTriggered(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        }
    }

    public int getLastTransition() {
        return this.lastTransition;
    }

    public void setLastTransition(int status) {
        if (!ListenerUtil.mutListener.listen(89)) {
            this.lastTransition = status;
        }
    }

    public boolean isUnknown() {
        return (ListenerUtil.mutListener.listen(94) ? (this.lastTransition >= 0) : (ListenerUtil.mutListener.listen(93) ? (this.lastTransition <= 0) : (ListenerUtil.mutListener.listen(92) ? (this.lastTransition > 0) : (ListenerUtil.mutListener.listen(91) ? (this.lastTransition < 0) : (ListenerUtil.mutListener.listen(90) ? (this.lastTransition != 0) : (this.lastTransition == 0))))));
    }

    public boolean hasGeofence() {
        return (ListenerUtil.mutListener.listen(99) ? (geofenceRadius >= 0) : (ListenerUtil.mutListener.listen(98) ? (geofenceRadius <= 0) : (ListenerUtil.mutListener.listen(97) ? (geofenceRadius < 0) : (ListenerUtil.mutListener.listen(96) ? (geofenceRadius != 0) : (ListenerUtil.mutListener.listen(95) ? (geofenceRadius == 0) : (geofenceRadius > 0))))));
    }

    @NonNull
    public String toString() {
        return "WaypointModel(" + getId() + "," + getTst() + "," + getDescription() + ")";
    }
}
