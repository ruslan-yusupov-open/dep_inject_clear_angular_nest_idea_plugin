import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.command.CommandProcessor

class ClearUnusedDependenciesAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = getEventProject(e)

        val editor = e.getData(PlatformDataKeys.EDITOR)!!
        val document = editor.document

        val text = document.text

        // find the constructor
        val constructorStr = "constructor\\(.*?\\).*?\\{".toRegex(RegexOption.DOT_MATCHES_ALL).find(text)?.value

        if (constructorStr != null) {
            val services = "private readonly (.*?): *(.*?),".toRegex().findAll(text)
            val unusedServices = services
                .filter { it.groupValues[1] == it.groupValues[2].smallFirstLetter() }
                .filter {
                    (it.groupValues[1].toRegex().findAll(text).count()
                            // don't count commented lines
                            - "[ \t]*//.*${it.groupValues[1]}".toRegex().findAll(text).count()) == 1
                }
                .map { it.value }
                .toList()
                .asReversed()

            if (unusedServices.isNotEmpty()) {
                CommandProcessor.getInstance().executeCommand(project, {
                    ApplicationManager.getApplication().runWriteAction {
                        // Assign the variable <selection> = _<selection>_;

                        unusedServices.forEach {
                            if (text.indexOf(it) > -1)
                                document.deleteString(text.indexOf(it), text.indexOf(it) + it.length)
                        }
                    }
                }, "delete services", null)
            }
        }
    }
}

fun String.smallFirstLetter() = this.replaceRange(0, 1, (this[0].toLowerCase()).toString())