package fr.free.nrw.commons.kvstore;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.Nullable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import timber.log.Timber;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class BasicKvStore implements KeyValueStore {

    private static final String KEY_VERSION = "__version__";

    /*
    This class only performs puts, sets and clears.
    A commit returns a boolean indicating whether it has succeeded, we are not throwing an exception as it will
    require the dev to handle it in every usage - instead we will pass on this boolean so it can be evaluated if needed.
    */
    private final SharedPreferences _store;

    public BasicKvStore(Context context, String storeName) {
        _store = context.getSharedPreferences(storeName, Context.MODE_PRIVATE);
    }

    /**
     * If you don't want onVersionUpdate to be called on a fresh creation, the first version supplied for the kvstore should be set to 0.
     */
    public BasicKvStore(Context context, String storeName, int version) {
        this(context, storeName, version, false);
    }

    public BasicKvStore(Context context, String storeName, int version, boolean clearAllOnUpgrade) {
        _store = context.getSharedPreferences(storeName, Context.MODE_PRIVATE);
        int oldVersion = getInt(KEY_VERSION);
        if (!ListenerUtil.mutListener.listen(5617)) {
            if ((ListenerUtil.mutListener.listen(5614) ? (version >= oldVersion) : (ListenerUtil.mutListener.listen(5613) ? (version <= oldVersion) : (ListenerUtil.mutListener.listen(5612) ? (version < oldVersion) : (ListenerUtil.mutListener.listen(5611) ? (version != oldVersion) : (ListenerUtil.mutListener.listen(5610) ? (version == oldVersion) : (version > oldVersion))))))) {
                if (!ListenerUtil.mutListener.listen(5615)) {
                    Timber.i("version updated from %s to %s, with clearFlag %b", oldVersion, version, clearAllOnUpgrade);
                }
                if (!ListenerUtil.mutListener.listen(5616)) {
                    onVersionUpdate(oldVersion, version, clearAllOnUpgrade);
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5623)) {
            if ((ListenerUtil.mutListener.listen(5622) ? (version >= oldVersion) : (ListenerUtil.mutListener.listen(5621) ? (version <= oldVersion) : (ListenerUtil.mutListener.listen(5620) ? (version > oldVersion) : (ListenerUtil.mutListener.listen(5619) ? (version != oldVersion) : (ListenerUtil.mutListener.listen(5618) ? (version == oldVersion) : (version < oldVersion))))))) {
                throw new IllegalArgumentException("kvstore downgrade not allowed, old version:" + oldVersion + ", new version: " + version);
            }
        }
        if (!ListenerUtil.mutListener.listen(5624)) {
            // Keep this statement at the end so that clearing of store does not cause version also to get removed.
            putIntInternal(KEY_VERSION, version);
        }
    }

    public void onVersionUpdate(int oldVersion, int version, boolean clearAllFlag) {
        if (!ListenerUtil.mutListener.listen(5626)) {
            if (clearAllFlag) {
                if (!ListenerUtil.mutListener.listen(5625)) {
                    clearAll();
                }
            }
        }
    }

    public Set<String> getKeySet() {
        Map<String, ?> allContents = new HashMap<>(_store.getAll());
        if (!ListenerUtil.mutListener.listen(5627)) {
            allContents.remove(KEY_VERSION);
        }
        return allContents.keySet();
    }

    @Nullable
    public Map<String, ?> getAll() {
        Map<String, ?> allContents = _store.getAll();
        if (!ListenerUtil.mutListener.listen(5634)) {
            if ((ListenerUtil.mutListener.listen(5633) ? (allContents == null && (ListenerUtil.mutListener.listen(5632) ? (allContents.size() >= 0) : (ListenerUtil.mutListener.listen(5631) ? (allContents.size() <= 0) : (ListenerUtil.mutListener.listen(5630) ? (allContents.size() > 0) : (ListenerUtil.mutListener.listen(5629) ? (allContents.size() < 0) : (ListenerUtil.mutListener.listen(5628) ? (allContents.size() != 0) : (allContents.size() == 0))))))) : (allContents == null || (ListenerUtil.mutListener.listen(5632) ? (allContents.size() >= 0) : (ListenerUtil.mutListener.listen(5631) ? (allContents.size() <= 0) : (ListenerUtil.mutListener.listen(5630) ? (allContents.size() > 0) : (ListenerUtil.mutListener.listen(5629) ? (allContents.size() < 0) : (ListenerUtil.mutListener.listen(5628) ? (allContents.size() != 0) : (allContents.size() == 0))))))))) {
                return null;
            }
        }
        if (!ListenerUtil.mutListener.listen(5635)) {
            allContents.remove(KEY_VERSION);
        }
        return new HashMap<>(allContents);
    }

    @Override
    public String getString(String key) {
        return getString(key, null);
    }

    @Override
    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    @Override
    public long getLong(String key) {
        return getLong(key, 0);
    }

    @Override
    public int getInt(String key) {
        return getInt(key, 0);
    }

    @Override
    public String getString(String key, String defaultValue) {
        return _store.getString(key, defaultValue);
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        return _store.getBoolean(key, defaultValue);
    }

    @Override
    public long getLong(String key, long defaultValue) {
        return _store.getLong(key, defaultValue);
    }

    @Override
    public int getInt(String key, int defaultValue) {
        return _store.getInt(key, defaultValue);
    }

    public void putAllStrings(Map<String, String> keyValuePairs) {
        SharedPreferences.Editor editor = _store.edit();
        if (!ListenerUtil.mutListener.listen(5637)) {
            {
                long _loopCounter78 = 0;
                for (Map.Entry<String, String> keyValuePair : keyValuePairs.entrySet()) {
                    ListenerUtil.loopListener.listen("_loopCounter78", ++_loopCounter78);
                    if (!ListenerUtil.mutListener.listen(5636)) {
                        putString(editor, keyValuePair.getKey(), keyValuePair.getValue(), false);
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(5638)) {
            editor.apply();
        }
    }

    @Override
    public void putString(String key, String value) {
        SharedPreferences.Editor editor = _store.edit();
        if (!ListenerUtil.mutListener.listen(5639)) {
            putString(editor, key, value, true);
        }
    }

    private void putString(SharedPreferences.Editor editor, String key, String value, boolean commit) {
        if (!ListenerUtil.mutListener.listen(5640)) {
            assertKeyNotReserved(key);
        }
        if (!ListenerUtil.mutListener.listen(5641)) {
            editor.putString(key, value);
        }
        if (!ListenerUtil.mutListener.listen(5643)) {
            if (commit) {
                if (!ListenerUtil.mutListener.listen(5642)) {
                    editor.apply();
                }
            }
        }
    }

    @Override
    public void putBoolean(String key, boolean value) {
        if (!ListenerUtil.mutListener.listen(5644)) {
            assertKeyNotReserved(key);
        }
        SharedPreferences.Editor editor = _store.edit();
        if (!ListenerUtil.mutListener.listen(5645)) {
            editor.putBoolean(key, value);
        }
        if (!ListenerUtil.mutListener.listen(5646)) {
            editor.apply();
        }
    }

    @Override
    public void putLong(String key, long value) {
        if (!ListenerUtil.mutListener.listen(5647)) {
            assertKeyNotReserved(key);
        }
        SharedPreferences.Editor editor = _store.edit();
        if (!ListenerUtil.mutListener.listen(5648)) {
            editor.putLong(key, value);
        }
        if (!ListenerUtil.mutListener.listen(5649)) {
            editor.apply();
        }
    }

    @Override
    public void putInt(String key, int value) {
        if (!ListenerUtil.mutListener.listen(5650)) {
            assertKeyNotReserved(key);
        }
        if (!ListenerUtil.mutListener.listen(5651)) {
            putIntInternal(key, value);
        }
    }

    @Override
    public boolean contains(String key) {
        return _store.contains(key);
    }

    @Override
    public void remove(String key) {
        SharedPreferences.Editor editor = _store.edit();
        if (!ListenerUtil.mutListener.listen(5652)) {
            editor.remove(key);
        }
        if (!ListenerUtil.mutListener.listen(5653)) {
            editor.apply();
        }
    }

    @Override
    public void clearAll() {
        int version = getInt(KEY_VERSION);
        SharedPreferences.Editor editor = _store.edit();
        if (!ListenerUtil.mutListener.listen(5654)) {
            editor.clear();
        }
        if (!ListenerUtil.mutListener.listen(5655)) {
            editor.apply();
        }
        if (!ListenerUtil.mutListener.listen(5656)) {
            putIntInternal(KEY_VERSION, version);
        }
    }

    @Override
    public void clearAllWithVersion() {
        SharedPreferences.Editor editor = _store.edit();
        if (!ListenerUtil.mutListener.listen(5657)) {
            editor.clear();
        }
        if (!ListenerUtil.mutListener.listen(5658)) {
            editor.apply();
        }
    }

    private void putIntInternal(String key, int value) {
        SharedPreferences.Editor editor = _store.edit();
        if (!ListenerUtil.mutListener.listen(5659)) {
            editor.putInt(key, value);
        }
        if (!ListenerUtil.mutListener.listen(5660)) {
            editor.apply();
        }
    }

    private void assertKeyNotReserved(String key) {
        if (!ListenerUtil.mutListener.listen(5661)) {
            if (key.equals(KEY_VERSION)) {
                throw new IllegalArgumentException(key + "is a reserved key");
            }
        }
    }

    public void registerChangeListener(SharedPreferences.OnSharedPreferenceChangeListener l) {
        if (!ListenerUtil.mutListener.listen(5662)) {
            _store.registerOnSharedPreferenceChangeListener(l);
        }
    }

    public void unregisterChangeListener(SharedPreferences.OnSharedPreferenceChangeListener l) {
        if (!ListenerUtil.mutListener.listen(5663)) {
            _store.unregisterOnSharedPreferenceChangeListener(l);
        }
    }

    public Set<String> getStringSet(String key) {
        return _store.getStringSet(key, new HashSet<>());
    }

    public void putStringSet(String key, Set<String> value) {
        if (!ListenerUtil.mutListener.listen(5664)) {
            _store.edit().putStringSet(key, value).apply();
        }
    }
}
