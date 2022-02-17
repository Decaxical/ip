package duke.tasks;

/**
 * Parent Class of all duke tasks.
 */
public class Task {
    private String content;
    private boolean markedDone = false;

    /**
     * Constructor for Task.
     *
     * @param content Description of Task.
     */
    public Task(String content) {
        this.content = content;
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.markedDone = true;
    }

    /**
     * Unmarks task as not done.
     */
    public void markAsNotDone() {
        this.markedDone = false;
    }

    /**
     * Formats Task indicating done tasks with an X followed by content of task.
     *
     * @return Formatted string representation of task.
     */
    @Override
    public String toString() {
        String markedDoneIndicator = this.markedDone ? "X" : " ";
        return String.format("[%s] %s", markedDoneIndicator, this.content);
    }

    /**
     * Formats Task to write into save file.
     * Marked done tasks are denoted by 1 and 0 otherwise.
     *
     * @return Data to write into save file.
     */
    public String toSaveData() {
        String markedDoneIndicator = this.markedDone ? "1" : "0";
        return String.format("%s|%s", markedDoneIndicator, this.content);
    }

}
