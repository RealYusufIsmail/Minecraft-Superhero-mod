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
import io.github.realyusufismail.datagen.lang.ModEnLangProvider
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
class MainEventBusSubscriber {

    init {
        val modEventBus = FMLJavaModLoadingContext.get().modEventBus

        ITEMS.register(modEventBus)
        BLOCKS.register(modEventBus)

        modEventBus.addListener(this::attachDataProviders)
    }

    @SubscribeEvent
    fun commonSetup(event: FMLCommonSetupEvent) {
        logger.info("Hello from SuperHeroMod!")
    }

    /**
     * An event listener that, when fired, attaches the providers to the data generator to generate
     * the associated files.
     *
     * The 'mod' argument in within 'minecraft.runs.data' in the buildscript must be equal to [ID].
     *
     * @param event the [GatherDataEvent] event
     */
    private fun attachDataProviders(event: GatherDataEvent) {
        val gen = event.generator
        val existingFileHelper = event.existingFileHelper

        if (event.includeClient()) {
            logger.info("Attaching data providers for client...")
            gen.addProvider(true, ModEnLangProvider(gen))
        }
    }

    companion object {
        val ITEMS: DeferredRegister<Item> =
            DeferredRegister.create(ForgeRegistries.ITEMS, SuperHeroMod.MOD_ID)
        val BLOCKS: DeferredRegister<Block> =
            DeferredRegister.create(ForgeRegistries.BLOCKS, SuperHeroMod.MOD_ID)
    }
}
