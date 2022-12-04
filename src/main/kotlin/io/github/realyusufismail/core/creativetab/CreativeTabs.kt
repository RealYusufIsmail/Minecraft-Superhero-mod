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

import io.github.realyusufismail.SuperHeroMod
import io.github.realyusufismail.core.init.ItemInit
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack

class CreativeTabs(index: Int, label: String) : CreativeModeTab(index, label) {
    override fun makeIcon(): ItemStack {
        return ItemStack(ItemInit.mjolnir.get())
    }

    companion object {
        val MARVEL = CreativeTabs(TABS.size, SuperHeroMod.MOD_ID)
    }
}
