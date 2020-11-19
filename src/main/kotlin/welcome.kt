import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLInputElement
import styled.css
import styled.styledDiv
import styled.styledInput
import react.dom.*
import styled.*

import Polyglot
import react.*

external interface WelcomeProps : RProps {
    var name: String
}

class WelcomeState() : RState {
    var polyglotEN = Polyglot()
    var polyglotES = Polyglot()
}

@JsExport
class Welcome(props: WelcomeProps) : RComponent<WelcomeProps, WelcomeState>(props) {

    init {
        state = WelcomeState()

        state.polyglotEN.extend(phrases = js("{greeting : 'Hello World!'}"))
        state.polyglotES.extend(phrases = js("{greeting : 'Hola Mundo!'}"))
    }

    override fun RBuilder.render() {
        div {
            p {
                + state.polyglotEN.t("greeting")
            }
        }

        div {
            p {
                + state.polyglotES.t("greeting")
            }
        }
    }
}
