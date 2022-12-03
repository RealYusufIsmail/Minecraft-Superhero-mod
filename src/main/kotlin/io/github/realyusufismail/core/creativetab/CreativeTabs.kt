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
package io.github.realyusufismail.core.creativetab

import io.github.realyusufismail.core.init.ItemInit
import kotlin.reflect.KProperty0
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.SwordItem
import net.minecraftforge.registries.RegistryObject

enum class CreativeTabs(val tab: CreativeModeTab) {
    MARVEL(createTab("marvel", ItemInit::mjolnir))
}

private fun createTab(name: String, iconItem: KProperty0<RegistryObject<SwordItem>>) =
    object : CreativeModeTab(TABS.size, name) {
        override fun makeIcon() = ItemStack(iconItem.get().get())
    }
