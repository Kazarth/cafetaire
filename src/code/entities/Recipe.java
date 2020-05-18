package code.entities;

/**
 * TODO: description
 * @author Tor Stenfeldt
 * @version 1.0
 */
public class Recipe {
    private String name;
    private String[] instructions;
    private Content[] contents;
    private String category;

    public Recipe(String name) {
        this.name = name;
        this.instructions = new String[0];
        this.contents = new Content[0];
        this.category = "test";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInstructions(String[] instructions) {
        this.instructions = instructions;
    }

    public void setContents(Content[] contents) {
        this.contents = contents;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String[] getInstructions() {
        return instructions;
    }

    public Content[] getContents() {
        return contents;
    }

    public String getCategory() {
        return category;
    }
}
