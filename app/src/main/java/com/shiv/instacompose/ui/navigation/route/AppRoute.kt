package com.shiv.instacompose.ui.navigation.route
import com.shiv.instacompose.core.NavigationUtils

enum class AppRoute(
    val route: String
) : BaseRoute {
    PROFILE( "app/profile"),
    STORY("app/story/{${AppConstant.INDEX}}");

    override fun withArgs(vararg args: String): String {
        return NavigationUtils.withArgs(args, route)
    }
}
object AppConstant{
    const val INDEX = "Index"
}