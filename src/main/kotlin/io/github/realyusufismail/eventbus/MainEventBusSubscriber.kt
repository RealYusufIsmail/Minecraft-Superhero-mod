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
package io.github.realyusufismail.eventbus

import io.github.realyusufismail.SuperHeroMod
import io.github.realyusufismail.SuperHeroMod.Companion.logger
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.forge.runForDist

class MainEventBusSubscriber {

    init {
        val modEventBus = MOD_BUS

        ITEMS.register(modEventBus)
        BLOCKS.register(modEventBus)

        modEventBus.addListener(this::commonSetup)

        runForDist(
            clientTarget = { modEventBus.addListener(this::clientSetup) },
            serverTarget = { modEventBus.addListener(this::serverSetup) })
    }

    fun commonSetup(event: FMLCommonSetupEvent) {
        logger.info("Hello from SuperHeroMod!")
    }

    fun clientSetup(event: FMLClientSetupEvent) {
        logger.info("Hello from SuperHeroMod!")
    }

    fun serverSetup(event: FMLDedicatedServerSetupEvent) {
        logger.info("Hello from SuperHeroMod!")
    }

    companion object {
        val ITEMS: DeferredRegister<Item> =
            DeferredRegister.create(ForgeRegistries.ITEMS, SuperHeroMod.MOD_ID)
        val BLOCKS: DeferredRegister<Block> =
            DeferredRegister.create(ForgeRegistries.BLOCKS, SuperHeroMod.MOD_ID)
    }
}
