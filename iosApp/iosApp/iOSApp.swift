import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init(){
        KoinDiModuleKt.d
    }
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
