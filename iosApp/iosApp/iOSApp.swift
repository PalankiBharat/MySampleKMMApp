import SwiftUI
import shared

@main
struct iOSApp: App {
    
    init(){
        KoinDiModuleKt.doInitKoin()
    }
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
