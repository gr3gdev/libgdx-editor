package com.github.gr3gdev.libgdx.editor.screen.editor;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.github.gr3gdev.libgdx.editor.screen.editor.content.ActorContent;
import com.github.gr3gdev.libgdx.editor.screen.editor.content.ScreenEditorContent;

import java.util.Arrays;

public class ScreenEditorWorker {

    private final ScreenEditorContent screenEditorContent;
    private final Skin skin;
    private boolean modified = false;

    public ScreenEditorWorker(ScreenEditorContent screenEditorContent, Skin skin) {
        this.screenEditorContent = screenEditorContent;
        this.skin = skin;
    }

    public void add(ActorContent actor) {
        screenEditorContent.getActors().add(actor);
        modified = true;
    }

    public void initRenderWhenReady(Stage stage) {
        screenEditorContent.getActors()
                .forEach(actor -> {
                    final Actor gdxActor = actor.convert(skin);
                    if (Arrays.stream(stage.getActors().items)
                            .noneMatch(a -> a.getName().equals(gdxActor.getName()))) {
                        stage.addActor(gdxActor);
                    }
                });
    }

    public void renderWhenReady(Stage stage, float delta) {
        if (modified) {
            initRenderWhenReady(stage);
            modified = false;
        }
        // TODO
    }
}
