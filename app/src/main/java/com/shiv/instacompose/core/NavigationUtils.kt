package com.shiv.instacompose.core

object NavigationUtils {
    fun withArgs(args: Array<out String>, route: String): String {
        var formattedRoute = route
        args.forEach { arg ->
            formattedRoute = formattedRoute.replaceFirst(Regex("\\{[^}]+\\}"), arg)
        }
        return formattedRoute
    }
}