package me.xcue.teleportscrolls

import com.hypixel.hytale.logger.HytaleLogger
import com.hypixel.hytale.protocol.MouseButtonState
import com.hypixel.hytale.server.core.event.events.player.PlayerMouseButtonEvent
import com.hypixel.hytale.server.core.plugin.JavaPlugin
import com.hypixel.hytale.server.core.plugin.JavaPluginInit
import com.hypixel.hytale.server.core.universe.PlayerRef
import me.xcue.hylib.api.events.PlayerRightClickEvent
import me.xcue.hylib.lib.util.teleport.TeleportUtil
import me.xcue.teleportscrolls.commands.TeleportScrollCommand
import me.xcue.teleportscrolls.services.TeleportScrollService

/* This is the main class: the entry point for your plugin.
 * Use the setup function to register commands or event listeners.
 */

class TeleportScrolls(init: JavaPluginInit) : JavaPlugin(init) {
    companion object {
        val teleportScrollService = TeleportScrollService()
        val LOGGER: HytaleLogger = HytaleLogger.forEnclosingClass()
    }

    init {
        LOGGER.atInfo().log("Hello from ${this.name} version ${this.manifest.version}")
    }

    override fun setup() {
        LOGGER.atInfo().log("Setting up plugin ${this.name}")

        commandRegistry.registerCommand(TeleportScrollCommand())

        eventRegistry.register(PlayerRightClickEvent::class.java, teleportScrollService::onUse)
    }

}

