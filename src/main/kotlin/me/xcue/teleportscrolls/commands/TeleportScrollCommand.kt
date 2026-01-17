package me.xcue.teleportscrolls.commands

import com.hypixel.hytale.component.Ref
import com.hypixel.hytale.component.Store
import com.hypixel.hytale.math.vector.Location
import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.command.system.CommandContext
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand
import com.hypixel.hytale.server.core.entity.entities.Player
import com.hypixel.hytale.server.core.inventory.transaction.ItemStackTransaction
import com.hypixel.hytale.server.core.universe.PlayerRef
import com.hypixel.hytale.server.core.universe.world.World
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import me.xcue.teleportscrolls.TeleportScroll

class TeleportScrollCommand: AbstractPlayerCommand("teleport-scroll", "Create a teleport scroll at your current location") {
    override fun execute(
        ctx: CommandContext,
        store: Store<EntityStore?>,
        ref: Ref<EntityStore?>,
        pRef: PlayerRef,
        world: World
    ) {
        val stack = TeleportScroll(Location(world.name, pRef.transform.clone()))

        val pComponent = store.getComponent(ref, Player.getComponentType())!!

        val transaction: ItemStackTransaction =
            pComponent.inventory.combinedHotbarFirst.addItemStack(stack.asItem())

        val remainder = transaction.remainder

        if (remainder != null) {
            pRef.sendMessage(Message.raw("Player's inventory was full. Item not received."))
        }
    }
}