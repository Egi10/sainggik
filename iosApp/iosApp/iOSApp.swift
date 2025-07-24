import SwiftUI
import SainggikApp

@main
struct iOSApp: App {
    init() {
        KoinInitializer.shared.doInit()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}