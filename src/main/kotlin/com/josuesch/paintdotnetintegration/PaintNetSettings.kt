package com.josuesch.paintdotnetintegration

import com.intellij.openapi.components.*

@State(
    name = "PaintDotNetIntegrationSettings",
    storages = [Storage("PaintDotNetIntegration.xml")]
)
@Service(Service.Level.APP)
class PaintNetSettings : PersistentStateComponent<PaintNetSettings.State> {
    data class State(var paintNetPath: String = "")

    private var myState = State()

    override fun getState(): State = myState

    override fun loadState(state: State) {
        myState = state
    }

    companion object {
        fun getInstance(): PaintNetSettings = service()
    }
}
