// Generated by view binder compiler. Do not edit!
package com.example.italify.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.italify.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityPracticeBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final LinearLayout buttonGroupsInPractice;

  @NonNull
  public final Button evaluatePractice;

  @NonNull
  public final Button goBackToLesson;

  @NonNull
  public final RelativeLayout main;

  @NonNull
  public final TextView practiceDescription;

  @NonNull
  public final RecyclerView practiceList;

  private ActivityPracticeBinding(@NonNull RelativeLayout rootView,
      @NonNull LinearLayout buttonGroupsInPractice, @NonNull Button evaluatePractice,
      @NonNull Button goBackToLesson, @NonNull RelativeLayout main,
      @NonNull TextView practiceDescription, @NonNull RecyclerView practiceList) {
    this.rootView = rootView;
    this.buttonGroupsInPractice = buttonGroupsInPractice;
    this.evaluatePractice = evaluatePractice;
    this.goBackToLesson = goBackToLesson;
    this.main = main;
    this.practiceDescription = practiceDescription;
    this.practiceList = practiceList;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityPracticeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityPracticeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_practice, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityPracticeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonGroupsInPractice;
      LinearLayout buttonGroupsInPractice = ViewBindings.findChildViewById(rootView, id);
      if (buttonGroupsInPractice == null) {
        break missingId;
      }

      id = R.id.evaluatePractice;
      Button evaluatePractice = ViewBindings.findChildViewById(rootView, id);
      if (evaluatePractice == null) {
        break missingId;
      }

      id = R.id.goBackToLesson;
      Button goBackToLesson = ViewBindings.findChildViewById(rootView, id);
      if (goBackToLesson == null) {
        break missingId;
      }

      RelativeLayout main = (RelativeLayout) rootView;

      id = R.id.practiceDescription;
      TextView practiceDescription = ViewBindings.findChildViewById(rootView, id);
      if (practiceDescription == null) {
        break missingId;
      }

      id = R.id.practiceList;
      RecyclerView practiceList = ViewBindings.findChildViewById(rootView, id);
      if (practiceList == null) {
        break missingId;
      }

      return new ActivityPracticeBinding((RelativeLayout) rootView, buttonGroupsInPractice,
          evaluatePractice, goBackToLesson, main, practiceDescription, practiceList);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}