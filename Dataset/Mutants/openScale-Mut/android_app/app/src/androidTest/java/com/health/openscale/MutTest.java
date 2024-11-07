package com.health.openscale;

import android.Manifest;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.GrantPermissionRule;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

import br.ufmg.labsoft.mutvariants.listeners.IMutantListener;
import br.ufmg.labsoft.mutvariants.listeners.ListenerUtil;

public class MutTest implements IMutantListener {
    @Rule
    public GrantPermissionRule mRuntimePermissionRule = GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE);

    private static int neededMutantId;

    /*
    private static File mutantReachedFile;

    private static FileWriter fw;

    private static BufferedWriter bw;

    private static HashSet<Integer> reachedMutantSet = new HashSet<Integer>();;
    */

    @Before
    public void setMutListener() {
        ListenerUtil.mutListener = this;
    }

    @BeforeClass
    public static void setUpMutConditions() throws IOException {
        neededMutantId = Integer.parseInt(InstrumentationRegistry.getArguments().getString("neededMutantId"));

        /*
        mutantReachedFile = new File(InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext().getExternalFilesDir(null), "mutantReached.txt");
        Log.v("mutag", mutantReachedFile.getAbsolutePath());
        fw = new FileWriter(mutantReachedFile, true);
        bw = new BufferedWriter(fw);
         */
    }

    /*
    @AfterClass
    public static void tearDownMutConditions() throws IOException {
        bw.close();
    }
    */

    @Override
    public boolean listen(int mutantId) {
        Log.v("mutag","Reached mutant id: " + mutantId);

        /*
        if (!reachedMutantSet.contains(mutantId)) {
            try {
                bw.write(Integer.toString(mutantId));
                bw.newLine();

                reachedMutantSet.add(mutantId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        */

        if (mutantId == neededMutantId) {
            Log.v("mutag","Reached needed mutant id: " + mutantId);

            return true;
        }
        else {
            return false;
        }
    }
}
