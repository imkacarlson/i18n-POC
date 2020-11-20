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

var polyglotEN = Polyglot()
var polyglotES = Polyglot()

external interface WelcomeProps : RProps {
    var phrase: String
}

class WelcomeState(val phrase: String) : RState

@JsExport
class Welcome(props: WelcomeProps) : RComponent<WelcomeProps, WelcomeState>(props) {

    init {
        state = WelcomeState(props.phrase)

        polyglotEN.extend(phrases = js("{greeting : 'Hello World!'}"))
        polyglotES.extend(phrases = js("{greeting : 'Hola Mundo!'}"))
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
                        WelcomeState(phrase = polyglotES.t("greeting"))
                )
            }
            + "Spanish"
        }

        button {
            attrs.onClickFunction = { event ->
                setState(
                        WelcomeState(phrase = polyglotEN.t("greeting"))
                )
            }
            + "English"
        }
    }
}
