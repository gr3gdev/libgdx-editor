package com.github.gr3gdev.libgdx.editor.screen.editor.content;

import java.util.LinkedList;

public class ScreenEditorContent {

    private LinkedList<ActorContent> actors = new LinkedList<>();

    public LinkedList<ActorContent> getActors() {
        return actors;
    }

    public void setActors(LinkedList<ActorContent> actors) {
        this.actors = actors;
    }
}
