/*  _____ _
 * |_   _| |_  _ _ ___ ___ _ __  __ _
 *   | | | ' \| '_/ -_) -_) '  \/ _` |_
 *   |_| |_||_|_| \___\___|_|_|_\__,_(_)
 *
 * Threema for Android
 * Copyright (c) 2017-2021 Threema GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package ch.threema.app.voip;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.SystemClock;
import android.util.Log;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

/**
 * Simple CPU monitor.  The caller creates a CpuMonitor object which can then
 * be used via sampleCpuUtilization() to collect the percentual use of the
 * cumulative CPU capacity for all CPUs running at their nominal frequency.  3
 * values are generated: (1) getCpuCurrent() returns the use since the last
 * sampleCpuUtilization(), (2) getCpuAvg3() returns the use since 3 prior
 * calls, and (3) getCpuAvgAll() returns the use over all SAMPLE_SAVE_NUMBER
 * calls.
 *
 * <p>CPUs in Android are often "offline", and while this of course means 0 Hz
 * as current frequency, in this state we cannot even get their nominal
 * frequency.  We therefore tread carefully, and allow any CPU to be missing.
 * Missing CPUs are assumed to have the same nominal frequency as any close
 * lower-numbered CPU, but as soon as it is online, we'll get their proper
 * frequency and remember it.  (Since CPU 0 in practice always seem to be
 * online, this unidirectional frequency inheritance should be no problem in
 * practice.)
 *
 * <p>Caveats:
 * o No provision made for zany "turbo" mode, common in the x86 world.
 * o No provision made for ARM big.LITTLE; if CPU n can switch behind our
 * back, we might get incorrect estimates.
 * o This is not thread-safe.  To call asynchronously, create different
 * CpuMonitor objects.
 *
 * <p>If we can gather enough info to generate a sensible result,
 * sampleCpuUtilization returns true.  It is designed to never throw an
 * exception.
 *
 * <p>sampleCpuUtilization should not be called too often in its present form,
 * since then deltas would be small and the percent values would fluctuate and
 * be unreadable. If it is desirable to call it more often than say once per
 * second, one would need to increase SAMPLE_SAVE_NUMBER and probably use
 * Queue<Integer> to avoid copying overhead.
 *
 * <p>Known problems:
 * 1. Nexus 7 devices running Kitkat have a kernel which often output an
 * incorrect 'idle' field in /proc/stat.  The value is close to twice the
 * correct value, and then returns to back to correct reading.  Both when
 * jumping up and back down we might create faulty CPU load readings.
 */
public class CpuMonitor {

    private static final String TAG = "CpuMonitor";

    private static final int MOVING_AVERAGE_SAMPLES = 5;

    private static final int CPU_STAT_SAMPLE_PERIOD_MS = 2000;

    private static final int CPU_STAT_LOG_PERIOD_MS = 6000;

    private final Context appContext;

    // User CPU usage at current frequency.
    private final MovingAverage userCpuUsage;

    // System CPU usage at current frequency.
    private final MovingAverage systemCpuUsage;

    // Total CPU usage relative to maximum frequency.
    private final MovingAverage totalCpuUsage;

    // CPU frequency in percentage from maximum.
    private final MovingAverage frequencyScale;

    private ScheduledExecutorService executor;

    private long lastStatLogTimeMs;

    private long[] cpuFreqMax;

    private int cpusPresent;

    private int actualCpusPresent;

    private boolean initialized;

    private boolean cpuOveruse;

    private String[] maxPath;

    private String[] curPath;

    private double[] curFreqScales;

    private ProcStat lastProcStat;

    private static class ProcStat {

        final long userTime;

        final long systemTime;

        final long idleTime;

        ProcStat(long userTime, long systemTime, long idleTime) {
            this.userTime = userTime;
            this.systemTime = systemTime;
            this.idleTime = idleTime;
        }
    }

    private static class MovingAverage {

        private final int size;

        private double sum;

        private double currentValue;

        private double[] circBuffer;

        private int circBufferIndex;

        public MovingAverage(int size) {
            if (!ListenerUtil.mutListener.listen(60838)) {
                if ((ListenerUtil.mutListener.listen(60837) ? (size >= 0) : (ListenerUtil.mutListener.listen(60836) ? (size > 0) : (ListenerUtil.mutListener.listen(60835) ? (size < 0) : (ListenerUtil.mutListener.listen(60834) ? (size != 0) : (ListenerUtil.mutListener.listen(60833) ? (size == 0) : (size <= 0))))))) {
                    throw new AssertionError("Size value in MovingAverage ctor should be positive.");
                }
            }
            this.size = size;
            if (!ListenerUtil.mutListener.listen(60839)) {
                circBuffer = new double[size];
            }
        }

        public void reset() {
            if (!ListenerUtil.mutListener.listen(60840)) {
                Arrays.fill(circBuffer, 0);
            }
            if (!ListenerUtil.mutListener.listen(60841)) {
                circBufferIndex = 0;
            }
            if (!ListenerUtil.mutListener.listen(60842)) {
                sum = 0;
            }
            if (!ListenerUtil.mutListener.listen(60843)) {
                currentValue = 0;
            }
        }

        public void addValue(double value) {
            if (!ListenerUtil.mutListener.listen(60844)) {
                sum -= circBuffer[circBufferIndex];
            }
            if (!ListenerUtil.mutListener.listen(60845)) {
                circBuffer[circBufferIndex++] = value;
            }
            if (!ListenerUtil.mutListener.listen(60846)) {
                currentValue = value;
            }
            if (!ListenerUtil.mutListener.listen(60847)) {
                sum += value;
            }
            if (!ListenerUtil.mutListener.listen(60854)) {
                if ((ListenerUtil.mutListener.listen(60852) ? (circBufferIndex <= size) : (ListenerUtil.mutListener.listen(60851) ? (circBufferIndex > size) : (ListenerUtil.mutListener.listen(60850) ? (circBufferIndex < size) : (ListenerUtil.mutListener.listen(60849) ? (circBufferIndex != size) : (ListenerUtil.mutListener.listen(60848) ? (circBufferIndex == size) : (circBufferIndex >= size))))))) {
                    if (!ListenerUtil.mutListener.listen(60853)) {
                        circBufferIndex = 0;
                    }
                }
            }
        }

        public double getCurrent() {
            return currentValue;
        }

        public double getAverage() {
            return (ListenerUtil.mutListener.listen(60858) ? (sum % (double) size) : (ListenerUtil.mutListener.listen(60857) ? (sum * (double) size) : (ListenerUtil.mutListener.listen(60856) ? (sum - (double) size) : (ListenerUtil.mutListener.listen(60855) ? (sum + (double) size) : (sum / (double) size)))));
        }
    }

    public CpuMonitor(Context context) {
        if (!ListenerUtil.mutListener.listen(60859)) {
            Log.d(TAG, "CpuMonitor ctor.");
        }
        appContext = context.getApplicationContext();
        userCpuUsage = new MovingAverage(MOVING_AVERAGE_SAMPLES);
        systemCpuUsage = new MovingAverage(MOVING_AVERAGE_SAMPLES);
        totalCpuUsage = new MovingAverage(MOVING_AVERAGE_SAMPLES);
        frequencyScale = new MovingAverage(MOVING_AVERAGE_SAMPLES);
        if (!ListenerUtil.mutListener.listen(60860)) {
            lastStatLogTimeMs = SystemClock.elapsedRealtime();
        }
        if (!ListenerUtil.mutListener.listen(60861)) {
            scheduleCpuUtilizationTask();
        }
    }

    public void pause() {
        if (!ListenerUtil.mutListener.listen(60865)) {
            if (executor != null) {
                if (!ListenerUtil.mutListener.listen(60862)) {
                    Log.d(TAG, "pause");
                }
                if (!ListenerUtil.mutListener.listen(60863)) {
                    executor.shutdownNow();
                }
                if (!ListenerUtil.mutListener.listen(60864)) {
                    executor = null;
                }
            }
        }
    }

    public void resume() {
        if (!ListenerUtil.mutListener.listen(60866)) {
            Log.d(TAG, "resume");
        }
        if (!ListenerUtil.mutListener.listen(60867)) {
            resetStat();
        }
        if (!ListenerUtil.mutListener.listen(60868)) {
            scheduleCpuUtilizationTask();
        }
    }

    public synchronized void reset() {
        if (!ListenerUtil.mutListener.listen(60872)) {
            if (executor != null) {
                if (!ListenerUtil.mutListener.listen(60869)) {
                    Log.d(TAG, "reset");
                }
                if (!ListenerUtil.mutListener.listen(60870)) {
                    resetStat();
                }
                if (!ListenerUtil.mutListener.listen(60871)) {
                    cpuOveruse = false;
                }
            }
        }
    }

    public synchronized int getCpuUsageCurrent() {
        return doubleToPercent((ListenerUtil.mutListener.listen(60876) ? (userCpuUsage.getCurrent() % systemCpuUsage.getCurrent()) : (ListenerUtil.mutListener.listen(60875) ? (userCpuUsage.getCurrent() / systemCpuUsage.getCurrent()) : (ListenerUtil.mutListener.listen(60874) ? (userCpuUsage.getCurrent() * systemCpuUsage.getCurrent()) : (ListenerUtil.mutListener.listen(60873) ? (userCpuUsage.getCurrent() - systemCpuUsage.getCurrent()) : (userCpuUsage.getCurrent() + systemCpuUsage.getCurrent()))))));
    }

    public synchronized int getCpuUsageAverage() {
        return doubleToPercent((ListenerUtil.mutListener.listen(60880) ? (userCpuUsage.getAverage() % systemCpuUsage.getAverage()) : (ListenerUtil.mutListener.listen(60879) ? (userCpuUsage.getAverage() / systemCpuUsage.getAverage()) : (ListenerUtil.mutListener.listen(60878) ? (userCpuUsage.getAverage() * systemCpuUsage.getAverage()) : (ListenerUtil.mutListener.listen(60877) ? (userCpuUsage.getAverage() - systemCpuUsage.getAverage()) : (userCpuUsage.getAverage() + systemCpuUsage.getAverage()))))));
    }

    public synchronized int getFrequencyScaleAverage() {
        return doubleToPercent(frequencyScale.getAverage());
    }

    private void scheduleCpuUtilizationTask() {
        if (!ListenerUtil.mutListener.listen(60883)) {
            if (executor != null) {
                if (!ListenerUtil.mutListener.listen(60881)) {
                    executor.shutdownNow();
                }
                if (!ListenerUtil.mutListener.listen(60882)) {
                    executor = null;
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(60884)) {
            executor = Executors.newSingleThreadScheduledExecutor();
        }
        // Prevent downstream linter warnings.
        @SuppressWarnings("unused")
        Future<?> possiblyIgnoredError = executor.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                if (!ListenerUtil.mutListener.listen(60885)) {
                    cpuUtilizationTask();
                }
            }
        }, 0, CPU_STAT_SAMPLE_PERIOD_MS, TimeUnit.MILLISECONDS);
    }

    private void cpuUtilizationTask() {
        boolean cpuMonitorAvailable = sampleCpuUtilization();
        if (!ListenerUtil.mutListener.listen(60898)) {
            if ((ListenerUtil.mutListener.listen(60895) ? (cpuMonitorAvailable || (ListenerUtil.mutListener.listen(60894) ? ((ListenerUtil.mutListener.listen(60889) ? (SystemClock.elapsedRealtime() % lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60888) ? (SystemClock.elapsedRealtime() / lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60887) ? (SystemClock.elapsedRealtime() * lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60886) ? (SystemClock.elapsedRealtime() + lastStatLogTimeMs) : (SystemClock.elapsedRealtime() - lastStatLogTimeMs))))) <= CPU_STAT_LOG_PERIOD_MS) : (ListenerUtil.mutListener.listen(60893) ? ((ListenerUtil.mutListener.listen(60889) ? (SystemClock.elapsedRealtime() % lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60888) ? (SystemClock.elapsedRealtime() / lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60887) ? (SystemClock.elapsedRealtime() * lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60886) ? (SystemClock.elapsedRealtime() + lastStatLogTimeMs) : (SystemClock.elapsedRealtime() - lastStatLogTimeMs))))) > CPU_STAT_LOG_PERIOD_MS) : (ListenerUtil.mutListener.listen(60892) ? ((ListenerUtil.mutListener.listen(60889) ? (SystemClock.elapsedRealtime() % lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60888) ? (SystemClock.elapsedRealtime() / lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60887) ? (SystemClock.elapsedRealtime() * lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60886) ? (SystemClock.elapsedRealtime() + lastStatLogTimeMs) : (SystemClock.elapsedRealtime() - lastStatLogTimeMs))))) < CPU_STAT_LOG_PERIOD_MS) : (ListenerUtil.mutListener.listen(60891) ? ((ListenerUtil.mutListener.listen(60889) ? (SystemClock.elapsedRealtime() % lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60888) ? (SystemClock.elapsedRealtime() / lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60887) ? (SystemClock.elapsedRealtime() * lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60886) ? (SystemClock.elapsedRealtime() + lastStatLogTimeMs) : (SystemClock.elapsedRealtime() - lastStatLogTimeMs))))) != CPU_STAT_LOG_PERIOD_MS) : (ListenerUtil.mutListener.listen(60890) ? ((ListenerUtil.mutListener.listen(60889) ? (SystemClock.elapsedRealtime() % lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60888) ? (SystemClock.elapsedRealtime() / lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60887) ? (SystemClock.elapsedRealtime() * lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60886) ? (SystemClock.elapsedRealtime() + lastStatLogTimeMs) : (SystemClock.elapsedRealtime() - lastStatLogTimeMs))))) == CPU_STAT_LOG_PERIOD_MS) : ((ListenerUtil.mutListener.listen(60889) ? (SystemClock.elapsedRealtime() % lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60888) ? (SystemClock.elapsedRealtime() / lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60887) ? (SystemClock.elapsedRealtime() * lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60886) ? (SystemClock.elapsedRealtime() + lastStatLogTimeMs) : (SystemClock.elapsedRealtime() - lastStatLogTimeMs))))) >= CPU_STAT_LOG_PERIOD_MS))))))) : (cpuMonitorAvailable && (ListenerUtil.mutListener.listen(60894) ? ((ListenerUtil.mutListener.listen(60889) ? (SystemClock.elapsedRealtime() % lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60888) ? (SystemClock.elapsedRealtime() / lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60887) ? (SystemClock.elapsedRealtime() * lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60886) ? (SystemClock.elapsedRealtime() + lastStatLogTimeMs) : (SystemClock.elapsedRealtime() - lastStatLogTimeMs))))) <= CPU_STAT_LOG_PERIOD_MS) : (ListenerUtil.mutListener.listen(60893) ? ((ListenerUtil.mutListener.listen(60889) ? (SystemClock.elapsedRealtime() % lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60888) ? (SystemClock.elapsedRealtime() / lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60887) ? (SystemClock.elapsedRealtime() * lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60886) ? (SystemClock.elapsedRealtime() + lastStatLogTimeMs) : (SystemClock.elapsedRealtime() - lastStatLogTimeMs))))) > CPU_STAT_LOG_PERIOD_MS) : (ListenerUtil.mutListener.listen(60892) ? ((ListenerUtil.mutListener.listen(60889) ? (SystemClock.elapsedRealtime() % lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60888) ? (SystemClock.elapsedRealtime() / lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60887) ? (SystemClock.elapsedRealtime() * lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60886) ? (SystemClock.elapsedRealtime() + lastStatLogTimeMs) : (SystemClock.elapsedRealtime() - lastStatLogTimeMs))))) < CPU_STAT_LOG_PERIOD_MS) : (ListenerUtil.mutListener.listen(60891) ? ((ListenerUtil.mutListener.listen(60889) ? (SystemClock.elapsedRealtime() % lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60888) ? (SystemClock.elapsedRealtime() / lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60887) ? (SystemClock.elapsedRealtime() * lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60886) ? (SystemClock.elapsedRealtime() + lastStatLogTimeMs) : (SystemClock.elapsedRealtime() - lastStatLogTimeMs))))) != CPU_STAT_LOG_PERIOD_MS) : (ListenerUtil.mutListener.listen(60890) ? ((ListenerUtil.mutListener.listen(60889) ? (SystemClock.elapsedRealtime() % lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60888) ? (SystemClock.elapsedRealtime() / lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60887) ? (SystemClock.elapsedRealtime() * lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60886) ? (SystemClock.elapsedRealtime() + lastStatLogTimeMs) : (SystemClock.elapsedRealtime() - lastStatLogTimeMs))))) == CPU_STAT_LOG_PERIOD_MS) : ((ListenerUtil.mutListener.listen(60889) ? (SystemClock.elapsedRealtime() % lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60888) ? (SystemClock.elapsedRealtime() / lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60887) ? (SystemClock.elapsedRealtime() * lastStatLogTimeMs) : (ListenerUtil.mutListener.listen(60886) ? (SystemClock.elapsedRealtime() + lastStatLogTimeMs) : (SystemClock.elapsedRealtime() - lastStatLogTimeMs))))) >= CPU_STAT_LOG_PERIOD_MS))))))))) {
                if (!ListenerUtil.mutListener.listen(60896)) {
                    lastStatLogTimeMs = SystemClock.elapsedRealtime();
                }
                String statString = getStatString();
                if (!ListenerUtil.mutListener.listen(60897)) {
                    Log.d(TAG, statString);
                }
            }
        }
    }

    private void init() {
        try (FileReader fin = new FileReader("/sys/devices/system/cpu/present")) {
            try (BufferedReader reader = new BufferedReader(fin)) {
                try (Scanner scanner = new Scanner(reader).useDelimiter("[-\n]")) {
                    if (!ListenerUtil.mutListener.listen(60902)) {
                        // Skip leading number 0.
                        scanner.nextInt();
                    }
                    if (!ListenerUtil.mutListener.listen(60907)) {
                        cpusPresent = (ListenerUtil.mutListener.listen(60906) ? (1 % scanner.nextInt()) : (ListenerUtil.mutListener.listen(60905) ? (1 / scanner.nextInt()) : (ListenerUtil.mutListener.listen(60904) ? (1 * scanner.nextInt()) : (ListenerUtil.mutListener.listen(60903) ? (1 - scanner.nextInt()) : (1 + scanner.nextInt())))));
                    }
                }
            } catch (Exception e) {
                if (!ListenerUtil.mutListener.listen(60901)) {
                    Log.e(TAG, "Cannot do CPU stats due to /sys/devices/system/cpu/present parsing problem");
                }
            }
        } catch (FileNotFoundException e) {
            if (!ListenerUtil.mutListener.listen(60899)) {
                Log.e(TAG, "Cannot do CPU stats since /sys/devices/system/cpu/present is missing");
            }
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(60900)) {
                Log.e(TAG, "Error closing file");
            }
        }
        if (!ListenerUtil.mutListener.listen(60908)) {
            cpuFreqMax = new long[cpusPresent];
        }
        if (!ListenerUtil.mutListener.listen(60909)) {
            maxPath = new String[cpusPresent];
        }
        if (!ListenerUtil.mutListener.listen(60910)) {
            curPath = new String[cpusPresent];
        }
        if (!ListenerUtil.mutListener.listen(60911)) {
            curFreqScales = new double[cpusPresent];
        }
        if (!ListenerUtil.mutListener.listen(60921)) {
            {
                long _loopCounter732 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(60920) ? (i >= cpusPresent) : (ListenerUtil.mutListener.listen(60919) ? (i <= cpusPresent) : (ListenerUtil.mutListener.listen(60918) ? (i > cpusPresent) : (ListenerUtil.mutListener.listen(60917) ? (i != cpusPresent) : (ListenerUtil.mutListener.listen(60916) ? (i == cpusPresent) : (i < cpusPresent)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter732", ++_loopCounter732);
                    if (!ListenerUtil.mutListener.listen(60912)) {
                        // Frequency "not yet determined".
                        cpuFreqMax[i] = 0;
                    }
                    if (!ListenerUtil.mutListener.listen(60913)) {
                        curFreqScales[i] = 0;
                    }
                    if (!ListenerUtil.mutListener.listen(60914)) {
                        maxPath[i] = "/sys/devices/system/cpu/cpu" + i + "/cpufreq/cpuinfo_max_freq";
                    }
                    if (!ListenerUtil.mutListener.listen(60915)) {
                        curPath[i] = "/sys/devices/system/cpu/cpu" + i + "/cpufreq/scaling_cur_freq";
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(60922)) {
            lastProcStat = new ProcStat(0, 0, 0);
        }
        if (!ListenerUtil.mutListener.listen(60923)) {
            resetStat();
        }
        if (!ListenerUtil.mutListener.listen(60924)) {
            initialized = true;
        }
    }

    private synchronized void resetStat() {
        if (!ListenerUtil.mutListener.listen(60925)) {
            userCpuUsage.reset();
        }
        if (!ListenerUtil.mutListener.listen(60926)) {
            systemCpuUsage.reset();
        }
        if (!ListenerUtil.mutListener.listen(60927)) {
            totalCpuUsage.reset();
        }
        if (!ListenerUtil.mutListener.listen(60928)) {
            frequencyScale.reset();
        }
        if (!ListenerUtil.mutListener.listen(60929)) {
            lastStatLogTimeMs = SystemClock.elapsedRealtime();
        }
    }

    private int getBatteryLevel() {
        // Use sticky broadcast with null receiver to read battery level once only.
        Intent intent = appContext.registerReceiver(null, /* receiver */
        new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int batteryLevel = 0;
        int batteryScale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 100);
        if (!ListenerUtil.mutListener.listen(60944)) {
            if ((ListenerUtil.mutListener.listen(60934) ? (batteryScale >= 0) : (ListenerUtil.mutListener.listen(60933) ? (batteryScale <= 0) : (ListenerUtil.mutListener.listen(60932) ? (batteryScale < 0) : (ListenerUtil.mutListener.listen(60931) ? (batteryScale != 0) : (ListenerUtil.mutListener.listen(60930) ? (batteryScale == 0) : (batteryScale > 0))))))) {
                if (!ListenerUtil.mutListener.listen(60943)) {
                    batteryLevel = (int) ((ListenerUtil.mutListener.listen(60942) ? ((ListenerUtil.mutListener.listen(60938) ? (100f % intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)) : (ListenerUtil.mutListener.listen(60937) ? (100f / intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)) : (ListenerUtil.mutListener.listen(60936) ? (100f - intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)) : (ListenerUtil.mutListener.listen(60935) ? (100f + intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)) : (100f * intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)))))) % batteryScale) : (ListenerUtil.mutListener.listen(60941) ? ((ListenerUtil.mutListener.listen(60938) ? (100f % intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)) : (ListenerUtil.mutListener.listen(60937) ? (100f / intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)) : (ListenerUtil.mutListener.listen(60936) ? (100f - intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)) : (ListenerUtil.mutListener.listen(60935) ? (100f + intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)) : (100f * intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)))))) * batteryScale) : (ListenerUtil.mutListener.listen(60940) ? ((ListenerUtil.mutListener.listen(60938) ? (100f % intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)) : (ListenerUtil.mutListener.listen(60937) ? (100f / intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)) : (ListenerUtil.mutListener.listen(60936) ? (100f - intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)) : (ListenerUtil.mutListener.listen(60935) ? (100f + intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)) : (100f * intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)))))) - batteryScale) : (ListenerUtil.mutListener.listen(60939) ? ((ListenerUtil.mutListener.listen(60938) ? (100f % intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)) : (ListenerUtil.mutListener.listen(60937) ? (100f / intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)) : (ListenerUtil.mutListener.listen(60936) ? (100f - intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)) : (ListenerUtil.mutListener.listen(60935) ? (100f + intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)) : (100f * intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)))))) + batteryScale) : ((ListenerUtil.mutListener.listen(60938) ? (100f % intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)) : (ListenerUtil.mutListener.listen(60937) ? (100f / intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)) : (ListenerUtil.mutListener.listen(60936) ? (100f - intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)) : (ListenerUtil.mutListener.listen(60935) ? (100f + intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)) : (100f * intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)))))) / batteryScale))))));
                }
            }
        }
        return batteryLevel;
    }

    /**
     *  Re-measure CPU use.  Call this method at an interval of around 1/s.
     *  This method returns true on success.  The fields
     *  cpuCurrent, cpuAvg3, and cpuAvgAll are updated on success, and represents:
     *  cpuCurrent: The CPU use since the last sampleCpuUtilization call.
     *  cpuAvg3: The average CPU over the last 3 calls.
     *  cpuAvgAll: The average CPU over the last SAMPLE_SAVE_NUMBER calls.
     */
    private synchronized boolean sampleCpuUtilization() {
        long lastSeenMaxFreq = 0;
        long cpuFreqCurSum = 0;
        long cpuFreqMaxSum = 0;
        if (!ListenerUtil.mutListener.listen(60946)) {
            if (!initialized) {
                if (!ListenerUtil.mutListener.listen(60945)) {
                    init();
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(60952)) {
            if ((ListenerUtil.mutListener.listen(60951) ? (cpusPresent >= 0) : (ListenerUtil.mutListener.listen(60950) ? (cpusPresent <= 0) : (ListenerUtil.mutListener.listen(60949) ? (cpusPresent > 0) : (ListenerUtil.mutListener.listen(60948) ? (cpusPresent < 0) : (ListenerUtil.mutListener.listen(60947) ? (cpusPresent != 0) : (cpusPresent == 0))))))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(60953)) {
            actualCpusPresent = 0;
        }
        if (!ListenerUtil.mutListener.listen(61009)) {
            {
                long _loopCounter733 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(61008) ? (i >= cpusPresent) : (ListenerUtil.mutListener.listen(61007) ? (i <= cpusPresent) : (ListenerUtil.mutListener.listen(61006) ? (i > cpusPresent) : (ListenerUtil.mutListener.listen(61005) ? (i != cpusPresent) : (ListenerUtil.mutListener.listen(61004) ? (i == cpusPresent) : (i < cpusPresent)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter733", ++_loopCounter733);
                    if (!ListenerUtil.mutListener.listen(60954)) {
                        curFreqScales[i] = 0;
                    }
                    if (!ListenerUtil.mutListener.listen(60971)) {
                        if ((ListenerUtil.mutListener.listen(60959) ? (cpuFreqMax[i] >= 0) : (ListenerUtil.mutListener.listen(60958) ? (cpuFreqMax[i] <= 0) : (ListenerUtil.mutListener.listen(60957) ? (cpuFreqMax[i] > 0) : (ListenerUtil.mutListener.listen(60956) ? (cpuFreqMax[i] < 0) : (ListenerUtil.mutListener.listen(60955) ? (cpuFreqMax[i] != 0) : (cpuFreqMax[i] == 0))))))) {
                            // We have never found this CPU's max frequency.  Attempt to read it.
                            long cpufreqMax = readFreqFromFile(maxPath[i]);
                            if (!ListenerUtil.mutListener.listen(60970)) {
                                if ((ListenerUtil.mutListener.listen(60965) ? (cpufreqMax >= 0) : (ListenerUtil.mutListener.listen(60964) ? (cpufreqMax <= 0) : (ListenerUtil.mutListener.listen(60963) ? (cpufreqMax < 0) : (ListenerUtil.mutListener.listen(60962) ? (cpufreqMax != 0) : (ListenerUtil.mutListener.listen(60961) ? (cpufreqMax == 0) : (cpufreqMax > 0))))))) {
                                    if (!ListenerUtil.mutListener.listen(60966)) {
                                        Log.d(TAG, "Core " + i + ". Max frequency: " + cpufreqMax);
                                    }
                                    if (!ListenerUtil.mutListener.listen(60967)) {
                                        lastSeenMaxFreq = cpufreqMax;
                                    }
                                    if (!ListenerUtil.mutListener.listen(60968)) {
                                        cpuFreqMax[i] = cpufreqMax;
                                    }
                                    if (!ListenerUtil.mutListener.listen(60969)) {
                                        // Kill path to free its memory.
                                        maxPath[i] = null;
                                    }
                                }
                            }
                        } else {
                            if (!ListenerUtil.mutListener.listen(60960)) {
                                // A valid, previously read value.
                                lastSeenMaxFreq = cpuFreqMax[i];
                            }
                        }
                    }
                    long cpuFreqCur = readFreqFromFile(curPath[i]);
                    if (!ListenerUtil.mutListener.listen(60983)) {
                        if ((ListenerUtil.mutListener.listen(60982) ? ((ListenerUtil.mutListener.listen(60976) ? (cpuFreqCur >= 0) : (ListenerUtil.mutListener.listen(60975) ? (cpuFreqCur <= 0) : (ListenerUtil.mutListener.listen(60974) ? (cpuFreqCur > 0) : (ListenerUtil.mutListener.listen(60973) ? (cpuFreqCur < 0) : (ListenerUtil.mutListener.listen(60972) ? (cpuFreqCur != 0) : (cpuFreqCur == 0)))))) || (ListenerUtil.mutListener.listen(60981) ? (lastSeenMaxFreq >= 0) : (ListenerUtil.mutListener.listen(60980) ? (lastSeenMaxFreq <= 0) : (ListenerUtil.mutListener.listen(60979) ? (lastSeenMaxFreq > 0) : (ListenerUtil.mutListener.listen(60978) ? (lastSeenMaxFreq < 0) : (ListenerUtil.mutListener.listen(60977) ? (lastSeenMaxFreq != 0) : (lastSeenMaxFreq == 0))))))) : ((ListenerUtil.mutListener.listen(60976) ? (cpuFreqCur >= 0) : (ListenerUtil.mutListener.listen(60975) ? (cpuFreqCur <= 0) : (ListenerUtil.mutListener.listen(60974) ? (cpuFreqCur > 0) : (ListenerUtil.mutListener.listen(60973) ? (cpuFreqCur < 0) : (ListenerUtil.mutListener.listen(60972) ? (cpuFreqCur != 0) : (cpuFreqCur == 0)))))) && (ListenerUtil.mutListener.listen(60981) ? (lastSeenMaxFreq >= 0) : (ListenerUtil.mutListener.listen(60980) ? (lastSeenMaxFreq <= 0) : (ListenerUtil.mutListener.listen(60979) ? (lastSeenMaxFreq > 0) : (ListenerUtil.mutListener.listen(60978) ? (lastSeenMaxFreq < 0) : (ListenerUtil.mutListener.listen(60977) ? (lastSeenMaxFreq != 0) : (lastSeenMaxFreq == 0))))))))) {
                            // No current frequency information for this CPU core - ignore it.
                            continue;
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(60990)) {
                        if ((ListenerUtil.mutListener.listen(60988) ? (cpuFreqCur >= 0) : (ListenerUtil.mutListener.listen(60987) ? (cpuFreqCur <= 0) : (ListenerUtil.mutListener.listen(60986) ? (cpuFreqCur < 0) : (ListenerUtil.mutListener.listen(60985) ? (cpuFreqCur != 0) : (ListenerUtil.mutListener.listen(60984) ? (cpuFreqCur == 0) : (cpuFreqCur > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(60989)) {
                                actualCpusPresent++;
                            }
                        }
                    }
                    if (!ListenerUtil.mutListener.listen(60991)) {
                        cpuFreqCurSum += cpuFreqCur;
                    }
                    if (!ListenerUtil.mutListener.listen(60992)) {
                        /* Here, lastSeenMaxFreq might come from
       * 1. cpuFreq[i], or
       * 2. a previous iteration, or
       * 3. a newly read value, or
       * 4. hypothetically from the pre-loop dummy.
       */
                        cpuFreqMaxSum += lastSeenMaxFreq;
                    }
                    if (!ListenerUtil.mutListener.listen(61003)) {
                        if ((ListenerUtil.mutListener.listen(60997) ? (lastSeenMaxFreq >= 0) : (ListenerUtil.mutListener.listen(60996) ? (lastSeenMaxFreq <= 0) : (ListenerUtil.mutListener.listen(60995) ? (lastSeenMaxFreq < 0) : (ListenerUtil.mutListener.listen(60994) ? (lastSeenMaxFreq != 0) : (ListenerUtil.mutListener.listen(60993) ? (lastSeenMaxFreq == 0) : (lastSeenMaxFreq > 0))))))) {
                            if (!ListenerUtil.mutListener.listen(61002)) {
                                curFreqScales[i] = (ListenerUtil.mutListener.listen(61001) ? ((double) cpuFreqCur % lastSeenMaxFreq) : (ListenerUtil.mutListener.listen(61000) ? ((double) cpuFreqCur * lastSeenMaxFreq) : (ListenerUtil.mutListener.listen(60999) ? ((double) cpuFreqCur - lastSeenMaxFreq) : (ListenerUtil.mutListener.listen(60998) ? ((double) cpuFreqCur + lastSeenMaxFreq) : ((double) cpuFreqCur / lastSeenMaxFreq)))));
                            }
                        }
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61022)) {
            if ((ListenerUtil.mutListener.listen(61020) ? ((ListenerUtil.mutListener.listen(61014) ? (cpuFreqCurSum >= 0) : (ListenerUtil.mutListener.listen(61013) ? (cpuFreqCurSum <= 0) : (ListenerUtil.mutListener.listen(61012) ? (cpuFreqCurSum > 0) : (ListenerUtil.mutListener.listen(61011) ? (cpuFreqCurSum < 0) : (ListenerUtil.mutListener.listen(61010) ? (cpuFreqCurSum != 0) : (cpuFreqCurSum == 0)))))) && (ListenerUtil.mutListener.listen(61019) ? (cpuFreqMaxSum >= 0) : (ListenerUtil.mutListener.listen(61018) ? (cpuFreqMaxSum <= 0) : (ListenerUtil.mutListener.listen(61017) ? (cpuFreqMaxSum > 0) : (ListenerUtil.mutListener.listen(61016) ? (cpuFreqMaxSum < 0) : (ListenerUtil.mutListener.listen(61015) ? (cpuFreqMaxSum != 0) : (cpuFreqMaxSum == 0))))))) : ((ListenerUtil.mutListener.listen(61014) ? (cpuFreqCurSum >= 0) : (ListenerUtil.mutListener.listen(61013) ? (cpuFreqCurSum <= 0) : (ListenerUtil.mutListener.listen(61012) ? (cpuFreqCurSum > 0) : (ListenerUtil.mutListener.listen(61011) ? (cpuFreqCurSum < 0) : (ListenerUtil.mutListener.listen(61010) ? (cpuFreqCurSum != 0) : (cpuFreqCurSum == 0)))))) || (ListenerUtil.mutListener.listen(61019) ? (cpuFreqMaxSum >= 0) : (ListenerUtil.mutListener.listen(61018) ? (cpuFreqMaxSum <= 0) : (ListenerUtil.mutListener.listen(61017) ? (cpuFreqMaxSum > 0) : (ListenerUtil.mutListener.listen(61016) ? (cpuFreqMaxSum < 0) : (ListenerUtil.mutListener.listen(61015) ? (cpuFreqMaxSum != 0) : (cpuFreqMaxSum == 0))))))))) {
                if (!ListenerUtil.mutListener.listen(61021)) {
                    Log.e(TAG, "Could not read max or current frequency for any CPU");
                }
                return false;
            }
        }
        /*
     * Since the cycle counts are for the period between the last invocation
     * and this present one, we average the percentual CPU frequencies between
     * now and the beginning of the measurement period.  This is significantly
     * incorrect only if the frequencies have peeked or dropped in between the
     * invocations.
     */
        double currentFrequencyScale = (ListenerUtil.mutListener.listen(61026) ? (cpuFreqCurSum % (double) cpuFreqMaxSum) : (ListenerUtil.mutListener.listen(61025) ? (cpuFreqCurSum * (double) cpuFreqMaxSum) : (ListenerUtil.mutListener.listen(61024) ? (cpuFreqCurSum - (double) cpuFreqMaxSum) : (ListenerUtil.mutListener.listen(61023) ? (cpuFreqCurSum + (double) cpuFreqMaxSum) : (cpuFreqCurSum / (double) cpuFreqMaxSum)))));
        if (!ListenerUtil.mutListener.listen(61041)) {
            if ((ListenerUtil.mutListener.listen(61031) ? (frequencyScale.getCurrent() >= 0) : (ListenerUtil.mutListener.listen(61030) ? (frequencyScale.getCurrent() <= 0) : (ListenerUtil.mutListener.listen(61029) ? (frequencyScale.getCurrent() < 0) : (ListenerUtil.mutListener.listen(61028) ? (frequencyScale.getCurrent() != 0) : (ListenerUtil.mutListener.listen(61027) ? (frequencyScale.getCurrent() == 0) : (frequencyScale.getCurrent() > 0))))))) {
                if (!ListenerUtil.mutListener.listen(61040)) {
                    currentFrequencyScale = (ListenerUtil.mutListener.listen(61039) ? (((ListenerUtil.mutListener.listen(61035) ? (frequencyScale.getCurrent() % currentFrequencyScale) : (ListenerUtil.mutListener.listen(61034) ? (frequencyScale.getCurrent() / currentFrequencyScale) : (ListenerUtil.mutListener.listen(61033) ? (frequencyScale.getCurrent() * currentFrequencyScale) : (ListenerUtil.mutListener.listen(61032) ? (frequencyScale.getCurrent() - currentFrequencyScale) : (frequencyScale.getCurrent() + currentFrequencyScale)))))) % 0.5) : (ListenerUtil.mutListener.listen(61038) ? (((ListenerUtil.mutListener.listen(61035) ? (frequencyScale.getCurrent() % currentFrequencyScale) : (ListenerUtil.mutListener.listen(61034) ? (frequencyScale.getCurrent() / currentFrequencyScale) : (ListenerUtil.mutListener.listen(61033) ? (frequencyScale.getCurrent() * currentFrequencyScale) : (ListenerUtil.mutListener.listen(61032) ? (frequencyScale.getCurrent() - currentFrequencyScale) : (frequencyScale.getCurrent() + currentFrequencyScale)))))) / 0.5) : (ListenerUtil.mutListener.listen(61037) ? (((ListenerUtil.mutListener.listen(61035) ? (frequencyScale.getCurrent() % currentFrequencyScale) : (ListenerUtil.mutListener.listen(61034) ? (frequencyScale.getCurrent() / currentFrequencyScale) : (ListenerUtil.mutListener.listen(61033) ? (frequencyScale.getCurrent() * currentFrequencyScale) : (ListenerUtil.mutListener.listen(61032) ? (frequencyScale.getCurrent() - currentFrequencyScale) : (frequencyScale.getCurrent() + currentFrequencyScale)))))) - 0.5) : (ListenerUtil.mutListener.listen(61036) ? (((ListenerUtil.mutListener.listen(61035) ? (frequencyScale.getCurrent() % currentFrequencyScale) : (ListenerUtil.mutListener.listen(61034) ? (frequencyScale.getCurrent() / currentFrequencyScale) : (ListenerUtil.mutListener.listen(61033) ? (frequencyScale.getCurrent() * currentFrequencyScale) : (ListenerUtil.mutListener.listen(61032) ? (frequencyScale.getCurrent() - currentFrequencyScale) : (frequencyScale.getCurrent() + currentFrequencyScale)))))) + 0.5) : (((ListenerUtil.mutListener.listen(61035) ? (frequencyScale.getCurrent() % currentFrequencyScale) : (ListenerUtil.mutListener.listen(61034) ? (frequencyScale.getCurrent() / currentFrequencyScale) : (ListenerUtil.mutListener.listen(61033) ? (frequencyScale.getCurrent() * currentFrequencyScale) : (ListenerUtil.mutListener.listen(61032) ? (frequencyScale.getCurrent() - currentFrequencyScale) : (frequencyScale.getCurrent() + currentFrequencyScale)))))) * 0.5)))));
                }
            }
        }
        ProcStat procStat = readProcStat();
        if (!ListenerUtil.mutListener.listen(61042)) {
            if (procStat == null) {
                return false;
            }
        }
        long diffUserTime = (ListenerUtil.mutListener.listen(61046) ? (procStat.userTime % lastProcStat.userTime) : (ListenerUtil.mutListener.listen(61045) ? (procStat.userTime / lastProcStat.userTime) : (ListenerUtil.mutListener.listen(61044) ? (procStat.userTime * lastProcStat.userTime) : (ListenerUtil.mutListener.listen(61043) ? (procStat.userTime + lastProcStat.userTime) : (procStat.userTime - lastProcStat.userTime)))));
        long diffSystemTime = (ListenerUtil.mutListener.listen(61050) ? (procStat.systemTime % lastProcStat.systemTime) : (ListenerUtil.mutListener.listen(61049) ? (procStat.systemTime / lastProcStat.systemTime) : (ListenerUtil.mutListener.listen(61048) ? (procStat.systemTime * lastProcStat.systemTime) : (ListenerUtil.mutListener.listen(61047) ? (procStat.systemTime + lastProcStat.systemTime) : (procStat.systemTime - lastProcStat.systemTime)))));
        long diffIdleTime = (ListenerUtil.mutListener.listen(61054) ? (procStat.idleTime % lastProcStat.idleTime) : (ListenerUtil.mutListener.listen(61053) ? (procStat.idleTime / lastProcStat.idleTime) : (ListenerUtil.mutListener.listen(61052) ? (procStat.idleTime * lastProcStat.idleTime) : (ListenerUtil.mutListener.listen(61051) ? (procStat.idleTime + lastProcStat.idleTime) : (procStat.idleTime - lastProcStat.idleTime)))));
        long allTime = (ListenerUtil.mutListener.listen(61062) ? ((ListenerUtil.mutListener.listen(61058) ? (diffUserTime % diffSystemTime) : (ListenerUtil.mutListener.listen(61057) ? (diffUserTime / diffSystemTime) : (ListenerUtil.mutListener.listen(61056) ? (diffUserTime * diffSystemTime) : (ListenerUtil.mutListener.listen(61055) ? (diffUserTime - diffSystemTime) : (diffUserTime + diffSystemTime))))) % diffIdleTime) : (ListenerUtil.mutListener.listen(61061) ? ((ListenerUtil.mutListener.listen(61058) ? (diffUserTime % diffSystemTime) : (ListenerUtil.mutListener.listen(61057) ? (diffUserTime / diffSystemTime) : (ListenerUtil.mutListener.listen(61056) ? (diffUserTime * diffSystemTime) : (ListenerUtil.mutListener.listen(61055) ? (diffUserTime - diffSystemTime) : (diffUserTime + diffSystemTime))))) / diffIdleTime) : (ListenerUtil.mutListener.listen(61060) ? ((ListenerUtil.mutListener.listen(61058) ? (diffUserTime % diffSystemTime) : (ListenerUtil.mutListener.listen(61057) ? (diffUserTime / diffSystemTime) : (ListenerUtil.mutListener.listen(61056) ? (diffUserTime * diffSystemTime) : (ListenerUtil.mutListener.listen(61055) ? (diffUserTime - diffSystemTime) : (diffUserTime + diffSystemTime))))) * diffIdleTime) : (ListenerUtil.mutListener.listen(61059) ? ((ListenerUtil.mutListener.listen(61058) ? (diffUserTime % diffSystemTime) : (ListenerUtil.mutListener.listen(61057) ? (diffUserTime / diffSystemTime) : (ListenerUtil.mutListener.listen(61056) ? (diffUserTime * diffSystemTime) : (ListenerUtil.mutListener.listen(61055) ? (diffUserTime - diffSystemTime) : (diffUserTime + diffSystemTime))))) - diffIdleTime) : ((ListenerUtil.mutListener.listen(61058) ? (diffUserTime % diffSystemTime) : (ListenerUtil.mutListener.listen(61057) ? (diffUserTime / diffSystemTime) : (ListenerUtil.mutListener.listen(61056) ? (diffUserTime * diffSystemTime) : (ListenerUtil.mutListener.listen(61055) ? (diffUserTime - diffSystemTime) : (diffUserTime + diffSystemTime))))) + diffIdleTime)))));
        if (!ListenerUtil.mutListener.listen(61074)) {
            if ((ListenerUtil.mutListener.listen(61073) ? ((ListenerUtil.mutListener.listen(61067) ? (currentFrequencyScale >= 0) : (ListenerUtil.mutListener.listen(61066) ? (currentFrequencyScale <= 0) : (ListenerUtil.mutListener.listen(61065) ? (currentFrequencyScale > 0) : (ListenerUtil.mutListener.listen(61064) ? (currentFrequencyScale < 0) : (ListenerUtil.mutListener.listen(61063) ? (currentFrequencyScale != 0) : (currentFrequencyScale == 0)))))) && (ListenerUtil.mutListener.listen(61072) ? (allTime >= 0) : (ListenerUtil.mutListener.listen(61071) ? (allTime <= 0) : (ListenerUtil.mutListener.listen(61070) ? (allTime > 0) : (ListenerUtil.mutListener.listen(61069) ? (allTime < 0) : (ListenerUtil.mutListener.listen(61068) ? (allTime != 0) : (allTime == 0))))))) : ((ListenerUtil.mutListener.listen(61067) ? (currentFrequencyScale >= 0) : (ListenerUtil.mutListener.listen(61066) ? (currentFrequencyScale <= 0) : (ListenerUtil.mutListener.listen(61065) ? (currentFrequencyScale > 0) : (ListenerUtil.mutListener.listen(61064) ? (currentFrequencyScale < 0) : (ListenerUtil.mutListener.listen(61063) ? (currentFrequencyScale != 0) : (currentFrequencyScale == 0)))))) || (ListenerUtil.mutListener.listen(61072) ? (allTime >= 0) : (ListenerUtil.mutListener.listen(61071) ? (allTime <= 0) : (ListenerUtil.mutListener.listen(61070) ? (allTime > 0) : (ListenerUtil.mutListener.listen(61069) ? (allTime < 0) : (ListenerUtil.mutListener.listen(61068) ? (allTime != 0) : (allTime == 0))))))))) {
                return false;
            }
        }
        if (!ListenerUtil.mutListener.listen(61075)) {
            // Update statistics.
            frequencyScale.addValue(currentFrequencyScale);
        }
        double currentUserCpuUsage = (ListenerUtil.mutListener.listen(61079) ? (diffUserTime % (double) allTime) : (ListenerUtil.mutListener.listen(61078) ? (diffUserTime * (double) allTime) : (ListenerUtil.mutListener.listen(61077) ? (diffUserTime - (double) allTime) : (ListenerUtil.mutListener.listen(61076) ? (diffUserTime + (double) allTime) : (diffUserTime / (double) allTime)))));
        if (!ListenerUtil.mutListener.listen(61080)) {
            userCpuUsage.addValue(currentUserCpuUsage);
        }
        double currentSystemCpuUsage = (ListenerUtil.mutListener.listen(61084) ? (diffSystemTime % (double) allTime) : (ListenerUtil.mutListener.listen(61083) ? (diffSystemTime * (double) allTime) : (ListenerUtil.mutListener.listen(61082) ? (diffSystemTime - (double) allTime) : (ListenerUtil.mutListener.listen(61081) ? (diffSystemTime + (double) allTime) : (diffSystemTime / (double) allTime)))));
        if (!ListenerUtil.mutListener.listen(61085)) {
            systemCpuUsage.addValue(currentSystemCpuUsage);
        }
        double currentTotalCpuUsage = (ListenerUtil.mutListener.listen(61093) ? (((ListenerUtil.mutListener.listen(61089) ? (currentUserCpuUsage % currentSystemCpuUsage) : (ListenerUtil.mutListener.listen(61088) ? (currentUserCpuUsage / currentSystemCpuUsage) : (ListenerUtil.mutListener.listen(61087) ? (currentUserCpuUsage * currentSystemCpuUsage) : (ListenerUtil.mutListener.listen(61086) ? (currentUserCpuUsage - currentSystemCpuUsage) : (currentUserCpuUsage + currentSystemCpuUsage)))))) % currentFrequencyScale) : (ListenerUtil.mutListener.listen(61092) ? (((ListenerUtil.mutListener.listen(61089) ? (currentUserCpuUsage % currentSystemCpuUsage) : (ListenerUtil.mutListener.listen(61088) ? (currentUserCpuUsage / currentSystemCpuUsage) : (ListenerUtil.mutListener.listen(61087) ? (currentUserCpuUsage * currentSystemCpuUsage) : (ListenerUtil.mutListener.listen(61086) ? (currentUserCpuUsage - currentSystemCpuUsage) : (currentUserCpuUsage + currentSystemCpuUsage)))))) / currentFrequencyScale) : (ListenerUtil.mutListener.listen(61091) ? (((ListenerUtil.mutListener.listen(61089) ? (currentUserCpuUsage % currentSystemCpuUsage) : (ListenerUtil.mutListener.listen(61088) ? (currentUserCpuUsage / currentSystemCpuUsage) : (ListenerUtil.mutListener.listen(61087) ? (currentUserCpuUsage * currentSystemCpuUsage) : (ListenerUtil.mutListener.listen(61086) ? (currentUserCpuUsage - currentSystemCpuUsage) : (currentUserCpuUsage + currentSystemCpuUsage)))))) - currentFrequencyScale) : (ListenerUtil.mutListener.listen(61090) ? (((ListenerUtil.mutListener.listen(61089) ? (currentUserCpuUsage % currentSystemCpuUsage) : (ListenerUtil.mutListener.listen(61088) ? (currentUserCpuUsage / currentSystemCpuUsage) : (ListenerUtil.mutListener.listen(61087) ? (currentUserCpuUsage * currentSystemCpuUsage) : (ListenerUtil.mutListener.listen(61086) ? (currentUserCpuUsage - currentSystemCpuUsage) : (currentUserCpuUsage + currentSystemCpuUsage)))))) + currentFrequencyScale) : (((ListenerUtil.mutListener.listen(61089) ? (currentUserCpuUsage % currentSystemCpuUsage) : (ListenerUtil.mutListener.listen(61088) ? (currentUserCpuUsage / currentSystemCpuUsage) : (ListenerUtil.mutListener.listen(61087) ? (currentUserCpuUsage * currentSystemCpuUsage) : (ListenerUtil.mutListener.listen(61086) ? (currentUserCpuUsage - currentSystemCpuUsage) : (currentUserCpuUsage + currentSystemCpuUsage)))))) * currentFrequencyScale)))));
        if (!ListenerUtil.mutListener.listen(61094)) {
            totalCpuUsage.addValue(currentTotalCpuUsage);
        }
        if (!ListenerUtil.mutListener.listen(61095)) {
            // Save new measurements for next round's deltas.
            lastProcStat = procStat;
        }
        return true;
    }

    private int doubleToPercent(double d) {
        return (int) ((ListenerUtil.mutListener.listen(61103) ? ((ListenerUtil.mutListener.listen(61099) ? (d % 100) : (ListenerUtil.mutListener.listen(61098) ? (d / 100) : (ListenerUtil.mutListener.listen(61097) ? (d - 100) : (ListenerUtil.mutListener.listen(61096) ? (d + 100) : (d * 100))))) % 0.5) : (ListenerUtil.mutListener.listen(61102) ? ((ListenerUtil.mutListener.listen(61099) ? (d % 100) : (ListenerUtil.mutListener.listen(61098) ? (d / 100) : (ListenerUtil.mutListener.listen(61097) ? (d - 100) : (ListenerUtil.mutListener.listen(61096) ? (d + 100) : (d * 100))))) / 0.5) : (ListenerUtil.mutListener.listen(61101) ? ((ListenerUtil.mutListener.listen(61099) ? (d % 100) : (ListenerUtil.mutListener.listen(61098) ? (d / 100) : (ListenerUtil.mutListener.listen(61097) ? (d - 100) : (ListenerUtil.mutListener.listen(61096) ? (d + 100) : (d * 100))))) * 0.5) : (ListenerUtil.mutListener.listen(61100) ? ((ListenerUtil.mutListener.listen(61099) ? (d % 100) : (ListenerUtil.mutListener.listen(61098) ? (d / 100) : (ListenerUtil.mutListener.listen(61097) ? (d - 100) : (ListenerUtil.mutListener.listen(61096) ? (d + 100) : (d * 100))))) - 0.5) : ((ListenerUtil.mutListener.listen(61099) ? (d % 100) : (ListenerUtil.mutListener.listen(61098) ? (d / 100) : (ListenerUtil.mutListener.listen(61097) ? (d - 100) : (ListenerUtil.mutListener.listen(61096) ? (d + 100) : (d * 100))))) + 0.5))))));
    }

    private synchronized String getStatString() {
        StringBuilder stat = new StringBuilder();
        if (!ListenerUtil.mutListener.listen(61104)) {
            stat.append("CPU User: ").append(doubleToPercent(userCpuUsage.getCurrent())).append("/").append(doubleToPercent(userCpuUsage.getAverage())).append(". System: ").append(doubleToPercent(systemCpuUsage.getCurrent())).append("/").append(doubleToPercent(systemCpuUsage.getAverage())).append(". Freq: ").append(doubleToPercent(frequencyScale.getCurrent())).append("/").append(doubleToPercent(frequencyScale.getAverage())).append(". Total usage: ").append(doubleToPercent(totalCpuUsage.getCurrent())).append("/").append(doubleToPercent(totalCpuUsage.getAverage())).append(". Cores: ").append(actualCpusPresent);
        }
        if (!ListenerUtil.mutListener.listen(61105)) {
            stat.append("( ");
        }
        if (!ListenerUtil.mutListener.listen(61112)) {
            {
                long _loopCounter734 = 0;
                for (int i = 0; (ListenerUtil.mutListener.listen(61111) ? (i >= cpusPresent) : (ListenerUtil.mutListener.listen(61110) ? (i <= cpusPresent) : (ListenerUtil.mutListener.listen(61109) ? (i > cpusPresent) : (ListenerUtil.mutListener.listen(61108) ? (i != cpusPresent) : (ListenerUtil.mutListener.listen(61107) ? (i == cpusPresent) : (i < cpusPresent)))))); i++) {
                    ListenerUtil.loopListener.listen("_loopCounter734", ++_loopCounter734);
                    if (!ListenerUtil.mutListener.listen(61106)) {
                        stat.append(doubleToPercent(curFreqScales[i])).append(" ");
                    }
                }
            }
        }
        if (!ListenerUtil.mutListener.listen(61113)) {
            stat.append("). Battery: ").append(getBatteryLevel());
        }
        if (!ListenerUtil.mutListener.listen(61115)) {
            if (cpuOveruse) {
                if (!ListenerUtil.mutListener.listen(61114)) {
                    stat.append(". Overuse.");
                }
            }
        }
        return stat.toString();
    }

    /**
     *  Read a single integer value from the named file.  Return the read value
     *  or if an error occurs return 0.
     */
    private long readFreqFromFile(String fileName) {
        long number = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            try {
                String line = reader.readLine();
                if (!ListenerUtil.mutListener.listen(61117)) {
                    number = parseLong(line);
                }
            } finally {
                if (!ListenerUtil.mutListener.listen(61116)) {
                    reader.close();
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return number;
    }

    private static long parseLong(String value) {
        long number = 0;
        try {
            if (!ListenerUtil.mutListener.listen(61119)) {
                number = Long.parseLong(value);
            }
        } catch (NumberFormatException e) {
            if (!ListenerUtil.mutListener.listen(61118)) {
                Log.e(TAG, "parseLong error.", e);
            }
        }
        return number;
    }

    /*
	 * Read the current utilization of all CPUs using the cumulative first line
	 * of /proc/stat.
	 */
    private ProcStat readProcStat() {
        long userTime = 0;
        long systemTime = 0;
        long idleTime = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("/proc/stat"));
            try {
                // user    nice  system     idle   iowait  irq   softirq
                String line = reader.readLine();
                String[] lines = line.split("\\s+");
                int length = lines.length;
                if (!ListenerUtil.mutListener.listen(61133)) {
                    if ((ListenerUtil.mutListener.listen(61128) ? (length <= 5) : (ListenerUtil.mutListener.listen(61127) ? (length > 5) : (ListenerUtil.mutListener.listen(61126) ? (length < 5) : (ListenerUtil.mutListener.listen(61125) ? (length != 5) : (ListenerUtil.mutListener.listen(61124) ? (length == 5) : (length >= 5))))))) {
                        if (!ListenerUtil.mutListener.listen(61129)) {
                            // user
                            userTime = parseLong(lines[1]);
                        }
                        if (!ListenerUtil.mutListener.listen(61130)) {
                            // nice
                            userTime += parseLong(lines[2]);
                        }
                        if (!ListenerUtil.mutListener.listen(61131)) {
                            // system
                            systemTime = parseLong(lines[3]);
                        }
                        if (!ListenerUtil.mutListener.listen(61132)) {
                            // idle
                            idleTime = parseLong(lines[4]);
                        }
                    }
                }
                if (!ListenerUtil.mutListener.listen(61142)) {
                    if ((ListenerUtil.mutListener.listen(61138) ? (length <= 8) : (ListenerUtil.mutListener.listen(61137) ? (length > 8) : (ListenerUtil.mutListener.listen(61136) ? (length < 8) : (ListenerUtil.mutListener.listen(61135) ? (length != 8) : (ListenerUtil.mutListener.listen(61134) ? (length == 8) : (length >= 8))))))) {
                        if (!ListenerUtil.mutListener.listen(61139)) {
                            // iowait
                            userTime += parseLong(lines[5]);
                        }
                        if (!ListenerUtil.mutListener.listen(61140)) {
                            // irq
                            systemTime += parseLong(lines[6]);
                        }
                        if (!ListenerUtil.mutListener.listen(61141)) {
                            // softirq
                            systemTime += parseLong(lines[7]);
                        }
                    }
                }
            } catch (Exception e) {
                if (!ListenerUtil.mutListener.listen(61122)) {
                    Log.e(TAG, "Problems parsing /proc/stat", e);
                }
                return null;
            } finally {
                if (!ListenerUtil.mutListener.listen(61123)) {
                    reader.close();
                }
            }
        } catch (FileNotFoundException e) {
            if (!ListenerUtil.mutListener.listen(61120)) {
                Log.e(TAG, "Cannot open /proc/stat for reading", e);
            }
            return null;
        } catch (IOException e) {
            if (!ListenerUtil.mutListener.listen(61121)) {
                Log.e(TAG, "Problems reading /proc/stat", e);
            }
            return null;
        }
        return new ProcStat(userTime, systemTime, idleTime);
    }
}
