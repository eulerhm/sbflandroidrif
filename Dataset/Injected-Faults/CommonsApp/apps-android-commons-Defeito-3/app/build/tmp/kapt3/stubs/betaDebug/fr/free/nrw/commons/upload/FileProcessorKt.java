package fr.free.nrw.commons.upload;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 2, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"DEFAULT_SUGGESTION_RADIUS_IN_METRES", "", "MAX_SUGGESTION_RADIUS_IN_METRES", "MIN_NEARBY_RESULTS", "RADIUS_STEP_SIZE_IN_METRES", "app-commons-v4.2.1-main_betaDebug"})
public final class FileProcessorKt {
    
    /**
     * Processing of the image filePath that is about to be uploaded via ShareActivity is done here
     */
    private static final int DEFAULT_SUGGESTION_RADIUS_IN_METRES = 100;
    private static final int MAX_SUGGESTION_RADIUS_IN_METRES = 1000;
    private static final int RADIUS_STEP_SIZE_IN_METRES = 100;
    private static final int MIN_NEARBY_RESULTS = 5;
}