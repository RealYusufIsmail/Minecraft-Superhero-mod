package io.github.realyusufismail.datagen.lang

import io.github.realyusufismail.SuperHeroMod
import io.github.realyusufismail.core.creativetab.CreativeTabs
import io.github.realyusufismail.core.init.ItemInit
import net.minecraft.data.DataGenerator
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraftforge.common.data.LanguageProvider
import net.minecraftforge.registries.RegistryObject

class ModEnLangProvider(gen: DataGenerator) : LanguageProvider(gen, SuperHeroMod.MOD_ID, "en_us") {
    override fun addTranslations() {
        item(ItemInit.mjolnir, "Mjolnir")

        add(CreativeTabs.MARVEL.name, "Marvel")
    }

    private fun <T : Item> item(entry: RegistryObject<T>, name: String) {
        add(entry.get(), name)
    }

    private fun <T : Block> block(entry: RegistryObject<T>, name: String) {
        add(entry.get(), name)
    }
}