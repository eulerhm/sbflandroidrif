package fr.free.nrw.commons.edit;

import java.lang.System;

/**
 * Interface for image transformation operations.
 *
 * This interface defines a contract for image transformation operations, allowing
 * implementations to provide specific functionality for tasks like rotating images.
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&\u00a8\u0006\u0007"}, d2 = {"Lfr/free/nrw/commons/edit/TransformImage;", "", "rotateImage", "Ljava/io/File;", "imageFile", "degree", "", "app-commons-v4.2.1-master_prodDebug"})
public abstract interface TransformImage {
    
    /**
     * Rotates the specified image file by the given degree.
     *
     * @param imageFile The File representing the image to be rotated.
     * @param degree The degree by which to rotate the image.
     * @return The rotated image File, or null if the rotation operation fails.
     */
    @org.jetbrains.annotations.Nullable
    public abstract java.io.File rotateImage(@org.jetbrains.annotations.NotNull
    java.io.File imageFile, int degree);
}