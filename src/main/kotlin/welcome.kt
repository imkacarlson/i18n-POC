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
    var name = ""
    var polyglot = Polyglot()
}

@JsExport
class Welcome(props: WelcomeProps) : RComponent<WelcomeProps, WelcomeState>(props) {

    init {
        state = WelcomeState()
        setState {
            name = "Phil"
        }

        val phrases = mapOf("greeting" to "Hello World!")
        state.polyglot.extend(phrases = phrases)

        if (state.polyglot.has("greeting")){
            println("has greeting")
        }else {
            println("does not have greeting")
        }
    }

    override fun RBuilder.render() {
        styledDiv {
            css {
                +WelcomeStyles.textContainer
            }
            +"Hello, ${state.name}"
            +" Your name backwards is ${state.name.reversed()}!"
        }
        styledInput {
            css {
                +WelcomeStyles.textInput
            }

            attrs {
                type = InputType.text
                value = state.name
                onChangeFunction = { event ->
                    setState{
                        state.name = (event.target as HTMLInputElement).value
                    }
                }
            }
        }
        div {
            p {
                + state.polyglot.t("greeting")
            }
        }
    }
}
