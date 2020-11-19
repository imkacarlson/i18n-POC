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
    var polyglot = Polyglot()
}

@JsExport
class Welcome(props: WelcomeProps) : RComponent<WelcomeProps, WelcomeState>(props) {

    init {
        state = WelcomeState()

        state.polyglot.extend(phrases = js("{greeting : 'Hello Keith'}"))
    }

    override fun RBuilder.render() {
        div {
            p {
                + state.polyglot.t("greeting")
            }
        }
    }
}
