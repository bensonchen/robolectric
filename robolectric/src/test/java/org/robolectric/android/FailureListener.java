package org.robolectric.android;

import org.jetbrains.annotations.NotNull;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

public class FailureListener extends RunListener {
  @NotNull
  public static List<Failure> runTests(Class<?> testClass) throws InitializationError {
    RunNotifier notifier = new RunNotifier();
    FailureListener failureListener = new FailureListener();
    notifier.addListener(failureListener);
    new RobolectricTestRunner(testClass).run(notifier);
    return failureListener.failures;
  }

  public final List<Failure> failures = new ArrayList<>();

  @Override
  public void testFailure(Failure failure) throws Exception {
    failures.add(failure);
  }

  @Override
  public void testAssumptionFailure(Failure failure) {
    failures.add(failure);
  }
}
