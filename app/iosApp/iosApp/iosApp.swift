import UIKit
import ComposeApp

@main
class AppDelegate: UIResponder, UIApplicationDelegate {

    lazy var componentContext: ComponentContext = DefaultComponentContext(
            lifecycle: ApplicationLifecycle(),
            stateKeeper: nil,
            instanceKeeper: nil,
            backHandler: nil
        )
    
    var window: UIWindow?
    
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?
    ) -> Bool {
        window = UIWindow(frame: UIScreen.main.bounds)
        if let window = window {
            window.rootViewController = MainKt.MainViewController(context: componentContext)
            window.makeKeyAndVisible()
        }
        return true
    }
}
