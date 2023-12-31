import kotlinx.browser.document
import react.create
import react.dom.client.createRoot

fun main() {
    val container = document.createElement("root")
    document.body!!.appendChild(container)

    val crud = Crud.create{}
    createRoot(container).render(crud)
    createRoot(container)
}