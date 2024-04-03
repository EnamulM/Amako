import java.util.ArrayList;
import java.util.List;

public class StoryMode {
    private List<String> story;
    private int currentChapter;

    public StoryMode() {
        this.story = new ArrayList<>();
        this.currentChapter = 0;
        initializeStory();
    }

    public void initializeStory() {
        story.add("Chapter 1: The Beginning");
        story.add("Chapter 2: Mobs");
        story.add("Chapter 3: Collecting Jewels");
        story.add("Chapter 4: Boss");
    }
    public void nextChapter() {
        currentChapter++;
        if (currentChapter >= story.size()) {
            System.out.println("The End.");
        } else {
            System.out.println("Chapter " + (currentChapter + 1) + ": " + story.get(currentChapter));
        }
    }


}
