import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import org.w3c.dom.HTMLInputElement
import styled.css
import styled.styledDiv
import styled.styledInput
import react.dom.*
import styled.*

import Polyglot
import kotlinx.html.js.onClickFunction
import react.*

external interface WelcomeProps : RProps {
    var name: String
}

class WelcomeState(val phraseIn: String) : RState {
    var polyglotEN = Polyglot()
    var polyglotES = Polyglot()

    var phrase = phraseIn

    var en = false
}

@JsExport
class Welcome(props: WelcomeProps) : RComponent<WelcomeProps, WelcomeState>(props) {

    init {
        state = WelcomeState("default")

        state.polyglotEN.extend(phrases = js("{greeting : 'Hello World!'}"))
        state.polyglotES.extend(phrases = js("{greeting : 'Hola Mundo!'}"))

        state.phrase = state.polyglotEN.t("greeting")
    }

    override fun RBuilder.render() {
        div {
            p {
                + state.phrase
            }
        }

        button {
            attrs.onClickFunction = { event ->
                setState(
                        WelcomeState(phraseIn = state.polyglotES.t("greeting"))
                )
            }
            + "Spanish"
        }

        button {
            attrs.onClickFunction = { event ->
                setState(
                        WelcomeState(phraseIn = state.polyglotEN.t("greeting"))
                )
            }
            + "English"
        }
    }
}
