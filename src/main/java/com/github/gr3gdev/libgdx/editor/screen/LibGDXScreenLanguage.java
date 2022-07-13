package com.github.gr3gdev.libgdx.editor.screen;

import com.intellij.lang.Language;

public class LibGDXScreenLanguage extends Language {

    public static final LibGDXScreenLanguage INSTANCE = new LibGDXScreenLanguage();

    protected LibGDXScreenLanguage() {
        super("LibGDXScreen", "application/json");
    }
}
