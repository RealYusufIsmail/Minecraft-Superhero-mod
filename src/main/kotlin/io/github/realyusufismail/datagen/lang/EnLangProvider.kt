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
import net.minecraft.data.DataGenerator
import net.minecraftforge.common.data.LanguageProvider

/**
 * A data provider which generates an 'en_us' localization for the mod.
 *
 * @param gen the generator being written to
 */
class EnLangProvider(gen: DataGenerator) : LanguageProvider(gen, SuperHeroMod.MOD_ID, "en_us") {

    override fun addTranslations() {
        this.addItem(ItemInit.mjolnir, "Mjolnir")

        this.add(CreativeTabs.MARVEL.displayName.toString(), "Marvel")
    }
}
