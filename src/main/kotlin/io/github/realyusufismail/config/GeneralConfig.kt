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
package io.github.realyusufismail.config

import net.minecraftforge.common.ForgeConfigSpec
import net.minecraftforge.fml.config.ModConfig

class GeneralConfig : IConfig {
    override val fileName: String = "general"
    override val configSpec: ForgeConfigSpec
    override val configType: ModConfig.Type = ModConfig.Type.SERVER

    init {
        ForgeConfigSpec.Builder()
            .apply {
                push("general")
                pop()
            }
            .build()
            .also { configSpec = it }
    }
}
