// Generated by view binder compiler. Do not edit!
package rachman.forniandi.listmakerapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import rachman.forniandi.listmakerapp.R;

public final class ActivityDetailBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final FloatingActionButton btnAddTask;

  @NonNull
  public final RecyclerView taskListRecyclerview;

  private ActivityDetailBinding(@NonNull ConstraintLayout rootView,
      @NonNull FloatingActionButton btnAddTask, @NonNull RecyclerView taskListRecyclerview) {
    this.rootView = rootView;
    this.btnAddTask = btnAddTask;
    this.taskListRecyclerview = taskListRecyclerview;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDetailBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_detail, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDetailBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_add_task;
      FloatingActionButton btnAddTask = ViewBindings.findChildViewById(rootView, id);
      if (btnAddTask == null) {
        break missingId;
      }

      id = R.id.task_list_recyclerview;
      RecyclerView taskListRecyclerview = ViewBindings.findChildViewById(rootView, id);
      if (taskListRecyclerview == null) {
        break missingId;
      }

      return new ActivityDetailBinding((ConstraintLayout) rootView, btnAddTask,
          taskListRecyclerview);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
