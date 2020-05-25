package code.entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * TODO: description
 * @author Tor Stenfeldt, Lucas Eliasson
 * @version 1.0
 */
public class Recipe implements Serializable {
    private transient static double serialVersionUID = 61D;
    private String name;
    private String instructions;
    private Content[] contents;
    private ArrayList<Content> contentList;
    private String category;

    public Recipe(String name) {
        this.name = name;
        this.contents = new Content[10];
        this.contentList = new ArrayList<>();
        this.category = "test";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void addContent(Content content) {
        this.contentList.add(content);
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

    public String getCategory() {
        return category;
    }

    public Content[] getContents() {
        return contents;
    }

    public ArrayList<Content> getContentList() {
        return contentList;
    }

    public Content getContent(String name) {
        for (Content c : contentList) {
            if (c.getIngredient().getType().equals(name)) {
                return c;
            }
        }
        return null;
    }

    // change to %5 for structure
    public String getIngredients() {
        StringBuilder out = new StringBuilder();
        for (Content c: contentList) {
            out.append(c.getIngredient().getType());
            out.append(": ");
            out.append(c.getValue());
            out.append(" ");
            out.append(c.getUnit());
            out.append("\n");
        }
        return out.toString();
    }

    public String getInstructions() {
        StringBuilder out = new StringBuilder();
        for (Content c: contentList) {
            out.append(c.getIngredient().getType()).append(", ");
        }
        return instructions;
    }

    @Override
    public String toString(){
        return getName();
    }
}
