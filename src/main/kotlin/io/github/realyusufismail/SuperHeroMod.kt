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
package io.github.realyusufismail

import com.mojang.logging.LogUtils
import io.github.realyusufismail.eventbus.MainEventBusSubscriber
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext

@Mod(SuperHeroMod.MOD_ID)
class SuperHeroMod {
    private val logger = LogUtils.getLogger()

    init {
        val modEventBus = FMLJavaModLoadingContext.get().modEventBus

        // Register the commonSetup method for modloading
        modEventBus.addListener { event: FMLCommonSetupEvent? ->
            MainEventBusSubscriber(event!!).reg()
        }
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this)
    }

    companion object {
        const val MOD_ID = "superheromod"
    }
}
