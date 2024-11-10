package com.google.android.gnd.model.feature;

import com.google.android.gnd.model.AuditInfo;
import com.google.android.gnd.model.Survey;
import com.google.android.gnd.model.job.Job;
import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

final class AutoValue_PolygonFeature extends $AutoValue_PolygonFeature {
  @LazyInit
  @SuppressWarnings("Immutable")
  private transient volatile int hashCode;

  @LazyInit
  @SuppressWarnings("Immutable")
  private transient volatile boolean hashCode$Memoized;

  AutoValue_PolygonFeature(String id$, Survey survey$, Job job$, String customId$, String caption$,
      AuditInfo created$, AuditInfo lastModified$, ImmutableList<Point> vertices$) {
    super(id$, survey$, job$, customId$, caption$, created$, lastModified$, vertices$);
  }

  @Override
  public int hashCode() {
    if (!hashCode$Memoized) {
      synchronized (this) {
        if (!hashCode$Memoized) {
          hashCode = super.hashCode();
          hashCode$Memoized = true;
        }
      }
    }
    return hashCode;
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }
    return that instanceof AutoValue_PolygonFeature && this.hashCode() == that.hashCode() && super.equals(that);
  }
}
