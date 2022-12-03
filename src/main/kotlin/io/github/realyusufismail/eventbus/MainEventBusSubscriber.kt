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
import io.github.realyusufismail.datagen.DataGenerators
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.data.event.GatherDataEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries

@Mod.EventBusSubscriber(
    modid = SuperHeroMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = [Dist.CLIENT])
object MainEventBusSubscriber {
    val ITEMS: DeferredRegister<Item> =
        DeferredRegister.create(ForgeRegistries.ITEMS, SuperHeroMod.MOD_ID)
    val BLOCKS: DeferredRegister<Block> =
        DeferredRegister.create(ForgeRegistries.BLOCKS, SuperHeroMod.MOD_ID)

    init {
        val modEventBus = FMLJavaModLoadingContext.get().modEventBus

        ITEMS.register(modEventBus)
        BLOCKS.register(modEventBus)
    }

    @SubscribeEvent
    @JvmStatic
    fun commonSetup(event: FMLCommonSetupEvent) {
        logger.info("Hello from SuperHeroMod!")
    }

    @SubscribeEvent
    @JvmStatic
    fun gatherData(event: GatherDataEvent) {
        DataGenerators.generateData(event)
    }
}
