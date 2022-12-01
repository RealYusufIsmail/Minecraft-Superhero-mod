package io.github.realyusufismail.core.init

import io.github.realyusufismail.SuperHeroMod
import net.minecraft.world.item.Item
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries


class ItemInit {
    companion object {
        val items: DeferredRegister<Item> = DeferredRegister.create(ForgeRegistries.ITEMS, SuperHeroMod.MOD_ID)
    }
    
}