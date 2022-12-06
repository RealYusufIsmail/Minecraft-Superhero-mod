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
package io.github.realyusufismail.core.init

import io.github.realyusufismail.core.creativetab.CreativeTabs
import io.github.realyusufismail.eventbus.MainEventBusSubscriber.ITEMS
import net.minecraft.world.item.Item.Properties
import net.minecraft.world.item.SwordItem
import net.minecraftforge.registries.RegistryObject

object ItemInit {
    private val properties = Properties().tab(CreativeTabs.MARVEL)

    // Marvel
    val mjolnir: RegistryObject<SwordItem> =
        ITEMS.register("mjolnir") { SwordItem(ToolMaterialInit.MJOLNIR, 3, -2.4f, properties) }
}
