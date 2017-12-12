package jp.nicovideo.account.androidvib


enum class Note(val value: Double) {
    Whole(4.0),
    Half(2.0),
    Quarter(1.0),
    Eighth(0.5),
    Sixteenth(0.25)
}

class PulseController() {
    private var thread: PulseThread? = null

    fun start(bpm: Int, note: Note, onPulse: () -> Unit) {
        thread?.stop()
        thread = PulseThread(bpm, note, onPulse)
        thread?.start()
    }

    fun stop() {
        thread?.stop()
    }

}
