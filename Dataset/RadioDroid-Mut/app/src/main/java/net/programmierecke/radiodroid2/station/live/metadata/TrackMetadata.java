package net.programmierecke.radiodroid2.station.live.metadata;

import java.util.ArrayList;
import java.util.List;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class TrackMetadata {

    public enum AlbumArtSize {

        SMALL, MEDIUM, LARGE, EXTRA_LARGE
    }

    public static class AlbumArt {

        public AlbumArtSize size;

        public String url;

        public AlbumArt(AlbumArtSize size, String url) {
            if (!ListenerUtil.mutListener.listen(2432)) {
                this.size = size;
            }
            if (!ListenerUtil.mutListener.listen(2433)) {
                this.url = url;
            }
        }
    }

    private String artist;

    private String album;

    private String track;

    private ArrayList<String> tags;

    private List<AlbumArt> albumArts;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        if (!ListenerUtil.mutListener.listen(2434)) {
            this.artist = artist;
        }
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        if (!ListenerUtil.mutListener.listen(2435)) {
            this.album = album;
        }
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        if (!ListenerUtil.mutListener.listen(2436)) {
            this.track = track;
        }
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        if (!ListenerUtil.mutListener.listen(2437)) {
            this.tags = tags;
        }
    }

    public List<AlbumArt> getAlbumArts() {
        return albumArts;
    }

    public void setAlbumArts(List<AlbumArt> albumArts) {
        if (!ListenerUtil.mutListener.listen(2438)) {
            this.albumArts = albumArts;
        }
    }
}
