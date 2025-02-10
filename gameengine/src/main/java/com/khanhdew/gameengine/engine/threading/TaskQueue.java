package com.khanhdew.gameengine.engine.threading;

import com.khanhdew.gameengine.engine.GameApp;
import com.khanhdew.gameengine.engine.threading.runnable.GameLogicRunnable;
import com.khanhdew.gameengine.engine.threading.runnable.GameRenderRunnable;
import com.khanhdew.gameengine.engine.threading.runnable.IoRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TaskQueue {
    private ExecutorService executor = Executors.newCachedThreadPool();
    private final List<AbstractRunnable> tasks = new ArrayList<>();

    public TaskQueue(GameApp gameApp) {
        addTask(new GameLogicRunnable(gameApp));
        addTask(new GameRenderRunnable(gameApp));
        addTask(new IoRunnable(gameApp));
    }

    public void addTask(AbstractRunnable task) {
        tasks.add(task);
    }

    private void submitTask() {
        if (!executor.isShutdown()) {
            for (AbstractRunnable task : tasks) {
                executor.submit(task);
            }
        }
    }

    private void resetTasks() {
        for (AbstractRunnable task : tasks) {
            task.reset();
        }
    }

    public void start() {
        if (executor.isShutdown() || executor.isTerminated()) {
            executor = Executors.newCachedThreadPool();
        }
        submitTask();

    }

    public void stop() {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
                if (!executor.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("ExecutorService did not terminate");
            }
        } catch (InterruptedException ie) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public void resume() {
        executor = Executors.newCachedThreadPool();
        submitTask();
    }
}
