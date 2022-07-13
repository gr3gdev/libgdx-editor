package com.github.gr3gdev.libgdx.editor.screen;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.util.NlsSafe;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class LibGDXScreenFileType extends LanguageFileType {

    public static final String EXTENSION = "screen";
    public static final LibGDXScreenFileType INSTANCE = new LibGDXScreenFileType();


    public LibGDXScreenFileType() {
        super(LibGDXScreenLanguage.INSTANCE);
    }

    @Override
    public @NonNls @NotNull String getName() {
        return "LibGDX Screen File";
    }

    @Override
    public @NlsContexts.Label @NotNull String getDescription() {
        return "A screen for LibGDX Game";
    }

    @Override
    public @NlsSafe @NotNull String getDefaultExtension() {
        return EXTENSION;
    }

    @Override
    public @Nullable Icon getIcon() {
        return LibGDXScreenIcons.FILE;
    }
}
