package com.github.gr3gdev.libgdx.editor.screen.editor.content;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class TextButtonActorContent extends ActorContent {

    @Override
    public Actor convert(Skin skin) {
        final TextButton textButton = new TextButton(getText(), skin);
        textButton.setName(getName());
        textButton.setWidth(getWidth());
        textButton.setHeight(getHeight());
        textButton.setPosition(getX(), getY());
        return textButton;
    }
}
