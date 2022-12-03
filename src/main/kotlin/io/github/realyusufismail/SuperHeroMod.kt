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
import io.github.realyusufismail.config.SuperHeroConfig
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.ModLoadingContext
import net.minecraftforge.fml.common.Mod
import org.slf4j.Logger
import software.bernie.geckolib3.GeckoLib

@Mod(SuperHeroMod.MOD_ID)
class SuperHeroMod {

    init {
        GeckoLib.initialize()
        MinecraftForge.EVENT_BUS.register(this)
        SuperHeroConfig.registerConfigs(ModLoadingContext.get().activeContainer)
    }

    companion object {
        const val MOD_ID = "superheromod"
        val logger: Logger = LogUtils.getLogger()
    }
}
