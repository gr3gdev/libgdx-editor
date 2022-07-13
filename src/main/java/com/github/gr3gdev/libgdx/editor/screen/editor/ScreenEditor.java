package com.github.gr3gdev.libgdx.editor.screen.editor;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.backends.lwjgl.LwjglCanvas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.gr3gdev.libgdx.editor.screen.editor.content.ScreenEditorContent;
import com.github.gr3gdev.libgdx.editor.screen.editor.content.TextButtonActorContent;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorLocation;
import com.intellij.openapi.fileEditor.FileEditorState;
import com.intellij.openapi.fileEditor.impl.LoadTextUtil;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;

public class ScreenEditor implements FileEditor {

    private final JPanel panel;
    private final Game editorGame;

    public ScreenEditor(VirtualFile file) throws JsonProcessingException {
        panel = new JPanel(new BorderLayout());

        final String skinPath = "/skin/skin.json";
        final ObjectMapper mapper = new ObjectMapper();
        final ScreenEditorContent screenEditorContent = mapper.readValue(LoadTextUtil.loadText(file).toString(),
                ScreenEditorContent.class);

        final JToolBar toolBar = new JToolBar();
        final JButton textButton = new JButton("Add TextButton");
        toolBar.add(textButton);
        panel.add(toolBar, BorderLayout.NORTH);

        editorGame = new Game() {
            private AssetManager assetManager;

            @Override
            public void create() {
                assetManager = new AssetManager();
                assetManager.load(skinPath, Skin.class);
                final ScreenEditorWorker screenEditorWorker = new ScreenEditorWorker(screenEditorContent,
                        assetManager.finishLoadingAsset(skinPath));
                textButton.addActionListener((e) -> screenEditorWorker.add(new TextButtonActorContent()));
                setScreen(new MyScreen(screenEditorWorker, assetManager));
            }

            @Override
            public void dispose() {
                super.dispose();
                assetManager.dispose();
            }
        };

        final LwjglCanvas lwjglCanvas = new LwjglCanvas(editorGame);
        panel.add(lwjglCanvas.getCanvas(), BorderLayout.CENTER);
    }

    @Override
    public @NotNull JComponent getComponent() {
        return panel;
    }

    @Override
    public @Nullable JComponent getPreferredFocusedComponent() {
        return panel;
    }

    @Override
    public @Nls(capitalization = Nls.Capitalization.Title) @NotNull String getName() {
        return "LibGDX Screen Editor";
    }

    @Override
    public void setState(@NotNull FileEditorState state) {

    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public void addPropertyChangeListener(@NotNull PropertyChangeListener listener) {

    }

    @Override
    public void removePropertyChangeListener(@NotNull PropertyChangeListener listener) {

    }

    @Override
    public @Nullable FileEditorLocation getCurrentLocation() {
        return null;
    }

    @Override
    public void dispose() {
        editorGame.dispose();
    }

    @Override
    public <T> @Nullable T getUserData(@NotNull Key<T> key) {
        return null;
    }

    @Override
    public <T> void putUserData(@NotNull Key<T> key, @Nullable T value) {

    }
}
