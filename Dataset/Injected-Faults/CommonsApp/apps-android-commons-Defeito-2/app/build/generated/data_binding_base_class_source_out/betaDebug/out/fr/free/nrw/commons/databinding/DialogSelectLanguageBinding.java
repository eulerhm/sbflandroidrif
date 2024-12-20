// Generated by view binder compiler. Do not edit!
package fr.free.nrw.commons.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import fr.free.nrw.commons.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DialogSelectLanguageBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView allLanguages;

  @NonNull
  public final ListView languageHistoryList;

  @NonNull
  public final ListView languageList;

  @NonNull
  public final TextView recentSearches;

  @NonNull
  public final EditText searchLanguage;

  @NonNull
  public final View separator;

  private DialogSelectLanguageBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView allLanguages, @NonNull ListView languageHistoryList,
      @NonNull ListView languageList, @NonNull TextView recentSearches,
      @NonNull EditText searchLanguage, @NonNull View separator) {
    this.rootView = rootView;
    this.allLanguages = allLanguages;
    this.languageHistoryList = languageHistoryList;
    this.languageList = languageList;
    this.recentSearches = recentSearches;
    this.searchLanguage = searchLanguage;
    this.separator = separator;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DialogSelectLanguageBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DialogSelectLanguageBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dialog_select_language, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DialogSelectLanguageBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.all_languages;
      TextView allLanguages = ViewBindings.findChildViewById(rootView, id);
      if (allLanguages == null) {
        break missingId;
      }

      id = R.id.language_history_list;
      ListView languageHistoryList = ViewBindings.findChildViewById(rootView, id);
      if (languageHistoryList == null) {
        break missingId;
      }

      id = R.id.language_list;
      ListView languageList = ViewBindings.findChildViewById(rootView, id);
      if (languageList == null) {
        break missingId;
      }

      id = R.id.recent_searches;
      TextView recentSearches = ViewBindings.findChildViewById(rootView, id);
      if (recentSearches == null) {
        break missingId;
      }

      id = R.id.search_language;
      EditText searchLanguage = ViewBindings.findChildViewById(rootView, id);
      if (searchLanguage == null) {
        break missingId;
      }

      id = R.id.separator;
      View separator = ViewBindings.findChildViewById(rootView, id);
      if (separator == null) {
        break missingId;
      }

      return new DialogSelectLanguageBinding((ConstraintLayout) rootView, allLanguages,
          languageHistoryList, languageList, recentSearches, searchLanguage, separator);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
