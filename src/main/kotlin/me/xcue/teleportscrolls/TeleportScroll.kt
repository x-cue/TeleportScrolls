package me.xcue.teleportscrolls

import com.hypixel.hytale.math.vector.Location
import com.hypixel.hytale.server.core.inventory.ItemStack
import me.xcue.hylib.lib.codecs.LocationCodec

class TeleportScroll(val target: Location) {
    companion object {
        const val ASSET_ID = "Deco_Scroll"
        val locationCodec = LocationCodec.LOCATION.builder

        fun fromItem(stack: ItemStack): TeleportScroll? {
            val loc = stack.getFromMetadataOrNull("Target", locationCodec) ?: return null

            return TeleportScroll(loc)
        }

        fun isTeleportScroll(stack: ItemStack): Boolean {
            return stack.itemId == ASSET_ID && stack.getFromMetadataOrNull("Target", locationCodec) != null
        }
    }

    fun asItem(): ItemStack {
        return ItemStack(ASSET_ID).withMetadata("Target", locationCodec, target)
    }
}