package com.github.gr3gdev.libgdx.editor.screen.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MyScreen extends ScreenAdapter {

    private final ShapeRenderer shapeRenderer;
    private final Stage stage;
    private final AssetManager assetManager;
    private final ScreenEditorWorker screenEditorWorker;
    private boolean loaded = false;

    public MyScreen(ScreenEditorWorker screenEditorWorker, AssetManager assetManager) {
        this.screenEditorWorker = screenEditorWorker;
        this.assetManager = assetManager;
        this.shapeRenderer = new ShapeRenderer();
        this.shapeRenderer.setAutoShapeType(true);
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0f, 0f, 0f, 1f, true);
        if (!loaded && assetManager.update()) {
            loaded = true;
            screenEditorWorker.initRenderWhenReady(stage);
        } else {
            screenEditorWorker.renderWhenReady(stage, delta);
            stage.act(delta);
            stage.draw();
        }
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        stage.dispose();
    }
}
