package com.github.gr3gdev.libgdx.editor.screen.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.gr3gdev.libgdx.editor.screen.LibGDXScreenFileType;
import com.github.gr3gdev.libgdx.editor.screen.bundle.PluginBundle;
import com.github.gr3gdev.libgdx.editor.screen.editor.content.ScreenEditorContent;
import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.util.io.jackson.IntelliJPrettyPrinter;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class LibGDXScreenFileAction extends CreateFileFromTemplateAction {

    public LibGDXScreenFileAction() {
        super(PluginBundle.message("com.github.gr3gdev.libgdx.editor.screen.action.LibGDXScreenFileAction.text"),
                PluginBundle.message("com.github.gr3gdev.libgdx.editor.screen.action.LibGDXScreenFileAction.description"),
                LibGDXScreenFileType.INSTANCE.getIcon());
    }

    @Override
    protected void buildDialog(@NotNull Project project, @NotNull PsiDirectory directory, CreateFileFromTemplateDialog.@NotNull Builder builder) {
        builder.setTitle(PluginBundle.message("com.github.gr3gdev.libgdx.editor.screen.action.LibGDXScreenFileAction.text"))
                .addKind(PluginBundle.message("com.github.gr3gdev.libgdx.editor.screen.action.LibGDXScreenFileAction.kind"),
                        LibGDXScreenFileType.INSTANCE.getIcon(), "LibGDXScreenFileTemplate");
    }

    @Override
    protected @NlsContexts.Command String getActionName(PsiDirectory directory, @NonNls @NotNull String newName, @NonNls String templateName) {
        return PluginBundle.message("com.github.gr3gdev.libgdx.editor.screen.action.LibGDXScreenFileAction.text");
    }

    @Override
    protected PsiFile createFile(String name, String templateName, PsiDirectory dir) {
        final ObjectMapper mapper = new ObjectMapper().setDefaultPrettyPrinter(new IntelliJPrettyPrinter());
        try {
            @NotNull PsiFile file = dir.createFile(name + "." + LibGDXScreenFileType.EXTENSION);
            file.getVirtualFile().setBinaryContent(mapper.writeValueAsBytes(new ScreenEditorContent()));
            FileEditorManager.getInstance(dir.getProject()).openFile(file.getVirtualFile(), true);
            return file;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
