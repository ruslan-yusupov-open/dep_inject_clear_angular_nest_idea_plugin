import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.command.CommandProcessor

class InjectDependencyAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val project = getEventProject(e)
        // Get the selection to generate the injectable name.
        val caret = e.getData(PlatformDataKeys.CARET)!!
        val className = caret.selectedText

        if (className != null) {

            val varName = className.replaceRange(0, 1, (className[0].toLowerCase()).toString())

            val editor = e.getData(PlatformDataKeys.EDITOR)!!
            val document = editor.document

            val text = document.text
            val indexOfConstructor = text.indexOf("constructor(")

            if (indexOfConstructor > -1) {

                CommandProcessor.getInstance().executeCommand(
                    project,
                    {
                        ApplicationManager.getApplication().runWriteAction {
                            // Assign the variable <selection> = _<selection>_;

                            document.insertString(
                                indexOfConstructor + "constructor(".length,
                                "\nprivate readonly $varName:$className,\n"
                            )

                            document.deleteString(caret.selectionStart, caret.selectionEnd)
                            document.insertString(caret.selectionStart, "this.$varName;")
                            caret.moveCaretRelatively("this.$varName".length, 0, false, false)

                            // Add injectable at the end of the parameter list.
                            // document.insertString(paramListOffset, injectParam);
                        }
                    },
                    "Inject Test",
                    null
                )
            }
        }
    }

    //  private static final Logger LOG = Logger.getInstance("#" + injectTestAction.class);
}
