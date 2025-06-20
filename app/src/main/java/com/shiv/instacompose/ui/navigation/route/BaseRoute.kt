package com.shiv.instacompose.ui.navigation.route

interface BaseRoute {
    fun withArgs(vararg args: String): String
}