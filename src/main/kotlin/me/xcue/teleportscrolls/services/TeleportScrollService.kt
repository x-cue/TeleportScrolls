package me.xcue.teleportscrolls.services

import com.hypixel.hytale.server.core.universe.PlayerRef
import me.xcue.hylib.api.events.PlayerRightClickEvent
import me.xcue.hylib.lib.util.teleport.TeleportUtil
import me.xcue.teleportscrolls.TeleportScroll

class TeleportScrollService {
    fun onUse(e: PlayerRightClickEvent) {
        val item = e.item ?: return
        if (!TeleportScroll.isTeleportScroll(item)) return

        val scroll = TeleportScroll.fromItem(item) ?: return

        val playerRef = e.playerRef.store.getComponent(e.playerRef, PlayerRef.getComponentType()) ?: return

        // This is where you would start the warmup...
        // Would need to cancel the warmup if the player 1. dropped item, 2. scrolled away, 3. opened inventory, 4. released click, 5??
        TeleportUtil.teleport(playerRef, scroll.target)
    }
}