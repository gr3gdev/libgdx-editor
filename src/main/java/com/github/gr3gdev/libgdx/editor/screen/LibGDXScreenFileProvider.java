package com.github.gr3gdev.libgdx.editor.screen;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.gr3gdev.libgdx.editor.screen.editor.ScreenEditor;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorPolicy;
import com.intellij.openapi.fileEditor.FileEditorProvider;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class LibGDXScreenFileProvider implements FileEditorProvider {

    @Override
    public boolean accept(@NotNull Project project, @NotNull VirtualFile file) {
        return Objects.equals(file.getExtension(), LibGDXScreenFileType.EXTENSION);
    }

    @Override
    public @NotNull FileEditor createEditor(@NotNull Project project, @NotNull VirtualFile file) {
        try {
            return new ScreenEditor(file);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @NotNull @NonNls String getEditorTypeId() {
        return "LibGDXScreenEditor";
    }

    @Override
    public @NotNull FileEditorPolicy getPolicy() {
        return FileEditorPolicy.NONE;
    }
}
