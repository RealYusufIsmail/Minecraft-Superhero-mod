/*
 * Copyright 2022 RealYusufIsmail.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.realyusufismail.datagen.lang

import io.github.realyusufismail.SuperHeroMod
import io.github.realyusufismail.core.creativetab.CreativeTabs
import io.github.realyusufismail.core.init.ItemInit
import io.github.realyusufismail.realyusufismailcore.data.support.lang.ModEnLangProviderSupport
import net.minecraft.data.DataGenerator
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraftforge.registries.RegistryObject

class ModEnLangProvider(gen: DataGenerator) :
    ModEnLangProviderSupport(gen, SuperHeroMod.MOD_ID, "en_us") {

    override fun addTranslations() {
        item(ItemInit.mjolnir, "Mjolnir")

        add(CreativeTabs.MARVEL.displayName.toString(), "Marvel")
    }

    override fun <T : Item> item(entry: RegistryObject<T>, name: String) {
        add(entry.get(), name)
    }

    override fun <T : Block> block(entry: RegistryObject<T>, name: String) {
        add(entry.get(), name)
    }

    override fun <T : Entity> entity(key: EntityType<*>, name: String) {
        add(key.descriptionId, name)
    }

    override fun add(translatableComponent: Component, lang: String) {
        super.add(translatableComponent.string, lang)
    }
}
